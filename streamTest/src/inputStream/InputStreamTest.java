package inputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InputStreamTest {
	public static void main(String[] args) throws Exception {
		File file = new File("D:/inputStream/404.jpg");
		File file2 = new File("D:/outputStream/404.jpg");
		OutputStream out = new FileOutputStream(file2);
		//实例化inputStream类
		InputStream input = new FileInputStream(file);
		//实现数据读取操作
		byte[] data = new byte[1024];
		//input.read(data);	//将数据读取到数组之中
		int temp = 0;
		int i = 1;
		while((temp = input.read(data)) !=-1){
				out.write(data);
			System.out.println(temp+"\t"+i);
			i++;
		}
		//关闭输入流
		input.close();
		out.close();
		
		
	}
}
