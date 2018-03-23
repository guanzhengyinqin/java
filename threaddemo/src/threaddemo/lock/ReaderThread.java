package threaddemo.lock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class ReaderThread extends Thread {
	
	private PipedReader pr;
	
	//用户包装管道流的BufferReader对象
	private BufferedReader br;
	
	public ReaderThread(){}
	public ReaderThread(PipedReader pr){
		this.pr = pr;
		this.br = new BufferedReader(pr);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String buf = null;
		try{
			//逐行读取管道输入流中的内容
			while((buf = br.readLine())!=null){
				System.out.println(buf);
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}finally{
			try {
				if(null!=br){
					br.close();
				}
				
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
	}

}
class WriterThread extends Thread{
	
	String[] books = new String[]{
			"Struts2权威指南",
			"ROR敏捷开发指南",
			"给予J2EE的Ajax宝典",
			"轻量级J2EE企业应用指南"
	};
	
	private PipedWriter pw;
	
	public WriterThread(){}
	public WriterThread(PipedWriter pw){
		this.pw = pw;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			for (int i = 0; i < 100; i++) {
				pw.write(books[i % 4]+"\n");
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pw!=null){
					pw.close();
				}
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}
	
}
