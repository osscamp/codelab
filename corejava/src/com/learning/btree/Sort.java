package com.learning.btree;

import java.util.Random;

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
	
	public static void selectionSort(int[] a) {
		for(int i = 0; i<a.length; i++) {
			int min = i;
			for(int j=i+1; j<a.length; j++) {
				if(a[j] < a[min]) {
					min = j;
				}
			}
			int t = a[min];
			a[min] = a[i];
			a[i] = t;
			
		}
		MaxHeap.printArray(a);
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
		int j = l;
		for(int i=l; i<r; i++) {
			if(a[j] < pivot) {
				int tmp = a[j];
				a[j] = a[i];
				a[i] = tmp;
				j++;
			}
		}
		int tmp = a[j];
		a[j] = pivot;
		a[r - 1] = tmp;
		System.out.println("pivot pos "+j);
		return j;
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
	
	//sort num in range of 1-10 - simpler version , would not work for non integer/complex data as indices are assumed = data
	//can be used for sorting string
	public static void countSortAlt(int[] a) {
		int[] idx = new int[10];
		for(int v : a) {
			idx[v]++;
		}
		int finalidx = 0;
		for(int k=0;k<idx.length;k++) {
			int freq = idx[k];
			while(freq > 0) {
				a[finalidx++] = k;
				freq--;
			}
		}
		MaxHeap.printArray(a);
	}
	
	
	// sort in O(n), letters between 'a'<= char <= 'z'
	public static void sort()
	{
		String input = "bookdfdgd";
	    char[] a = input.toCharArray();
	    int[] ab = new int[256]; //26
	    char[] result = new char[a.length];
	    for(int i=0; i<a.length; i++) {
	        char c = a[i];
	        ab[(int)c]++;
	    }
	    for(int i=1; i<ab.length; i++) {
	        ab[i] = (ab[i] + ab[i-1]);
	    }
	    for(int i=0; i<a.length; i++)  {        
	        result[--ab[a[i]]] = a[i]; 
	    }
	    String res = String.valueOf(result); 
	    System.out.println(res);
	}
	
	public static void lsdRadixSort(){
		String[] a = {"AFG",
				"ALB",
				"DZA",
				"ASM",
				"AND",
				"BLZ",				
				"BMU",
				"AIA",
				"BEN"
		};

		String [] ra = new String[a.length];
		String[] prevra = a;
		for(int j=2; j>=0; j--) {
			int[] k = new int[256];
			char[] pa = new char[a.length];
			for(int i = 0; i<a.length; i++) {
				String is = a[i];
				pa[i] = is.charAt(j);
			}
			for(int i=0; i<pa.length; i++) {
				k[pa[i]]++;
			}
			for(int i=1; i<k.length; i++) {
				k[i] = k[i] + k[i-1];
			}
			//char[] result = new char[pa.length];
			for(int i=pa.length-1; i>=0; i--) {
				ra[--k[pa[i]]] = prevra[i];
			}
			for(int i=0; i<ra.length; i++) {
				prevra[i] = ra[i];
			}
		}
		for(String s : ra) {
			System.out.println(s);
		}

	}
	
	public static void sort3Values2()
	{
		int[] a = {0,1,0,0,1,2,0,0};
		int lo = 0;
		int mid = 0;
		int hi = a.length-1;
		while(mid <= hi) {
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

	
	public static void shellSort(int[] a) {
		//shell sort is similar to optimized insertion sort. Complexity for this method is proportional to N3/2
		//h sequence is chosen to be H*3 + 1
		a = new int[]{8,11,0,43,62,13,7,18};
		int N = a.length;
		int h = 1;
		while(h < N/3) {
			h = h*3+1;
		}
		while(h >= 1) {
			for(int i = h; i<N; i++) {
				int j = i;
				while(j >= h && a[j-h] > a[j]) {
					int t = a[j-h];
					a[j-h] = a[j];
					a[j] = t;
					j -= h;
				}
			}
			h = h/3;
		}MaxHeap.printArray(a);
	}
	
	public static void insertionSortSentinel() {
		int[] a = {3,5,0,2,0,9};
		//move smallest element to its position and then insertion sort, removing the bounds check.
		if(a.length < 2) { return; }
		int minidx = 0;
		for(int i=0; i<a.length; i++) {
			if(a[i] < a[minidx]) {
				minidx = i;
			}
		}
		swap(a, minidx, 0);
		MaxHeap.printArray(a);

		for(int i = 1; i<a.length; i++) {
			int j = i;
			while(a[j-1] > a[j]) {
				swap(a, j-1, j);
				j--;
			}
		}
		System.out.println("ins--sentinel");
		MaxHeap.printArray(a);
	}
	
	public static void insertionSortNoSwap() {
		int[] a = {2,0,2,2,0,2};

		for(int i = 1; i<a.length; i++) {
			int j = i;
			int tmp = a[j];
			while(j > 0 && a[j-1] > tmp ) {
					a[j] = a[j-1];
					j--;
			}
			a[j] = tmp;
		}
		System.out.println("ins--no swap");
		MaxHeap.printArray(a);
	}
	
	public static void mergeSortBU() {
		int[] a = {20,12,15,29,23,17,22,21,40,26,51};
		//int N = 100000;
		//int[] a = getRandomArray(N);

		long startNanos = System.nanoTime();
		int sz = 1;
		while(sz < a.length) {
			for(int i=0; i<a.length-sz; i += sz+sz) {
				mergeBU(a, i, i+sz-1, Math.min(i+sz+sz-1, a.length-1));
			}
			sz += sz;
		}
		long endNanos = System.nanoTime();
		System.out.println("time taken "+(endNanos-startNanos)/1000+"us");
	}
	
	public static void mergeBU(int[] a, int l, int m, int r) {
		int[] L = new int[m-l+2];
		int[] R = new int[r-m+1];
		L[L.length-1] = Integer.MAX_VALUE;
		R[R.length-1] = Integer.MAX_VALUE;
		for(int p=0; p<L.length-1; p++) {
			L[p] = a[l+p];
		}
		for(int q=0; q<R.length-1; q++) {
			R[q] = a[m+q+1];
		}
		int k = l, i=0, j=0;
		while(k <= r) {
			if(L[i] <= R[j]) {
				a[k] = L[i];
				i++;
			} else {
				a[k] = R[j];
				j++;
			}
			k++;
		}
	}
	
	public static void optimizedMergeBU(int[] a, int l, int m, int r) {
		if(r-l <= 27) {
			//insertionsort
			for(int i=l+1; i<=r; i++) {
				int j = i;
				int tmp = a[j];
				while(j > l && a[j-1] > tmp) {
					a[j] = a[j-1];
					j--;
				}
				a[j] = tmp;
			}
			//System.out.println("insertion sorted ");
			//MaxHeap.printArray(a);
			return;
		}
/*		if((a[m] < a[m+1] && a[l] < a[m+1]) 
				|| (a[l] > a[m+1] && a[l] > a[r])) {
			//subarrays already sorted
			//System.out.println("returning "+a[l]+" "+a[m]+" "+ a[Math.min(m+1,r)]+" "+a[r]);
			return;
		}*/
		int[] L = new int[m-l+2];
		int[] R = new int[r-m+1];
		L[L.length-1] = Integer.MAX_VALUE;
		R[R.length-1] = Integer.MAX_VALUE;
		for(int p=0; p<L.length-1; p++) {
			L[p] = a[l+p];
		}
		for(int q=0; q<R.length-1; q++) {
			R[q] = a[m+q+1];
		}
		int k = l, i=0, j=0;
		while(k <= r) {
			if(L[i] <= R[j]) {
				a[k] = L[i];
				i++;
			} else {
				a[k] = R[j];
				j++;
			}
			k++;
		}
		//MaxHeap.printArray(a);

	}
	
	public static int[] getRandomArray(int N) {
		int[] a = new int[N];
		Random rdm = new Random();
		for(int i=0; i<N; i++) {
			a[i] = rdm.nextInt(N*2);
		}
		return a;
	}
	
	public static void findMedian() {
		
		int[] a = {32,4};
		int l = a.length;
		int med = -1;
		
		if(l%2==0){
			int m1 = kthlargestR(a, l/2-1, 0 , a.length-1);
			int m2 = kthlargestR(a, l/2, 0 , a.length-1);
			med = (m1+m2)/2;
		}else{
			med = kthlargestR(a, l/2, 0 , a.length-1);
		}
		MaxHeap.printArray(a);
		System.out.println("median = "+med);
	}
	
	public static int kthlargestR(int[] a, int k, int l, int r) {
		if(l <= r) {
			int p = partitionmed(a, l, r);
			if(k==p) {
				return a[p];
			}else if(k < p){
				return kthlargestR(a, k, l, p-1);
			}else {
				return kthlargestR(a, k, p+1, r);
			}
		}
		return -1;
	}

	public static int partitionmed(int[] a, int l, int r) {
		int pivot = a[r];
		int j = l;
		for(int i=l; i<r; i++) {
			if(a[i] < pivot) {
				int t = a[j];
				a[j] = a[i];
				a[i] = t;
				j++;
			}
		}
		a[r] = a[j];
		a[j] = pivot;
		return j;
		
	}
	
	public static void findKNearest(){
		int K=4, X=45;
		int[] a = {12,16,22,30,35,39,42,45,45,50,53,55,56};
		int l = 0;
		int r = a.length-1;
		int L = a.length;
		int idx = -1;
		int[] c = new int[K];
		while(l <= r) {
			int mid = l + (r-l)/2;
			if(X < a[mid] ){
				r = mid - 1;
			}else if(X > a[mid]){
				l = mid+1;
			}else{
				idx = mid;
				break;
			}
		}
		int ct = 0;
		int ll = r;
		int rr = l;
		if(idx != -1){
			ll = idx - 1;
			rr = idx + 1;
			c[c.length-1] = a[idx];
			K--;
		}else{
			ll = r;
			rr = l;
		}
		while(ct < K){
			if(ll >= 0 && ll < L && rr < L && rr >= 0) {
				if(Math.abs(X-a[ll]) < Math.abs(X-a[rr])) {
					c[ct++] = a[ll--];
				}else if(Math.abs(X-a[ll]) > Math.abs(X-a[rr])) {
					c[ct++] = a[rr++];
				}else{
					c[ct++] = a[ll--];
					c[ct++] = a[rr++];
				}
			}else if (ll < 0 && rr < L){
				c[ct++] = a[rr];
				rr++;				
			}else if (ll >= 0 && rr >= L && ll < L){
				c[ct++] = a[ll];
				ll--;				
			}
		}
		MaxHeap.printArray(c);
	}
	
	public static void main(String[] args) {
		int[] a = {4,3,5,6,5,4,8,2,4,3};
		

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
		sort3Values2();
		sortStrings(new String[]{"algo","galo","altus","sell", "belt"});
		selectionSort(a);
		shellSort(a);
		insertionSortSentinel();
		insertionSortNoSwap();
		mergeSortBU();
		countSortAlt(a);
		lsdRadixSort();
		sort();
		findMedian();
		findKNearest();
	}
}
