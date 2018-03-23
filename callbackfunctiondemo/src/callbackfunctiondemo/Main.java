package callbackfunctiondemo;

public class Main  {
	
	public static void main(String[] args) {
		Caller call = new Caller();
		call.setCallfuc(new MyCallInterface() {
			
			@Override
			public void fuc() {
				// TODO Auto-generated method stub
				System.out.println("hello");
			}
		});
		
		call.call();
		
	}
	
	
}
