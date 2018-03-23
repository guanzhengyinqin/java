package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import bean.Animal;
import bean.ListDemo;
import bean.Person;
import funcclass.Copy;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class Main {
	
	//交换变量函数
	  public static byte[] fun(byte[] a, byte[] b){
		  if(a.length>b.length){
			  byte[] d = new byte[a.length];
			  for(int j=0;j<a.length;j++){
				  if(j>b.length-1){
					  d[j] = 0;
				  }else{
					  d[j] = b[j];
				  }
			}
			  b = d;
		  }else if(a.length<b.length){
			  byte[] d = new byte[b.length];
			  for(int j=0;j<b.length;j++){
				  if(j>a.length-1){
					  d[j] = 0;
				  }else{
					  d[j] = a[j];
				  }
			}
			  a = d;
		  }
		  
	        byte[] c = new byte[a.length];
	        for (int i = 0; i < a.length; i++) {
	            c[i] = (byte) (a[i]^b[i]);
	        }
	        return c;
	    }
	
	public static void main(String[] args) {
		
		
		
		
		/*
		Boolean has = new Boolean(true);
		if(has){
			System.out.println("么么哒");
		}
		*/
		
		//System.out.println(String.valueOf(1).concat("-").concat(String.valueOf(1)));
		
		/*
		String[] a = "1".split("-");
		System.out.println(a);
		
		System.out.println(Integer.parseInt("1".split("-")[0]));
		*/
		
		//System.out.println("{'meetAddress':'','meetTime':1512530665987,'visitorRemark':{'meetAddress':'','meetTime':1512525267204,'visitorRemark':'地铁还没修好？','intervieweeRemark':'不知道，我换的另一条线，走路走了一个站','amendTheTreaty':false},'intervieweeRemark':'','amendTheTreaty':true}".length());
		
		
		/*
		File f1 = new File("D:\\a1.jpg");
		File f2 = new File("D:\\copy.jpg");
		long l;
		try {
			l = Copy.copyFile(f1, f2);
			System.out.println(l);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		/*
		System.out.println(Integer.class.getName());
		*/
		//System.out.println("{'meetAddress':'','visitorRemark':{'meetAddress':'','visitorRemark':'','intervieweeRemark':'','amendTheTreaty':false},'intervieweeRemark':'','amendTheTreaty':false}".length());
		
		/*
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date(1511930880000L)));
		
		System.out.println(new Date().getTime());
		*/
		/*
		JSONObject json = new JSONObject();
		json.put("a", "");
		System.out.println(json.toString());
		System.out.println(json.getString("a"));
		System.out.println(json.isEmpty());
		*/
		/*
		String a = null;
		String dataRemark = a;
		if(null!=dataRemark&&!("".equals(dataRemark))&&!("null".equals(dataRemark))){
			System.out.println("true");
		}else{
			System.out.println("false");
		}
		*/
		/*
		Map<Long,Object> map = new HashMap<>();
		map.put(1L, null);
		System.out.println(map.get(1));
		map.remove(1);
		System.out.println(map.get(1));
		*/
		
		//System.out.println(4 >>> 1);
		
		//List a = new ArrayList
		//System.out.println(9%10);
		
		/*
		ListDemo<Integer> list = new ListDemo<Integer>();
		for(int i=0;i<48;i++){
			list.addItem(i+1);
		}
		
		List<Integer> list_2 = new ArrayList<Integer>();
		for(int j=0;j<12;++j){
			list_2.add(j);
		}
		
		list.addAll(list_2);
		
		for(Integer i:list){
			System.out.println(i);
		}
		//list.clear();
		System.out.println(list.removeAll(list_2));
		System.out.println(list.contains(49));
		*/
		
		
		
		/*
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					try {
						Thread.sleep(3000);
						System.out.println("打印");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		*/
		
		
		//System.out.println(1 << 30);
		
		/*
		Person person = new Person();
		person.setHeight(168);
		person.setName("李娜");
		person.setSex(0);
		person.setWeight(50);
		Map<String,Person> map = new HashMap<String,Person>();
		
		map.put("person", person);
		person.setHeight(162);
		person.setName("么么哒");
		person.setSex(1);
		person.setWeight(52);
		
		System.out.println(map.get("person").getName());
		*/
		
		/*
		Map<String,Object> json = new HashMap<String,Object>();
		boolean flag = true;
		json.put("flag", flag);
		flag = false;
		System.out.println(json.get("flag"));
		*/

		//Random
		//String a=RandomStringUtils.random(8, "abcdefghijklmnopqrstuvwxyz1234567890");
        //System.out.println(a);
		
		/*
		try {
			System.out.println(URLDecoder.decode("http%3A%2F%2Flocalhost%3A8080%2Fvisitorsystemlocalhost%2Fwechat%2Fvisit_info.html%3Fuuid%3Def4a2230c1b44a94ada5d4f318feba29", "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//System.out.println("么么哒\n么么哒\n么么哒");
		
		//System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		
		/*
		try {
			
			String urlCode = URLEncoder.encode("许柏胜", "utf-8");
			System.out.println(urlCode);
			String nickName = URLDecoder.decode(urlCode,"utf-8");
			System.out.println(nickName);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		/*
		byte[] a = "saodifjsadfsadffsadf".getBytes();
		byte[] b = "klsahjdfkljh".getBytes();
		
		a = fun(a, b);
		b = fun(a, b);
		a = fun(a, b);
		
		System.out.println(new String(a));
		System.out.println(new String(b));
		*/
		
//		byte[] c = fun(new byte[]{1}, new byte[]{1});
//		System.out.println("xx:"+c[0]);
//		
		
		/*
		long start = System.currentTimeMillis();
		
		File file = new File("D:\\test\\upload2.ext");
		try {
			
			if(!file.exists()){
				file.createNewFile();
			}
			
			OutputStream out = new FileOutputStream(file);
			byte[] data = new byte[1024*1024*5];
			long max = 5368709120l;
			while(true){
				out.write(data);
				if(file.length()>max){
					break;
				}
			}
			
			if(null!=out){
				out.flush();
				out.close();
			}
			System.out.println("花费时间：");
			System.out.println(System.currentTimeMillis()-start);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//System.out.println(String.valueOf(11).length());
		
		//System.out.println(new Main().zeroFill("13455", 6));
		
		//System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		//String str = ""+2;
		//System.out.println(str);
		/*
		Person person = new Person();
		person.setAttribute("str", "么么哒");
		person.setAttribute("str2", "么么哒2");
		Person person2 = new Person();
		person2.setAttribute("str", "person2么么哒");
		person2.setAttribute("str2", "person2么么哒2");
		System.out.println(person.getAttribute("str"));
		System.out.println(person.getAttribute("str2"));
		System.out.println(person2.getAttribute("str"));
		System.out.println(person2.getAttribute("str3"));
		*/
		/*
		String[] str = "2017-09-21 13:53:11.0".split("\\.");
		System.out.println(str[0]);
		*/
		/*
		String str = "http://wx.qlogo.cn/mmopen/xbIQx1GRqdvyqkMMhEaGOX802l1CyqMJNgUzKP8MeAeHFicRDSnZH7FY4XB7p8XHXIf6uJA2SCun"+
					"TPicGKezDC4saKISzRj3nz/0";
		System.out.println(str.length());
		*/
		
//		String str = "http:\\/\/wx.qlogo.cn\/mmopen\/PiajxSqBRaEKc5hFe4ibFGIKJpyeXqFuomptgxcMuoNicTn3p8saUPlMQfctSNFZJusuVzvlRsbw3MRgMEdmCDLEA\/0";
		
		//String str = "sfs\/df";
		//System.out.println(str);
		
		//System.out.println(str2HexStr("40"));
		//System.out.println(hexStr2Str("31"));
		
		System.out.println();
		/*
		try{
			JSONObject json = JSONObject.fromObject("{'1':'8908105141224688','2':'1232132131'}");
			System.out.println(json.getString("1"));
		}catch(JSONException e){
			e.printStackTrace();
		}
		*/
		
		JSONObject json = new JSONObject();
		json.put("1", "8908105141224688");
		json.put("2", "1232132131");
		System.out.println(json.toString().length());
		
		
		
	}
	
	
	/**
	 * 字符串转换成为16进制(无需Unicode编码)
	 * @param str
	 * @return
	 */
	public static String str2HexStr(String str) {
	    char[] chars = "0123456789ABCDEF".toCharArray();
	    StringBuilder sb = new StringBuilder("");
	    byte[] bs = str.getBytes();
	    int bit;
	    for (int i = 0; i < bs.length; i++) {
	        bit = (bs[i] & 0x0f0) >> 4;
	        sb.append(chars[bit]);
	        bit = bs[i] & 0x0f;
	        sb.append(chars[bit]);
	        // sb.append(' ');
	    }
	    return sb.toString().trim();
	}
	
	//16进制转为字符串
	/**
	 * 16进制直接转换成为字符串(无需Unicode解码)
	 * @param hexStr
	 * @return
	 */
	public static String hexStr2Str(String hexStr) {
	    String str = "0123456789ABCDEF";
	    char[] hexs = hexStr.toCharArray();
	    byte[] bytes = new byte[hexStr.length() / 2];
	    int n;
	    for (int i = 0; i < bytes.length; i++) {
	        n = str.indexOf(hexs[2 * i]) * 16;
	        n += str.indexOf(hexs[2 * i + 1]);
	        bytes[i] = (byte) (n & 0xff);
	    }
	    return new String(bytes);
	}
	
	
	
	public String zeroFill(String number,int places){
		String result = "";
		int length = number.length();
		if(length<places){
			int circulation = places - length;
			for(int i=0;i<circulation;++i){
				result += "0";
			}
		}
		result = result + number;
		return result;
	}
	
}
