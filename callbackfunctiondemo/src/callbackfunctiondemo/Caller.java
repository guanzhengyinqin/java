package callbackfunctiondemo;

public class Caller {
	private MyCallInterface mc;
	public Caller(){}
	public void setCallfuc(MyCallInterface mc){
		this.mc = mc;
		//call();
	}
	public void call(){
		mc.fuc();
	}
}
