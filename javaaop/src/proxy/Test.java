package proxy;

import proxy.annon.AnnoInjection;

public class Test {

	public static void main(String[] args) {
		DogImp dogImp = new DogImp();
		dogImp.setName("王尼玛");
		dogImp.say();
		System.out.println(dogImp.getName());
		dogImp.getProperty();
		Object obj = AnnoInjection.getBean(dogImp);
		System.out.println(obj instanceof DogImp);
		
	}
}
