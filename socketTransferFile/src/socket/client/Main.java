package socket.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
	
	public static void main(String[] args) {
		
		InputStream in = null;
		
		OutputStream out = null;
		try {
			in = new FileInputStream(new File("D:/exeproject.zip"));
			Socket socket = new Socket("192.168.3.30", 8765);
			out = socket.getOutputStream();
			byte[] data = new byte[512];
			int temp = 0;
			while((temp=in.read(data))!=-1){
				out.write(data, 0, temp);
			}
			in.close();
			out.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
