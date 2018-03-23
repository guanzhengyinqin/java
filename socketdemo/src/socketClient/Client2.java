package socketClient;

import java.io.*;  
import java.net.*;  
  
public class Client2   
{  
    Socket socket;  
    BufferedReader in;  
    PrintWriter out;  
  
    public Client2()   
    {  
        try   
        {  
            socket = new Socket("192.168.3.30", 21);
            BufferedReader line = null;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));  
            out = new PrintWriter(socket.getOutputStream(), true);  
            line = new BufferedReader(new InputStreamReader(System.in)); 
              while(true){
            	  String str = line.readLine();
                  out.println(str);  
                  System.out.println(in.readLine());
                   
                  if(str.equals("quit")){
                	  break;
                  }
              }
             
              line.close();  
              out.close();  
              in.close();
            socket.close();  
              
        } catch (Exception e) {  
        	e.printStackTrace();
        }  
    }  
  
    public static void main(String[] args)   
    {
        new Client2();  
    }
}  