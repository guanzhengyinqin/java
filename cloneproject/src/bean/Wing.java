package bean;

import java.io.Serializable;

public class Wing implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6155952068194347820L;
	
	private int width;
	public Wing(int width){
		this.width = width;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	

}
