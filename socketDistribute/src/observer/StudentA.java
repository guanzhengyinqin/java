package observer;

import annotation.SocketDispose;

@SocketDispose
public class StudentA implements ObserverInterface {

	@Override
	public void receiptNotification(Object obj) {
		// TODO Auto-generated method stub
		System.out.println(obj);
	}

}
