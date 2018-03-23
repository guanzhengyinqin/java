package time.task;

public class Test {
	
	public static void main(String[] args) {
		TimedTaskImpl t = new TimedTaskImpl();
		t.startTask(2000);
		
		for(int i=0;i<100;++i){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(i);
			switch(i){
			case 10:
				t.stopTask();
				break;
			case 20:
				t.startTask(1);
				break;
			case 30:
				t.stopTask();
				break;
			case 40:
				t.startTask(1);
				break;
			case 50:
				t.stopTask();
				break;
			case 60:
				t.startTask(1);
				break;
			case 70:
				t.stopTask();
				break;
			case 80:
				t.startTask(1);
				break;
			case 90:
				t.stopTask();
				break;
			case 99:
				t.startTask(1);
				break;
			}
		}
	}
	
	
}
