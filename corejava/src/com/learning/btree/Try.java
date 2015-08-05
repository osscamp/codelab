package com.learning.btree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Try {
	
	public static void knapSack() {
		int[] val = {20,45,38,26};
		int[] wt = {1,3,4,2};
		int MAX = 6;
		int[][] k = new int[5][MAX+1];
		int i,w=0;
		for(i=0; i<k.length; i++) {
			for(w = 0; w<=MAX; w++) {
				if(i == 0 || w==0) {
					k[i][w] = 0;
				} else if(wt[i-1] <= w) {
					int pval = val[i-1];
					int pkval = k[i-1][w-wt[i-1]];
					int kminus = k[i-1][w];
					k[i][w] = Math.max(pval+pkval,kminus);
					//k[i][w] = Math.max(val[i-1]+k[i-1][w-wt[i-1]],k[i-1][w]);
				} else {
					k[i][w] = k[i-1][w];
				}
			}
		}
		for(int p=0; p<k.length; p++) {
			StringBuilder sb = new StringBuilder();
			for(int j=0; j<k[0].length; j++) {
				sb.append(k[p][j]).append(" ");
			}
			System.out.println(sb);
		}
		System.out.println("max wt "+k[i-1][w-1]);
	}
	
	public static int divide() {
		int dividend = 2124;
		int divisor = 3;
        int ctr = 0;
        if(divisor == 0) {
            return Integer.MAX_VALUE;
        }
        if(dividend == Integer.MIN_VALUE) {
            dividend += Math.abs(divisor);
            ctr++;
        }
        int divisor1 = divisor;
        int dividend1 = dividend;
        divisor = Math.abs(divisor);
        dividend = Math.abs(dividend);
		int curr = divisor;
		int currend = divisor;
		
		int rctr = 0;
		while(curr <= dividend) {
			if(ctr == 0) { ctr++; }
			//if(curr + curr <= dividend) {
			if(curr <= dividend-curr) {	
				curr+=curr;
				ctr+=ctr;
				
			} else {
				break;
			}
		}
		while(currend <= dividend-curr) {
			if(rctr == 0) { rctr++; }
			if(currend <= (dividend-curr)-currend) {
				currend += currend;
				rctr += rctr;
			}else {
				break;
			}
		}
		while(currend+divisor <= dividend-curr) {
			currend += divisor;
			rctr++;
		}
		int total = ctr+rctr;
		if((divisor1 < 0 && dividend1 > 0) || (divisor1 > 0 && dividend1 < 0)) {
			total *= -1;
		} 
		System.out.println("total times= "+total);
		return total;
	}
	
	public static void reverse(char[] a, int l, int r) {
		while(l < r) {
			char tmp = a[l];
			a[l] = a[r];
			a[r] = tmp;
			l++;
			r--;
		}
	}
	
	public static void reverseSentence(String s) {
		int l = 0;
		int r = s.length()-1;
		char[] a = s.toCharArray();
		reverse(a, l, r);
		l = 0;
		r = 0;
		for(int i=0; i<a.length; i++) {
			if((a[i] == ' ' ) || i==a.length-1) {
				if(i == a.length-1) {
					r++;
				}
				reverse(a, l, r-1);
				l = r+1;
			}
			r++;
		}
		System.out.println(new String(a));
	}
	
	public static int divideWithoutOp() {
		int dividend = 6365;
		int divisor =31;
		int current = 1;
		int answer = 0;
		if(divisor > dividend) {
			return 0;
		}
		if(divisor == dividend) {
			return 1;
		}
		while(divisor < dividend) {
			divisor <<= 1;
			current <<= 1;
		}
		while(current != 0) {
			if(dividend >= divisor) {
				dividend -= divisor;
				answer |= current;
			}
			current >>= 1;
			divisor >>=1;
		}
		System.out.println("answer "+answer);
		return answer;
	}
	
	public static int longestnr(String str) {
		char[] c = str.toCharArray();
		int maxlen = 0;
		for(int i=0; i<c.length; i++) {
			int len =1 ;
			Set<Character> set = new HashSet<>();
			set.add(c[i]);

			for(int j=i+1; j<c.length; j++) {
				if(!set.contains(c[j])) {
					len++;
					if(maxlen < len) {
						maxlen = len;
					}
				} else {
					break;
				}
				set.add(c[j]);
			}

		}
		System.out.println("maxlen "+maxlen);
		return maxlen;
	}
	
	public static void permutation(List<Integer> pre, List<Integer> a) {
		if(a.size() == 0) {
			System.out.println(pre);
		} else {
			for(int i=0; i<a.size(); i++) {
				List<Integer> preL = new ArrayList<>(pre);
				preL.add(a.get(i));
				List<Integer> next = new ArrayList<>();
				next.addAll(a.subList(0, i));
				next.addAll(a.subList(i+1, a.size()));
				permutation(preL, next);
			}
		}
	}
	
	public static int strstr(String n, String h) {
		char nc = n.charAt(0);
		for(int i=0; i<h.length(); i++) {
			if(h.charAt(i) == nc) {
				int k = 1;
				while(k<n.length() && h.charAt(i+k) == n.charAt(k)) {
					k++;
				}
				if(k == n.length()) {
					return i;
				}
			}
		}
		return -1;
	}
	
	public static void justSomeFunction() {
		Random random = new Random();
		int[] a = new int[10000];
		for(int i=0; i<a.length; i++) {
			a[i] = 1000000 + random.nextInt(1000000);
			//a[i+1] = a[i];

		}
	int ctgrp10 = 0;
	int ctgrp20 = 0;
	int ctgrp30 = 0;
	int totalct = 0;
		for(int v : a) {
			if(v % 10 == 0) {
				totalct++;
				int d = v % 1000;
				//System.out.println(d);
				if(d>=0 && d<10) {
					ctgrp10++;
				}
				if(d>=10 && d<20) {
					ctgrp20++;
				}
				if(d>=20 && d<30) {
					ctgrp30++;
				}
				//System.out.println(v+" "+v % 100);
			}
		}
		System.out.println("totalct  "+totalct);
		System.out.println("ctgrp10 "+ctgrp10);
		System.out.println("ctgrp20 "+ctgrp20);
		System.out.println("ctgrp30 "+ctgrp30);
		//MaxHeap.printArray(a);
	}

	public static void main(String[] args) {
		knapSack();
		divide();
		reverseSentence("a quick  brown fox");
		divideWithoutOp();
		longestnr("1415926535897932384626433832795");
		List<Integer> l1 = new ArrayList<>();
		List<Integer> l2 = new ArrayList<>();
		l2.add(3);
		l2.add(2);
		l2.add(1);
		permutation(l1, l2);
		System.out.println(strstr("ssip", "mississippi"));
		justSomeFunction();
	}

}
