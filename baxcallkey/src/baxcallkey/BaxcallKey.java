package baxcallkey;

import java.security.MessageDigest;

public class BaxcallKey {
	/**
	 * MD5加密方法
	 * @param str
	 * @return
	 */
	public static String encode(String str){
		if(str==null){
			return null;
		}
		StringBuilder sb = new StringBuilder();	//创建一个可变字符串序列对象
		
		try{
			//创建制定具体算法名称的信息摘要
			MessageDigest code = MessageDigest.getInstance("MD5");
			//使用制定的字节数组对摘要进行更新
			code.update(str.getBytes());
			byte[] bs = code.digest();
			for(int i=0;i<bs.length;++i){
				int v = bs[i] & 0xFF;
				if(v<16){
					//向可变字符串
					sb.append(Integer.toHexString(v));
				}
				sb.append(v);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return sb.toString().toUpperCase();
	}

}
