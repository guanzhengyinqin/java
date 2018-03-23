package inputStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) throws Exception {
		File file = new File("D:/网站记录.txt");
		
		BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
		


		char[] data = new char[1024];
		int length = 0;
		StringBuffer a = new StringBuffer();
		
		while((length=input.read(data))!=-1){
			System.out.println(length);
			a.append(new String(data,0,length));
		}
		System.out.println(a);
		//System.out.println(a);
		input.close();
		
		System.out.println("320C57C083E7F678ED14B8974732225E".length());
	}
	
}
