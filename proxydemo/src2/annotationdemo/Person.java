package annotationdemo;

public class Person implements BeforInterface {

	@BeforRun
	public String print(String str){
		System.out.println(str);
		return str;
	}


	@Override
	public boolean beforMethod(Object[] parameters) {
		// TODO Auto-generated method stub
		System.out.println("interface参数"+parameters);
		for(Object o:parameters){
			System.out.println("参数"+o);
		}
		return true;
	}

}
