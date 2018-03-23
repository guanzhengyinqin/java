package main;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main_8 {
	
	public static void main(String[] args) {
		//new ServerS
		/*
		try {
			System.out.println(InetAddress.getLocalHost().getHostAddress());
			System.out.println(InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		//System.out.println(simpleDateFormat.format(new Date()));
		
		String str = "D:/picture/717860/Face_717860_45_1517591780.jpg";
		System.out.println(str.startsWith("Face"));
		System.out.println(str.contentEquals("Face"));
		System.out.println(str.indexOf("Face1"));
		
		
	}

}
