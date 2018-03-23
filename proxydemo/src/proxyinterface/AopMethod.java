package proxyinterface;

import java.lang.reflect.Method;

public interface AopMethod {

	//实例方法执行后执行的方法
	void after(Object proxy, Method method, Object[] args);
	//实例方法执行前执行的方法
	boolean before(Object proxy, Method method, Object[] args);
	
}
