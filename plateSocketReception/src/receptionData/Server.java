package receptionData;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;
class ServerThread extends Thread
{
	private Socket ThisSocket=null;
	private PrintWriter output=null;
	private DataInputStream input=null;
	public ServerThread(Socket This) throws IOException
	{
		ThisSocket=This;			
		output=new PrintWriter(ThisSocket.getOutputStream());
		input=new DataInputStream(ThisSocket.getInputStream());
	}
    
	public void run()
	{
		String recv;
		try
		{
			System.out.println("客户端发来信息:");
			getImg(input,Math.round(MAX_PRIORITY));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				input.close();
	            output.close();
	            Server.socketgroup.remove(ThisSocket);
				ThisSocket.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
    public static void getImg(InputStream in,int time) throws IOException {
    	OutputStream o = null;
		byte[] lData = new byte[4];
		in.read(lData);
		int length = byteArrayToInt(lData);
		byte[] tmp = new byte[254];
	    int len = 0;
	    
	    File of = new File("D:/R"+ time++ +".jpg");
	    of.createNewFile();
	    o = new FileOutputStream(of);
	    
	    System.out.println("新建文件:"+of.getPath());
	    
		while((len=in.read(tmp)) != -1) {
			o.write(tmp);
		}
		o.flush();
		o.close();
    }

    private static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }
}
public class Server
{
	private ServerSocket server;
	public static ArrayList<Socket> socketgroup;
	public static final int port=9876;
	public Server() throws IOException
	{
		server=new ServerSocket(port);
		socketgroup=new ArrayList<Socket>();
	}
	public void running() throws IOException
	{
		while(true)
		{
			Socket connection=server.accept();
			socketgroup.add(connection);
			new ServerThread(connection).start();
		}
	}
	public static void main(String[] args) throws IOException
	{
		new Server().running();
	}
}