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
		System.out.println("left ctr at exit "+l);
		System.out.println("right ctr at exit "+r);

		return false;
	}
	
	public static int binarySearchIndex(int[] arr, int key, int l, int r) {
		int mid = 0;
		while ( l <= r ) {
			mid = l+(r-l)/2;
			if(key < arr[mid]) {
				r = mid - 1; 
			} else if(key > arr[mid]) {
				l = mid + 1;
			}else {
				return mid;
			}
		}
		return -1;
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
	
	public static void binarySearchHistogram(int[] arr) {
		//int[] arr = {8,8,8,8,8,8,9,16,16,16,17};
		int i=0;
		while(i<arr.length) {
			int end = findEndIdx(arr, arr[i], i, arr.length);
			System.out.println(arr[i] +":" +(end-i+1));
			i = end+1;
		}
	}
	
	public static int findEndIdx(int[] a, int n, int l, int r) {
		int idx = -1;
		while(l<=r) {
			int mid = l+(r-l)/2;
			if(n < a[mid]) {
				r = mid - 1;
			} else if(n > a[mid]) {
				l = mid + 1;
			} else {
				idx = mid;
				if(idx < a.length-1 && a[idx] == a[idx+1]) {
					return findEndIdx(a, n, mid+1, r);
				} else {
					return idx;
				}
			}
		}
		return idx;
	}
	
	public static int findEndIndex(int[] a, int target, int l, int r) {
		while(l<=r) {
			int mid = l+(r-l)/2;
			if(target < a[mid]) {
				r = mid - 1;
			} else if(target > a[mid]) {
				l = mid + 1;
			} else {
				if(mid < a.length-1 && a[mid+1] == target) {
					return findEndIndex(a, target, mid+1, r);
				} else {
					return mid;
				}
			}
		}
		return -1;
	}
	
	public static int findStartIndex(int[] a, int target, int l, int r) {
		while(l<=r) {
			int mid = l+(r-l)/2;
			if(target < a[mid]) {
				r = mid - 1;
			} else if(target > a[mid]) {
				l = mid + 1;
			} else {
				if(mid > 0 && a[mid-1] == target) {
					return findStartIndex(a, target, l, mid-1);
				} else {
					return mid;
				}
			}
		}
		return -1;
	}
	
	public static void binarySearchRange(int[] idx, int[] a, int target, int l, int r) {
		if(l<r) {
			int mid = l+(r-l)/2;
			if(target < a[mid]) {
				r = mid - 1;
			} else if(target > a[mid]) {
				l = mid + 1;
			} else {
				idx[0] = mid;
				idx[1] = mid;
				if(mid > 0 && a[mid-1] == target) {
					int lindex = findStartIndex(a, target, l, mid-1);
					idx[0] = lindex;
				} 
				if(mid < a.length-1 && a[mid+1] == target) {
					int rindex = findEndIndex(a, target, mid+1, r);
					idx[1] = rindex;
				}
			}
		}
		MaxHeap.printArray(idx);
	}
	
	public static void binarySearchSparse() {
		String[] a = {"","","","art","","bst","","","crane","","dig","","","",""};
		String s = "dig";
		int idx = binarySearchSparseR(a, s, 0, a.length-1);
		System.out.println("indx = "+idx);
	}
	
	public static int binarySearchSparseR(String[] a, String s, int l, int r) {
		int ret = -1;
		if(l <= r) {
			int mid = l+(r-l)/2;
			if(!a[mid].isEmpty()) {
				if(s.compareTo(a[mid]) < 0) {
					return binarySearchSparseR(a, s, l, mid-1);
				}
				else if(s.compareTo(a[mid]) > 0) {
					return binarySearchSparseR(a, s, mid+1, r);
				} else {
					return mid;
				}
			} else {
				ret = binarySearchSparseR(a, s, l, mid-1);
				if( ret > 0) {
					return ret;
				}
				ret = binarySearchSparseR(a, s, mid+1, r);
				if( ret > 0) {
					return ret;
				}
			}
		}
		return ret;
	}
	
	public static void main(String[] args) {
		int[] iArray = {1,2,4,5,6,9,13,18,21,25,28,29,33,34,37,49};
		//int[] iArray = {1,2};
		boolean isThere = binarySearch(iArray, 0, iArray.length - 1, 5);
		System.out.println("isthr "+isThere);
		isThere = binarySearchFunctional(iArray, 8);
		System.out.println("isthr "+isThere);	
		countOnes(new int[] {0,0,1,1,1});
		binarySearchHistogram(new int[] {8,8,8,8,8,8,9,16,16,16,17});
		int[] a = {4,5,8,8,8,8,8,8,9,16,16,16,17};
		binarySearchRange(new int[2], a, 8, 0, a.length-1);
		binarySearchSparse();
	}

}
