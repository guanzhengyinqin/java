package bean;

import java.util.HashMap;
import java.util.Map;

public class Person {

	private double height;
	private double weight;
	private int sex;
	private String name;
	private Map<Object,Object> attribute = new HashMap<>();
	
	public void setAttribute(Object key,Object value){
		attribute.put(key, value);
	}
	
	public Object getAttribute(Object key){
		return attribute.get(key);
	}
	
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void say(){
		System.out.println("您好");
	}
	
	
}
