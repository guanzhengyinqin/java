package wechat.example;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Sign {
	
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
    	
    	Map<String,String> map = sign("O1d5SKRqgngOy2rgxJN0tknTRWv8Dy-pdsyl0rAcc3kfIRtOMfVTxoC4NHO9NLsF8wf36adyOlCUYcHEDHf1TO",
    									"VHdusjedDox5mRtV",
    									"1516930618",
    									"http://192.168.3.10/project/notification");
    	
	}

    public static Map<String, String> sign(String jsapi_ticket,String noncestr,String timestamp, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
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
    
    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
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
        ret.put("nonceStr", nonce_str);
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

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}


