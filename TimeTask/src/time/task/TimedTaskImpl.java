package time.task;

public class TimedTaskImpl extends TimedTask {
	
	//boolean flag ;
	Thread thread;
	
	

	@Override
	void startTask(long time) {
		// TODO Auto-generated method stub
		try {
			
			thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(time);
						
						/*
						while(true){
							Thread.sleep(1000);
							
						}
						*/
						
						
						//dosomething
						
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thread.start();
		
	}

	@Override
	void stopTask() {
		// TODO Auto-generated method stub
		thread.interrupt();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		thread.start();
	}

}
