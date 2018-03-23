package socket.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient implements Runnable {

	private Socket socket;
	private InputStream in;
	private OutputStream out;
	private BufferedReader bin;
	private ReadData readData;
	
	public SocketClient(Socket socket,ReadData readData){
		this.socket = socket;
		this.readData = readData;
		try {
			in = socket.getInputStream();
			out = socket.getOutputStream();
			bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	 @Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				String data = bin.readLine();
				readData.read(data);
				if(socket.isClosed()){
					break;
				}
				if(null==data){
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	 
	public void write(String data){
		try {
			out.write(data.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
