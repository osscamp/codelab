package com.learning.java;

import com.learning.btree.MaxHeap;

public class Sip {
	
	public static void isort(){
		int[] a = {3, 9, 2, 11, 4, 8, 25, 13};
		for(int i=1; i<a.length; i++){
			int j = i;
			while(j > 0 && a[j-1] > a[j]) {
				int t =a[j-1];
				a[j-1] = a[j];
				a[j] = t;
				j--;
			}
		}
		MaxHeap.printArray(a);
	}
	
	public static void main(String[] args) {
		isort();
	}

}
