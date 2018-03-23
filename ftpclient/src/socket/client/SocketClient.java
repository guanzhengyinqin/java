package socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient implements Runnable {
	
	private int port;
	private ReadData readData;
	//private InputStream in;
	//private OutputStream out;
	private PrintWriter pout;
	private BufferedReader bin;
	private Socket socket;
	
	public SocketClient(String host,int port,ReadData readData){
		this.port = port;
		this.readData = readData;
		try {
			this.socket = new Socket(host, port);
			//in = socket.getInputStream();
			
			pout = new PrintWriter(socket.getOutputStream());
			//out = socket.getOutputStream();
			new Thread(this).start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	public void write(byte[] data){
		try {
			out.write(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	public void write(String data){
		pout.println(data);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true){
			try {
				String data = bin.readLine();
				readData.readData(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			
		}
	}

}
