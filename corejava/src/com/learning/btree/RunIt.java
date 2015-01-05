package com.learning.btree;

public class RunIt {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] vals = {1,2,3,4};
		System.out.println(System.getenv());
		StringBuilder sb = new StringBuilder();
		for (int i: vals) {
			for(int ct=0; ct<i; ct++) {
				sb.append(i);
			}
			sb.append("\n");
		}
		System.out.println(sb);
		ThreadLocal<String> strLocal = new ThreadLocal<String>() {
			@Override
			protected String initialValue() {
				return "localValue";
			}
		};
		strLocal.get();
		
		int a1 = 130;
		//a1 >>= 3;
		String i1 = Integer.toBinaryString(a1);
		
		int a2 = 3;
		int a1copy = a1;
		int div = 0;
		while(a1copy > a2) {
			a1copy -= a2;
			div++;
		}
		System.out.println("division = "+div);
		changeBase(2, "1111", 10);
	}
	
	public static String changeBase(int b1, String num, int b2) {
		int nn = 0;
		for(int i=num.length()-1; i>=0; i--) {
			int digit = Character.getNumericValue(num.charAt(i));
			int p = num.length()-i-1;
			nn+=digit*Math.pow(b1, p);
		}
		System.out.println("nn "+nn);
		StringBuilder sb = new StringBuilder();
		while (nn > 0) {
			int bb2 = nn % b2;
			nn = nn / b2;
			sb.insert(0, bb2);
		}
		System.out.println("base "+b2+"="+sb);
		return sb.toString();
	}

}
