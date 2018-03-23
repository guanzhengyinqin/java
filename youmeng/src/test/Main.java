package test;

import apppush.Demo;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo demo = new Demo("597c1723c62dca6cbc00036e","tfdubtvkdznthzkwcnej1pciwyhzybpj");
		try {
			demo.sendAndroidBroadcast("测试", "测试","2017-08-03 15:53:30" );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
