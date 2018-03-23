package bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Swallow implements Cloneable,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4213650650166238462L;
	private String name;
	private Wing leftWing;
	private Wing rightWing;
	
	public Swallow(String name,Wing leftWing,Wing reghtWing){
		this.name = name;
		this.leftWing = leftWing;
		this.rightWing = reghtWing;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Wing getLeftWing() {
		return leftWing;
	}

	public void setLeftWing(Wing leftWing) {
		this.leftWing = leftWing;
	}

	public Wing getRightWing() {
		return rightWing;
	}

	public void setRightWing(Wing rightWing) {
		this.rightWing = rightWing;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		
		return super.clone();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "leftWing="+leftWing.getWidth()+",rightWing="+rightWing.getWidth()+",name="+name;
	}
	
	public Swallow deepClone() throws IOException,ClassNotFoundException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(this);
		
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bais);
		return (Swallow) ois.readObject();
	}

}
