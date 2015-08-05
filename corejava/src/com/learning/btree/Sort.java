package com.learning.btree;

import java.util.Arrays;

public class Sort {
		
	public static void bubbleSort(int[] a) {
		int length = a.length;
		int invoc = 0;
		for(int i=0; i<length; i++) {
			for(int j=i; j<length; j++) {
				invoc++;
				if(a[i] > a[j]) {
					int tmp = a[j];
					a[j] = a[i];
					a[i] = tmp;
				}
			}
		}
		System.out.println("invoc "+invoc);
	}
	
	public static void insertionSort(int[] a) {
		int invoc = 0;
		for(int i=1; i<a.length; i++) {
			int j = i;
			while(j > 0 && a[j] < a[j-1]) {
				invoc++;
				int tmp = a[j];
				a[j] = a[j-1];
				a[j-1] = tmp;
				j--;
			}
		}
		System.out.println("invoc "+invoc);
		
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
		int j = l;
		for(int i=l; i<r; i++) {
			if(a[i] < x) {				
				int tmp = a[i];
				a[i] = a[j];
				a[j] = tmp;
				j++;
			}
		}
		int tmp = a[j];
		a[j] = a[r];
		a[r] = tmp;
		return j;
	}

	public static void mergeSort(int[] a, int l, int r) {
		if (l < r) {
			int m = (l + r) / 2;
			mergeSort(a, m + 1, r);
			mergeSort(a, l, m);
			merge0(a, l, m, r);
		}

	}

	public static void merge(int[] a, int l, int m, int r) {
		int[] helper = new int[r+1];

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
	
	//partition the array at end , if pivot == k, kth is found
	public static int findKthLargest(int[] a, int k) {
		return findKthLargest(a, a.length-k, 0, a.length);
	}
	public static int findKthLargest(int[] a, int k, int l, int r) {
		int pivot = -1;
		if(l<r) {
			pivot = kthPartition(a, l, r);
			if(pivot == k) {
				return a[pivot];
			}
			if(k < pivot) {
				return findKthLargest(a, k, l, pivot);
			} else {
				return findKthLargest(a, k, pivot + 1, r);
			}
		}
		if(l == r && l<a.length) {
			return a[l];
		}
		return pivot;
	}
	
	public static int kthPartition(int[] a, int l, int r) {
		//int len = a.length;
		int pivot = a[r - 1];
		int i = l;
		for(int j=i; j<r; j++) {
			if(a[j] < pivot) {
				int tmp = a[j];
				a[j] = a[i];
				a[i] = tmp;
				i++;
			}
			//j++;
		}
		int tmp = a[i];
		a[i] = pivot;
		a[r - 1] = tmp;
		System.out.println("pivot pos "+i);
		return i;
	}

	
	public static void countSort() {
		int k = 9;
		int[] a = {1,2,2,8,5,2,1,5,4,8,3,2,2,5,1};
		int[] aux = new int[k];
		int[] sorted = new int[a.length];
		MaxHeap.printArray(a);
		for(int i=0; i<a.length; i++) {
			aux[a[i]]++;
		}
		MaxHeap.printArray(aux);

		for(int i=1; i<aux.length; i++) {
			aux[i] = aux[i] + aux[i-1];
		}
		MaxHeap.printArray(aux);

		for(int i=a.length-1; i>=0; i--) {
			//int ai = a[i];
			//int auxai = aux[ai];
			sorted[--aux[a[i]]] = a[i];
			MaxHeap.printArray(sorted);
			//aux[a[i]] = aux[a[i]] - 1;
		}
		for(int i=0; i<sorted.length; i++) {
			//System.out.println("nn "+sorted[i]);
		}
		
	}
	
	public static void sort3values() {
		char[] a = "bbgbrr".toCharArray();
		int ctr = 0;
		int ridx = getRidx(a, 0);
		int bidx = getBidx(a, a.length - 1);
		boolean ismodified = false;
		while(ctr <= bidx && ridx < bidx) {
			if(a[ctr] == 'r' && ridx < ctr) {
				char tmp = a[ctr];
				a[ctr] = a[ridx];
				a[ridx] = tmp;
				ridx = getRidx(a, ridx);
				bidx = getBidx(a, bidx);
			} else if(a[ctr] == 'b'){
				char tmp = a[ctr];
				a[ctr] = a[bidx];
				a[bidx] = tmp;
				bidx = getBidx(a, bidx);
				ridx = getRidx(a, ridx);
				ismodified = true;
			}
			if(ctr > 0 && a[ctr] == 'r' && ismodified) {
				ismodified = false;
				continue;
			}
			ctr++;
		}
		System.out.println(new String(a));
	}
	
	static int getRidx(char[] a, int start) {
		int i = start;
		while(i<a.length - 1 && a[i] == 'r') { i++; }
		return i;
	}
	
	static int getBidx(char[] a, int end) {
		int i = end;
		while(i>0 && a[i] == 'b') { i--; }

		return i;
	}
	
	public static void sort3Values2()
	{
		int[] a = {1,0,2,1,0};
		int lo = 0;
		int mid = 0;
		int hi = a.length-1;
		while(mid < hi) {
			if(a[mid] == 0) {
				swap(a, lo, mid);
				lo++;
				mid++;
			} else if(a[mid] == 1) {
				mid++;
			} else {
				swap(a, mid, hi);
				hi--;
			}
		}
		MaxHeap.printArray(a);
	}
	
	public static void swap(int[] a, int l, int r) {
		int tmp = a[l];
		a[l] = a[r];
		a[r] = tmp;
	}
	
	public static void sortStrings(String[] a) {
		quickSortStr(a, 0, a.length-1);
		for(String s:a)System.out.println(s);
	}
	
	public static void quickSortStr(String[] a, int l, int r) {
		if(l < r) {
			int p = partition(a, l, r);
			quickSortStr(a, l, p-1);
			quickSortStr(a, p+1, r);
		}
	}
	
	public static int partition(String[] a, int l, int r) {
		String pivot = a[r];
		int i=l;
		int j=i;
		while(i < r) {
			if(a[i].compareTo(pivot) < 0) {
				String t = a[i];
				a[i] = a[j];
				a[j] = t;
				j++;
			}
			i++;
		}
		String tmp = a[j];
		a[j] = pivot;
		a[r] = tmp;
		return j;
	}
	
	public static void main(String[] args) {
		int[] arr = {12,20,15,29,23,17,22,21,40,26,51,19};
		//int[] arr = { 5, 1, 20, 34, 8 };
		//bubbleSort(arr);
		insertionSort(arr);
		//mergeSort(arr, 0, arr.length - 1);
		//ins(arr);
		//quickSort(arr, 0, arr.length -1 );
		MaxHeap.printArray(arr);
		//bubbleSort(arr);
		//buildMaxHeap(arr);
		//heapsort(arr);

		int value = findKthLargest(new int[]{9, 11, 5, 7, 16, 1, 4, 12}, 3);
		System.out.println("kth largest "+value);
		//insertionSort(arr);
		countSort();
		sort3values();
		sort3Values2();
		sortStrings(new String[]{"algo","galo","altus","sell", "belt"});
	}
}
