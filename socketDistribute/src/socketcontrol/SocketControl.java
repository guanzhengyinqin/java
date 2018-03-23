package socketcontrol;

import annotation.Person;
import annotation.SocketDispose;
import annotation.SocketDistribute;

@SocketDispose
public class SocketControl {

	@SocketDistribute("1")
	public void pr1(String a,double b,int c,float d){
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		System.out.println("pr1");
	}
	
	@SocketDistribute("2")
	public void pr2(float p,int a,String b,String d){
		System.out.println(p);
		System.out.println(a);
		System.out.println(b);
		System.out.println(d);
		System.out.println("pr2");
	}
	
	@SocketDistribute("3")
	public void pr3(int c){
		System.out.println(c);
		System.out.println("pr3");
	}
	
}
