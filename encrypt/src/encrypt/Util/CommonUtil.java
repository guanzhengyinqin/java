package encrypt.Util;

import java.util.Map;

import encrypt.bean.RemouldHashMap;

//import javax.servlet.http.HttpServletResponse;

public class CommonUtil {
	
	private CommonUtil() throws Exception{
		throw new Exception("对象不能被创建");
	}

	/*
	public static boolean outPrintJson(HttpServletResponse response,String json){
		PrintWriter out = null;
		boolean result = false;
		try{
			out = response.getWriter();
			out.print(json);
		}catch(Exception e){
			result = false;
			e.printStackTrace();
		}finally{
			if(null!=out){
				out.flush();
				out.close();
				result = true;
			}
		}
		return result;
	}
	*/

	public static boolean isNull(Object obj){
		if(null==obj||"".equals(obj.toString().trim())){
			return true;
		}
		return false;
	}

	public static void judgeNullAction(NullAction action,RemouldHashMap<String,Object> objs){
		for(Map.Entry<String,Object> entry: objs.entrySet()){
			if(isNull(entry.getValue())){
				action.isNullAction(entry.getKey(),entry.getValue());
			}else{
				action.isNotNullAction(entry.getKey(), entry.getValue());
			}
		}
	}



	public interface NullAction{
		void isNullAction(String name,Object obj);
		void isNotNullAction(String name,Object obj);
	}
	
}
