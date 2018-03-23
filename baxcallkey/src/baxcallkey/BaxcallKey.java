package baxcallkey;

import java.security.MessageDigest;

public class BaxcallKey {
	/**
	 * MD5���ܷ���
	 * @param str
	 * @return
	 */
	public static String encode(String str){
		if(str==null){
			return null;
		}
		StringBuilder sb = new StringBuilder();	//����һ���ɱ��ַ������ж���
		
		try{
			//�����ƶ������㷨���Ƶ���ϢժҪ
			MessageDigest code = MessageDigest.getInstance("MD5");
			//ʹ���ƶ����ֽ������ժҪ���и���
			code.update(str.getBytes());
			byte[] bs = code.digest();
			for(int i=0;i<bs.length;++i){
				int v = bs[i] & 0xFF;
				if(v<16){
					//��ɱ��ַ���
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
