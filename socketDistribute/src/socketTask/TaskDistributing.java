package socketTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import annotation.SocketDispose;
import annotation.SocketDistribute;
import bean.MethodInfo;
import exception.ParameterFormateException;
import observer.ObserverInterface;
import util.ClassUtil;

public class TaskDistributing implements ObserverInterface {

	private static Object[] objects;
	private Map<String,MethodInfo> methods;
	private Socket socket;
	private ServerSocket serverSocket;
	
	//private static Object c = "2|4";
	
	
	
	

	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
		init();
	}

	@SuppressWarnings("unused")
	private void setRunnable(){
		startRead(new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					BufferedReader in;
					PrintWriter out;
					try {
						socket = serverSocket.accept();
						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						out = new PrintWriter(socket.getOutputStream(), true);
						while(true){
							String line = in.readLine();
							receiptNotification(line);
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}));
	}
	
	@Override
	public void receiptNotification(Object obj) {
		// TODO Auto-generated method stub
		String instruct = obj.toString().split("-")[0];
		MethodInfo methodInfo = methods.get(instruct);
		try{
			invokeByParameter(methodInfo.getMethod(),objects[methodInfo.getIndex()],obj.toString().split("-")[1]);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void init(){
		try{
			methods = new HashMap<>();
			
			List<Class<?>> annotationClasses = ClassUtil.getClassesByAnnotationType(SocketDispose.class, "socketcontrol");
			if(annotationClasses!=null){
				if(annotationClasses.size()>0){
					
					objects = new Object[annotationClasses.size()];
					for(int i=0;i<objects.length;i++){
						objects[i] = annotationClasses.get(i).newInstance();
						Method[] ms = annotationClasses.get(i).getDeclaredMethods();
						for(Method m:ms){
							if(m.isAnnotationPresent(SocketDistribute.class)){
								SocketDistribute s = m.getAnnotation(SocketDistribute.class);
								String instruct = s.value();
								methods.put(instruct,new MethodInfo().setIndex(i).setMethod(m));
							}
							
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		setRunnable();
	}
	
	/**
	 * 调用方法
	 * @param m			方法
	 * @param obj		调用方法依赖的对象
	 * @param actualPar	实参
	 * @throws Exception
	 */
	public void invokeByParameter(Method m,Object obj,Object actualPar) throws Exception{
		Class<?>[] parameters = m.getParameterTypes();
		if(parameters.length==0){
			m.invoke(obj, null);
		}else{
			Object[] par = getActualParameters(actualPar, "\\|", parameters);
			//System.out.println(m.getName());
			//System.out.println(obj.toString());
			//System.out.println(par.length);
			/*
			for(Object o:par){
				System.out.println("参数："+o.toString());
			}
			*/
			m.invoke(obj, par);
			
		}
	}
	
	/**
	 * 获取实参
	 * @param msg			实参
	 * @param division		分割参数
	 * @param parameters	形参类型列表
	 * @return	实参列表
	 */
	public Object[] getActualParameters(Object msg,String division,Class<?>[] parameters){
		Object[] result = new Object[parameters.length];
		try{
			EqualsAction eq = new EqualsActionImplement();
			String[] types = {"boolean|java.lang.Boolean","int|java.lang.Integer","double|java.lang.Double","float|java.lang.Float","long|java.lang.Long","byte|java.lang.Byte","short|java.lang.Short","char|java.lang.Character","java.lang.String|java.lang.String"};
			boolean result4 = true;
			if(null!=division){
				String[] ps = msg.toString().split(division);
				
				for(int i=0;i<parameters.length;i++){
					String parName = parameters[i].getName();
					if(i<ps.length){
						if(!judgeEqualsAction(eq,parName, types,i,result,ps[i])){
							result4 = false;
						}
					}
				}
			}else{
				for(int i=0;i<parameters.length;i++){
					String parName = parameters[i].getName();
					if(!judgeEqualsAction(eq,parName, types,i,result,msg)){
						result4 = false;
					}
				}
			}
			if(!result4){
				throw new ParameterFormateException("Parameter types that can not be defined");
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		
		return result;
	}
	
	
	interface EqualsAction{
		
		/**
		 * 
		 * @param o
		 * @param index
		 * @param result
		 * @param actualParameter
		 * @throws Exception
		 */
		void equalsAction(String o,int index,Object[] result,Object actualParameter) throws Exception;
	}
	
	private class EqualsActionImplement implements EqualsAction{

		@Override
		public void equalsAction(String o,int index,Object[] result,Object actualParameter) throws Exception {
			// TODO Auto-generated method stub
			Class<?> s = Class.forName(o);
			Method m = null;
			if(!("java.lang.String".equals(o))){
				m = s.getMethod("valueOf", java.lang.String.class);
			}else{
				m = s.getMethod("valueOf", java.lang.Object.class);
			}
			if(null!=m){
				result[index] = m.invoke(null, actualParameter);
			}
			
		}
		
	}
	
	/**
	 * 
	 * @param action			得到参数类型后执行的方法
	 * @param equalsArgs		要比较的基本数据类型
	 * @param args				需要比较的参数类型列表
	 * @param index				当前参数类型下标
	 * @param result			拆分后的实际参数列表
	 * @param actualParameter	实际参数
	 * @return					是否有不符合规定的参数
	 * @throws Exception
	 */
	public boolean judgeEqualsAction(EqualsAction action,String equalsArgs,String[] args,int index,Object[] result,Object actualParameter) throws Exception{
		boolean flag = false;
		if(null!=equalsArgs){
			for(String o:args){
				if(equalsArgs.equals(o.split("\\|")[0])){
					action.equalsAction(o.split("\\|")[1],index,result,actualParameter);
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
	
	
	public void startRead(Thread run){
		run.start();
	}
	
	public static void main(String[] args) {
		
		
		TaskDistributing t = new TaskDistributing();
		
		try {
			t.setServerSocket(new ServerSocket(9956));
			//t.init();
			//t.receiptNotification("1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		try {
			Class c = Class.forName("socketcontrol.SocketControl");
			Method m = c.getMethod("pr1", new Class[]{String.class,double.class,int.class,float.class});
			Double d = null;
			Integer i = null;
			Float f = null;
			m.invoke(c.newInstance(), new Object[]{"12",d,i,f});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
	}
	
	
	
	
	

}
