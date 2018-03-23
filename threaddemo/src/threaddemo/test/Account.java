package threaddemo.test;

public class Account {
	
	private String accountNo;
	private double balance;
	//标识账户中是否已经有存款的旗标
	private boolean flag = false;
	public Account(){}
	public Account(String accountNo, double balance){
		this.accountNo = accountNo;
		this.balance = balance;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public synchronized void draw(double drawAmount){
		try{
			//如果flag为假，表明账户中还没有人存钱进去，则取钱方法阻塞
			if(!flag){
				wait();
			}else{
				//执行取钱
				System.out.println(Thread.currentThread().getName()+"取钱："+drawAmount);
				balance -= drawAmount;
				System.out.println("账户余额为："+balance);
				//将标识账户是否已有存款的旗标设为false；
				flag = false;
				notifyAll();
			}
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized void deposit(double depositAmount){
		try{
			//如果flag为真，表明账户中已有人存钱进去，则存钱方法阻塞
			if(flag){
				wait();
			}else{
				//执行存款
				System.out.println(Thread.currentThread().getName()+"存款："+depositAmount);
				balance += depositAmount;
				System.out.println("账户余额为："+balance);
				//将表示账户是否已有存款的旗标示为true
				flag = true;
				notifyAll();
			}
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
	}

}
