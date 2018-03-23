package socketClient;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientTest {
	public ServiceFrame sf;
	private Socket csocket;
	private DataInputStream in;
	private DataOutputStream out;
	public static void main(String args[]){
		String who = "";
		//获得输入流
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//输出提示信息
		System.out.println("请输入你的名字");
		try{
			who = in.readLine().trim();
		}catch(Exception e){
			e.printStackTrace();
		}
		new ClientTest(who,"172.16.1.102",3848);
	}
	
	public ClientTest(String who,String server,int port){
		sf = new ServiceFrame("客户端聊天窗口");		//创建客户端窗体
		sf.sendFD.addKeyListener(new ActListener(this,sf));
		sf.addWindowListener(new ExitListener(this));
		try{
			csocket = new Socket(server,port);
			//输入流
			in = new DataInputStream(new BufferedInputStream(csocket.getInputStream()));
			out = new DataOutputStream(new BufferedOutputStream(csocket.getOutputStream()));
			out.writeUTF(who);
			out.flush();
			while(true){
				sf.showTA.append("--"+in.readUTF()+"\n");
			}
		}catch(Exception e){
			System.out.println("Server error");
			this.close();
			System.exit(0);
		}
	}
	
	protected void send(String msg){
		try{
			out.writeUTF(msg);
			out.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected void close(){
		try{
			sf.dispose();
			out.close();
			in.close();
			csocket.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}

class ServiceFrame extends Frame{
	public TextArea showTA;
	public TextField sendFD;
	public ServiceFrame(String winnm){
		super(winnm);
		setLayout(new BorderLayout());
		add("North",showTA = new TextArea());
		showTA.setEditable(false);
		add("South",sendFD = new TextField());
		pack();
		show();
		sendFD.requestFocus();
	}
}

class ActListener extends KeyAdapter{
	ClientTest client;
	ServiceFrame Sframe;
	public ActListener(ClientTest c,ServiceFrame sf){
		client = c;
		Sframe = sf;
	}
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			client.send(Sframe.sendFD.getText());
			Sframe.sendFD.setText("");
		}
	}
}

class ExitListener extends WindowAdapter{
	ClientTest client;
	public ExitListener(ClientTest c){
		client = c;
	}
	public void windowClosing(WindowEvent e){
		client.close();
		System.exit(0);
	}
}
