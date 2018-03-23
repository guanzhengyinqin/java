package mina.test;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import mina.coding.ByteArrayCodecFactory;
import mina.handler.Handler;

public class Test {

	public static void main(String[] args) {
		/*
		//创建一个非阻塞的Server端socket，用NIO  
		IoAcceptor acceptor = new NioSocketAcceptor();
        //创建接受数据的过滤器  
        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
        //设定这个过滤器将一行一行的读取数据  
        //TextLineCodecFactory text = new TextLineCodecFactory();
        //text.
        chain.addLast("codec",new ProtocolCodecFilter(new ByteArrayCodecFactory()));// 指定编码过滤器  
        // 指定业务逻辑处理器  
        acceptor.setHandler(new Handler());  
        // 设置端口号  
        acceptor.setDefaultLocalAddress(new InetSocketAddress(9655));  
        try {
			acceptor.bind();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //启动监听  
        System.out.println("Mina Server is Listen on:"+9655); 
        */
		/*
		File f = new File("");
		try {
			System.out.println(f.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
        
	}
	
}
