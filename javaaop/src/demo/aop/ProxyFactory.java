package demo.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
	//维护一个目标对象
	private Object target;
	public ProxyFactory(Object target){
		this.target = target;
	}
	
	//给目标对象生成代理对象
	public Object getProxyInstance(){
		Object obj = Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), 
				new Handler(target));
		return obj;
	}
}

class Handler implements InvocationHandler{
	private Object target;
	public Handler(Object target){
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.out.println("开始");
		Object returnValue = method.invoke(target, args);
		System.out.println("结束");
		
		return returnValue;
	}
	
}
