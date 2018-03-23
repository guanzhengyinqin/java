package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Random;

public class ClientThread extends Thread {
	
	private Socket socketClient;	//客户端socket
	private String dir;				//绝对路径
	private String pdir = "/";		//相对路径
	private final static Random generator = new Random();//随机数
	
	private ReadData readData;
	
	public ClientThread(Socket client,String F_DIR,ReadData readData){
		this.readData = readData;
		this.socketClient = client;
		this.dir = F_DIR;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		InputStream is = null;
		OutputStream os = null;
		try{
			is = socketClient.getInputStream();
			os = socketClient.getOutputStream();
		}catch(IOException e){
			e.printStackTrace();
			
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		PrintWriter pw = new PrintWriter(os);
		String clientIp = socketClient.getInetAddress().toString().substring(1);	//记录客户端ip
		
		String username = "not logged in";	//用户名
		String password = "";	//口令
		String command = "";	//命令
		boolean loginStuts = false;	//登入状态
		final String LOGIN_WARNING = "530 Please log in with USER and PASS first.";
		String str = "";	//命令内容字符串
		int port_high = 0;
		int port_low = 0;
		String retr_ip = "";//接收文件的IP地址
		
		Socket tempsocket = null;
		
		//打印欢迎信息
		pw.println("220 FTP Server A version 1.0 written by Leon Guo");
		pw.flush();
		boolean b = true;
		while(b){
			try{
				//获取用户输入的命令
				command = br.readLine();
				readData.readData(command);
				if(null==command) break;
			}catch(IOException e){
				pw.println("331 Failed to get command");
				pw.flush();
				b = false;
			}
			
			/*
			 * 访问控制命令
			 */
			//USER命令
			if(command.toUpperCase().startsWith("USER")){
				username = command.substring(4).trim();
				if("".equals(username)){
					pw.println("501 Syntax error");
					pw.flush();
					username = "not logged in";
				}else{
					pw.println("331 Password required for "+username);
					pw.flush();
				}
				loginStuts = false;
			}//end USER
			
			//PASS命令
			else if(command.toUpperCase().startsWith("PASS")){
				password = command.substring(4).trim();
				if(username.equals("User")&&password.equals("123456")){
					pw.println("230 Logged on");
					pw.flush();
					loginStuts = true;
				}else{
					pw.println("530 Login or password incorrect!");
					pw.flush();
					username = "not logged in";
				}
			}//end PASS
			
			//PWD命令
			else if(command.toUpperCase().startsWith("PWD")){
				if(loginStuts){
					pw.println("257 "+pdir);
					pw.flush();
				}else{
					pw.println(LOGIN_WARNING);
					pw.flush();
				}
			}//end PWD
			
			//CWD命令
			else if(command.toUpperCase().startsWith("CWD")){
				if(loginStuts){
					str = command.substring(3).trim();
					if("".equals(str)){
						pw.println("250 Broken client detected, missing argument to CWD. /"+pdir+"/ is current directory.");
						pw.flush();
					}else{
						//判断目录是否存在
						String tmpDir = dir + "/" +str;
						File file = new File(tmpDir);
						if(file.exists()){	//目录存在
							dir = dir + "/" + str;
							if("/".equals(pdir)){
								pdir = pdir + str;
							}else{
								pdir = pdir + "/" + str;
							}
							pw.println("250 CWD successful./"+pdir+"/is current directory");
							pw.flush();
						}else{	//目录不存在
							pw.println("550 CWD failed./"+pdir+"/:directory not found.");
							pw.flush();
						}
					}
				}else{
					pw.println(LOGIN_WARNING);
					pw.flush();
				}
			}//end CWD
			
			//QUIT命令
			else if(command.toUpperCase().startsWith("QUIT")){
				b = false;
				pw.println("221 Goodbye");
				pw.flush();
				try{
					Thread.currentThread();
					Thread.sleep(1000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}//end QUIT
			
			/*
			 * 传输参数命令
			 */
			//PORT命令，主动模式传输数据
			else if(command.toUpperCase().startsWith("PORT")){
				if(loginStuts){
					try{
						str = command.substring(4).trim();
						port_low = Integer.parseInt(str.substring(str.lastIndexOf(",")+1));
						port_high = Integer.parseInt(str.substring(0,str.lastIndexOf(","))
								.substring(str.substring(0, str.lastIndexOf(",")).lastIndexOf(",")+1));
						String str1 = str.substring(0,str.substring(0,str.lastIndexOf(",")).lastIndexOf(","));
						retr_ip = str1.replace(",", ".");
						try{
							//实例化主动模式下的socket
							tempsocket = new Socket(retr_ip, port_high*256 +port_low);
							pw.println("200 port command successful");
							pw.flush();
						}catch(ConnectException ce){
							pw.println("425 Can't open data connection.");
							pw.flush();
							ce.printStackTrace();
						}catch(UnknownHostException e){
							e.printStackTrace();
						}catch(IOException e){
							e.printStackTrace();
						}
					}catch(NumberFormatException e){
						e.printStackTrace();
						pw.println("503 Bad sequence of commands.");
						pw.flush();
					}
				}else{
					pw.println(LOGIN_WARNING);
					pw.flush();
				}
				
			}//end PORT
			
			//PASV命令，被动模式传输数据
			else if(command.toUpperCase().startsWith("PASV")){
				if(loginStuts){
					ServerSocket ss = null;
					
					InetAddress i = null;
					try{
						i = InetAddress.getLocalHost();
					}catch(UnknownHostException e1){
						e1.printStackTrace();
					}
					
					while(true){
						//获取服务器空闲端口
						port_high = 1 + generator.nextInt(20);
						port_low = 100 + generator.nextInt(1000);
						
						try{
							int port = port_high *256 + port_low;
							System.out.println("port-->"+port);
							//服务器绑定端口
							ss = new ServerSocket(port,10,i);
							break;
						}catch(IOException e){
							continue;
						}
					}
					
					System.out.println(i.getHostAddress());
					String response = "227 Entering Passive Mode("+i.getHostAddress().replaceAll("\\.", ",")+","+port_high+","+port_low+")";
					pw.println(response);
					System.out.println("进入被动模式--->"+response);
					pw.flush();
					try{
						tempsocket = ss.accept();
						ss.close();
					}catch(IOException e){
						
					}
				}else{
					pw.println(LOGIN_WARNING);
					pw.flush();
				}
			}//end PASV
		
			//RETR命令
			else if(command.toUpperCase().startsWith("RETR")){
				if(loginStuts){
					str = command.substring(4).trim();
					if("".equals(str)){
						pw.println("501 Syntax error");
						pw.flush();
					}else{
						try{
							pw.println("150 Opening data channel for file transfer.");
							pw.flush();
							RandomAccessFile outfile = null;
							OutputStream outsocket = null;
							try{
								//创建从中读取和向其中写入（可选）的随机访问文件流，该文件具有指定名称
								outfile = new RandomAccessFile(dir+"/"+str, "r");
								outsocket = tempsocket.getOutputStream();
							}catch(FileNotFoundException e){
								e.printStackTrace();
							}catch(IOException e){
								e.printStackTrace();
							}
							byte butebuffer[] = new byte[1024];
							int length;
							try{
								while((length = outfile.read(butebuffer)) != -1){
									outsocket.write(butebuffer, 0, length);
								}
								outsocket.close();
								outfile.close();
								tempsocket.close();
							}catch(IOException e){
								e.printStackTrace();
							}
							pw.println("226 Transfer OK");
							pw.flush();
						}catch(Exception e){
							pw.println("530 Bad sequence of commands.");
							pw.flush();
						}
						
					}
				}else{
					pw.println(LOGIN_WARNING);
					pw.flush();
				}
			} //end ERTR
			
			//STOR命令
			else if(command.toUpperCase().startsWith("STOR")){
				if(loginStuts){
					str = command.substring(4).trim();
					if("".equals(str)){
						pw.println("501 Syntax error");
						pw.flush();
					}else{
						try{
							pw.println("150 Opening data channel for file transfer.");
							pw.flush();
							RandomAccessFile infile = null;
							InputStream insocket = null;
							try{
								infile = new RandomAccessFile(dir+"/"+str, "rw");
								insocket = tempsocket.getInputStream();
							}catch(FileNotFoundException e){
								e.printStackTrace();
							}catch(IOException e){
								e.printStackTrace();
							}
							byte[] bytebuffer = new byte[1024];
							int length;
							try{
								while((length = insocket.read(bytebuffer)) != -1){
									infile.write(bytebuffer, 0, length);
								}
								insocket.close();
								infile.close();
								tempsocket.close();
							}catch(IOException e){
								e.printStackTrace();
							}
							pw.println("226 Transfer OK");
							pw.flush();
						}catch(Exception e){
							pw.println("503 Bad sequence of commands");
							pw.flush();
							e.printStackTrace();
						}
					}
				}else{
					pw.println(LOGIN_WARNING);
					pw.flush();
				}
			} //end STOR
			
			//NLST命令
			else if(command.toUpperCase().startsWith("NLST")){
				if(loginStuts){
					try{
						pw.println("150 Opening data channel for directory list.");
						pw.flush();
						PrintWriter pwr = null;
						try{
							pwr = new PrintWriter(tempsocket.getOutputStream(),true);
						}catch(IOException e){
							e.printStackTrace();
						}
						File file = new File(dir);
						String[] dirstructure = new String[10];
						dirstructure = file.list();
						for (int i = 0; i < dirstructure.length; i++) {
							pwr.println(dirstructure[i]);
						}
						try{
							tempsocket.close();
							pwr.close();
						}catch(IOException e){
							e.printStackTrace();
						}
						pw.println("226 Transfer OK");
						pw.flush();
					}catch(Exception e){
						pw.println("503 Bad sequence of commands.");
						pw.flush();
					}
				}else{
					pw.println(LOGIN_WARNING);
					pw.flush();
				}
			} //end NLST
			
			//LIST命令
			else if(command.toUpperCase().startsWith("LIST")){
				if(loginStuts){
					try{
						pw.println("150 Opening data channel for directory list.");
						pw.flush();
						PrintWriter pwr = null;
						try{
							pwr = new PrintWriter(tempsocket.getOutputStream(),true);
						}catch(IOException e){
							e.printStackTrace();
						}
						FtpUtil.getDetailList(pwr, dir);
						try{
							tempsocket.close();
							pwr.close();
						}catch(IOException e){
							e.printStackTrace();
						}
						pw.println("226 Transfer OK");
						pw.flush();
					}catch(Exception e){
						pw.println("503 Bad sequence of commands.");
						pw.flush();
						e.printStackTrace();
					}
				}else{
					pw.println(LOGIN_WARNING);
					pw.flush();
				}
			} //end LIST
			
			//SYST命令
			else if(command.toUpperCase().startsWith("SYST")){
				if(loginStuts){
					pw.println("200 ftp server version 1.0");
					pw.flush();
				}else{
					pw.println(LOGIN_WARNING);
					pw.flush();
				}
			}	//end SYST
			
			else if(command.toUpperCase().startsWith("TYPE")){
				if(loginStuts){
					pw.println("200 ftpServer ready");
					pw.flush();
				}else{
					pw.println(LOGIN_WARNING);
					pw.flush();
				}
			}else if(command.toUpperCase().startsWith("SIZE")){
				if(loginStuts){
					String path = command.substring(4).trim();
					File file = new File(dir+path);
					long size = file.length();
					pw.println("200 "+size);
					pw.flush();
					
				}else{
					pw.println(LOGIN_WARNING);
					pw.flush();
				}
			}
			
			//输入非法命令
			else{
				pw.println("500 Syntax error,command unrecognized.");
				pw.flush();
			}
			
		} //end while
		
		try{
			br.close();
			socketClient.close();
			pw.close();
			if(null!=tempsocket){
				tempsocket.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

}
