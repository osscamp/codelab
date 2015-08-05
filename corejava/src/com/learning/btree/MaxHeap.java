package com.learning.btree;

public class MaxHeap {
	
	public static int heapSize;
	
	public static void buildMaxHeap(int[] a) {
		heapSize = a.length;
		for(int i=heapSize/2-1; i>=0; i--) {
			maxheap(a, i);
		}
        printArray(a);
	}
	
	public static void maxheap(int[] a, int i) {
		int l = 2*i+1;
		int r = 2*i+2;
		int max	 = i;
		if( l < heapSize && a[l] > a[i]) {
			max = l;
		} else {
			max = i;
		}
		if( r < heapSize && a[r] > a[max]) {
			max = r;
		} 
		if(max != i) {
			int tmp = a[i];
			a[i] = a[max];
			a[max] = tmp;
			maxheap(a, max);
		}
	}
	
	
	public static void heapsort(int[] a) {
        int n = a.length - 1;
        buildMaxHeap(a);
        for(int i =  n; i >= 0; i --) {
			int tmp = a[0];
			a[0] = a[i];
			a[i] = tmp;
			heapSize=heapSize-1;
        	maxheap(a,0);
        }
        printArray(a);
	}
	
	public static void printArray(int[] a) {
		StringBuilder sb = new StringBuilder();
		for(int i: a) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) {
		int[] arr = {12,20,15,29,23,17,22,35,40,26,51,19};
		//buildMaxHeap(arr);
		heapsort(arr);
	}

}
