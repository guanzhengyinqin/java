package aopdemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SomeHandler implements InvocationHandler {

	
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		//Object result = method.invoke(arg0, arg1)
		Object result = null;
		System.out.println(args);
		System.out.println("调用");
		result = method.invoke(proxy, args);
		return result;
	}
}
