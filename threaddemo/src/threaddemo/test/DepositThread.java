package threaddemo.test;

public class DepositThread extends Thread {
	
	//模拟用户账户
	private Account account;
	//模拟取钱线程所希望取款的钱数
	private double depositAmount;
	
	public DepositThread(String name,Account account,double depositAmount){
		super(name);
		this.account = account;
		this.depositAmount = depositAmount;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 100; i++) {
			account.deposit(depositAmount);
			System.out.println(i);
			if(i==99){
				System.out.println(this.getName()+"执行完毕！");
			}
		}
	}
	

}
