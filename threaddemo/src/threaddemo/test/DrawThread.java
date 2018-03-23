package threaddemo.test;import org.omg.Messaging.SyncScopeHelper;

public class DrawThread extends Thread {

	//模拟用户账户
	private Account account;
	//当前取钱线程所希望取的钱数
	private double drawAmount;
	public DrawThread(String name,Account account,double drawAmount){
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;
	}
	//重复100执行取钱操作
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0;i<100;i++){
			
			account.draw(drawAmount);
			System.out.println(i);
			if(i==99){
				System.out.println(this.getName()+"取钱执行完毕！");
			}
		}
	}
	
	
	
}
