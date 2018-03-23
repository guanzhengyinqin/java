package minasocketdemo;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class Handler extends IoHandlerAdapter {

	//当一个客户端连接进入时  
    @Override  
    public void sessionOpened(IoSession session)throws Exception {
    	
    	//session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 15);  
        //session.setAttribute(Configure.cfg.KEYISALIVE, true);  
        //setIoSession(session); 
    	
        System.out.println("incoming client:"+session.getRemoteAddress());
        System.out.println("有客户端加入ID为"+session.getId());
        System.out.println("当前有客户端数量"+session.getService().getManagedSessionCount());
        //session.write("么么哒2");
        
        System.out.println(session.getLocalAddress().toString());
        
    }
    
    @Override  
    public void messageReceived(IoSession session, Object message)throws Exception {
    	String str = message.toString();
		//distribute(str);
		System.out.println("-------------------------------收到消息----------------------------");
		System.out.println(str);
		if("quit".equals(str)){
			System.out.println("**************重启服务************");
			//System.out.println();Main.socketServer.blinker.getName();
			Main.socketServer.stop();
			//SocketServer.blinker.interrupt();
			//session.close(true);
			//session.getService().dispose(true);
			//SocketServer.acceptor.dispose(true);
			//SocketServer.acceptor.
			//SocketServer.socketServerStart();
			//session.getService()
			//SocketServer.acceptor.
			//session.getService().
		}else if("thread".equals(str)){
			Thread[] threads = findAllThreads();
			for(Thread t:threads){
				System.out.println("线程名："+t.getName());
			}
		}
    }
    
    private Thread[] findAllThreads() {
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = group;
        // 遍历线程组树，获取根线程组
        while (group != null) {
            topGroup = group;
            group = group.getParent();
        }
        // 激活的线程数加倍
        int estimatedSize = topGroup.activeCount() * 2;
        Thread[] slacks = new Thread[estimatedSize];
         //获取根线程组的所有线程
        int actualSize = topGroup.enumerate(slacks);
        Thread[] threads = new Thread[actualSize];
        System.arraycopy(slacks, 0, threads, 0, actualSize);
        return threads;
    }
    
    
	
}
