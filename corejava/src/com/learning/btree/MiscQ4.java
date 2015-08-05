package com.learning.btree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class MiscQ4 {
	
	public static void openDoors() {
		int N = 100;
		for(int i=1; i*i <= N; i++) {
			System.out.println("open "+i*i);
		}
	}
	
	public static void findBinarySum() {

		int x1 = 100011;
		int x2 = 1001;
		int carry = 0;
		StringBuilder sb = new StringBuilder();
		while(x1 > 0 || x2 > 0) {
			int d1 = x1 % 10; x1/=10;
			int d2 = x2 % 10; x2/=10;
			int sum = d1+d2+carry;
			if(sum == 0) {
				carry = 0;
				sb.insert(0, 0);
			}else if(sum == 1){
				carry = 0;
				sb.insert(0, 1);
			}else if(sum == 2) {
				carry = 1;
				sb.insert(0, 0);
			}else if(sum > 2) {
				carry = 1;
				sb.insert(0, 1);
			}
			if(carry > 0 && (x1 == 0 && x2 == 0)) {
				sb.insert(0, carry);
			}
		}
		System.out.println("bindig sum "+sb);
	}
	
	public static void isSubAnagram() {
		String big = "semaphore";
		String needle = "amp";
		char[] biga = big.toCharArray();

		char[] sorted1 = needle.toCharArray();
		Arrays.sort(sorted1);
		boolean isFound = false;
		
		for(int i=0; i+sorted1.length<=biga.length; i++) {
			char[] tmp = new char[sorted1.length];
			System.arraycopy(biga, i, tmp, 0, sorted1.length);
			Arrays.sort(tmp);
			for(int j=0; j<sorted1.length; j++) {
				if(sorted1[j] != tmp[j]) {
					break;
				}
				if(j == sorted1.length - 1) {
					isFound = true;
					break;
				}
			}
			if(isFound) { break; }
		}
		System.out.println(" is anagram() " +isFound);
		
	}
	
	public static void isPathPossible() {
		String[][] path = {
				{"1,2", "2,1", "0,1"},
				{"1,1", "0,2", "1,0"},
				{"2,2", "2,0", null}
		};
		int i=0, j=0;
		String pp = path[i][j];
		path[i][j] = "NN";
		int ct = 0;
		while(pp != null && !(pp.trim().length() == 0)){
			int nexti = pp.charAt(0)-'0';
			int nextj = pp.charAt(2)-'0';
			if(nexti >= path.length || nextj >= path.length || (path[nexti][nextj] != null && path[nexti][nextj].equals("NN"))) {
				System.out.println("not possible");
				break;
			}
			pp = path[nexti][nextj];
			path[nexti][nextj] = "NN";
			ct++;
			if(ct >= 8) {
				break;
			}
		}
		if(ct < 8) {
			System.out.println("not traversed");
		}else {
			System.out.println("traversed");
		}
	}
	
	//str2 = f.c.*o*k
	//doesn't work
/*	public static void matchPattern(String str1, String str2) {
		int i=0;
		int j=0;
		if(str2 == null || str1 == null || str1.trim().isEmpty() || str2.trim().isEmpty()) {
			System.out.println("no match ");
		}
		while(i<str1.length() && j<str2.length()) {
			char cd = str1.charAt(i);
			char cp = str2.charAt(j);
			if(i<str1.length()-1 && cp == '.' && str2.charAt(i+1) == '*') {
				j+=2;
				if(j >= str2.length()) {
					i=str1.length();
					break;
				}else{
					cp = str2.charAt(j);
					while(cd != cp && i<str1.length()-1) {
						i++;
						cd = str1.charAt(i);
					}
					if(cp != cd) {
						System.out.println("no match ");
						break;
					}
				}
			}
			else if(cp == '.') {
				i++;
				j++;
				continue;
			}else if(cp == '*') {
				char ptrnprev = str2.charAt(j-1);
				//char ccc = str1.charAt(i);
				j++;
				if(j >= str2.length()) {
					i=str1.length();
					break;
				}else{
					cp = str2.charAt(j);
					while(cd == ptrnprev && i<str1.length()-1) {
						i++;
						cd = str1.charAt(i);
					}
					if(cp != cd) {
						System.out.println("no match ");
						break;
					}
				}
				//continue;
			} else {
				if(cd != cp) {
					System.out.println("does not match ");
					break;
				}
			}
			i++;
			j++;
		}
	}*/
	
	public static void rebalanceArray() {
		int[] a = {1,1,1,1,1,1,2,2,2,2,2,};
		int M = 2;
		int rep = 1;
		int backtrackidx = 0;
		for(int i=0; i<a.length-1; i++) {
			if(a[i+1] == a[i]) {
				rep++;
				if(rep > M) {
					int j = i+1;
					boolean isFound = false;
					while(j < a.length) {
						if(a[i+1] != a[j]) {
							isFound = true;
							break;
						}
						j++;
					}
					if(!isFound) {
						j = i-1;
						while(j >= backtrackidx) {

							if(a[i+1] != a[j]) {
								backtrackidx = i+1;
								isFound = true;
								i--;
								break;
							}
							j--;
						}
					}
					if(i+1 < a.length - M || j > i) {
						int tmp = a[i+1];
						a[i+1] = a[j];
						a[j] = tmp;
						rep = 1;
					}
				}
			} else {
				rep = 1;
			}
		}
		System.out.println("loop "+backtrackidx);
		MaxHeap.printArray(a);
	}
	
	public static void printParenthesis() {
		int n = 4;
		printit(n/2, n/2, "");
	}
	
	public static void printit(int l, int r, String s) {
		if(l ==0 && r==0){
			System.out.println(s);
		}
		if(l > r) {
			return;
		}if(l>0) {
			printit(l-1, r, s+"(");
		}
		if(r>0) {
			printit(l, r-1, s+")");
		}

	}
	
	//deprecated
	public static void findAllSubstringsN3(String s) {
		for(int level = 1; level <= s.length(); level++) {
			for(int i=0; i<=(s.length()-level); i++) {
				String sub = s.substring(i, i+level);
				if(Combinatorics.isPalindrome(sub)) {
					System.out.println(sub);
				}
			}
		}
	}
	
	public static Set<String> palindromes(final String input) {

		final Set<String> result = new HashSet<>();

		for (int i = 0; i < input.length(); i++) {
			// expanding even length palindromes:
			expandPalindromes(result, input, i, i + 1);
			// expanding odd length palindromes:
			expandPalindromes(result, input, i, i);
		}
		return result;
	}

	public static void expandPalindromes(final Set<String> result, final String s,
			int i, int j) {
		while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
			result.add(s.substring(i, j + 1));
			i--;
			j++;
		}
	}
	
	public static void kClosestFromOrigin() {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int[][] points = {{5,3},{5,1},{7,2},{2,4},{3,4},{2,6}};
		int npoints = points.length;
		Map<Integer, Integer> positions = new HashMap<>();
		for(int i=0; i<npoints; i++) {
			int dist = points[i][0]*points[i][0] + points[i][1]*points[i][1];
			pq.add(dist);
			positions.put(dist, i);
		}
		for(int i=0; i<3; i++) {
			System.out.println("least dist index "+positions.get(pq.poll()));
		}
	}
	
	public static void anagramBuckets(){
		List<String> input = Arrays.asList(new String[]{"star","rats","ice","cie","arts"});
		Map<String, List<String>> map = new HashMap<>();
		for(int i=0; i<input.size(); i++) {
			String s = input.get(i);
			char[] sarray = s.toCharArray();
			Arrays.sort(sarray);
			StringBuilder nb = new StringBuilder();
			for(char c: sarray) {
				nb.append(c);
			}
			String sortedKey = nb.toString();
			List<String> slist = map.get(sortedKey);
			if(slist == null) {
				slist = new ArrayList<>();
				slist.add(s);
				map.put(sortedKey, slist);
			} else {
				slist.add(s);
			}
		}
		System.out.println(map);
	}
	
	public static void mergeEqualSizeArraysInplace(){
		int[] a = {1,5,7};
		int[] b = {0,4,6,0,0,0};
		int i=a.length-1;
		int j=2;
		int k=b.length-1;
		while (i>=0 && j<b.length && j>=0 && k>=0) {
			if(a[i] > b[j] ) {
				b[k] = a[i];
				i--;
			}else {
				b[k] = b[j];				
				j--;
			}
			k--;
		}
		if(i==0 && j==-1) {
			b[0] = a[i];
		}
		MaxHeap.printArray(b);
	}
	
	public static void findNeedle(String sub, String hay) {
		char[] hc = hay.toCharArray();
		char[] sc = sub.toCharArray();
		if(hc.length == 0 || sc.length == 0){ return;}
		char fc = sc[0];
		int j = 1;
		for(int i=0; i<=(hc.length-sc.length); i++) {
			if(fc == hc[i]) {
				int k= i+1;
				while(j<sc.length && k < hc.length && hc[k] == sc[j]) {
					k++;
					j++;
				}
				if(j == sc.length) {
					System.out.println("found");
					break;
				}
				if(j < sc.length) {
					j = 1; // reset
					continue;
				}
			}
		}
	}
	
	public static void isTwoSumRandom() {
		int[] a = {3,8,1,5,9,2};
		//1,2,3,5,8,9
		int sum = 4;
		Set<Integer> vals = new HashSet<>();
		for(int v : a) {
			vals.add(v);
			if(vals.contains(sum-v)) {
				System.out.println("true");
				break;
			}
		}
		
		Arrays.sort(a);
		int i=0; int j= a.length-1;
		while (i<j) {
			if(a[i] > sum) {
				return;
			}else if (a[i] + a[j] > sum) {
				j--;
			}else if (a[i] + a[j] < sum) {
				i++;
			}else {
				System.out.println("found sort");
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		openDoors();
		findBinarySum();
		isSubAnagram();
		isPathPossible();
		//matchPattern("ssissip","s.s*p");
		rebalanceArray();
		printParenthesis();
		System.out.println(palindromes("abba"));
		kClosestFromOrigin();
		anagramBuckets();
		mergeEqualSizeArraysInplace();
		findNeedle("lav", "balaclava");
		isTwoSumRandom();
	}

}
