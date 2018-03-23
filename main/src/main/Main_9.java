package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main_9 {
	
	public static void main(String[] args) {
		/*
		int[] ps = separationPort(3396);
		
		int a = 3396;
		System.out.println((a & 0xff));
		
		System.out.println(ps[0]+"\t"+ps[1]);
		
		int port = restorePort(ps);
		
		System.out.println(port);
		 */
		Scanner sin = new Scanner(System.in);
		try {
			Socket s = new Socket("192.168.3.30",2333);
			OutputStream out = s.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			while(true){
				String sendStr = sin.nextLine();
				out.write((sendStr+"\r\n").getBytes("UTF-8"));
				System.out.println("发送的数据--->"+sendStr);
				String str = br.readLine();
				System.out.println("接收的数据--->"+str);
			}
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static int[] separationPort(int port){
		
		int a = port >> 8;
		int b = port & 0xFF;
		
		return new int[]{a,b};
		
	}
	
	public static int restorePort(int[] separationPort){
		int a = separationPort[0];
		int b = separationPort[1];
		
		int c = a << 8;
		int d = c | b;
		return d;
		
	}

}
