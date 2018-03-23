package observer;

public class Person {
	
	private OnListenerClass onListenerClass;
	
	public Person(){}
	
	public Person(OnListenerClass onListenerClass){
		this.onListenerClass = onListenerClass;
	}
	
	
	
	public void setOnListenerClass(OnListenerClass onListenerClass) {
		this.onListenerClass = onListenerClass;
	}



	public void sayHello(){
		System.out.println("hello");
		onListenerClass.print();
	}

}
