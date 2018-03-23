package com.ftp.server;

import java.net.*;
import java.io.*;
public class ChatHandler  extends Thread
{
	Socket theSocket;//保存本线程相关的Socket对象
	BufferedReader in;
	PrintWriter out;
	int thePort;
	ChatServer parent;
	String user="";//保存用户名
	boolean disconnect=false;//保存连接状态
	/*创建线程来响应客户端*/
	public ChatHandler(Socket socket,ChatServer parent)
	{
		try{
		theSocket=socket;
		this.parent=parent;
		in=new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
		out=new PrintWriter(new OutputStreamWriter(theSocket.getOutputStream()),true);
		thePort=theSocket.getPort();
		start();
		}catch(IOException io){}
	}
	/*将服务器发送的消息传到客户端*/
	public void sendMessage(String line)
	{
		out.println(line);
	}
	/*服务器发送信息，告知其他客户新客户的连接*/
	public void setupUserName(String setname)
	{
		user=setname;
		parent.broadcastMessage("Join:"+user);
	}
	/*处理客户端发来的信息，并发送对应的信息到客户端*/
	public void extractMessage(String line)
	{
		Message messageline;
		messageline=new Message(line);
		if(messageline.isValid())
		{
			if(messageline.getType().equals("USER"))//如果信息为申请连接
			{
				setupUserName(messageline.getType());
				//向其他所有客户发送新的用户名列表
				parent.broadcastMessage("List:"+parent.returnUsernameList());
			}
			else if(messageline.getType().equals("MESSAGE"))
			{
				parent.broadcastMessage("Message:"+user+"说："+messageline.getBody());
			}
			else if(messageline.getType().equals("LEAVE"))
			{
				//客户端离开
				String c=disconnectClient();
				parent.broadcastMessage("RMOVE:"+c);
				//告知其他用户，改用户已经离开
				parent.broadcastMessage("List:"+parent.returnUsernameList());
			}
		}
		else
			sendMessage("命令不存在");
		

	}
	/*与服务器断开，并返回用户名*/
	public String disconnectClient()
	{
		try
		{
			in.close();
			out.close();
			theSocket.close();
			parent.removeConnectionList(this);
			//将本线程从服务器的chatters数组中移除
			disconnect=true;
		}catch(Exception ex){}
		return user;
	}
	/*获取客户输入的信息*/
	public void run()
	{
		String line;
		try
		{
			while((line=in.readLine())!=null)
			{
				System.out.println("收到："+line);
				extractMessage(line);
				
			}
		}catch(IOException io){}
	}
}
