package readjson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ReadJson {
	
	public static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	public static String readJson(String path){
		String result = null;
		File file = new File(path);
		InputStream input = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			if(!file.exists()){
				result = "文件不存在";
				return result;
			}
			input = new FileInputStream(file);
			isr = new InputStreamReader(input, "UTF-8");   
			br = new BufferedReader(isr); 
			char[] data = new char[1024];
			
			int temp = 0;
			
			char[] code = new char[1];
			br.read(code);
//			System.out.println(code[0]);
//			System.out.println(code[1]);
//			System.out.println(code[2]);
			
		    StringBuffer strb = new StringBuffer();
			while((temp = br.read(data)) !=-1){
				String strTemp = new String(data,0,temp);
				strTemp = strTemp.replace("\r","");
				strTemp = strTemp.replace("\t","");
				strTemp = strTemp.replace("\n","");
				strTemp = strTemp.replace(" ","");
				strTemp = strTemp.replaceAll("\"","\'");
				strb.append(strTemp);
			}
			result = strb.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = "系统执行出错";
			e.printStackTrace();
		}finally {
			try{
				if(null!=input){
					input.close();
				}
				if(null!=isr){
					isr.close();
				}
				if(null!=br){
					br.close();
				}
				
			}catch(Exception e){
				result = "系统执行出错";
				e.printStackTrace();
			}
		}
		System.out.println(result);
		return result;
	}
	
	
	public static String writeExcel(JSONArray jsonArr,String path){
		String result = "";
		
		if(null==jsonArr){
			result =  "json为空";
		}else{
			if(jsonArr.size()==0){
				result = "json为空";
			}else{
				File folder = new File(path);
				if(!folder.exists()){
					folder.mkdirs();
				}
				
				String fileName = FORMATTER.format(new Date());
				String name = fileName.concat(".xls");
				WritableWorkbook book = null;
				File file = null;
				
				try {
					
					file = new File(path.concat(File.separator).concat(name));
					book = Workbook.createWorkbook(file);//创建xls文件
					WritableSheet sheet  =  book.createSheet("访问记录",0);
					for(int s=0;s<12;++s){
						switch (s) {
						case 0:
							sheet.addCell(new Label(s,0,"序号"));
							break;
						case 1:
							sheet.addCell(new Label(s,0,"姓名"));						
							break;
						case 2:
							sheet.addCell(new Label(s,0,"性别"));
							break;
						case 3:
							sheet.addCell(new Label(s,0,"电话"));
							break;
						case 4:
							sheet.addCell(new Label(s,0,"身份证号"));
							break;
						case 5:
							sheet.addCell(new Label(s,0,"生日"));
							break;
						case 6:
							sheet.addCell(new Label(s,0,"登记时间"));
							break;
						case 7:
							sheet.addCell(new Label(s,0,"离开时间"));
							break;
						case 8:
							sheet.addCell(new Label(s,0,"值班人员"));
							break;
						case 9:
							sheet.addCell(new Label(s,0,"来访事由"));
							break;
						case 10:
							sheet.addCell(new Label(s,0,"住址"));
							break;
						case 11:
							sheet.addCell(new Label(s,0,"备注"));
							break;
						}
					}
					
					for(int j=0;j<jsonArr.size();++j){
						//for(int k=1;k)
						JSONObject temp = jsonArr.getJSONObject(j).getJSONObject("visitor");
						sheet.addCell(new Label(0,j+1,jsonArr.getJSONObject(j).get("uuid").toString()));
						sheet.addCell(new Label(1,j+1,temp.get("name").toString()));
						
						switch(Integer.parseInt(temp.getString("sex"))){
						case 0:
							sheet.addCell(new Label(2,j+1,"女"));
							break;
						case 1:
							sheet.addCell(new Label(2,j+1,"男"));
							break;
						}
						sheet.addCell(new Label(3,j+1,temp.getString("phone")));
						sheet.addCell(new Label(4,j+1,temp.getString("idNumber")));
						sheet.addCell(new Label(5,j+1,temp.getString("birthdate")));
						sheet.addCell(new Label(6,j+1,jsonArr.getJSONObject(j).getString("registerTime")));
						sheet.addCell(new Label(7,j+1,jsonArr.getJSONObject(j).getString("leaveTime")));
						sheet.addCell(new Label(8,j+1,jsonArr.getJSONObject(j).getString("userUuid")));
						sheet.addCell(new Label(9,j+1,jsonArr.getJSONObject(j).getString("visitorContent")));
						sheet.addCell(new Label(10,j+1,temp.getString("site")));
						sheet.addCell(new Label(11,j+1,jsonArr.getJSONObject(j).getString("remark")));

					}
					
					book.write();
					result = file.getPath();
					
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					if(book!=null){
						try {
							book.close();
						} catch (WriteException e) {
							// TODO Auto-generated catch block
							result = "WriteException";
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							result = "IOException";
							e.printStackTrace();
						}
					}
				}
				
			}
		}
		return result;
	}
	
	public static String createExcel(String inputPath,String outputPath){
		return writeExcel(JSONObject.fromObject(readJson(inputPath)).getJSONArray("response"), outputPath);
	}
	
	
	
	public static void main(String[] args) {
		
		//Scanner input = new Scanner(System.in);
		//boolean flag = true;
		//do{
			System.out.println("请输入txt文件路径(只支持utf-8编码):");
			//String txt = input.next();
			System.out.println("请输入转换文件路径：");
			//String txt2 = input.next();
			try{
				System.out.println("正在创建excel...");
				//System.out.println("输出路径为："+createExcel("C:\\Users\\user\\Desktop\\json读取\\json.txt","C:\\Users\\user\\Desktop\\json读取"));
				System.out.println("输出路径为："+createExcel(args[0].split("-")[0],args[0].split("-")[1]));
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("系统崩溃...");
			}
			//System.out.println("请输入 quit 退出");
			//if(input.next().equals("quit")){
			//	flag = false;
			//}
		//}while(flag);
		
		
		//new JTextFieldDemo1();
		
	}
}
