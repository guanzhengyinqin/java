package demo;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class NServer {

	//用于检测所有Channel状态的Selector
	private Selector selector = null;
	private List<SocketChannel> socketClients = new ArrayList();
	DataOutputStream sout;
	
	//定义实现编码、编码的字符集对象
	private Charset charset = Charset.forName("utf-8");
	public void init() throws IOException{
		selector = Selector.open();
		//通过open方法来打开一个未绑定的ServerSocketChannel实例
		ServerSocketChannel server = ServerSocketChannel.open();
		
		InetSocketAddress isa = new InetSocketAddress(9967);
		
		//将该ServerSocketChannel绑定到指定IP地址
		server.socket().bind(isa);
		
		//设置ServerSocket以非阻塞方式工作
		server.configureBlocking(false);
		
		
		
		
		
		
		//将server注册到指定Selector对象
		server.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("0");
		
		while(selector.select() > 0){
			System.out.println("1");
			//一次处理selector上的每个已选择的SelectionKey
			for (SelectionKey sk : selector.selectedKeys()) {
				
				
				
				//从selector上的已选择Key集中删除正在处理的SelectionKey
				selector.selectedKeys().remove(sk);
				
				//如果sk对应的通道包含客户端的链接请求
				if(sk.isAcceptable()){
					//调用accept方法接受链接，产生服务器端对应的SocketChannel
					SocketChannel sc = server.accept();
					//sc.socket().getInetAddress()
					//设置采用非阻塞模式
					sc.configureBlocking(false);
					//将该SocketChannel也注册到selector
					sc.register(selector, SelectionKey.OP_READ);
					socketClients.add(sc);
					//将sk对应的Channel设置成准备接受其他请求
					sk.interestOps(SelectionKey.OP_ACCEPT);
					
					printClients();
				}
				
				//如果sk对应的通道有数据需要读取
				if(sk.isReadable()){
					//获取该SelectionKey对应的Channel，该Channel中有可读的数据
					SocketChannel sc = (SocketChannel)sk.channel();
					//System.out.println(sc.);
					//定义准备执行读取数据的ByteBuffer
					ByteBuffer buff = ByteBuffer.allocate(512);
					ByteBuffer sendb = ByteBuffer.wrap(new byte[]{(byte) 0xff});
					String content = "";
					//开始读取数据
					try{
						while(sc.read(buff) > 0){
							buff.flip();
							byte[] by = new byte[buff.limit()-buff.position()];
							buff.get(by);
							System.out.println();
							
							
							if(null==sout){
								File f = new File("D:\\nioStreamTest\\receive\\c.jpg");
								if(!f.exists()){
									f.createNewFile();
								}
								sout = new DataOutputStream(new FileOutputStream(f));
							}
							sout.write(by);
							
							System.out.println();
							//content += charset.decode(buff);
						}
						//sc.write(sendb);
						
					}catch(Exception e){
						e.printStackTrace();
						sk.cancel();
						if(sk.channel()!=null){
							sk.channel().close();
						}
						//e.printStackTrace();
					}finally{
						/*
						if(null!=sout)
							sout.close();
						*/
					}
					//如果content的长度大于0，即聊天信息不为空
					if(content.length() > 0){
						System.out.print(content);
						//遍历该selector里注册的所有SelectionKey
						for(SelectionKey key : selector.keys()){
							//获取该可以对应的Channel
							Channel targetChannel = key.channel();
							//如果该Channel是SocketChannel对象
							if(targetChannel instanceof SocketChannel){
								//将读到的内容写入该Channel中
								SocketChannel dest = (SocketChannel)targetChannel;
								dest.write(charset.encode(content));
							}
						}
					}
				}
			}
			
		}
		System.out.println("2");
		
	}
	
	
	public void printClients(){
		System.out.println("--------------------socket列表--------------------");
		for (SocketChannel socketChannel : socketClients) {
			try {
				System.out.println(socketChannel.socket().getInetAddress());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("####################socket列表####################");
	}
	
	public static void main(String[] args)throws IOException{
		new NServer().init();
	}
	
	
}
