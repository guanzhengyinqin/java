package threaddemo.lock;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class PipedCommunicationTest {

	public static void main(String[] args) {
		PipedWriter pw = null;
		PipedReader pr = null;
		try{
			//分别创建两个独立的管道输出流、输入流
			pw = new PipedWriter();
			pr = new PipedReader();
			
			//链接管道输出流、输入流
			pr.connect(pw);
			
			//将连接好的管道流分别传入两个线程
			//就可以让两个线程通过管道流进行通信
			
			new WriterThread(pw).start();
			new ReaderThread(pr).start();
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
	
}
