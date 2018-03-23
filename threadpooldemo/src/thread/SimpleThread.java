package thread;

public class SimpleThread extends Thread {

	private volatile boolean running;
	
	private volatile int threadNumber;
	
	private volatile MyMethod myMethod;
	
	public SimpleThread(){}
	
	public SimpleThread(int number){
		this.threadNumber = number;
		System.out.println("SimpleThread:"+threadNumber+" start!");
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public synchronized void setRunning(boolean running){
		this.running = running;
		if(running)
			this.notify();
	}
	
	public void setThreadNumber(int threadNumber){
		this.threadNumber = threadNumber;
	}
	
	public int getThreadNumber(){
		return threadNumber;
	}
	
	public synchronized void setMyMethod(MyMethod myMethod){
		this.myMethod = myMethod;
	}
	
	public MyMethod getMyMethod(){
		return myMethod;
	}
	
	public synchronized void dosomething(){
		if(getMyMethod()!=null)
			getMyMethod().dosomething();
	}
	
	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		try{
			while(true){
				if(!isRunning()){
					System.out.println("SimpleThread:"+threadNumber+"wait!");
					this.wait();
				}else{
					System.out.println("SimpleThread:"+threadNumber+"run");
					dosomething();
					Thread.sleep(500);
					setRunning(false);
				}
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
}
