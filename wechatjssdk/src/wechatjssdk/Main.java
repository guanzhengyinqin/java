package wechatjssdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	
	private static final String token = "syncoToken";
	
	public static void main(String[] args) {
		
        System.out.println("开始签名校验");  
        String signature = "Wm3WZYTPz0wzccnW";
        String timestamp = "sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg";
        String nonce = "1414587457";
        String echostr = "";
          
        System.out.println("signature=" + signature);  
        System.out.println("timestamp=" + timestamp);  
        System.out.println("nonce=" + nonce);  
        System.out.println("echostr=" + echostr);  
  
        List<String> array = new ArrayList<String>();  
        array.add(signature);  
        array.add(timestamp);  
        array.add(nonce); 
        // 排序  
        String sortString = sort(token, timestamp, nonce);
        // 加密 
        System.out.println(sortString);
        String mytoken = Decript.SHA1("jsapi_ticket=sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg&noncestr=Wm3WZYTPz0wzccnW&timestamp=1414587457&url=http://mp.weixin.qq.com?params=value");
        System.out.println(mytoken);
        
	}
	
	
	
	/**  
     * 排序方法  
     *   
     * @param token  
     * @param timestamp  
     * @param nonce  
     * @return  
     */  
    public static String sort(String token, String timestamp, String nonce) {
        String[] strArray = { token, timestamp, nonce };
        Arrays.sort(strArray);

        StringBuilder sbuilder = new StringBuilder();
        for (String str : strArray) {
            sbuilder.append(str);
        }

        return sbuilder.toString();
    }

}
