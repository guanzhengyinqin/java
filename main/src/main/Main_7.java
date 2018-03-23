package main;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import util.JSONObject;

public class Main_7 {
	
    public static void main(String[] args) {
    	/*
    	String jsapi_ticket = "HoagFKDcsGMVCIY2vOjf9iN8DdXQJ1Xt_w6dv_h9EIbxGOzXWNBVyr4cbAVpIlcma-DLMZdMNvJKXtbgsZ3JOw";

        // 注意 URL 一定要动态获取，不能 hardcode
        String url = "http://mp.weixin.qq.com?params=value";
        Map<String, String> ret = sign(jsapi_ticket, url);
        for (Map.Entry<String,String> entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
        */
    	/*
    	Map<String,String> map = sign("O1d5SKRqgngOy2rgxJN0tknTRWv8Dy-pdsyl0rAcc3kfIRtOMfVTxoC4NHO9NLsF8wf36adyOlCUYcHEDHf1TO",
    									"VHdusjedDox5mRtV",
    									"1516930618",
    									"http://192.168.3.10/project/notification");
    	
    	System.out.println(map.get("signature"));
    	*/
    	
    	//System.out.println("ba9fffdbbeaa3b23eb4f3ffbeadf83c6a002219c".length());
    	//System.out.println("0f9de62fce790f9a083d5c99e95740ceb90c27ed".length());
    	/*
    	Formatter formatter = new Formatter();
    	formatter.format("%02x", 23);
    	formatter.format("%02x", 24);
    	System.out.println(formatter.toString());
    	*/
    	/*
    	JSONObject json = new JSONObject();
    	JSONArray jrr = new JSONArray();
    	jrr.add(new JSONObject().put("address","rtsp://192.168.3.200:554/av0_0"));
    	jrr.add(new JSONObject().put("address", "rtsp://192.168.3.201:554/av0_0"));
    	json.put("rtspList", jrr);
    	System.out.println(json.toString());
    	for (Object o : json) {
			System.out.println(o.toString());
		}
    	*/
    	
    	
	}

    public static Map<String, String> sign(String jsapi_ticket,String noncestr,String timestamp, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "api_ticket=" + jsapi_ticket +
                  "&noncestr=" + noncestr +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", noncestr);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }
    
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}
