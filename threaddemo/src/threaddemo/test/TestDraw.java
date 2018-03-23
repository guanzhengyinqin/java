package threaddemo.test;

public class TestDraw {
	
	public static void main(String[] args) {
		//创建一个账户
		Account acc = new Account("1234567",0);
		new DrawThread("取钱者1",acc,800).start();
//		new DrawThread("取钱者2",acc,1000).start();
		new DepositThread("存钱者甲",acc,800).start();
		new DepositThread("存钱者乙",acc,800).start();
		new DepositThread("存钱者丙",acc,800).start();
		
		
	}

}
