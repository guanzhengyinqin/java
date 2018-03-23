package proxydemo2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import proxyinterface.BeforeInterface;

public class Func{
	
	private BeforeInterface beforeInterface;
	
	public void setBeforeInterface(BeforeInterface beforeInterface){
		this.beforeInterface = beforeInterface;
	}
	
	public <T> Object func(Class<T> clazz,String methodName,Object param1,Object param2,Object param3){
		return this.func(clazz, methodName, new Object[]{param1,param2,param3});
	}
	
	public <T> Object func(Class<T> clazz,String methodName,Object param1,Object param2){
		return this.func(clazz, methodName, new Object[]{param1,param2});
	}
	
	public <T> Object func(Class<T> clazz,String methodName,Object param1){
		return this.func(clazz, methodName, new Object[]{param1});
	}
	
	@SuppressWarnings("rawtypes")
	public <T> Object func(Class<T> clazz,String methodName,Object[] paramters){
		Object result = null;
		boolean hasParamter = false;
		if(paramters!=null){
			hasParamter = true;
		}
		try {
			Class[] paramterClazz = null;
			if(hasParamter){
				paramterClazz = new Class[paramters.length];
				for(int i=0;i<paramters.length;++i){
					paramterClazz[i] = Class.forName(paramters[i].getClass().getName());
				}
			}
			if(null!=beforeInterface){
				if(beforeInterface.setBeforeMethod(paramters)){
					Method m = null;
					if(hasParamter){
						m = clazz.getDeclaredMethod(methodName,paramterClazz);
					}else{
						m = clazz.getDeclaredMethod(methodName);
					}
					if(null!=m){
						result = m.invoke(clazz.newInstance(), paramters);
					}
				}
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	

}
