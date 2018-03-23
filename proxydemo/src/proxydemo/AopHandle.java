package proxydemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import proxyinterface.AopMethod;

public class AopHandle implements InvocationHandler {
	
	//保存对象
	private AopMethod method;
	private Object o;
	
	public AopHandle(Object o,AopMethod method) {
		this.o=o;
		this.method=method;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		Object result = null;
		Object [] paramters = null;
		if(args.length>0){
			paramters = (Object[]) args[0];
		}
		if(this.method.before(proxy, method, paramters)){
			result = method.invoke(o, args);
		}
		this.method.after(proxy, method, paramters);
		return result;
	}

}
