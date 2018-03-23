package test;

import java.io.IOException;

import bean.Swallow;
import bean.Wing;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Wing lw = new Wing(23);
		Wing rw = new Wing(25);
		Swallow s = new Swallow("1", lw, rw);
		
		Swallow s2 = (Swallow) s.deepClone();
		
		System.out.println(s.getLeftWing()==s2.getLeftWing());
		System.out.println(s.getRightWing()==s2.getRightWing());
		System.out.println(s==s2);
		System.out.println(s.toString());
		System.out.println(s2.toString());
		
	}

}
