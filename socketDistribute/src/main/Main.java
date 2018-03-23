package main;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.List;

import annotation.Person;
import annotation.SocketDispose;
import annotation.SocketDistribute;
import observed.Observed;
import observed.ObservedInterface;
import observer.ObserverInterface;
import observer.StudentA;
import observer.StudentB;
import util.ClassUtil;

public class Main {

	public static void main(String[] args) {
		
		/*
		ObservedInterface ob = new Observed();
		ob.addObserver(new StudentA());
		ob.addObserver(new StudentA());
		ob.addObserver(new StudentB());
		ob.addObserver(new StudentA());
		
		Observed o = (Observed) ob;
		o.faZuoye();
		System.out.println(ob.observerSize());
		*/
		
		/*
		try {
			List<Class<?>> clazzes = ClassUtil.getClassesByAnnotationType(SocketDispose.class,"observer");
			for(Class<?> c:clazzes){
				//System.out.println(c.getAnnotation(SocketDispose.class));
				//System.out.println(c.getAnnotation(SocketDistribute.class));
				System.out.println(c.getName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/*
		try{
			
			String s = "bbb";
	    	//System.out.println(s.toString());
			Method m = s.getClass().getMethod("toString", null);
	    	
			
			System.out.println(m.invoke(s,null));
		}catch(Exception e){
			e.printStackTrace();
		}
		*/
		/*
		try{
			
			//Person p = new Person();
	    	//System.out.println(s.toString());
			
			
			Class<?> c = Class.forName("annotation.Person");
	    	Method m = c.getMethod("print", Class.forName("java.lang.String"));
	    	Class<?>[] mcs = m.getParameterTypes();
	    	for(Class mc:mcs){
	    		System.out.println(mc.getName());
	    	}
			m.invoke(c.newInstance(),"");
			
		}catch(Exception e){
			
		}
		*/
		
		/*
		Object[] o = new Object[2];
		o[0] = 1;
		o[1] = "2";
		Object a = 1;
		
		int b = Integer.valueOf(a.toString()).intValue();
		*/
		//System.out.println("1-2-3".split("-")[1]);
		//System.out.println("1|4|6".split("\\|")[2]);
		
		String[] a = {"a","b","c"};
		String b = "d";
		boolean c = false;
		for(String s:a){
			if(s.equals(b)){
				c = true;
			}
		}
		System.out.println(c);
		
		/*
		System.out.println(a.getClass().getName());
		System.out.println();
		System.out.println(int.class.getName());
		*/
		//System.out.println(Boolean.valueOf("true"));
		
	}
	
}
