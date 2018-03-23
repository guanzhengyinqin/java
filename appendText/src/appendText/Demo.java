package appendText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo {
	
	/**
	 * 
	 * @param path		文件路径
	 * @param str		字符串
	 * @param append	追加
	 */
	public static void appendText(String path,String str,boolean append){
		File file = new File(path);
		OutputStream out = null;
		try{
			if(!(file.exists())){
				file.createNewFile();
			}
			out = new FileOutputStream(file,append);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String writeDate = sf.format(new Date());
			writeDate = "\n"+writeDate+"\n";
			out.write(writeDate.getBytes());
			out.write(str.getBytes());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(null!=out){
					out.flush();
					out.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		appendText("D:\\test.txt","撒谎绝地反击阿斯加德佛IE就我",true);
	}

}
