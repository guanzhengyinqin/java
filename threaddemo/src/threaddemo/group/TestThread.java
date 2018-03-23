package threaddemo.group;

public class TestThread extends Thread {
	
	//提供指定线程名的构造器
	public TestThread(String name){
		super(name);
	}
	//提供指定线程名、线程组的构造器
	public TestThread(ThreadGroup group,String name){
		super(group,name);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 20; i++) {
			System.out.println(getName()+"线程的i变量"+i);
		}
	}

}
