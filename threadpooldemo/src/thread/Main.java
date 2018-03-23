package thread;

public class Main {

	public static void main(String[] args) {
		
		
		ThreadPool t = new ThreadPool(15);
		
		MyMethod m = new MyMethod(){
			@Override
			public void dosomething() {
				// TODO Auto-generated method stub
				System.out.println("干活");
			}
		};
		
		

		for (int i = 0; i < 10; i++) {
			t.execute(m);
		} 
		
		
		
	}
	
	
	
}
