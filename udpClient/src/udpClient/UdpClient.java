package udpClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.Scanner;

public class UdpClient {
	
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		DatagramSocket ds = new DatagramSocket();
		byte[] data = null;

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				byte[] data = new byte[1024];
				DatagramPacket rece = new DatagramPacket(data, data.length);
				while(true){
					try {
						ds.receive(rece);
						System.out.println("收到消息--->"+new String(rece.getData(),0,rece.getLength(),Charset.forName("UTF-8")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}).start();
		
		while(true){
			data = scan.nextLine().getBytes();
			DatagramPacket dp = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.3.30"), 3388);
			
			ds.send(dp);
			//ds.close();
		}
		

	}

}
