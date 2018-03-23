package socketClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	String ip;
	int port;
	Socket server = null;
	Boolean isConnected;
	
	public Client(String ip,int port){
		this.ip = ip;
		this.port = port;
	}
	
	public void connect() throws IOException{
		Socket server = new Socket(ip,port);
		//BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream(),"utf-8"));
		//PrintWriter out = new PrintWriter(server.getOutputStream());
		//BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));
		//server.sendUrgentData(0xFF);
		//if(server.isConnected()){
		//	isConnected = true;
		//}
		/*
		while(isConnected){
			//System.out.println("服务器信息："+in.readLine());
			System.out.println("请输入>");
			//String str = wt.readLine();
//			if(str.equals("quit")){
//				isConnected = false;
//			}
			//out.println(str);
			out.flush();
		}
		*/
		while(true){
			try {
				Thread.sleep(1000);
				if(checkConnection(server)){
					System.out.println("链接未中断");
					
					PrintWriter out = new PrintWriter(new OutputStreamWriter(server.getOutputStream(),"utf-8"));
					//String str = new String("阿什顿发撒的发生地方","utf-8");
					//out.println(str);
					out.flush();
					//out.close();
					System.out.println(out);
					
				}else{
					System.out.println("链接中断,尝试重连");
					connect();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		//out.close();
		//in.close();
		//wt.close();
		//server.close();
	}
	
	public boolean checkConnection(Socket socket){
		try{
			socket.sendUrgentData(0xFF);
			return true;
		}catch(Exception e){
			return false;
		}
		
	}
	
}
