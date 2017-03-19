package com.learning.btree;

import java.util.List;

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
	
	public static boolean binarySearchDescSort(int[] arr, int l, int r, int toFind) {
		if (l <= r) {
			int mid = l+(r-l)/2;
			if(arr[mid] == toFind) {
				return true;
			}
			else if(toFind < arr[mid]) {
				return binarySearchDescSort(arr, mid+1, r, toFind);
			} else {
				return binarySearchDescSort(arr, l, mid-1, toFind);
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
	
	public static int binarySearchString(List<String> arr, String key, int l, int r) {
		if(l < 0 || r > arr.size()-1) {
			throw new IllegalArgumentException();
		}
		int mid = 0;
		while ( l <= r ) {
			mid = (l+r)/2;
			if(key.compareTo(arr.get(mid)) < 0) {
				r = mid - 1; 
			} else if(key.compareTo(arr.get(mid)) > 0) {
				l = mid + 1;
			}else {
				return mid;
			}
		}

		return -1;
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
	
	//search in bitonic array - increasing sequence of integers followed immediately by a decreasing sequence of integers
	public static void binarySearchBitonic() {
		int[] arr = new int[]{0,11,17,12,9,6,5};
		int key = 5;
		int l = 0;
		int r = arr.length - 1;
		int mid = 0;
		int ip = 0;

		while(l <= r) {
			mid = (l+r)/2;

			int vl = arr[Math.max(0,mid-1)];
			int vm = arr[mid];
			int vr = arr[Math.min(r,mid+1)];
			System.out.println("l "+l+" vl "+vl+" mid "+mid+" vm "+vm+" r "+r+" vr "+vr);
			ip = mid;

			if(vl <= vm && vm >= vr) {
				ip=mid;
				break;
			}else if(vl < vm && vm < vr) {
				l = mid+1;
			}else if(vl > vm && vm > vr) {
				r = mid - 1;
			}
			
		}
		l = 0;
		r = arr.length-1;
		System.out.println("pivotv "+arr[ip]);
		//if(key >= arr[0] && key <= arr[ip]) {
			boolean found = binarySearch(arr, l, ip, key);
			System.out.println("found in bitonic "+found);
		//}else if(key <= arr[ip] && key >= arr[r]) {
			found = binarySearchDescSort(arr, ip, r, key);
			System.out.println("found in bitonic decreasing part "+found);
		//}else{
		//	System.out.println("Not found in bitonic");
		//}
	}
	
	public static boolean binarySearchInDescendingRotatedSorted(int[] a, int key) {
		//{1,2,21,17,13,12,11,5}
		int l = 0;
		int r = a.length-1;
		int mid = 0;
		if(a == null || r == 0) { return false; }
		while(l <= r) {
			mid = l +(r-l)/2;
			System.out.println("mid "+a[mid]+" l "+a[l]+" r "+a[r]+" key "+key);
			if(key == a[l] || key == a[r]) {
				return true;
			}			
			else if(key == a[mid]){
				return true;
			} else if(key < a[mid] && key > a[r]) {
					l = mid+1;
			} else if(key > a[mid] && key > a[r]) {
				if(a[mid] < a[r]) {
					l = mid+1;
				} else {
					r = mid - 1;
				}
			}
			else if(key < a[mid] && key < a[r]) {
				r = mid - 1;
			}
			else if(key > a[mid] && key < a[r]) {
				l = mid+1;
			}
		}
		return false;	
	}
	
	public static int leastIndexBinarySearch(int[] a, int key) {
		//find least index of key in array with dupes
		int l = 0;
		int r = a.length-1;
		int mid = -1;
		int mmid = -1;
		while (l <= r) {
			mid = l+(r-l)/2;
			if(key < a[mid]) {
				r = mid -1 ;
			}else if(key > a[mid]) {
				l = mid + 1;
			} else {//match
				r = mid;
				mmid = l+(r-l)/2;
				System.out.println("mmid "+mmid);
				if(a[mmid] == key) {
					r = mmid -1;
				}else {
					l = mmid + 1;
				}
			}
		}
		int rtVal = mmid != -1 ? mid : -1;
		System.out.println("rtval "+mmid);
		return rtVal;
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
	
	public static int safeDrop() {
		//get min attempts to find safe fall for egg.
		//this dyn prog problem
		int N = 25; //20 story building
		int l = 0;
		int r = N-1;
		int used = 0;
		int mid = -1;
		boolean breaks = fall(r);
		if(!breaks) return used;
		else {
			while(l < r) {
				mid = l+(r-l)/2;
				breaks = fall(mid);
				used++;
				if(breaks) {
					r = mid - 1;
				} else {
					l = mid  + 1;
				}
			}
		}
		System.out.println("safe "+mid);
		return used;
	}
	
	public static void floorCeil() {
		int flr = -1;
		int ceil = -1;
		int[] a = {2,5,9,13,21,25,100};
		int key = 3;
		int l = 0, r = a.length - 1;
		int mid = l;
		while(l <= r) {
			mid = l+(r-l)/2;
			if(key < a[mid]) {
				r = mid - 1;
			}else if(key > a[mid]) {
				l = mid + 1;
			} else {
				break;
			}
		}
		System.out.println(l+"  "+r+" "+mid);
		System.out.println("mid reduc "+mid);
		flr =  a[mid] <= key? a[mid] : a[Math.max(0, mid-1)];
		ceil = a[mid] >= key? a[mid] : a[Math.min(a.length-1, mid+1)];
		System.out.println("flr "+flr+" ceil "+ceil);
	}
	
	public static void majority(){
		String[] a = {"a","s","c","b","c","c","c"}; //frq count using hashmap
		String[] as = {"a","a","b","c","c","c","c","c","d"};
		int len = as.length;
		String maj = "";
		for(int i = 0; i<=len/2; i++){
			if(as[i].equals(as[Math.min(len-1, i+len/2)])) {
				maj = as[i];
				break;
			}
		}
		System.out.println(maj);
	}
	
	public static void majorityLinear(){
		String[] a = {"c","a","c","c","c","b","a","c","b"}; //frq count using hashmap
		int midx = 0;
		int i = 1;
		int majct = 1;
		for(; i<a.length; i++){
			if(a[midx] == a[i]) {
				majct++;
			}else{
				majct--;
			}if(majct == 0) {
				midx = i;
				majct = 1;
			}
		}
		System.out.println("majority "+a[midx]+" maj ct "+majct);
	}
	
	public static boolean fall(int n) {
		System.out.println("check fall "+n);
		if(n >= 1) {
			return true;
		}
		return false;
	}
	
	public static void binarySearchRotatedSorted() {
		//int[] a = {11,13,17,21,2,5,8};
		int[] a = {17,21,2,5,8,11,13,15};
		int key = 22;
		int l = 0;
		int r = a.length - 1;
		while(l <= r) {
			int mid = l +(r-l)/2;
			if(a[mid] == key) {
				System.out.println("found in rotated "+key);
				break;
			} else if(a[mid] > a[r]) {//rotation in right
				if(key > a[mid] || key < a[l]) {
					l = mid + 1;
				} else {
					r = mid - 1;
				}
			} else if(a[mid] < a[l]) {
				if(key < a[mid] || key > a[r]) {
					r = mid - 1;
				} else {
					l = mid + 1;
				}				
			} else {
				if(key < a[mid]) {
					r = mid - 1;
				} else {
					l = mid + 1;
				}
			}

		}
	}
	
	public static void rotationPoint() {
		int[] a = {3,4,7,10,13,15,2};
		int rotationPt = -1;
		//2,4,7,10,13
		//15,18,2,4,7,10,13
		int l = 0;
		int r = a.length-1;
		while(l <= r) {
			int mid = l+(r-l)/2;
			int al = a[l];
			int ar = a[r];
			int am = a[mid];
			if(mid > 0) {
				if(am < a[mid-1]){
					rotationPt = mid; break;
				}
			}
			if(mid < a.length-1) {
				if(am > a[mid+1]){
					rotationPt = mid; break;
				}
			}
			if(ar < am) {
				l = mid+1;
			}else if(am < al) {
				r = mid - 1;
			}else{
				break;
			}
		}
		System.out.println("rot "+rotationPt);
	}
	
	public static void binarySearchUnknownLength() {
		int key = 21;
		int[] a = {3,6,8,11,12,19,21};
		int l = 0;
		int r = Integer.MAX_VALUE;
		while (l<= r) {
			int mid = l + (r-l)/2;
			try{
				int k = a[mid];
			}catch(Exception e) {
				r = mid-1;
				continue;
			}
			if(key > a[mid]) {
				l = mid+1;
			}else if(key < a[mid]) {
				r = mid - 1;
			}else {
				System.out.println("true");
				break;
			}
		}
	}
	
	public static void firstAndLastIndex() {
		int[] a = { 5, 5, 5, 5, 5 ,8, 15, 75}; //x=5
		int x = 8;
		int l = 0;
		int r = a.length-1;
		int[] idx = new int[2];
		idx[0] = Integer.MAX_VALUE;
		idx[1] = Integer.MIN_VALUE;
		bsrch(a, l, r, x, idx);
		MaxHeap.printArray(idx);
	}
	
	public static void bsrch(int[] a, int l, int r, int k, int[] idx) {
		if( l <= r) {
			int mid = l+(r-l)/2;
			if(k < a[mid]) {
				bsrch(a, l, mid-1, k, idx);
			} else if(k > a[mid]){
				bsrch(a, mid+1, r, k, idx);
			} else {
				if(mid < idx[0]) idx[0] = mid;
				if(mid > idx[1]) idx[1] = mid;
				if(mid > 0 && a[mid-1] == k){
					if(mid-1 <= idx[0])
						idx[0] = mid-1;
					bsrch(a, l, mid-1, k, idx);
				}if(mid < a.length-1 && a[mid+1] == k){
					if(mid+1 >= idx[1])
						idx[1] = mid+1;
					bsrch(a, mid+1, r, k, idx);
				}
			}
		}
	}
	
	public static void floor() {
		int[] a = {1,8,10,11,12};
		int x = 0;
	    int l = 0;
	    int r = a.length -1;
	    while(l <= r) {
	        int mid = l + (r-l)/2;
	        if(x < a[mid]) {
	            r = mid-1;
	        }else if(x > a[mid]) {
	            l = mid + 1;
	        }else {
	            System.out.println("flr:"+a[mid]);
	            break;
	        }
	    }
	    System.out.println("l:"+l+" r:"+r);
	    if(r < 0) System.out.println("flr: -1");
	    else if(l > a.length) System.out.println("flr:"+a[r]);
	    else System.out.println("flr:"+a[r]);
	    
	}
	
	public static void binarySearchRotated() {
		//int[] a = { 10, 1,2, 3, 5, 6, 7, 8,9 ,};
		int[] a = {5, 3};
		int toFind = 3;
	    int l=0;
	    int r = a.length-1;
	    while(l<=r) {
	        int m = l+(r-l)/2;
	        if(toFind == a[m]) {
	        	System.out.println("iidx "+m);
	        	return;
	        }
	        if(a[m] < a[l]) {//rotn in left
	        	if(toFind > a[m] && toFind <= a[r]) {
	        		l = m+1;
	        	}else {
	        		r = m-1;
	        	}
	        }else if(a[m] > a[r]){ //in right
	        	if(toFind < a[m] && toFind >= a[l]) {
	        		r = m-1;
	        	}else{
	        		l = m+1;
	        	}
	        	
	        }else if(a[m] >= a[l] && a[m] <= a[r]) {//no rotn
	        	if(toFind > a[m]) {
	        		l = m+1;
	        	}else{
	        		r = m-1;
	        	}
	        	
	        }else if(a[m] <= a[l] && a[m] >= a[r]) {//reverse
	        	if(toFind > a[m]) {
	        		r = m-1;
	        	}else{
	        		l = m+1;
	        	}	        	
	        }
	    }
	    System.out.println("iidx "+(-1));
	}
	

		
	public static void main(String[] args) {
		int[] iArray = {1,2,4,5,6,9,13,18,21,25,28,29,33,34,37,49};
		//int[] iArray = {1,2};
		boolean isThere = binarySearch(iArray, 0, iArray.length - 1, 1);
		System.out.println("isthr "+isThere);
		isThere = binarySearchFunctional(iArray, 11);
		System.out.println("isthr "+isThere);	
		countOnes(new int[] {0,0,1,1,1});
		binarySearchHistogram(new int[] {8,8,8,8,8,8,9,16,16,16,17});
		int[] a = {4,5,8,8,8,8,8,8,9,16,16,16,17};
		//binarySearchRange(new int[2], a, 8, 0, a.length-1);
		binarySearchSparse();
		System.out.println(binarySearchInDescendingRotatedSorted(new int[]{1,2,5,9,10,11,12,23,21,17,13}, 21));
		System.out.println(binarySearchInDescendingRotatedSorted(new int[]{23,21,17,13,12,11,10,9,5,2,1}, 21));
		leastIndexBinarySearch(new int[]{1,3,5,5,11,13,13,13}, 6);
		System.out.println(safeDrop());
		floorCeil();
		majorityLinear();
		binarySearchRotatedSorted();
		rotationPoint();
		binarySearchBitonic();
		binarySearchUnknownLength();
		firstAndLastIndex();
		floor();
		binarySearchRotated();
	}

}
