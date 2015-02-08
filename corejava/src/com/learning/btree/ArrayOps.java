package com.learning.btree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArrayOps {
	

	
	public static void findNumMatrix() {
		int[][] mat = {{1,3,8},{10,15,18},{22,26,29}};
		final int tofind = 9;
		int flatl = mat.length*mat.length - 1;
		System.out.println("is pr "+ex(mat,0,flatl,tofind,mat.length));
	}
	
	public static boolean ex(int[][] a, int l, int r, int srch, int sz) {
		if(l < r) {
			int mid = l + (r-l)/2;
			int rr = mid / sz;
			int ll = mid % sz;
			if(srch < a[rr][ll]) {
				return ex(a, l, mid, srch, sz);
			} else if (srch > a[rr][ll]) {
				return ex(a, mid+1, r, srch, sz);
			} else if (srch == a[rr][ll]) {
				return true;
			}
		}
		return false;
	}
	
	public static int[] findMaxSumSubArray(int[] a) {
		int[] ids = new int[2];
		ids[0] = ids[1] = 0;
		int minidx = -1;
		int minsum = 0;
		int maxsum = 0;
		int sum = 0;
		for(int i = 0; i<a.length; i++) {
			sum += a[i];
			if(sum < minsum) {
				minsum = sum;
				minidx = i;
			} else if(sum - minsum > maxsum) {
				maxsum = sum - minsum;
				ids[0] = minidx + 1;
				ids[1] = i;
			}
		}
		System.out.println("maxsum "+maxsum+" ids ["+ids[0]+" "+ids[1]+"]");
		return ids;
	}
	
	public static int findMaxIncreasingSubArrayLength(int[] a) {
		int[] subarray = new int[a.length];
		int len = 1;
		subarray[0] = 0;
		
		for(int i=1; i<a.length; i++) {
			if(a[i] < subarray[0]) {
				subarray[0] = a[i];
			}else if(a[i] > subarray[len-1]) {
				subarray[len++] = a[i];
			}else {
				subarray[CeilIndex(a, -1, len - 1, a[i])] = a[i];
			}
		}
		System.out.println("len "+len);
		return len;
	}
	
	static int CeilIndex(int A[], int l, int r, int key) {
	    int m = 0;
	 
	    while( r - l > 1 ) {
	        m = l + (r - l)/2;
	        //m = (A[m] >= key) ? r : l; // ternary expression returns an l-value
			if(key < A[m]) {
				r = m; 
			} else if(key > A[m]) {
				l = m+1;
			}
	    }
	 
	    return r;
	}
	
	public static void maxvalue() {
		int[] W = {20, 8, 60, 55, 40, 70, 85, 25, 30, 65, 75, 10, 95, 50, 40, 10};
		int[] V = {65, 35, 245, 195, 65, 150, 275, 155, 120, 320, 75, 40, 200, 100, 220, 99};
		int max = knapsack(130, W, V, 16);
		System.out.println("max knapsack "+max);
	}
	
	public static int knapsack(int W, int wt[], int val[], int n){
		if(n == 0 || W == 0) return 0;
		if(wt[n-1] > W) {
			//System.out.println("n-1 "+W);
			return knapsack(W, wt, val, n-1);
		} else {
			//System.out.println("else "+W+ " " +n);
			return Math.max(val[n-1] + knapsack(W-wt[n-1], wt, val, n-1), knapsack(W, wt, val, n-1));
		}
	}
	
	public static int countoccur(int n, int[]a) {
		int l=0;
		int r=a.length -1;
		
		int foundidx = -1;
		int ct = 0;
		while(l < r) {
			int m = l+(r-l)/2;
			if(n < a[m]) {
				r = m-1;
			} else if(n > a[m]) {
				l = m+1;
			} else {
				foundidx = m;
				break;
			}
		}
		if(foundidx == -1) return 0;
		int ii = foundidx;
		int jj = foundidx;
		int val = a[foundidx];
		while(ii >= 0 && a[ii] == val) {
			ct++;
			ii--;
		}
		while(jj+1 < a.length && a[jj+1] == val) {
			ct++;
			jj++;
		}
		System.out.println("ct "+ct);
		return ct;
	}
	
	public static void countMaxAlive() {
		int[] births = {1901, 1901, 1901, 1903, 1910, 1910, 1921, 1935, 1950, 1971};
		int[] deaths = {1945, 1948, 1962, 1973, 1980, 1995, 1996, 1997, 1999};
		int[] alive = new int[100];
		int runningsum = 0;
		for(int i=0; i<100; i++) {
//			int b = countoccur(1900+i, births);
//			runningsum+=b;
//			alive[i] += runningsum;
			for(int k=0; k<births.length; k++) {
				if(births[k] == 1900+i) {
					alive[i] = ++runningsum;
				}else if(births[k] > 1900+i) {
					break;
				}
			}
//			int d = countoccur(1900+i, deaths);
//			runningsum-=d;
//			alive[i] -= runningsum;
			for(int k=0; k<deaths.length; k++) {
				if(deaths[k] == 1900+i) {
					alive[i] = --runningsum;
				}else if(deaths[k] > 1900+i) {
					break;
				}
			}
		}
		for(int j=0; j<alive.length; j++) {
			if(alive[j] > 0) {
				System.out.println("index "+j+" val "+alive[j]);
			}
		}
	}
	
	public static int intersectionSorted(int[] a1, int[] a2) {
		int common = 0;
		int i=0, j=0;
		if(a1 == null || a2 == null || a1.length == 0 || a2.length == 0) {return 0;}
		while(true) {
			if(i >= a1.length || j>=a2.length) {break;}
			if(a1[i] < a2[j]) {
				i++;
			} else if( a1[i] > a2[j]) {
				j++;
			} else {
				common++;
				i++;
				j++;
			}
		}
		System.out.println("common "+common);
		return common;
	}
	
	public static void merge(int[] a1, int[] a2) {
		int l1 = a1.length;
		int l2 = a2.length;
		int ll = l1+l2;
		int[] r = new int[ll];
		int i=0, j=0, k=0;

		while(i < l1 && j < l2) {
			if(a1[i] < a2[j]) {
				r[k] = a1[i];
				i++;
				k++;
			} else {
				r[k] = a2[j];
				j++;
				k++;				
			}
		}
		if(j < l2-1) {
			for(;j<l2;j++) {
				r[k] = a2[j];
				k++;
			}
		}
		if(i < l1-1) {
			for(;i<l1;i++) {
				r[k] = a1[i];
				k++;
			}
		}
		System.out.println("merge ");
		for(int p:r) {
			System.out.println(p);
		}
	}
	
	public static void partition(int[] a) {
		int len = a.length;
		int pivot = a[len - 1];
		int j = 0;
		for(int i=0; i<len; i++) {
			if(a[i] <= pivot) {
				if(j != i) {
					int tmp = a[i];
					a[i] = a[j];
					a[j] = tmp;
				}
				j++;
			}
		}
		/*int tmp = a[j];
		a[j] = a[len - 1];
		a[len -1] = tmp;*/
		for(int i:a) {
			System.out.println(i);
		}
	}

	public static void main(String[] args) {

		findNumMatrix();
		findMaxSumSubArray(new int[]{0, -12, 23, 34, -5, 21, -8, 11, -6, -8});
		findMaxIncreasingSubArrayLength(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15});
		maxvalue();
		int[] a = {-1,0,0,4,5,8,8,8,9,17,22,22,45};
		countoccur(8,a);
		//countMaxAlive();
		intersectionSorted(new int[]{1,5,8,8,11}, new int[]{1,2,3,8,11,11,25,35,45});
		partition(new int[]{2,8,7,12,4,5,6,9});
	}
}
