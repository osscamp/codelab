package com.learning.btree;

import java.util.HashMap;
import java.util.Map;

public class Zig {
	
	static final char[] sym = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	
	public static String replaceSpace(String str) {
		if(str == null || str.isEmpty()) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		for(int i=0; i<sb.length(); i++) {
			if(sb.charAt(i) == ' ') {
				sb.insert(i++, '%') ;
				sb.insert(i++, '2') ;				
				sb.insert(i++, '0') ; 
				sb.deleteCharAt(i--);
			}
		}
		return sb.toString();
	}
	
	public static String convertBin(int n) {
		int rem = n%2;
		StringBuilder result = new StringBuilder();
		if(n-rem == 0) {
			result.append(rem); 
		}
		else {
			result.append(convertBin((n-rem)/2)).append(rem);
		}
		return result.toString();
	}
	
	public static void floorCeil() {
		int[] a = {5,7,11,14,17,21,26,30};
		int n =32;
		int l = 0;
		int r = a.length-1;
		int floor = 0;
		int ceil = 0;
		while(l<=r) {
			int mid = l+(r-l)/2;
			if(n<a[mid]) {
				r=mid-1;
			}else if(n>a[mid]) {
				l=mid+1;
			} else {
				floor = a[mid];
				ceil = floor;
				break;
			}
		}
		r = Math.max(0, Math.min(a.length-1,r));
		l = Math.max(0, Math.min(a.length-1,l));
		floor = a[r];
		ceil = a[l];
		System.out.println("flr "+floor+"ceil "+ceil);
	}
	
	public static String longestCommonPrefix(String[] strs) {
		StringBuilder common = new StringBuilder();
		boolean breakloop = false;
		for(int i=0; !breakloop ;i++)
		{
			char prev = ' ';
			int j = 0;
			for(j=0; j<strs.length; j++) {
				String s = strs[j];
				if(s.length() <= i) {
					breakloop = true;
					break;
				}
				char c = s.charAt(i);
				if(j == 0) {
					prev = c;
				} else {
					if(c != prev) {
						break;
					}
				}
				prev = c;
			}
			if(j == strs.length) {//common str
				common.append(prev);
			} else {//first char is not common in all strings, break
				break;
			}
		}
		return common.toString();
	}
	
	public static String shortestPalindrome(String str) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			sb.append(c);
			if(i+1 < str.length()) {
				char cn = str.charAt(i+1);
				//sb.append(cn);
				//if(cn != c ) {
					sb.insert(0, cn);
				//}
				//i++;
			}
		}
		return sb.toString();
	}
	
	public static boolean isomorphic(String s1, String s2) {
		if(s1 == null || s2 == null || s1.length() != s2.length()) { return false; }
		Map<Character, Character> map = new HashMap<>();
		
		for(int i=0; i<s1.length(); i++) {
			char c1 = s1.charAt(i);
			char c2 = s2.charAt(i);
			Character cc = map.get(c1);
			if(cc == null) {
				map.put(c1,  c2);
			} else if(!cc.equals(c2)){
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(Zig.replaceSpace(" "));
		System.out.println(convertBin(121));
		floorCeil();
		System.out.println(longestCommonPrefix(new String[] {"singer","simba","singer"}));
		System.out.println(shortestPalindrome("bdabb"));
		System.out.println(isomorphic("egg", "abc"));
		System.out.println("bit and "+(3^5));
	}

}
