package socket.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient implements Runnable {

	private InputStream in;
	private OutputStream out;
	
	private ReadData readData;
	private Socket socket;
	
	public SocketClient(Socket socket,ReadData readData){
		this.socket = socket;
		this.readData = readData;
		try {
			this.in = socket.getInputStream();
			this.out = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public SocketClient(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		try {
			this.in = socket.getInputStream();
			this.out = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void closeSocket(){
		try {
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void send(byte[] data){
		try {
			out.write(data);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void send(byte[] data,int off,int len){
		try {
			out.write(data, off, len);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

		int temp = 0;
		byte[] data = new byte[1024];
		OutputStream fout = null;
		try {
			File f = new File("D:/receptionFile");
			f.createNewFile();
			fout = new FileOutputStream(f);
			while((temp=in.read(data))!=-1){
				//readData.readData(data);
				fout.write(data, 0, temp);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				in.close();
				fout.close();
				closeSocket();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}
	
	

}
