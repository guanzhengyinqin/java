package test;

import proxyinterface.AopInterface;

public class Person implements AopInterface {

	@Override
	public Object setObj(Object[] obj) {
		// TODO Auto-generated method stub
		for(Object o:obj){
			System.out.println(o);
		}
		return null;
	}


}
