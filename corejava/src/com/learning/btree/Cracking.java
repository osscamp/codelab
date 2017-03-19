package com.learning.btree;

import java.util.HashMap;
import java.util.Map;

public class Cracking {
	
	static char[][] board = {
		{'x','o','x','o'},
		{'o','x','o','x'},
		{'x','o','o','x'},
		{'o','x','o','x'},
	};
	
	static char whowon()  {
		int N = board.length;
		char[][] a = board;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i>0 && j>0 && i<N-1 && j<N-1) {
					continue; //continue for non edge
				}
				char cc = a[i][j];
				int k = i;
				int l = j;
				while(l < N-1 && a[k][l+1] == cc){
					l++;
				}
				if(l-j == N-1){ return cc;}
				k = i; l = j;
				while(l > 0 && a[k][l-1] == cc){
					l--;
				}
				if(j-l == N-1){ return cc;}
				
				k = i; l = j;
				while(k < N-1 && a[k+1][l] == cc){
					k++;
				}
				if(k-i == N-1){ return cc;}
				
				k = i; l = j;
				while(k > 0 && a[k-1][l] == cc){
					k--;
				}
				if(i-k == N-1){ return cc;}
				
				k = i; l = j;
				while(l<N-1 && k<N-1 && a[k+1][l+1] == cc){
					k++;l++;
				}
				if(i-k == N-1 && l-j == N-1){ return cc;}
				
				k = i; l = j;
				while(k > 0 && l > 0 && a[k-1][l-1] == cc){
					k--;l--;
				}
				if(k-i == N-1 && j-l==N-1){ return cc;}
				
				k = i; l = j;
				while(k > 0 && l < N-1 && a[k-1][l+1] == cc){
					k--;l++;
				}
				if(k==0 && l-j==N-1){ return cc;}
				
				k = i; l = j;
				while(k < N-1 && l > 0 && a[k+1][l-1] == cc){
					k++;l--;
				}
				if(k-i == N-1 && l==0){ return cc;}
				
			}
		}
		return 'N';
	}
	
	public static String printNumber(int n) {
		Map<Integer, String> imap = new HashMap<>();
		imap.put(0, "");
		imap.put(1, "One");
		imap.put(2, "Two");
		imap.put(3, "Three");
		imap.put(4, "Four");
		imap.put(5, "Five");
		imap.put(6, "Six");
		imap.put(7, "Seven");
		imap.put(8, "Eight");
		imap.put(9, "Nine");
		imap.put(10, "Ten");
		imap.put(11, "Eleven");
		imap.put(12, "Twelve");
		imap.put(13, "Thirteen");
		imap.put(14, "Fourteen");
		imap.put(15, "Fifteen");
		imap.put(16, "Sixteen");
		imap.put(17, "Seventeen");
		imap.put(18, "Eighteen");
		imap.put(19, "Nineteen");
		imap.put(20, "Twenty");
		imap.put(30, "Thirty");
		imap.put(40, "Forty");
		imap.put(50, "Fifty");
		imap.put(60, "Sixty");
		imap.put(70, "Seventy");
		imap.put(80, "Eighty");
		imap.put(90, "Ninety");
		imap.put(100, "Hundred");
		imap.put(1000, "Thousand");
		
		StringBuilder sb = new StringBuilder();
		int tmp = n;
		boolean first = true;
		int ct = 1;
		while(first) {
			int d = tmp %100;
			if(d > 19) {
				int dd = tmp % 10;
				sb.insert(0, imap.get(dd));
				ct *= 10;
				sb.insert(0, imap.get(ct*(d/10)));

			} else {
				sb.insert(0, imap.get(d));
				ct *= 10;
			}
			tmp = tmp / 100;
			ct*=10;
			if(tmp > 0) {
				sb.insert(0, imap.get(ct));
			}
			first = false;
		}
		while(tmp > 0) {
			if(tmp / 100 > 0) {
				int d = tmp%10;
				sb.insert(0,  imap.get(d));
				ct*=10;
				tmp /= 10;
				if(tmp > 0) {
					sb.insert(0, imap.get(ct));
				}
			} else {
				int d = tmp %100;
				if(d > 19) {
					int dd = tmp % 10;
					sb.insert(0, imap.get(dd));
					ct *= 10;
					sb.insert(0, imap.get((d/10)*10));

				} else {
					sb.insert(0, imap.get(d));
					ct *= 10;
				}
				tmp = tmp/100;
			}
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public static void main(String[] args) {
		char w  = whowon();
		System.out.println("won "+w);
		printNumber(23278);
	}

}
