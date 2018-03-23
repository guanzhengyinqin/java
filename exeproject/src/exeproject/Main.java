package exeproject;

import java.util.Scanner;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated constructor stub
		/*
		Scanner input = new Scanner(System.in);
		System.out.println("请输入文字");
		String aa = input.next();
		System.out.println("你输入的文字是:"+aa);
		System.out.println("请输入任意字符退出");
		input.next();
		*/
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<2;++i){
			JSONObject temp = new JSONObject();
			temp.put("uuid","uuid32");
			
			temp.put("operationCode", i+1);
			
			temp.put("name", "访客姓名");
			temp.put("departmentName", "部门名称");
			temp.put("phone", "电话");
			temp.put("site", "地址");
			jsonArray.add(temp);
		}
		json.put("machineType", 2);
		json.put("organizationId", "机构id");
		json.put("machineCode", "机器码");
		json.put("data", jsonArray);
		System.out.println(json.toString());
	}
}
