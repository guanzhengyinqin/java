package com.baxcall.socket2;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class MySocketServer {
	
	private List<MySocketClient> sockets = new ArrayList<MySocketClient>();
	private ServerSocket serverSocket;
	
	
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public MySocketServer(int port){
		try {
			serverSocket = new ServerSocket(port);
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init(){
		try {
			while(true){
				MySocketClient client = new MySocketClient(serverSocket.accept());
				sockets.add(client);
				new Thread(client).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	

}
