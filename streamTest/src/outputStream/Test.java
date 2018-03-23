package outputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//输出流
		File file = new File("d:"+File.separator+"outputStream"+File.separator+"my.txt");
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();	//创建父文件
			
		}
		//第二步：利用OutputStream的子类为父类进行实例化
		OutputStream output = new FileOutputStream(file,true);//true为追加操作
		String msg = "么么哒\r\n";
		//为了方便输出，需要将字符变为字节数组
		byte[] data = msg.getBytes();
		
		for(int i=0;i<data.length;++i){
			output.write(data[i]);
		}
		
		output.write(data);
		output.close();
	}

}
