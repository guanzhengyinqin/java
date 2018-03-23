package threaddemo.callable;

import java.util.concurrent.Callable;

public class RtnThread implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		int i = 0;
		for (; i < 100; i++) {
			System.out.println(Thread.currentThread().getName()+"的循环变量i的值："+i);
		}
		//call()方法可以有返回值
		return i;
	}

}
