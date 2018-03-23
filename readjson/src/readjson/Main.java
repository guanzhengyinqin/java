package readjson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Main {
	
	public static void main(String[] args) {
		
		/*
		JSONObject json = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		for(int i=0;i<3;++i){
			JSONObject temp = new JSONObject();
			temp.put("id", i+1);
			temp.put("name", "zhangsan");
			temp.put("idNumber", "3263453");
			temp.put("phone", "110");
			jsonArr.add(temp);
		}
		json.put("data",jsonArr);
		System.out.println(json.toString());
		*/
		/*
		JSONObject json = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		for(int i=0;i<3;++i){
			JSONObject temp = new JSONObject();
			temp.put("uuid", UUID.randomUUID().toString().replaceAll("-",""));
			//temp.put("name", "zhangsan");
			//temp.put("idNumber", "3263453");
			//temp.put("phone", "110");
			jsonArr.add(temp);
		}
		json.put("data",jsonArr);
		System.out.println(json.toString());
		*/
		
		/*
		JSONObject json = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		for(int i=0;i<3;++i){
			JSONObject temp = new JSONObject();
			temp.put("uuid", UUID.randomUUID().toString().replaceAll("-",""));
			temp.put("time", 1504146546);
			//temp.put("name", "zhangsan");
			//temp.put("idNumber", "3263453");
			//temp.put("phone", "110");
			jsonArr.add(temp);
		}
		json.put("data",jsonArr);
		System.out.println(json.toString());
		*/
		/*
		System.out.println(Date2TimeStamp("1991-06-20 00:00:00","yyyy-MM-dd HH:mm:ss"));
		System.out.println(date2TimeStamp("1991-06-20 00:00:00","yyyy-MM-dd HH:mm:ss"));
		//System.out.println(TimeStamp2Date("1991-06-20 00:00:00","yyyy-MM-dd HH:mm:ss"));
		System.out.println(TimeStamp2Date("677347200", "yyyy-MM-dd HH:mm:ss"));
		System.out.println(TimeStamp2Date("677343600", "yyyy-MM-dd HH:mm:ss"));
		*/
		/*
		JSONObject json = new JSONObject();
		json.put("uuid",UUID.randomUUID().toString().replaceAll("-",""));
		System.out.println(json.toString());
		*/
	}
	/*
	public static long date2TimeStamp(String date_str,String format){  
		 try {  
		 SimpleDateFormat sdf = new SimpleDateFormat(format);  
		return sdf.parse(date_str).getTime();  
		} catch (Exception e) {  
		e.printStackTrace();  
		}  
		return 1l;  
		}
	
	public static String TimeStamp2Date(String timestampString, String formats) {
        if ("".equals(formats))
            formats = "yyyy-MM-dd HH:mm:ss";
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        return date;
    }
	
	public static String Date2TimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
	*/
}
