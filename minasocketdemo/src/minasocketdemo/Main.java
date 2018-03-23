package minasocketdemo;

public class Main {

	public static SocketServer socketServer;
	
	public static void main(String[] args) {
		//SocketServer.socketServerStart();
		//SocketServer.start();
		socketServer = new SocketServer();
		socketServer.start();
	}
	
}
