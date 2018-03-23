package main;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Main_3 {

	public static void main(String[] args) {
	      List<String> names = new ArrayList<String>();
			
	      names.add("Google");
	      names.add("W3CSchool");
	      names.add("Taobao");
	      names.add("Baidu");
	      names.add("Sina");
			
	      //names.forEach(System.out::println);
	      Consumer<Object> c = new Consumer<Object>() {

			@Override
			public void accept(Object t) {
				// TODO Auto-generated method stub
				System.out.println(t);
			}
		};
		//names.forEach(c);
		
		names.forEach(t -> System.out.println(t));
		
		//Main_3 ma = new Main_3();
		//ma.say_2(1, "2",() -> System.out.println("a");() -> System.out.println("b"));
		
	}
	
	interface C{
		void say();
		void hello();
	}
	
	private void say_2(int a,String b,C c){
		c.say();
		c.hello();
	}
	
}
