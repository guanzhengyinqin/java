package demo.aop;

import java.net.Proxy;

public class AopTest {

	public static void main(String[] args) {
		new AopTest().test1();
	}
	
	
	public void test1(){
		//无日志记录功能
		IHello hello1 = new Hello();
		
		//有日志记录功能
		IHello hello2 = new ProxyHello(new Hello());
		
		hello1.sayHello("wallet white");
		
		System.out.println("-------------------------------");
		
		hello2.sayHello("wallet white");
		
		IHello hello3 = (IHello) new ProxyFactory(new Hello()).getProxyInstance();
		hello3.sayHello("哦");
		hello3.sayGoodBye("哦哦");
		
	}
	
}
