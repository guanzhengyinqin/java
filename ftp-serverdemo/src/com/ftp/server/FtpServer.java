package com.ftp.server;

/**
 * 难点：
 * 1、类Shell命令行的实现
 * 2、判断另一端写入结束
 * 
 * 待解决的问题：
 * 1、socket.shutdownOutput(),socket.shutdownInput()问题
 * 	putOnServer()与putOnClient(),getOnServer()与getOnClient(),在不关闭流的情况下，如何使另一方读到-1
 * 2、OOBINLINE,socket.setOOBInline(),socket.getOOBInline()
 * 3、能不能直接通过PipedInputStream,PipedOutputStream进行读写
 * 
 * 待完善部分：
 * 1、命令行参数文件名或路径名中含有空白符的情况
 * 2、How to detect in the server whether a remote client has been closed?
 * 3、"get"或"put"时，若存在同名文件，询问是否放弃或重命名
 * 4、执行一个命令就建立一个socket
 */
import java.net.*;
import java.io.*;

public class FtpServer
{
	public static void main(String[] args) throws IOException
	{
		ServerSocket ss = new ServerSocket(21);
		DataInputStream dis;
		Socket socket;
		while(true)
		{
			socket = ss.accept();
			dis = new DataInputStream(socket.getInputStream());
			System.out.println(dis.readUTF() + " has connected.");
			Thread thread = new Thread(new HandleClient(socket));	// create a new thread to handle the request from the client
			thread.start();
		}
	}
}
class HandleClient implements Runnable
{
	Socket socket = null;
	String WD = ".";	// set the initial working directory
	HandleClient(Socket socket)
	{
		this.socket = socket;
	}
	public void run()
	{
		this.WD = new File("").getAbsolutePath();	// convert "." to absolute path
		int cmdNum = -1;	// command number is the index of <String[] cmdList> in <class FTPClient>
		DataInputStream dis;
		try
		{
		dis = new DataInputStream(this.socket.getInputStream());
       mark:
			while(cmdNum!=5)
			{
				 try
					{
						cmdNum = dis.readInt();
					}
					catch(IOException e)
					{
						System.out.println("A client connection has been closed, socket on server will be closed.");
					}
				switch(cmdNum)
				{
				case 1:
					cdOnServer();
					break;
				case 2:
					lsOnServer();
					break;
				case 3:
					getOnServer();
					break;
				case 4:
					putOnServer();
					break;
				case 5:	// quit
					this.socket.close();
					System.out.println("A client has left.");
					break ;	// [note]
								// Don't use System.exit(0), which is used to terminate a process, rather than a thread.
				default:
					continue mark;
				}		
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	void cdOnServer() throws IOException
	{
		DataInputStream dis = new DataInputStream(this.socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(this.socket.getOutputStream());
		String dir = dis.readUTF();
		File file = new File(dir);
		if(!file.exists())
		{
			dos.writeUTF("directoy not found.");
		}
		else
		{
			this.WD += "\\" + dir;
			System.out.println("[client]cd: the current working directory is " + this.WD);
			dos.writeUTF("the current working directory is " + this.WD);
		}
	}
	void lsOnServer() throws IOException
	{
		File file = new File(this.WD);
		File[] files = file.listFiles();
		ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());	// output stream to the client
		oos.writeObject(files);	// write file list in the working directory to the client
		oos.flush();
		/* [note]
		 * this.socket.shutdownOutput();
		 * oos.close();
		 * 此处最好不要关闭任何流
		 */
	}
	void getOnServer() throws IOException
	{
		DataInputStream dis = new DataInputStream(this.socket.getInputStream());
		OutputStream os = this.socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		String filename = dis.readUTF();
		filename = this.WD + "\\" + filename;
//		System.out.println(filename);
		if(new File(filename).exists())
		{
			dos.writeInt(1);	// "1" presents that the file "filename" is exists.
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filename));
			BufferedOutputStream bos = new BufferedOutputStream(os);
			dos.writeLong(new File(filename).length());	// send the length of the file to client
														// 用文件长度来控制另一端接收何时结束
			byte[] b = new byte[128];
			int len;
			while( (len = bis.read(b, 0, b.length)) != -1 )
			{
				bos.write(b, 0, len);
			}
			bos.flush();

		}
		else
		{
			dos.writeInt(0);	// "0" presents that the file "filename" is not exists.
		}
	}
	void putOnServer() throws IOException
	{
		InputStream is = this.socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		String filename = dis.readUTF();
		BufferedInputStream bis = new BufferedInputStream(is);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filename));
		long fileLength = dis.readLong();
		byte[] b = new byte[128];
		long length = 0L;
		int len;
		while(true)
		{
			len = bis.read(b, 0, b.length);
			bos.write(b, 0, len);
			length += (long)len;
			if(length == fileLength)
			{
				break;
			}
		}
		bos.flush();
		new DataOutputStream(this.socket.getOutputStream()).writeInt(1);	// send the signal of finishing putting file
	}
}