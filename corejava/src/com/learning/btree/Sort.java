package com.learning.btree;

public class Sort {

	public static void insertionSort(int[] a) {
		for (int j = 1; j < a.length; j++) {
			int k = a[j];
			int i = j - 1;
			while (i >= 0 && a[i] > k) {
				a[i + 1] = a[i];
				i--;
				a[i + 1] = k;
			}
		}
		for (int n : a) {
			System.out.println(n);
		}
	}
	
	public static void insertionSort1(int[] a) {
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
	
	public static void quickSort(int[] a, int l, int r) {
		if(l < r) {
			int q = partition(a, l, r);
			quickSort(a, l, q-1);
			quickSort(a, q+1, r);
		}
		
	}
	
	public static int partition(int[] a, int l, int r) {
		int x = a[r];
		int i = l;
		for(int j=l; j<r; j++) {
			if(a[j] <= x) {				
				int tmp = a[i];
				a[i] = a[j];
				a[j] = tmp;
				i++;
			}
		}
		int tmp = a[i];
		a[i] = a[r];
		a[r] = tmp;
		return i;
	}

	public static void mergeSort(int[] a, int l, int r) {
		if (l < r) {
			int m = (l + r) / 2;
			mergeSort(a, l, m);
			mergeSort(a, m + 1, r);
			merge0(a, l, m, r);
		}

	}

	public static void merge(int[] a, int l, int m, int r) {
		int[] helper = new int[a.length];

		for (int i = 0; i <= r; i++) {
			helper[i] = a[i];
		}

		int i = l;
		int j = m + 1;
		int k = l;

		while (i <= m && j <= r) {
			if (helper[i] < helper[j]) {
				a[k] = helper[i];
				i++;
			} else {
				a[k] = helper[j];
				j++;
			}
			k++;
		}
		while (i <= m) {
			a[k] = helper[i];
			k++;
			i++;
		}
	}

	public static void merge0(int[] a, int l, int m, int r) {
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
		for(int k=l; k<=r; k++) {
			if(L[i] <= R[j]) {
				a[k] = L[i];
				i++;
			} else  {
				a[k] = R[j];
				j++;
			}
		}

	}

	public static void main(String[] args) {
		int[] arr = { 5, 1, 20, 34, 2, 8, 212, 145, 38, 75, 21 };
		//int[] arr = { 5, 1, 20, 34, 8 };
		// insertionSort(arr);
		mergeSort(arr, 0, arr.length - 1);
		//quickSort(arr, 0, arr.length -1 );
		for (int n : arr) {
			System.out.println(n);
		}
	}
}
