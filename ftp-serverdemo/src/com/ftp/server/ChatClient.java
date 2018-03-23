package com.ftp.server;

import java.io.*;
import  java.net.*;
import java .awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/*建立客户端*/
public class ChatClient extends Thread implements ActionListener
{
	JTextField messageField,IDField,ipField,portField;
	JTextArea message,users;
	JButton connect,disconnect;
	String user="";
	String userList[]=new String[10];//保存在线的用户名，最多10个
	Socket theSocket;
	BufferedReader in;
	PrintWriter out;
	boolean connected=false;//连接状态
	Thread thread;
	public static void main(String args[])
	{
		JFrame frame=new JFrame("聊天室");//创建聊天窗口
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//关闭窗口
		ChatClient cc=new ChatClient();
		JPanel content=cc.createComponents();
		frame.getContentPane().add(content);
		frame.setSize(550,310);//设置窗口大小
		frame.setVisible(true);//设置frame这个组建显示为可见
		
	}
	/*创建图行用户界面*/
	public JPanel createComponents()
	{
		JPanel pane=new JPanel(new BorderLayout());
		message=new JTextArea(10,35);//用来显示聊天类容
		message.setEditable(false);//设置为只读
		JPanel paneMsg=new JPanel();//用来容纳message组件
		paneMsg.setBorder(BorderFactory.createTitledBorder("聊天类容:"));
		paneMsg.add(message);
		users=new JTextArea(10,10);//显示在线用户名
		JPanel listPanel=new JPanel();//容纳user组件
		listPanel.setBorder(BorderFactory.createTitledBorder("在线用户:"));
		listPanel.add(users);
		messageField=new JTextField(50);//用户在此输入聊天信息
		IDField=new JTextField(5);//用来输入用户名
		ipField=new JTextField("LocalHost");//用来输入服务器的ip
		portField=new JTextField("8888");//用来输入服务器的端口号
		connect=new JButton("连  接");//连接按钮
		disconnect=new JButton("断   开");//断开按钮
		disconnect.setEnabled(false);//断开按钮初始化状态为未激活
		JPanel buttonPanel=new JPanel();//用来容纳按钮及其标签
		buttonPanel.add(new Label("服务器IP:"));
		buttonPanel.add(ipField);
		buttonPanel.add(new Label("端口："));
		buttonPanel.add(portField);
		buttonPanel.add(new Label("用户名："));
		buttonPanel.add(IDField);
		buttonPanel.add(connect);
		buttonPanel.add(disconnect);
		pane.add(messageField,"South");//设置messageField在面板中得位置
		pane.add(buttonPanel,"North");
		pane.add(paneMsg,"Center");
		pane.add(listPanel,"West");
		//把connect实例加上了一个监听器，当鼠标单机是就会触发
		connect.addActionListener(this);
		disconnect.addActionListener(this);
		messageField.addActionListener(this);
		return pane;
	}
	public void actionPerformed(ActionEvent e)//用于接收操作事件的侦听器接口
	{
		if(e.getSource()==connect)
		{
			//调用getText()方法获得IDField文本区的内容并赋值给user
			user=IDField.getText();
			String ip=ipField.getText();
			//获得portField区的内容，并转化为整型
			int port=Integer.parseInt(portField.getText());
			if(!user.equals("")&&connectToServer(ip,port,user))
			{
				disconnect.setEnabled(true);//设置该对象的启用状态
			}
			connect.setEnabled(false);//设置该对象的禁用状态
		}
		if(e.getSource()==disconnect)
		{
			disconnectFromServer();
		}
		if(e.getSource()==messageField)
			if(theSocket!=null)
				//输出messageField文本区的内容
			{
				out.println("MESSAGE:"+messageField.getText());
				messageField.setText("");
			}
		
	}
	/*与服务器断开*/
	public void disconnectFromServer()
	{
		if(theSocket!=null)
		{
			try
			{
				connected=false;
				out.println("LEAVE:"+user);
				disconnect.setEnabled(false);
				connect.setEnabled(true);
				thread=null;
				theSocket.close();
			}catch(IOException io){}
			theSocket=null;
			message.setText("");
			users.setText("");
		}
	}
	/*与服务器建立连接*/
	public boolean connectToServer(String ip,int port,String ID)
	{
		if(theSocket!=null)//若连接已经建立则返回false
			return false;
		try
		{
			theSocket=new Socket(ip,port);
			in=new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
			out=new PrintWriter(new OutputStreamWriter(theSocket.getOutputStream()),true);
			out.println("USER"+user);
			message.setText("");
			connected=true;
			thread=new Thread(this);
			thread.start();
		}catch(Exception e){return false;}
		return true;
	}
	/*处理服务器发来的消息，并作出适当的回应*/
	public void extractMessage(String line)
	{
		System.out.println(line);
		Message messageline=new Message(line);
		if(messageline.isValid())
		{
			if(messageline.getType().equals("JOIN"))//如果有新用户加入
			{
				user=messageline.getBody();
				message.append(user+"进入聊天室\n");//添加显示内容
			}
			else if(messageline.getType().equals("LIST"))//更新在线用户名
				updateList(messageline.getBody());
			else if(messageline.getType().equals("MESSAGE"))
				message.append(messageline.getBody()+"\n");//添加聊天信息
			else if(messageline.getType().equals("REMOVE"))
				message.append(messageline.getBody()+"离开聊天室\n");		
		}
		else
			message.append("出现问题："+line+"\n");
	}
	/*更新在线用户*/
	public void updateList(String line)
	{
		users.setText("");
		String str=line;
		for(int i=0;i<10;i++)
			userList[i]="";//清除原有的用户信息
		int index=str.indexOf(":");
		int a=0;
		while(index!=-1)
		{
			userList[a]=str.substring(0,index);//用户名为第一个分号之前的字符串
			str=str.substring(index+1);//保存第一个分号之后的字符串
			a++;
			index=str.indexOf(":");//获取新字符第一个分号所在的位置
		}
		for(int i=0;i<10;i++)
			users.append(userList[i]+"\n");
	}
	/*读取服务器发送来的信息*/
	public void run()
	{
		try
		{
			String line="";
			while(connected&&line!=null)
			{
				line=in.readLine();
				if(line!=null)
					extractMessage(line);
			}
		}catch(IOException e){}
	}
}
