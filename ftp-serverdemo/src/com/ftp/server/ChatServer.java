package com.ftp.server;

import java.net.*;
import java.io.*;
public class ChatServer
{
	final static int thePort=21;//设定服务器端口
	ServerSocket theServer;//声明服务器的socket
	ChatHandler[] chatters;//保存所有正在运行的对象线程
	int numbers;//保存正在运行的线程数
	/*构造器*/
	public ChatServer()
	{
		try
		{
			theServer=new ServerSocket(thePort);
			chatters=new ChatHandler[10];//服务器最多可以同时处理10个线程
		}catch(IOException io){}
	}
	/*运行服务器端，接收客户端连接请求*/
	public void run()
	{
		try
		{
		System.out.println("服务器已建立！");
		while(numbers<10)//判断连接的客户端是否超过10个
		{
			Socket theSocket=theServer.accept();
			ChatHandler chatHandler=new ChatHandler(theSocket,this);
			chatters[numbers]=chatHandler;
			numbers++;
		}
		}catch(IOException e){}
	}
	/*当摸个客户端与服务器断开时，从chatters数组中移除*/
	public synchronized void removeConnectionList(ChatHandler c)
	{
		int index=0;
		for(int i=0;i<=numbers-1;i++)
		{
			if(chatters[i]==c) index=1;
		}
		for(int i=index;i<=numbers-1;i++)
		{
			chatters[i]=chatters[i+1];
			chatters[numbers-1]=null;//最后一位位null
			numbers--;//线程减1
		}
	}
	/*返回在线的用户名*/
	public synchronized String returnUsernameList()
	{
		String line="";
		for(int i=0;i<=numbers-1;i++)
			line=line+chatters[i].user+":";
		//将所有的用户名保存到line中，之间用：隔开
		return line;
	}
	/*将信息发给每个客户端*/
	public void broadcastMessage(String line)
	{
		System.out.println("发布信息："+line);
		for(int i=0;i<numbers;i++)
			chatters[i].sendMessage(line);
	}
	public static void main(String args[])
	{
		ChatServer app=new ChatServer();
		app.run();//调用run()方法
	}
}
