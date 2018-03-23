package observer;

public class Main {

	public static void main(String[] args) {
		Person p = new Person(new OnListenerClass(){
			@Override
			public void print() {
				// TODO Auto-generated method stub
				super.print();
			}
		});
		p.sayHello();
	}
}
