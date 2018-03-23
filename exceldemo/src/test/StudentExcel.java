package test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Student;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class StudentExcel {
	
	private StudentExcel(){}
	
	public static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	/**
	 * 获取属性值
	 * @param fieldName	字段名称
	 * @param o	对象
	 * @return	Object
	 */
   private static Object getFieldValueByName(String fieldName, Object o) {
       try {
           String firstLetter = fieldName.substring(0, 1).toUpperCase();    
           String getter = "get" + firstLetter + fieldName.substring(1);    
           Method method = o.getClass().getMethod(getter, new Class[] {});
           Object value = method.invoke(o, new Object[] {});    
           return value;
       } catch (Exception e) {
           e.printStackTrace();
           return null;    
       }    
   }
	
	/**
	 * 将list集合转成Excel文件
	 * @param list	对象集合
	 * @param path	输出路径
	 * @return
	 */
	public static String createExcel(List<Student> list,String path){
		String result = "";
		if(list.size()==0||list==null){
			result = "没有对象信息";
		}else{
			if(list.get(0) instanceof Student){
				Student o = list.get(0);
				@SuppressWarnings("unchecked")
				Class<Student> clazz = (Class<Student>) o.getClass();
				String className = clazz.getSimpleName();
				Field[] fields=clazz.getDeclaredFields();//通过反射获取字段数组
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
					WritableSheet sheet  =  book.createSheet(className,0);
					int i = 0;	//列
					int j = 0;	//行
					for(Field f:fields){
						boolean flag = false;
						j = 0;
						switch(indexOfField(f.getName().toString())){
						
						case 0:
							sheet.addCell(new Label(i, j,"姓名"));
							flag = true;
							break;
						case 1:
							sheet.addCell(new Label(i, j,"性别"));
							flag = true;
							break;
						case 2:
							sheet.addCell(new Label(i, j,"生日"));
							flag = true;
							break;
						case 3:
							sheet.addCell(new Label(i, j,"爱心数量"));
							flag = true;
							break;
						default:
							flag = false;
							break;
						}
						
						
						j = 1;
						if(flag){
							for(Student obj:list){
								Object temp = getFieldValueByName(f.getName(),obj);
								String strTemp = "";
								if(temp!=null){
									strTemp = temp.toString();
								}
								sheet.addCell(new Label(i,j,strTemp));
								j++;
							}
							i++;
						}
						
			        }
					book.write();
					result = file.getPath();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					result = "SystemException";
					e.printStackTrace();
				}finally{
					fileName = null;
					name = null;
					folder = null;
					file = null;
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
			}else{
				result = "对象不匹配";
			}
			
	        
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		
		Student stu1 = new Student();
		Student stu2 = new Student();
		stu1.setSname("张三");
		stu1.setSex("男");
		stu1.setBirthday("1992-03-04");
		stu1.setLovenum(7);
		stu2.setSname("李四");
		stu2.setSex("女");
		stu2.setBirthday("1993-07-17");
		stu2.setLovenum(20);
		List<Student> stus = new ArrayList<Student>();
		stus.add(stu1);
		stus.add(stu2);
		System.out.println(createExcel(stus,"d:"));
		
		
		
	}
	
	
	private static int indexOfField(String property){
		int i = 0;
		try{
			if("sname".equals(property)){
				i = 0;
			}else if("sex".equals(property)){
				i = 1;
			}else if("birthday".equals(property)){
				i = 2;
			}else if("lovenum".equals(property)){
				i = 3;
			}else{
				i = 10;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return i;
	}
}
