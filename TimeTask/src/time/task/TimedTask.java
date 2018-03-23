package time.task;

abstract public class TimedTask {
	
    private Runnable runnable;
    
    /**
     * 开始任务
     * @param time 开始时间
     */
    abstract void startTask(long time);

    /**
     * 停止任务
     */
    abstract void stopTask();

    /**
     * 设置任务
     * @param runnable
     */
    public void setTask(Runnable runnable){
    	this.runnable = runnable;
    }
    
    

    public Runnable getRunnable() {
		return runnable;
	}

	/**
     * 执行任务
     */
    public void execute(){
        runnable.run();
    }

}

