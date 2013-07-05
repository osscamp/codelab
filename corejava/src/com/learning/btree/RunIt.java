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

	}

}
