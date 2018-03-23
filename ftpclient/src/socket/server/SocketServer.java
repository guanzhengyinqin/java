package socket.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class SocketServer {
	
	public static List<SocketClient> sockets;
	private ServerSocket serverSocket;
	
	
	public SocketServer(int port){
		sockets = new ArrayList<>();
		try {
			this.serverSocket = new ServerSocket(port);
			SocketClient socket = new SocketClient(serverSocket.accept(),new DufaultHandler());
			sockets.add(socket);
			new Thread(socket).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
