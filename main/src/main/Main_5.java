package main;

import java.io.UnsupportedEncodingException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.Util;

public class Main_5 {
	
	public static void main(String[] args) {
		/*
		byte[] a;
		try {
			a = "中".getBytes("utf-8");
			System.out.println(a.length);
			for (byte b : a) {
				System.out.print(b+"\t");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//System.out.println(Util.byteArrayToInt(a));
		
		//byte[] b = new byte[]{};
		//System.out.println(new String(b));
		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
		jarr.add("http://server.domain.name/image/uuid_1");
		jarr.add("http://server.domain.name/image/uuid_2");
		json.put("stallNo", 2);
		json.put("imageUrl", jarr);
		System.out.println(json.toString());
		
	}

}
