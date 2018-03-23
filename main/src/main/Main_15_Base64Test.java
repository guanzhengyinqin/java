package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

public class Main_15_Base64Test {
	
	public static void main(String[] args) throws Exception {
		
		/*
		ServerSocket server = new ServerSocket(8765);
		
		Socket s = server.accept();
		
		String a = new BufferedReader(new InputStreamReader(s.getInputStream(),"UTF-8")).readLine();
		
		System.out.println("收到消息-->"+a);
		Thread.sleep(10000);
		*/
		
		//System.out.println(new String(new byte[]{46}));
		/*
		//第一个字节
		byte a = -5;
		System.out.println("原二进制数-->"+Integer.toBinaryString(a&0XFF));
		//System.out.println(Integer.toBinaryString(-2));
		//System.out.println(Integer.toBinaryString(a));
		//取前六位bit
		System.out.println(a&0XFC);
		System.out.println("第一个前六位二进制"+Integer.toBinaryString((a&0XFC)&0XFF));
		
		//取后两位bit并往左移六位
		byte b = (byte) (a << 6);
		System.out.println(b);
		System.out.println("第一个后两位二进制"+Integer.toBinaryString((b)&0XFF));

		
		//第二个字节
		byte a2 = -6;
		//取前四位bit并往右移两位
		byte c = (byte) ((a2&0XF0) >> 2);
		//System.out.println(a);
		System.out.println(c);
		System.out.println("第二个前四位二进制"+Integer.toBinaryString(c&0XFF));
		
		//把第一个字节后两位和第二个字节前四位拼接起来
		byte d = (byte) (b | c);
		System.out.println(d);
		System.out.println("第一个后二位和第二个前四位拼接的二进制"+Integer.toBinaryString(d&0XFF));
		
		
		//第二个字节后四位bit往左移四位
		byte e = (byte) (a2 << 4);
		System.out.println(e);
		System.out.println("第二个后四位往前移动四位后的二进制"+Integer.toBinaryString(e&0XFF));
		
		
		
		
		//第三个字节
		byte a3 = 5;
		//取前两位并和第二个字节后四位拼接
		byte f = (byte) (a3 & 0XC0);
		byte g = (byte) (e | (f >> 4));
		System.out.println(g);
		System.out.println("第二个后四位与第三个前两位拼接的二进制"+Integer.toBinaryString(g&0XFF));
		
		//取最后六位
		byte h = (byte) ((a3 & 0X3F) << 2);
		System.out.println(h);
		System.out.println("第三个后六位的二进制"+Integer.toBinaryString(h&0XFF));
		
		
		//后两位组合前四位
		System.out.println(convertBinaryUnsigned(h));
		*/
		
		
		
		//System.out.println(Byte.MAX_VALUE);
		//System.out.println(Byte.MIN_VALUE);
		
		/*
		byte[] das = "BC".getBytes();
		int[] d = convertBinaryBase64(das);
		for (int i : d) {
			System.out.println(BASE_64_STR[i]);
		}
		*/
		File f = new File("D:\\picture\\886168\\Face_886168_3195_1520605313.jpg");
		InputStream in = new FileInputStream(f);
		int length = (int) f.length();
		byte[] a = new byte[length];
		in.read(a);
		/*
		for (int i = 0; i < 3; i++) {
			System.out.println(a[i]);
		}
		*/
		
		String str = convertBase64StrForBytes(a);
		System.out.println(str.length());
		
	}
	
	//把有符号转成无符号
	public static int convertBinaryUnsigned(byte b){
		return ((int)b) & 0XFF;
	}
	
	
	/**
	 * 把三个字节按6位一个字节转成四个字节
	 * @param bytes
	 * @return
	 */
	public static int[] convertBinaryBase64(byte[] bytes){
		int[] result = new int[4];
		
		if(bytes.length==3){
			byte a = bytes[0];
			byte b = bytes[1];
			byte c = bytes[2];
			byte a_1 = (byte) (a & 0XFC);
			int out1 = (byte) (convertBinaryUnsigned(a_1) >> 2);
			result[0] = out1;
			
			byte a_2 = (byte) ((a & 0X3) << 6);
			byte b_1 =  (byte) ((b & 0XF0) >> 2);
			int out2 = (convertBinaryUnsigned((byte) (a_2 | b_1)) >> 2);
			result[1] = out2;
			
			byte b_2 = (byte) ((b & 0XF) << 4);
			byte c_1 = (byte) ((c & 0XC0) >>4);
			int out3 = (convertBinaryUnsigned((byte) (b_2 | c_1)) >> 2);
			result[2] = out3;
			
			byte c_2 = (byte) (c & 0X3F);
			byte out4 = c_2;
			result[3] = out4;
		}else if(bytes.length==1){
			byte a = bytes[0];
			byte a_1 = (byte) (a & 0XFC);
			int out1 = (byte) (convertBinaryUnsigned(a_1) >> 2);
			result[0] = out1;
			
			byte a_2 = (byte) ((a & 0X3) << 6);
			int out2 = (convertBinaryUnsigned((byte) (a_2)) >> 2);
			result[1] = out2;
			
			result[2] = 64;
			result[3] = 64;
		}else if(bytes.length==2){
			byte a = bytes[0];
			byte b = bytes[1];
			byte a_1 = (byte) (a & 0XFC);
			int out1 = (byte) (convertBinaryUnsigned(a_1) >> 2);
			result[0] = out1;
			
			byte a_2 = (byte) ((a & 0X3) << 6);
			byte b_1 =  (byte) ((b & 0XF0) >> 2);
			int out2 = (convertBinaryUnsigned((byte) (a_2 | b_1)) >> 2);
			result[1] = out2;
			
			byte b_2 = (byte) ((b & 0XF) << 4);
			int out3 = (convertBinaryUnsigned((byte) (b_2)) >> 2);
			result[2] = out3;
			result[3] = 64;
		}
		return result;
	}
	
	public static int indexOfBase64(char c){
		int result = -1;
		for (int i = 0; i < BASE_64_STR.length; i++) {
			if(c == BASE_64_STR[i]){
				result = i;
				break;
			}
		}
		return result;
	}
	
	
	/**
	 * byte数组转base64字符串的方法
	 * @param bytes
	 * @return
	 */
	public static String convertBase64StrForBytes(byte[] bytes){
		int remainder = bytes.length % 3;
		
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<bytes.length-2;i+=3){
			int[] temp = convertBinaryBase64(new byte[]{bytes[i],bytes[i+1],bytes[i+2]});
			for (int j : temp) {
				buffer.append(BASE_64_STR[j]);
			}
			
		}
		
		
		if(remainder!=0){
			int doFor = (bytes.length - remainder) / 3;
			int index = doFor * 3;
			int[] end = null;
			if(bytes.length-index==1){
				end = convertBinaryBase64(new byte[]{bytes[index]});
			}else if(bytes.length-index==2){
				end = convertBinaryBase64(new byte[]{bytes[index],bytes[index+1]});
			}
			for (int i : end) {
				buffer.append(BASE_64_STR[i]);
			}
		}
		
		return buffer.toString();
		
	}
	
	
	public static final char[] BASE_64_STR = {
			'A','B','C','D','E','F','G','H','I','J',
			'K','L','M','N','O','P','Q','R','S','T',
			'U','V','W','X','Y','Z',
			'a','b','c','d','e','f','g','h','i','j',
			'k','l','m','n','o','p','q','r','s','t',
			'u','v','w','x','y','z',
			'0','1','2','3','4','5','6','7','8','9',
			'+','/','='
	};
	
	

}
