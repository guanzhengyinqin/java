package test;

import java.lang.reflect.Method;

import proxydemo.ProxyFactory;
import proxyinterface.AopInterface;
import proxyinterface.AopMethod;

public class Main {

	public static void main(String[] args) {
		AopInterface a = ProxyFactory.getProxy(Person.class, new AopMethod() {
			
			@Override
			public boolean before(Object proxy, Method method, Object[] args) {
				// TODO Auto-generated method stub
				System.out.println("之前");
				for(Object o:args){
					System.out.println("参数："+o);
				}
				return true;
			}
			
			@Override
			public void after(Object proxy, Method method, Object[] args) {
				// TODO Auto-generated method stub
				System.out.println("之后");
			}
		});
		
		String par1 = "a";
		Integer par2 = 5;
		Object[] o = new Object[]{par1,par2};
		
		a.setObj(o);
	}
	
}
