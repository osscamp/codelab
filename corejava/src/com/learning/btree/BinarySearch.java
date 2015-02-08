package com.learning.btree;

public class BinarySearch {
	
	public static boolean binarySearch(int[] arr, int l, int r, int toFind) {
		if (l <= r) {
			int mid = l+(r-l)/2;
			if(arr[mid] == toFind) {
				return true;
			}
			else if(toFind < arr[mid]) {
				return binarySearch(arr, l, mid-1, toFind);
			} else {
				return binarySearch(arr, mid+1, r, toFind);
			}
		}
		return false;
	}
	
	public static boolean binarySearchFunctional(int[] arr, int key) {
		int l = 0;
		int r = arr.length - 1;
		int mid = 0;
		while ( l <= r ) {
			mid = (l+r)/2;
			if(key < arr[mid]) {
				r = mid - 1; 
			} else if(key > arr[mid]) {
				l = mid + 1;
			}else {
				return true;
			}
		}
		return false;
	}
	
	public static int countOnes(int[] a) {
		int l = 0;
		int r = a.length - 1;
		int mid = 0;
		int ctr = 0;
		while(l <= r) {
			mid = (l+r)/2;
			if(a[mid] == 1){
				ctr += (r - mid)+1;
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		System.out.println("numones "+ctr); 
		return ctr;
	}
	
	public static void main(String[] args) {
		int[] iArray = {1,2,4,5,6,9,13,18,21,25,28,29,33,34,37,49};
		//int[] iArray = {1,2};
		boolean isThere = binarySearch(iArray, 0, iArray.length - 1, 29);
		System.out.println("isthr "+isThere);
		isThere = binarySearchFunctional(iArray, 49);
		System.out.println("isthr "+isThere);	
		countOnes(new int[] {0,0,0,0,1});
	}

}
