package com.learning.btree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MiscQ6 {
	
	public static void findPrimesTill100() {
		int N = 100;
		boolean[] nonprime = new boolean[N];
		int d=2;
		while(d < (int)Math.sqrt(N)+1) {
			for(int i=d+1; i<=N; i++) {
				//System.out.println("compare");
				if(i%d == 0) {
					nonprime[i-1] = true;
				}
			}
			d++;
			for(int k=d; k<N; k++) {
				if(!nonprime[k-1]) {
					d = k;
					break;
				}
			}
		}
		for(int k=2; k<N; k++) {
			if(!nonprime[k-1]) {
				System.out.println("Prime "+k);
			}
		}
	}
	
	public static void find5PrimesfromN() {
		long t1 = System.currentTimeMillis();
		long IN = 1000000000l;
		int N = (int)Math.sqrt(IN)+1;
		boolean[] nonprime = new boolean[N];
		int d=2;
		while(d < (int)Math.sqrt(N)+1) {
			for(int i=d+1; i<=N; i++) {
				//System.out.println("compare");
				if(i%d == 0) {
					nonprime[i-1] = true;
				}
			}
			d++;
			for(int k=d; k<N; k++) {
				if(!nonprime[k-1]) {
					d = k;
					break;
				}
			}
		}
		List<Integer> primesUpSqRoot = new ArrayList<>();
		for(int k=2; k<N; k++) {
			if(!nonprime[k-1]) {
				//System.out.println("Prime "+k);
				primesUpSqRoot.add(k);
			}
		}
		long p = IN+1;
		int np = 0;
		while(np < 5) {
			boolean isP = true;
			for(int div : primesUpSqRoot) {
				if(p%div == 0) {
					isP = false;
					break;
				}
			}
			if(isP) {
				System.out.println("prime "+p);
				np++;
			}
			p+=2;
		}
		long end = System.currentTimeMillis();
		System.out.println("time taken "+(end-t1));

	}
	
	public static void find5PrimesfromNBruteForce() {
		long t1 = System.currentTimeMillis();
		long IN = 1000000000l;
		long p = IN+1;
		int np = 0;
		while(np < 5) {
			boolean isP = true;
			for(int i=2; i<=Math.sqrt(IN) ;i++) {
				if(p%i == 0) {
					isP = false;
					break;
				}
			}
			if(isP) {
				System.out.println("prime "+p);
				np++;
			}
			p+=2;
		}
		long end = System.currentTimeMillis();
		System.out.println("time taken "+(end-t1));

	}
	
	public static void findMinSubarrayGreaterThanN() {
		int[] a = {2,-1,11,2,10};
		int N = 12;
		int st = 0;
		int minl = 1000;
		int sum = 0;
		int i = 0;
		while(i <= a.length) {
			if(sum < N) {
				if(i == a.length) {
					break;
				}
				sum += a[i];
				i++;
			}
			else {
				if(i-st < minl) {
					minl = i-st;
				}
				sum -= a[st];
				st++;
			}
		}
		System.out.println(minl);
	}
	
	//input - [17,5,1,9]
	//order - [2,1,3,0]
	//result - [1,5,9,17]
	public static void rearrangeArrayByIndexArray() {
		int[] idxa = {5,2,0,1,3,4};
		int[] arr = {11,16,6,9,5,8};
		int ctr = 0;
		int st = idxa[0];
		int tmp = arr[st];
		int tmp2 = 0;
		while(ctr < arr.length) {
			int match = -1;
			for(int k=0; k<idxa.length; k++) {
				if(idxa[k] == st) {
					match = k;
					break;
				}
			}
			st = match;
			tmp2 = arr[match];
			arr[match] = tmp;
			tmp = tmp2;
			ctr++;
		}
		MaxHeap.printArray(arr);
	}
	
	public static void findNextLargestPerm() {
		//int n = 217643;
		int n = 54321;
		int copy = n;
		String str = ""+n;
		int[] d = new int[str.length()];
		int idx = d.length-1;
		while(copy > 0) {
			int dig = copy % 10;
			copy /= 10;
			d[idx--] = dig;
		}
		int k = 0;
		for(k=d.length-1; k>0; k--) {
			if(d[k-1] >= d[k]) {
				continue;
			} else {
				//find least N > than this index in visited - it would be rightmost
				int mindiffidx = d.length-1;
				if(mindiffidx != -1) {
					int t = d[k-1];
					d[k-1] = d[mindiffidx];
					d[mindiffidx] = t;	
					
					//reverse remaining					
					int l = k;
					int r = d.length-1;
					while(l < r) {
						int tmp = d[l];
						d[l] = d[r];
						d[r] = tmp;
						l++;
						r--;
					}
					break;
				}

			}
		}
		
		int newn = 0;
		//int m = 10;
		for(int i=d.length-1,m=1; i>=0; i--,m*=10) {
			newn = d[i]*m+newn;
		}
		System.out.println("old "+n);
		System.out.println("newn "+newn);
	}
	
	public static void substringAnagram() {
		String b = "AABBABACDA";
		String a = "CDAC";
		boolean isFound = false;
		Set<Character> aset = new HashSet<>();
		for(char c: a.toCharArray()) {
			aset.add(c);
		}
		char[] bc = b.toCharArray();
		for(int i=0; i<bc.length && !isFound; i++) {
			char c = bc[i];
			if(aset.contains(c)) {
				int ctr = i+1;
				int len = a.length();
				int foundct = 1;
				while(ctr < bc.length) {
					char aa = bc[ctr];
					if(!aset.contains(aa)) {
						break;
					}else if(++foundct >= len) {
						isFound = true;
						break;
					}
					ctr++;
				}
			}
		}
		System.out.println("isfound "+isFound);
	}
	
	public static void sortAlphanumOrderIntact() {
		String st = "1ab2rc3kd4";
		char[] a = st.toCharArray();
		char pivot = 'a';
		int j = 0;
		int mid = 0;
		for(int i=0; i<a.length; i++) {
			if(a[i] >= pivot) {
				mid = i;
				char tmp = a[i];
				a[i] = a[j];
				a[j] = tmp;
				j++;
				
				while(mid > j) {
					char tmp2 = a[mid-1];
					a[mid-1] = a[mid];
					a[mid] = tmp2;
					mid--;
				}
			}
		}
		System.out.println(new String(a));
		
	}
	
	public static void printMatrixDiagonally() {
		int N = 4;
		int[][] a = {
				{1,2,3,4},
				{5,6,7,8},
				{9,10,11,12},
				{13,14,15,16}};
		int i = 0;
		int j= 0;
		int ctr = 0;
		for(int ictr = 0; ictr<N; ictr++ ) {
			while(ctr < N) {
				j = ctr;
				i=ictr;
				StringBuilder sb = new StringBuilder();
				while(j>=0 && i<N ) {
					sb.append((a[i][j])).append(" ");
					i++;
					j--;
				}
				System.out.println(sb);
				ctr++;
			}
			ctr = N-1;
		}
	}
	
	public static void main(String[] args) {
		MiscQ6.find5PrimesfromN();
		MiscQ6.find5PrimesfromNBruteForce();
		findMinSubarrayGreaterThanN();
		rearrangeArrayByIndexArray();
		findNextLargestPerm();
		substringAnagram();
		sortAlphanumOrderIntact();
		printMatrixDiagonally();
	}

}
