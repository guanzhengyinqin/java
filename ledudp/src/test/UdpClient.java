package test;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpClient implements Runnable {
	
	private DatagramPacket in = new DatagramPacket(new byte[1024], 1024);
	private InetAddress address;
	private int port;
	private DatagramSocket datagramSocket;
	private ReadData readData;
	
	
	public UdpClient(String ip,int port,ReadData readData){
		try {
			datagramSocket = new DatagramSocket();
			this.address = InetAddress.getByName(ip);
			this.port = port;
			this.readData = readData;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(SocketException ex){
			ex.printStackTrace();
		}
		if(null!=readData){
			new Thread(this).start();
		}
	}
	
	public void setUdpClientAddress(String ip,int port) throws Exception{
		this.address = InetAddress.getByName(ip);
		this.port = port;
	}
	
	private void send(byte[] data){
		try {
			datagramSocket.send(new DatagramPacket(data,data.length,address,port));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void send(String str) throws Exception{
		send(assemble(toBytes(bytes2HexString(str.getBytes("GB2312")))));
	}
	
	
	private static byte[] toBytes(String str) {
        if(str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for(int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }
	
    private static String bytes2HexString(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }
    
    private static byte[] assemble(byte[] data){
		byte[] data1 = new byte[]{
				(byte) 0xf6,
				0x5a,
				(byte) (data.length+14),
				0x00,
				(byte) 0xa3,
				(byte) 0xf0,
				0x00,
				0x00,
				0x00
		};
		
		byte[] data2 = new byte[]{
				0x00,
				0x07,
				0x00,
				0x5a,
				(byte) 0xf6
		};
		
		byte[] result = new byte[data1.length+data.length+data2.length];
		
		System.arraycopy(data1, 0, result, 0, data1.length);
		System.arraycopy(data,0, result, data1.length, data.length);
		System.arraycopy(data2, 0, result, (data1.length+data.length), data2.length);
		
		return result;
	}
	
	
	@Override
	public void run() {
		try {
			while(true){
				datagramSocket.receive(in);
				byte[] data = new byte[in.getLength()];
				byte[] datas = in.getData();
				
				for (int i = 0; i < data.length; i++) {
					data[i] = datas[i];
				}
				
				readData.readData(data);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
