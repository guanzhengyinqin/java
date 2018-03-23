package test;

import java.io.IOException;

import ftpServer.FtpServer;

public class Test {
	
	public static void main(String[] args) {
		//System.out.println(System.getProperty("user.dir"));
		
		try {
			FtpServer ftpServer = new FtpServer(21);
			ftpServer.listen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
