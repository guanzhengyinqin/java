package socket.client;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Test {
	
	public static void main(String[] args) {
		SocketClient socketClient = new SocketClient("192.168.3.30", 21, new ReadData() {
			
			@Override
			public void readData(String data) {
				// TODO Auto-generated method stub
				System.out.println(data);
			}
			
			@Override
			public void readData(byte[] data) {
				// TODO Auto-generated method stub
				System.out.println(new String(data));
				for (byte b : data) {
					System.out.print(b+"\t");
				}
			}
		});
		
		
		Scanner in = new Scanner(System.in);
		while(true){
			String data = in.nextLine();
			try {
				System.out.println("data-->"+data);
				socketClient.write(data);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
