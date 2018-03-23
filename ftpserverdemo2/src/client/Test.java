package client;

import java.net.ServerSocket;
import java.net.Socket;

public class Test {
	
	public static void main(String[] args) {
		final String F_DIR = "D:/picture"; //根路径
		final int PORT = 21;	//监听端口号
		try{
			ServerSocket s = new ServerSocket(PORT);
			
			while(true){
				Socket client = s.accept();
				new ClientThread(client,F_DIR,new ReadData() {
					
					@Override
					public void readData(String data) {
						// TODO Auto-generated method stub
						System.out.println("收到的命令\t"+data);
					}
				}).start();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
