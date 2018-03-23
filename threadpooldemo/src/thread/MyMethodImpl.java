package thread;

public class MyMethodImpl implements MyMethod {

	private int count;
	
	public MyMethodImpl(int count){
		this.count = count;
	}
	
	@Override
	public void dosomething() {
		// TODO Auto-generated method stub
		System.out.println("mymethod"+count+"in thread pool!");
	}

}
