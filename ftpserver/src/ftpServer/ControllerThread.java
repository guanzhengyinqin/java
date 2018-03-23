package ftpServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

/**
 * 
 * @author user
 * 用于处理控制链接数据请求的线程
 * 控制链接：在创建之后，知道socket.close()(四次回收的过程)，
 * 都是tcp里面的establish的阶段。可以自由的传输数据（全双工的）
 *
 */
public class ControllerThread extends Thread {

	private int count = 0;
	
	//客户端socket与服务器socket组成一个tcp链接
	private Socket socket;
	
	//当前的线程所对应的用户
	public static final ThreadLocal<String> USER = new ThreadLocal<>();
	
	//数据链接的ip
	private String dataIp;
	
	//数据链接的port
	private String dataPort;
	
	//用于标记用户是否已经登入
	private boolean isLogin = false;
	
	//当前目录
	private String nowDir = Share.rootDir;

	public Socket getSocket() {
		return socket;
	}

	public String getDataIp() {
		return dataIp;
	}

	public void setDataIp(String dataIp) {
		this.dataIp = dataIp;
	}

	public String getDataPort() {
		return dataPort;
	}

	public void setDataPort(String dataPort) {
		this.dataPort = dataPort;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	
	
	public String getNowDir() {
		return nowDir;
	}

	public void setNowDir(String nowDir) {
		this.nowDir = nowDir;
	}

	public ControllerThread(Socket socket){
		this.socket = socket;
	}
	
	@Override
	public void run() {
		System.out.println("hello");
		BufferedReader reader;
		try{
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Writer writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			while(true){
				//第一次访问，输入流里面是没有东西的，所以会阻塞住
				if(count ==0){
					writer.write("220 JAVA FTP Service");
					writer.write("\r\n");
					writer.flush();
					count++;
				}else{
					//两种情况会关闭链接：(1)quit命令(2)密码错误
					if(!socket.isClosed()){
						//进行命令的选择，然后进行处理，当客户端没有发送数据的时候，将会阻塞
						String command = reader.readLine();
						if(command != null){
							String[] datas = command.split(" ");
							Command commandSolver = CommandFactory.createCommand(datas[0]);
							//登入验证，在没有登入的情况下，无法使用除了user，pass之外的命令
							if(loginValiate(commandSolver)){
								if(commandSolver == null){
									writer.write("502  该命令不存在，请重新输入");
								}else{
									String data = "";
									if(datas.length >= 2){
										data = datas[1];
									}
									commandSolver.getResult(data, writer, this);
								}
							}else{
								writer.write("532  执行该命令需要登入，请登入后再执行相应的操作\r\n");
								writer.flush();
							}
						}else{
							break;
						}
					}else{
						//连接已经关闭，这个线程不再有存在的必要
						break;
					}
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			System.out.println("结束tcp连接");
		}
	}
	
	public boolean loginValiate(Command command){
		if(command instanceof UserCommand || command instanceof PassCommand){
			return true;
		}else{
			return isLogin;
		}
	}
	
	
}
