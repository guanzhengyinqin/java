package main;

import java.util.Arrays;
import java.util.Random;

public class Main_6 {
	
	public static void main(String[] args) {
		/*
		System.out.println("bxLdikRXVbTPdHSM05e5u5sUoXNKd8".length());
		System.out.println("41ZO3MhKoyN5OfkWITDGgnr2fwJ0m9E8NYzWKVZvdVtaUgWvsdshFKA".length());
		*/
		/*
		System.out.println(generateRandomStr(30)+"-"+generateRandomStr(55));
		System.out.println("BCNXwofqdfTZgV6sVZVsF4Cy2BMPRR".length());
		System.out.println("2GPATSjDCtxaR1YZJWmR6lvP2pLv10ex5ryyFOeEqcB00XDPM4tovcf".length());
		*/
		/*
		String[] str = {"noncestr","api_ticket","timestamp","url"};
		Arrays.sort(str);
		for (String s : str) {
			System.out.print(s+"\t");
		}
		*/
		System.out.println(generateRandomStr(16));
		
	}
	
	public static String generateRandomStr(int digit){
		String baseStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		int scope = baseStr.length();
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < digit; i++) {
			int b = random.nextInt(scope);
			sb.append(baseStr.substring(b, b+1));
		}
		return sb.toString();
	}

}
