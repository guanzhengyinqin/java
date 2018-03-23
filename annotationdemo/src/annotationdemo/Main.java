package annotationdemo;

import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) {
		try {
			Class c = Class.forName("annotationdemo.Person");
			boolean isExist = c.isAnnotationPresent(Description.class);
			Method[] mArr = c.getDeclaredMethods();
			for(Method m:mArr){
				if(m.isAnnotationPresent(Description.class)){
					Description e = (Description) m.getAnnotation(Description.class);
					System.out.println(e.author());
					System.out.println(e.age());
					System.out.println(e.desc());
				}
			}
			System.out.println(isExist);
			if(isExist){
				Description d = (Description) c.getAnnotation(Description.class);
				System.out.println(d.author());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
