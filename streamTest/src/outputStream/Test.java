package outputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//�����
		File file = new File("d:"+File.separator+"outputStream"+File.separator+"my.txt");
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();	//�������ļ�
			
		}
		//�ڶ���������OutputStream������Ϊ�������ʵ����
		OutputStream output = new FileOutputStream(file,true);//trueΪ׷�Ӳ���
		String msg = "ôô��\r\n";
		//Ϊ�˷����������Ҫ���ַ���Ϊ�ֽ�����
		byte[] data = msg.getBytes();
		
		for(int i=0;i<data.length;++i){
			output.write(data[i]);
		}
		
		output.write(data);
		output.close();
	}

}
