package main;

public class Main_14 {
	
	public static void main(String[] args) {
		byte[] b = new byte[3];
		test(b);
		System.out.println(b.length);
	}
	
	public static void test(byte[] a){
		a = new byte[5];
	}

}


