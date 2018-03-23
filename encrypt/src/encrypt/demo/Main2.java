package encrypt.demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main2 {
	
	public static void main(String[] args) {
		
		/*
		Integer a = new Integer(1);
		Integer b = new Integer(1);
		System.out.println();
		System.out.println(a==b);
		*/
		
		/*
		JSONObject jsona = new JSONObject();
		System.out.println(jsona.put("a","b")==null);
		System.out.println(jsona.put("a","b")==null);
		jsona.put("b", "c");
		System.out.println(jsona.toString().split(",")[0]);
		*/
		
		//Person p = new Person();
		//p.setAge(9);
		//p.setHeight(165);
		//p.setName("小王");
		//Person p2 = new Person();
		//System.out.println(p.hashCode()+"\t"+p2.hashCode());
		
		//GenericityJson<Object,Integer> json = new GenericityJson<>(new JSONObject());
		//json.put(p, "ab");
		//JSONObject j = JSONObject.fromObject("")
		/*
		json.put("a", "a");
		json.put("b", "a");
		json.put("c", "a");
		*/
		//json.put("a", 4);
		//System.out.println(json.getJson().toString());
		//System.out.println(json.getValueByIndex(1));
		
		/*
		String a = "{'a':1, 'b':2, 'c':3 ,'a':2}";
		json = json.fromObject(a);
		System.out.println(json.getValueByIndex(0));
		System.out.println(json.getJson().get("a"));
		json.put("a", 5);
		System.out.println(json.getValueByIndex(0));
		*/
		
		/*
		for (Integer s : json) {
			System.out.println(s);
		}
		*/
		
		
		/*
		JSONObject json = new JSONObject();
		json.put("a", "b");
		json.put("b", "b");
		json.put("c", "c");
		System.out.println(json.size());
		*/
		
	}
	
	  private static void myWrite() throws IOException {
		    // TODO Auto-generated method stub
		    // 创建数据输出流对象
		    FileOutputStream fos = new FileOutputStream("E:\\zikao\\file\\cs.txt");
		    DataOutputStream dos = new DataOutputStream(fos);
		  
		    // 写数据
		    dos.writeByte(10);
		    dos.writeShort(100);
		    dos.writeInt(1000);
		    dos.writeLong(10000);
		    dos.writeFloat(12.34F);
		    dos.writeDouble(12.56);
		    dos.writeChar('a');
		    dos.writeBoolean(true);
		  
		    // 释放资源
		    dos.close();
		  }
		  
		  private static void myReader() throws IOException {
		    // TODO Auto-generated method stub
		    // 创建数据输入流对象
		    FileInputStream fis = new FileInputStream("E:\\zikao\\file\\cs.txt");
		    DataInputStream dis = new DataInputStream(fis);
		  
		    // 读数据
		    byte b = dis.readByte();
		    short s = dis.readShort();
		    int i = dis.readInt();
		    long l = dis.readLong();
		    float f = dis.readFloat();
		    double d = dis.readDouble();
		    char c = dis.readChar();
		    boolean bl = dis.readBoolean();
		  
		    // 释放资源
		    dis.close();
		  
		    System.out.println(b);
		    System.out.println(s);
		    System.out.println(i);
		    System.out.println(l);
		    System.out.println(f);
		    System.out.println(d);
		    System.out.println(c);
		    System.out.println(bl);
		  }

}
