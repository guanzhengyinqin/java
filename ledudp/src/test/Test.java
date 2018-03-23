package test;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Test {
	
	public static void main(String[] args) throws Exception {
		
		UdpClient client = new UdpClient("192.168.3.230", 5050,(data)->{
			//System.out.println(new String(data));
		});
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MM dd  HH:mm:ss");
		
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					//System.out.println("执行..");
					client.send(dateFormat.format(new Date()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, 1000l,1000l);
		
				
	}
	

}
