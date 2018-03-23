package bean;

import java.lang.reflect.Method;

public class MethodInfo {

	private Method method;
	private int index;
	
	
	public Method getMethod() {
		return method;
	}
	public MethodInfo setMethod(Method method) {
		this.method = method;
		return this;
	}
	public int getIndex() {
		return index;
	}
	public MethodInfo setIndex(int index) {
		this.index = index;
		return this;
	}
	
	
	
}
