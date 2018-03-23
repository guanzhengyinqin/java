package thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {

	private int poolsize = 10;
	
	//工作线程
	private List<SimpleThread> threads = new ArrayList<SimpleThread>();
	
	public ThreadPool(){
		init();
	}
	
	public ThreadPool(int poolsize){
		this.poolsize = poolsize;
		init();
	}
	
	private void init(){
		for(int i = 0;i<poolsize;i++){
			SimpleThread thread = new SimpleThread(i);
			thread.setRunning(false);
			threads.add(thread);
			thread.start();
		}
	}
	
	public synchronized void execute(MyMethod myMethod){
		while(true){
			for(SimpleThread thread : threads){
				if(!thread.isRunning()){
					thread.setMyMethod(myMethod);
					thread.setRunning(true);
					return;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ThreadPool pool = new ThreadPool();
		for(int i=0;i<5;i++){
			pool.execute(new MyMethodImpl(i));
		}
	}
	
}
