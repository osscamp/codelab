package com.learning.btree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Scratch {
	
	public static int maxProfit() {
		int[] txn = {4, 11, 5, 7, 16, 1, 4, 2};
		int max = -1;
		int difm = -1;
		for(int i=txn.length - 1; i>=0; i--) {
			if(txn[i] > max){
				max = txn[i];
			}
			int dif = max  - txn[i];
			if(dif > difm ) {
				difm = dif;
			}
			
		}
		System.out.println(difm);
		return difm;
	}
	
	public static void rotateArray() {
		int[] a = {1,2,3,4,5,6,7};
		int k =3;
		reverse(a, a.length-k, a.length-1);
		reverse(a, 0, a.length-k-1);
		reverse(a, 0, a.length-1);
			//{4,3,2,1,7,6,5}
			//{5,6,7,1,2,3,4};
		MaxHeap.printArray(a);
			
	}
	
	public static void reverse(int[] a, int l, int r) {
		if(l < 0 || r >= a.length) {
			return;
		}
		while(l < r) {
			int tmp = a[l];
			a[l] = a[r];
			a[r] = tmp;
			l++;
			r--;
		}
		
	}
	
	public static Set<String> perms(String input) {
		Set<String> presult = new HashSet<>();
		if(input==null) {
			return null;
		}
		if(input.length() == 0) {
			presult.add("");
			return presult;
		}
		char first = input.charAt(0);
		String substr = input.substring(1);
		Set<String> permutedSet = perms(substr);
		for(String x : permutedSet) {
			for(int i=0; i<=x.length(); i++) {
				String inter = x.substring(0, i)+first+x.substring(i);
				presult.add(inter);
			}
		}
		return presult;
	}
	
	public static void findOverlap() {
		int[][] intervals = {{2,5},{1,3},{7,9},{8,10},{11,16},{18,19},{4,6}};
		for(int i=1; i<intervals.length; i++) {
			int j=i;
			while(j > 0 && intervals[j][0] < intervals[j-1][0]) {
				int[] tmp = intervals[j-1];
				intervals[j-1] = intervals[j];
				intervals[j] = tmp;
				j--;
			}
		}
		//{{1,5},{4,7},{6,9}};
		int start = intervals[0][0];
		int end = intervals[0][1];
		for(int i = 0; i<intervals.length; i++) {
			if(i < intervals.length-1 && end >= intervals[i+1][0]) {
				start = Math.min(intervals[i+1][0], start);
				end = Math.max(intervals[i+1][1], end);
			} else if(end < intervals[i][0]) {
				start = intervals[i][0];
				end = intervals[i][1];
				i--;
			}
			System.out.println("start "+start+" end "+end);
		}
		//System.out.println("start "+start+" end "+end);
	}
	
	public static void printallcombsinorder() {
		List<String> inlist = Arrays.asList("quick","fox");
		List<String> result = new ArrayList<>();
		for(String each : inlist) {
			char[] carray = each.toCharArray();
			List<String> temp = new ArrayList<>();
			for(char c : carray) {
				if(result.size() > 0) {
					for(String eachRes : result) {
						StringBuilder sb = new StringBuilder(eachRes);
						sb.append(c);
						temp.add(sb.toString());
					}					
				} else {
					temp.add(String.valueOf(c));

				}
			}
			result = temp;
		}
		System.out.println(result);
	}
	
	public static void printZig() {
		int[][] a = {
				{2,1,3,4},
				{7,9,8,5},
				{6,4,3,8},
				{9,5,2,1}
		
		};
		int N = a.length;
		int i = 0;
		int j = 0;
		while(j<N) {
			int ti = i;
			int tj = j;
			while(tj>=0) {
				System.out.println(a[ti][tj]);
				ti++;
				tj--;
			}
			j++;
		}
		j--;
		i++;
		while(i < N) {
			int ti = i;
			int tj = j;
			while(ti < N) {
				System.out.println(a[ti][tj]);
				ti++;
				tj--;
			}
			i++;
		}
	}
	
	public static void reduce() {
		String s = "aaaaabbbccdbbaae";
		StringBuilder sb = new StringBuilder();
		char prev = ' ';
		int count = 0;
		for(int i=0; i<s.length(); i++){
			char c = s.charAt(i);
			if(c == prev) {
				count++;
			}else{
				if(count % 2 != 0) {
					sb.append(prev);
				}
				count = 1;
			}
			
			prev = c;
		}
		if(count % 2 != 0) {
			sb.append(prev);
		}
		System.out.println(sb);
	}
	
	public static void cmelCaseCount(){
		String s = "saveChangesInTheEditor";
		if(s == null || s.length() == 0) {System.out.println("wc "+0);return;}
		int wc = 1;
		char prev = ' ';
		for(int i=0; i<s.length(); i++){
			char c = s.charAt(i);
			if(Character.isLowerCase(prev) && Character.isUpperCase(c)){
				wc++;
			}
			prev = c;
		}
		System.out.println("wc "+wc);
	}
	
	public static void main(String[] args) {
		maxProfit();
		rotateArray();
		System.out.println(perms("abc"));
		findOverlap();
		printallcombsinorder();
		printZig();
		//red();
		reduce();
		cmelCaseCount();
	}

}
