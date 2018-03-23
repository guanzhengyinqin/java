package proxydemo;

import java.lang.reflect.Proxy;
import proxyinterface.AopMethod;

public class ProxyFactory {
	
	/***
	 * 获取对象方法
	 * @param obj
	 * @return
	 */
	private static Object getObjBase(Object obj,AopMethod method){
		//获取代理对象
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), 
				obj.getClass().getInterfaces(), 
				new AopHandle(obj,method));
	}
	
	/***
	 * 获取对象方法
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getProxy(Object obj,AopMethod aopMethod){
		return (T) getObjBase(obj,aopMethod);
	}
	/***
	 * 获取对象方法
	 * @param className
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static  <T> T getProxy(String className,AopMethod method){
		Object obj=null;
		try {
			obj= getObjBase(Class.forName(className).newInstance(),method);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T)obj;
	}

	/***
	 * 获取对象方法
	 * @param clz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static  <T> T  getProxy(Class<?> clz,AopMethod method){
		Object obj=null;
		try {
			obj= getObjBase(clz.newInstance(),method);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T)obj;
	}

}
