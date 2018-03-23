package main;

import funcclass.For;
import funcclass.For.Iteration;

public class Main_2 {

	public static void main(String[] args) {
			Main_2 tester = new Main_2();
			
	      // 类型声明
//	      MathOperation addition = (int a, int b) -> a + b;
			
			
			String[] e = new String[]{};
			
			For.$(e, new Iteration(){

				@Override
				public boolean iteration(Object currentValue, int index) {
					return false;
				}
				
			});
			

			String[] f = new String[]{};
			For.$(f,(currentValue, index)-> 
				{int c = 1;
				int d = 2;
				return false;}
					);
			
	      MathOperation addition = new MathOperation(){

			@Override
			public int operation(int a, int b) {
				return a + b;
			}
	    	  
	      };
	      
	      
			
	      // 不用类型声明
	      MathOperation subtraction = (a, b) -> a - b;
			
	      // 大括号中的返回语句
	      MathOperation multiplication = (int a, int b) -> { return a * b; };
			
	      // 没有大括号及返回语句
	      MathOperation division = (int a, int b) -> a / b;
			
	      System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
	      System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
	      System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
	      System.out.println("10 / 5 = " + tester.operate(10, 5, division));
			
	      // 不用括号
	      GreetingService greetService1 = message ->
	      System.out.println("Hello " + message);
			
	      // 用括号
	      GreetingService greetService2 = (message) ->
	      System.out.println("Hello " + message);
			
	      greetService1.sayMessage("W3CSchool");
	      greetService2.sayMessage("Google");
	   }
		
	  
	 	interface MathOperation {
	      int operation(int a, int b);
	   }
		
	   interface GreetingService {
	      void sayMessage(String message);
	   }
		
	   private int operate(int a, int b, MathOperation mathOperation){
	      return mathOperation.operation(a, b);
	   }
	
}
