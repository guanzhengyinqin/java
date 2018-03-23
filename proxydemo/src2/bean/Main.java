package bean;

import proxyinterface.BeforeInterface;

public class Main {
	
	public static void main(String[] args) {
		
		Person p = new Person();
		p.setBeforeInterface(new BeforeInterface() {
			
			@Override
			public boolean setBeforeMethod(Object[] paramters) {
				// TODO Auto-generated method stub
				System.out.println("之前执行");
				System.out.print("参数：");
				for(Object o:paramters){
					System.out.print(o+"\t");
				}
				System.out.println();
				return true;
			}
		});
		
		
		Object a = p.func(p.getClass(), "print", "b");
		System.out.println("返回值："+a);
		
	}

}
