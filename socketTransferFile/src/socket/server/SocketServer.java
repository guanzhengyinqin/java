package socket.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketServer extends ServerSocket {
	
	List<SocketClient> clients = new ArrayList<>();

	public SocketServer(int port) throws IOException {
		super(port);
		Socket socket = this.accept();
		new Thread(new SocketClient(socket)).start();
	
		
		
		// TODO Auto-generated constructor stub
	}

}
