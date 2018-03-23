package annotationdemo;

import java.lang.reflect.Method;

public class BeforFunc {

	private BeforFunc(){
		try {
			throw new Exception();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static <T> Object methodInvok(Class<T> clazz,Object[] parameters){
		Object returnValue = null;
		try{
			
			if(BeforInterface.class.isAssignableFrom(clazz)){
				
				Class[] parClazz = null;
				boolean hasParameters = false;
				if(null!=parameters){
					hasParameters = true;
					parClazz = new Class[parameters.length];
					for(int i=0;i<parameters.length;i++){
						parClazz[i] = Class.forName(parameters[i].getClass().getName());
						System.out.println(parClazz[i]);
					}
				}
				
				Method[] ms = clazz.getMethods();
				Method im = null;
				Method inv = null;
				for(Method m:ms){
					if("beforMethod".equals(m.getName())){
						System.out.println(Class.forName(parameters.getClass().getName()));
						im = clazz.getMethod(m.getName(),Object[].class);
					}
					if(m.isAnnotationPresent(BeforRun.class)){
						if(hasParameters){
							inv = clazz.getDeclaredMethod(m.getName(), parClazz);
						}else{
							inv = m;
						}
						
					}
				}
				
				if(im!=null){
					
					T t = clazz.newInstance();
					boolean result = false;
					if(hasParameters){
						result = (boolean) im.invoke(t,new Object[]{parameters});
					}else{
						System.out.println(im.getName());
						result = (boolean) im.invoke(t,new Object[]{null});
					}
					
					if(result){
						if(null!=inv){
							if(hasParameters){
								returnValue = inv.invoke(t, parameters);
							}else{
								returnValue = inv.invoke(t);
							}
						}
					}
					
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
			
		return returnValue;
		
		
		
	}
	
}
