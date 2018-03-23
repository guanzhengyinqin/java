package annotationdemo;

import annotationdemo.FruitColor.Color;

public class Apple {
	
	@FruitColor(name="苹果")
	private String appleName;
	
	@FruitColor(fruitColor=Color.BULE)
	private String appleColor;
	
	
	
	public String getAppleName() {
		return appleName;
	}



	public void setAppleName(String appleName) {
		this.appleName = appleName;
	}



	public String getAppleColor() {
		return appleColor;
	}



	public void setAppleColor(String appleColor) {
		this.appleColor = appleColor;
	}



	public static void main(String[] args) {
		Apple apple = new Apple();
		System.out.println(apple.appleColor);
	}

}
