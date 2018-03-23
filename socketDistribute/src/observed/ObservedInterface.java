package observed;

import observer.ObserverInterface;

/**
 * 被观察者
 * @author user
 *
 */
public interface ObservedInterface {
	
	void addObserver(ObserverInterface observer);
	
	void notification(Object obj);
	
	int observerSize();

}
