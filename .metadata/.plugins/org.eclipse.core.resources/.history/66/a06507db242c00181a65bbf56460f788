package socketAccept;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerTest {
	
	public static void main(String[] args) throws Exception {
		ServerSocket socket = new ServerSocket(9963);
		while(true){
			Socket s = socket.accept();
			InputStream in = s.getInputStream();
			try{
				byte[] data = new byte[1024];
				int temp = 0;
				temp = in.read(data);
				String reData = new String(data,0,temp);
				System.out.println(reData);
				String[] s1 = reData.split("\r\n");
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(null!=in){
					in.close();
				}
				s.close();
			}
		}
		
	}

}
