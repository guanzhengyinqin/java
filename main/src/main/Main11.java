package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Main11 {
	
	
	
	public static void main(String[] args) {
		/*
		String color = "灰色或银色";
		byte[] cD = color.getBytes(Charset.forName("UTF-8"));
		System.out.println("长度-->"+cD.length);
		for (byte b : cD) {
			System.out.print(b+"\t");
		}
		*/
		/*
		byte[] d = new byte[]{3,4,5,8,9,7,4};
		ByteBuffer b = ByteBuffer.wrap(d);
		
		byte[] c = getByteArrayByByteBuffer(b);
		b.get();
		b.get();

		System.out.println(b.position());
		System.out.println(b.capacity()-b.position());
		*/
		
		
		try {
			
			SocketChannel skc = SocketChannel.open();
			//设置链接超时
 			skc.connect(new InetSocketAddress("127.0.0.1",3361));
			int fileTemp = 1;
			while(true){
				ByteBuffer bf = ByteBuffer.allocate(4);
				int a = skc.read(bf);
				bf.flip();
				System.out.println("读了"+a+"字节");
				byte[] lengthData = getByteArrayByByteBuffer(bf);
				int length = byteArrayToInt(lengthData);
				
				ByteBuffer bf2 = ByteBuffer.allocate(length);
				System.out.println("收到文件，数据大小："+length);
				readByteBuffer(skc,bf2,0);
				
				//byte[] data = bf2.array();
				bf2.flip();
				File f = new File("D:/接收到的文件/DDD"+fileTemp+".jpg");
				if(!f.getParentFile().exists()){
					f.getParentFile().mkdirs();
				}
				f.createNewFile();
				FileChannel fout = new FileOutputStream(f).getChannel();
				fout.write(bf2);
				
				fout.close();
				fileTemp++;
			}
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("系统退出");
		}
		
		
	}
	
	
    private static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    private static byte[] intToByteArray(int a) {
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }
	
	public static void readByteBuffer(SocketChannel socketChannel,ByteBuffer byteBuffer,int readLength) throws IOException{
		int temp = socketChannel.read(byteBuffer);
		temp = temp+readLength;
		if(temp!=byteBuffer.capacity()){
			readByteBuffer(socketChannel,byteBuffer,temp);
		}
		
	}
	
	public static byte[] getByteArrayByByteBuffer(ByteBuffer byteBuffer){
		byte[] temp = new byte[byteBuffer.capacity()];
		byteBuffer.get(temp, 0, temp.length);
		return temp;
	}

}
