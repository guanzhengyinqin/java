package minasocketdemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class SocketServer implements Runnable {
	public static NioSocketAcceptor acceptor;
	public Thread blinker;

	
	public void socketServerStart(){
		// TODO Auto-generated method stub
				System.out.println("================>[ServletContextListener]自动加载启动socket服务");  
				
				//创建一个非阻塞的Server端socket，用NIO  
				SocketServer.acceptor = new NioSocketAcceptor();
				
				//重用地址
				SocketServer.acceptor.setReuseAddress(true);
				//acceptor.
		        //创建接受数据的过滤器  
		        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
		        //设定这个过滤器将一行一行的读取数据  
		        //TextLineCodecFactory text = new TextLineCodecFactory();
		        //text.
		        chain.addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));// 指定编码过滤器  
		        // 指定业务逻辑处理器  
		        acceptor.setHandler(new Handler());  
		        // 设置端口号  
		        acceptor.setDefaultLocalAddress(new InetSocketAddress(9970));  
		        try {
					acceptor.bind();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //启动监听  
		        System.out.println("Mina Server is Listen on:"+9970); 
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		socketServerStart();
	}
	
	public void start() {
        blinker = new Thread(this);
        blinker.setName("socketServer1");
        System.out.println("SocketServer线程名：-->socketServer1");
        blinker.start();
    }
	
	 public void stop() {
		System.out.println("当前线程名："+Thread.currentThread().getName());
		System.out.println("终止线程。。。");
	    if(null!=blinker){
	    	System.out.println("终止线程："+Thread.currentThread().getName());
	    	Thread.currentThread().stop();
	    	System.out.println("blinker线程名："+blinker.getName());
	    	//blinker.interrupt();
	    	blinker.stop();
	    }
	 }
	 
	 public Thread getBlinker(){
		 return blinker;
	 }

	
	
}
