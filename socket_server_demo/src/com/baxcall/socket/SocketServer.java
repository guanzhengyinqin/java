package com.baxcall.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class SocketServer {
	
	private List<SocketClient> sockets = new ArrayList<>();
	private ServerSocket serverSocket;
	
	
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public SocketServer(int port){
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
				SocketClient client = new SocketClient(serverSocket.accept());
				sockets.add(client);
				new Thread(client).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	

}
