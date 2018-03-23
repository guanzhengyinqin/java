package niodemo.read;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class Test {
	
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		ByteBuffer head = ByteBuffer.allocate(2);
		ByteBuffer instruct = ByteBuffer.allocate(4);
		ByteBuffer lengthInfo = ByteBuffer.allocate(4);
		File f = new File("D:\\wangzhan.txt");
		FileChannel channel = null;
		try {
			channel = new FileInputStream(f).getChannel();
			channel.read(head);
			channel.read(instruct);
			channel.read(lengthInfo);
			System.out.println(new String(head.array()));
			System.out.println(new String(instruct.array()));
			System.out.println(new String(lengthInfo.array()));
			String a = "";
			Charset gb = Charset.forName("GB2312");
			CharsetDecoder de = gb.newDecoder();
			int temp;
			String b = "";
			System.out.println("channelSize:"+channel.size());
			System.out.println("channelPosition:"+channel.position());
			byte[] strab = new byte[1024];
			while((temp=channel.read(buffer))>0){
				buffer.flip();
				if(channel.size()==channel.position()){
					System.out.println("最后一次读取数据");
					byte [] arb = new byte[buffer.limit()];
					buffer.get(arb, 0, arb.length);
					b += new String(arb,"GB2312");
				}else{
					buffer.get(strab, 0, strab.length);
					b += new String(strab,"GB2312");
				}
				
				
				System.out.println("position:"+buffer.position());
				System.out.println("limit:"+buffer.limit());
				//byte[] bya = new byte[buffer.limit()];
				//buffer.get(bya, 0, buffer.limit());
				//b += new String(bya,"GB2312");
				
				buffer.clear();
				
			};
			System.out.println(channel.position());
			
			//System.out.println(a);
			System.out.println("\n\n");
			System.out.println(b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				if(null!=channel){
					channel.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
