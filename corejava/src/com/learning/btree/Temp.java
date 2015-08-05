package com.learning.btree;

import java.util.Random;

public class Temp {
	
	public static void insertionSort(int[] a) {
		for(int i=0; i<a.length-1; i++) {
			int j = i+1;
			int k = i;
			while(a[j] < a[k]) {
				int tmp = a[j];
				a[j] = a[k];
				a[k] = tmp;
				j--;
				if(k == 0) break;
				k--;

			}
		}
		for(int aa : a) {
			System.out.println(aa);
		}
		
	}
	
	public static void merge(int[] a, int l, int m, int r) {
		int[] L = new int[m-l+2];
		int[] R = new int[r-m+1];
		int LL = L.length;
		int RL = R.length;
		
		for(int i=0; i<LL-1; i++) {
			L[i] = a[l+i];
		}
		for(int i=0; i<RL-1; i++) {
			R[i] = a[m+i+1];
		}
		int i=0;
		int j=0;
		L[LL-1] = Integer.MAX_VALUE;
		R[RL-1] = Integer.MAX_VALUE;
		for(int k=l; k <= r; k++) {
			if(L[i] <= R[j]) {
				a[k] = L[i];
				i++;
			} else  {
				a[k] = R[j];
				j++;
			}
		}

	}
	
	public static void mergeSort(int[] a, int l, int r) {
		if(l<r) {
			int mid = l + (r-l)/2;
			mergeSort(a, l, mid);
			mergeSort(a, mid+1, r);
			merge(a, l, mid, r);
		}

	}
	
	public static boolean binSearch(int[] a, int val, int left, int right) {
		if(left <= right) {
			int mid = left + (right-left)/2;
			if(val == a[mid]) {
				return true;
			}
			else if(val < a[mid]) {
				return binSearch(a, val, left, mid-1);
			} else  {
				return binSearch(a, val, mid+1, right);
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		int N = 15;
		int[] a1 = new int[N];
		Random rm = new Random();
		for(int i = 0; i<N; i++) {
			a1[i] = rm.nextInt(N)+1;
		}
//		insertionSort(a1);
		mergeSort(a1, 0, a1.length-1);
/*		for (int j2 = 0; j2 < a1.length; j2++) {
			System.out.println(a1[j2]);
		}*/
		int[] a2 = {1,2,4,5,6,7};
		System.out.println(binSearch(a2, 7, 0, a2.length-1));
	}

}
