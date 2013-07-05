package com.learning.btree;

public class Sort {
	
	public static void insertionSort(int[] a){
		for(int j=1; j<a.length; j++){
			int k = a[j];
			int i = j-1;
			while(i >=0 && a[i] > k){
				a[i+1] = a[i];
				i--;
				a[i+1] = k;
			}
		}
		for(int n: a){
			System.out.println(n);
		}
	}
	
	public static void mergeSort(int[] a, int l, int r) {
		while(l<r){
			int m = (l+r)/2;
			mergeSort(a, l, m);
			mergeSort(a, m+1, r);
			merge(a, l, m, r);
		}
	}
	
	public static void merge(int[] a, int l, int m, int r){
		int[]  L = new int[m-l];
		int[] R = new int[r-m];
		for(int i=l; i<=m; i++){
			L[i] = a[i];
		}
		for(int i=m+1; i<r; i++){
			R[i] = a[i];
		}
		int k = Math.max(L.length, R.length);
		for(int i=0; i<k; i++){
			if(L[i] < R[i]){
				a[i] = L[i];
			} else{
				a[i] = R[i];
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = {5, 1, 20, 34, 2, 8, 212, 145, 38, 75, 48};
		insertionSort(arr);
	}
}
