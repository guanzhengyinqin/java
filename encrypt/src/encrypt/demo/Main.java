package encrypt.demo;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.util.Base64.Decoder;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import encrypt.bean.Person;
import encrypt.bean.RemouldHashMap;
import net.sf.json.JSONObject;

public class Main {

    public static void main(String[] args) {
    	
    	
    	JSONObject j = new JSONObject();
    	
    	
    	/*
    	Person p = new Person();
    	try {
			Method m = p.getClass().getMethod("setName", String.class);
			m.invoke(p,"小明");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	System.out.println(p.getName());
    	*/
    	
    	
    	
    	
    	
    	
    	
    	//HashMap<String,Object> hs = new HashMap<>();
    	//hs.put("fa", null);
    	//System.out.println(hs.get("fa"));
    	/*
    	Person p = new Person();
    	//p.setAge(2);
    	p.setNickName("么么哒");
    	JSONObject json = new JSONObject();
    	json = JSONObject.fromObject(p);
    	//json = JSONObject.fromObject(p);
    	//p.set
    	final JSONObject reJson = json;
    	RemouldHashMap<String,Object> hashMap = new RemouldHashMap<String,Object>();
    	judgeNullAction(new NullAction() {
			
			@Override
			public void isNullAction(String name,Object obj) {
				// TODO Auto-generated method stub
				reJson.put(name, "没有");
			}
			
			@Override
			public void isNotNullAction(String name,Object obj) {
				// TODO Auto-generated method stub
				reJson.put(name, obj.toString());
			}
		},hashMap.rPut("name",p.getName()).rPut("nickName", p.getNickName()).rPut("sex",p.getSex()));
    	
    	System.out.println(json.toString());
    	System.out.println("name:"+json.getString("name"));
    	System.out.println("nickName:"+json.getString("nickName"));
    	System.out.println("sex:"+json.getString("sex"));
    	*/
    	/*
    	String a = "   ";
    	System.out.println(isNull(a));
    	*/
    	
    	/*
    	String a = " ";
    	try {
    		String b = URLEncoder.encode(a,"utf-8");
    		System.out.println(b);
    		
			System.out.println(URLDecoder.decode(a,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	*/
    	
    	
    	/*
    	String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_-0123456789";
    	
    	
    	for(int i=0;i<10;i++){
    		String b = generateRandomString(str,6);
    		System.out.println(b);
    	}
    	*/
    	/*
    	Random random = new Random();
    	boolean flag = true;
    	while(flag){
    		int a = random.nextInt(10);
    		System.out.println(a);
    		if(a==9){
    			flag = false;
    		}
    		
    	}
    	*/
    	//String var1 = "hWbHV92555ykM095".substring(6);
       // int inviteId = Integer.parseInt(var1.substring(0,var1.length()-6));
       // System.out.println(inviteId);
        
    	//System.out.println("\\root\\data\\whiteList\\2017\\12\\19\\145812285".replaceAll("\\\\", "\\"));
    	//System.out.println("\\");
    	
    	//System.out.println("q8wgvM2vl9ruW".substring(6,7));
    	/*
    	try {
			System.out.println(URLEncoder.encode("深圳至壹有限公司", "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	*/


	}
    
    
    
    static boolean fun(Object... objs){
    	for(Object obj : objs){
    		if(obj == null) return false;
    	}
    	return true;
    }
   
    
    public static String generateRandomString(String str,int length){
    	String result = "";
    	Random random = new Random();
    	char[] chars = str.toCharArray();
    	for(int i=0;i<length;i++){
    		result += chars[random.nextInt(chars.length)];
    	}
    	return result;
    }
    
    public static boolean isNull(Object obj){
    	if(null==obj||"".equals(obj.toString().trim())){
    		return true;
    	}
    	return false;
    }
    
    public static void judgeNullAction(NullAction action,RemouldHashMap<String,Object> objs){
    	for(Entry<String,Object> entry: objs.entrySet()){
    		if(isNull(entry.getValue())){
    			action.isNullAction(entry.getKey(),entry.getValue());
    		}else{
    			action.isNotNullAction(entry.getKey(), entry.getValue());
    		}
    	}
    }
    
    
    interface NullAction{
    	public void isNullAction(String name,Object obj);
    	public void isNotNullAction(String name,Object obj);
    }
	
}




