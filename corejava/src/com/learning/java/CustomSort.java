package com.learning.java;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomSort {
	
	private static Map<Character, Integer> sortOrder = new HashMap<>();
	static{
		sortOrder.put('R', 0);
		sortOrder.put('W', 1);
		sortOrder.put('Q', 2);
		sortOrder.put('O', 3);
		sortOrder.put('J', 4);
		sortOrder.put('M', 5);
		sortOrder.put('V', 6);
		sortOrder.put('A', 7);
		sortOrder.put('H', 8);
		sortOrder.put('B', 9);
		sortOrder.put('S', 10);
		sortOrder.put('G', 11);
		sortOrder.put('Z', 12);
		sortOrder.put('X', 13);
		sortOrder.put('N', 14);
		sortOrder.put('T', 15);
		sortOrder.put('C', 16);
		sortOrder.put('I', 17);
		sortOrder.put('E', 18);
		sortOrder.put('K', 19);
		sortOrder.put('U', 20);
		sortOrder.put('P', 21);
		sortOrder.put('D', 22);
		sortOrder.put('Y', 23);
		sortOrder.put('F', 24);
		sortOrder.put('L', 25);
	}
	static class Comp implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			int cl = Math.min(o1.length(), o2.length());
			for(int i=0; i<cl; i++) {
				Integer c1 = sortOrder.get(o1.charAt(i));
				Integer c2 = sortOrder.get(o2.charAt(i));
				if(!c2.equals(c1)) {
					return c1.compareTo(c2);
				}
			}
			return Integer.valueOf(o1.length()).compareTo(Integer.valueOf(o2.length()));

		}
		
	}
	
	public static void main(String[] args) {
		List<String> strlist = Arrays.asList(new String[]{"HENRY","TRUMAN","RON","HALL","COMP","STRING"});
		Collections.sort(strlist, new Comp());
		for(String res : strlist) {
			System.out.println(res);
		}
	}

}
