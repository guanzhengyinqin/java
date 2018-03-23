package socketClient;

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintWriter;  
import java.net.ServerSocket;  
import java.net.Socket;  
  
public class Service   extends Thread
{  
    private static ServerSocket ss;  
    private Socket socket;  
    private BufferedReader in;  
    private PrintWriter out;   
    
    public Service(){
    	try {
			ss = new ServerSocket(9999);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Override
	public void run() {
    	try   
        {  
              
            System.out.println("The server is waiting your input...");  
              
            while(true)   
            {  
            	
                socket = ss.accept();
                //new Service().start();
                System.out.println("链接开始");
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));  
                out = new PrintWriter(socket.getOutputStream(), true);  
                String line = in.readLine();  
                  
                System.out.println("you input is : " + line);  
                  
                //out.println("you input is :" + line);  
                 out.print("来自服务器的消息：");
                out.close();  
                in.close();  
                if(!socket.isClosed()){
                	 socket.close();
                }
                if(line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit"))  
                    break;  
            }  
              
            ss.close();  
              
        } catch (IOException e) {
            e.printStackTrace();
        } 
	}


	public static void main(String[] args)   
    {  
        new Service().start();
    }  
} 