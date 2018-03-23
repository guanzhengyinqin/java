package mybatisdemo.main;

import java.util.function.Function;

import mybatisdemo.func.Inter;
import mybatisdemo.inter.Func1;

public class Main {
	
	public static void main(String[] args) {
		
		
		
		//Inter<String,Object,Integer,Boolean,Function<String,Integer>,StringBuffer> i = new Inter<>();
		
		
		
		
		/*
		te("hello",new Function<String, Integer>() {
			
			@Override
			public Integer apply(String t) {
				// TODO Auto-generated method stub
				System.out.println(t);
				return null;
			}
		});
		*/
		//Function a = (t) -> {System.out.println(t);return 3;};
		
		//a.apply("f");
		
		//System.out.println(te("hello",(String t) -> {System.out.println(t);return 2;}));
		//ta( ()-> System.out.println("a"));
	}
	
	interface A {
		void aa();
	}
	
	public static Integer te(String a,Function<String,Integer> f1){
		return f1.apply(a);
	}
	
	public static void ta(A a){
		a.aa();
	}

}
