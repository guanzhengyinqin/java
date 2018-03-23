package threaddemo.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
	
	//显示定义Lock对象
	private final Lock lock = new ReentrantLock();
	//获取指定Lock对象对应的条件变量
	private final Condition cond = lock.newCondition();
	
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
	
	public void draw(double drawAmount){
		//加锁
		lock.lock();
		try{
			//如果账户中还没有存入存款，该线程等待
			if(!flag){
				cond.await();
			}else{
				//执行取钱
				System.out.println(Thread.currentThread().getName()+"取钱："+drawAmount);
				balance -= drawAmount;
				System.out.println("账户余额为："+balance);
				//将标识账户是否已有存款的旗标设为false；
				flag = false;
				//唤醒该Lock对象对应的其他线程
				cond.signalAll();
			}
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}finally{
			//使用finally块来确保释放锁
			lock.unlock();
		}
	}
	
	public void deposit(double depositAmount){
		lock.lock();
		try{
			//如果账户中已经存入了存款，该线程等待
			if(flag){
				cond.await();
			}else{
				//执行存款操作
				System.out.println(Thread.currentThread().getName()+"存款："+depositAmount);
				balance += depositAmount;
				System.out.println("账户余额为："+balance);
				//将表示账户是否已有存款的旗标示为true
				flag = true;
				//唤醒该Lock对象对应的其他线程
				cond.signalAll();
			}
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}finally{
			//使用finally块来确保释放锁
			lock.unlock();
		}
	}

}
