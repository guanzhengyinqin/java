package main;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Main_13 {
	
	public class Test{
		public void say(){
			System.out.println("么么哒");
		}
	}
	
	public void say_1(){
		new Main_13.Test().say();
	}
	
	public static void main(String[] args) {
		
		
		String M = "M";
		String a = "a";
		String n = "n";
		
		System.out.println(M.getBytes()[0]+"\t"+a.getBytes()[0]+"\t"+n.getBytes()[0]);
		
		byte[] bb = {76};
		System.out.println(new String(bb));
		
		byte c = 77;
		byte b = 77>>2;
		byte[] d = {b};
		
		System.out.println(Integer.toBinaryString(c));
		System.out.println(b);
		System.out.println(new String(d));
		
		/*
		System.out.println("豫".getBytes(Charset.forName("utf-8")).length);
		*/
		/*
		byte[] b = new byte[]{3,4,5,6,7,8,9,0,0,0,0,0,0,0,0,0,0};
		
		ByteBuffer bf = ByteBuffer.wrap(b);
		*/
		
	}

}
