package base64convertimagedemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

import Decoder.BASE64Decoder;

public class StrGenerateImage {

	// base64字符串转化成图片
	public static byte[] GenerateImage(String imgStr) { //对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
		return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {		// 调整异常数据
					b[i] += 256;
				}
			}
			return b;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 读取base64格式图片文件
	 * @param path	文件路径
	 * @return
	 */
	public static String readFile(String path,boolean removeSpaces){
		String result = null;
		File file = new File(path+".txt");
		if(file.isFile()&&file.exists()){
			InputStream input = null;
			byte[] data = new byte[1024*100];
			StringBuffer str = null;
			int temp = 0;
			try{
				str = new StringBuffer();
				input = new FileInputStream(file);
				if(removeSpaces){
					while((temp = input.read(data)) !=-1){
						String tempstr = new String(data,0,temp);
						tempstr = tempstr.replace("\r","");
						tempstr = tempstr.replace("\n","");
						tempstr = tempstr.replace("\t","");
						str.append(tempstr);
					}
				}else{
					while((temp = input.read(data)) !=-1){
						String tempstr = new String(data,0,temp);
						str.append(tempstr);
					}
				}
				
				result = str.toString();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(input!=null){
					try {
						input.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}else{
			result = "文件不存在";
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		String str = readFile("\\root\\data\\faceBaseImage\\2017\\09\\01\\160742296", true);
		GenerateImage(str);
	}
	
}


