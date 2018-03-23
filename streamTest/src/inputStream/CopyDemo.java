package inputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyDemo {
	public static void main(String[] args) throws Exception {
		//如果要执行拷贝命令，则必须通过初始化参数传递文件路径以及目标文件路径
		if(args.length!=2){		//参数内容必须是两个
			System.out.println("错误的命令，格式为：CopyDemo 源文件路径  目标文件路径");
			System.exit(1); 	//退出程勋
		}
		//如果现在有参数了，那么还需要验证源文件是否存在
		File inFile = new File(args[0]);
		if(!inFile.exists()){
			System.out.println("路径错误，请确定源文件路径正确");
			System.exit(1);
		}
		//如果拷贝的目标文件存在，则也不应该进行拷贝
		File outFile = new File(args[1]);
		if(outFile.exists()){	//目标文件已经存在
			System.out.println("拷贝的路径已经存在，请更换路径");
			System.exit(1);
		}
		Long start = System.currentTimeMillis();
		InputStream in = new FileInputStream(inFile);
		OutputStream out = new FileOutputStream(outFile);
		copy(in,out);
		in.close();
		out.close();
		Long end = System.currentTimeMillis();
		System.out.println("花费的时间："+(end-start));
	}
	
	public static void copy(InputStream input,OutputStream output) throws Exception{
		int temp = 0; //保存每次读取字节量
		byte[] data = new byte[1024*1024];
		while((temp=input.read(data))!=-1){	//每次读取1024kb
			output.write(data,0,temp);
		}
	}
	
}
