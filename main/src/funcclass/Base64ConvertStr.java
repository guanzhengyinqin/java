package funcclass;

import java.util.Base64;

public class Base64ConvertStr {

	 //base64字符串转byte[]  
    public static byte[] base64String2ByteFun(String base64Str){  
        return Base64.getDecoder().decode(base64Str);
    }  
    //byte[]转base64  
    public static String byte2Base64StringFun(byte[] b){  
        return Base64.getEncoder().encodeToString(b);
    }  
	
}
