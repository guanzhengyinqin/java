package thread.runnablepool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadExcutor {
	//创建
	private volatile boolean running = true;
	
	//所有任务都放到队列中，让工作线程来消费
	private static BlockingQueue<Runnable> queue = null;
	
	private final HashSet<Worker> workers = new HashSet<>();
	
	private final List<Thread> threadList = new ArrayList<Thread>();

	//工作线程数
	private int poolSize = 0;
	
	//核心线程数（创建了多少个工作线程）
	private int coreSize = 0;
	
	boolean shutdown = false;
	
	public ThreadExcutor(int poolSize){
		this.poolSize = poolSize;
		queue = new LinkedBlockingQueue<Runnable>(poolSize);
	}
	
	public void exec(Runnable runnable){
		if(runnable == null) throw new NullPointerException();
		if(coreSize < poolSize){
			addThread(runnable);
		}else{
			try{
				queue.put(runnable);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}
	}
	
	public void addThread(Runnable runnable){
		coreSize ++;
		Worker worker = new Worker(runnable);
		workers.add(worker);
		Thread t = new Thread(worker);
		threadList.add(t);
		try{
			t.start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	class Worker implements Runnable{

		public Worker(Runnable runnable){
			queue.offer(runnable);
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true && running){
				if(shutdown == true){
					Thread.interrupted();
				}
				Runnable task = null;
				try{
					task = getTask();
					task.run();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		
		public Runnable getTask() throws InterruptedException{
			return queue.take();
		}
		
		public void interruptIfIdle(){
			for(Thread thread : threadList){
				System.out.println(thread.getName() + "interrupt");
				thread.interrupt();
			}
		}
		
		
	}
	
}






