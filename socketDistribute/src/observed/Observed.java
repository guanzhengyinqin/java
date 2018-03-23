package observed;

import java.util.ArrayList;
import java.util.List;

import observer.ObserverInterface;

public class Observed implements ObservedInterface {

	List<ObserverInterface> observers;
	
	public Observed() {
		// TODO Auto-generated constructor stub
		observers = new ArrayList<>();
	}
	
	@Override
	public void addObserver(ObserverInterface observer) {
		// TODO Auto-generated method stub
		notification("有观察者加入");
		observers.add(observer);
	}


	public void faZuoye(){
		notification("发作业了");
	}

	@Override
	public int observerSize(){
		return observers.size();
	}
	
	@Override
	public void notification(Object obj) {
		// TODO Auto-generated method stub
		for(ObserverInterface o:observers){
			o.receiptNotification(obj);
		}
	}

}
