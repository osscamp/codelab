package com.learning.btree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
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
	
	public static int findMaxIncreasingSubArrayLength(int[] a) {
		int[] subarray = new int[a.length];
		int len = 1;
		subarray[0] = a[0];
		
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
	
	///merge sorted array b into sorted array a, that has space at end for b
	public static void mergeInPlace(int[] a, int[] b) {
		if(a.length <= b.length) {
			return;
		}
		int i = a.length-b.length-1;
		int j = b.length-1;
		int k = a.length-1;
		while(i>=0 && j>=0) {
			if(a[i] > b[j]) {
				a[k] = a[i];
				i--;
			} else {
				a[k] =b[j];
				j--;
			}
			k--;
		}
		while(i>=0 && j<0) {
			a[k--] = a[i--];
		}
		while(j>=0 && i<0) {
			a[k--] = b[j--];
		}
		System.out.println("merged ");
		MaxHeap.printArray(a);
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
	
	public static boolean isSumPresent(int[] a, int sum) {
		int lp = 0;
		int rp = a.length - 1;
		int rs = 0;
		while(lp < rp) {
			rs = a[lp] + a[rp];
			if(rs < sum) {
				lp++;
			} else if (rs > sum) {
				rp--;				
			} else {
				return true;
			}
			
		}
		return false;
	}
	
	public static boolean isThreeSum0(int[] a) {
		if(a == null) {
			return false;
		}
		Arrays.sort(a);
		int nn = 0;
		for(nn=0; nn<a.length - 2 && a[nn] < 0; nn++) {
			int one = a[nn];
			if(one > 0) return false;
			int img = -1*one;
			if(isSumPresent(a, img)) {
				return true;
			}
		}
		if(a[nn] == 0 && nn+2 < a.length &&(a[nn] == 0 && a[nn+1] == 0 && a[nn+2]==0)) {
			return true;
		}
		return false;
	}
	
	public static void findMaxDiffBackward() {
		int[] a = new int[]{1, 20, 5, 7, 16, -6, 4, 15};
		int max = Integer.MIN_VALUE;
		int maxdiff = Integer.MIN_VALUE;
		for(int i=a.length - 1; i>=0; i--) {
			if(a[i] > max) {
				max = a[i];
			}
			if(max - a[i] > maxdiff) {
				maxdiff = max-a[i];
			}
		}
		System.out.println("mmmax diff backward "+maxdiff);
	}
	
	public static void findMaxDiffForward() {
		int[] a = {1, 20, 5, 7, 16, -6, 4, 15};
		int maxdiff = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i=0; i<a.length; i++) {
			if(a[i] < min) {
				min = a[i];
			}
			if(a[i] - min > maxdiff) {
				maxdiff = a[i] - min;
			}
				
		}
		System.out.println("mmmax diff forward "+maxdiff);
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
	
	//incorrect, intervals need to be sorted by start time.
	public static void findIntervalRange() {
		int[][] tuples = {{1,3},{2,5},{8,10},{4,9}};
		int rangeend = tuples[0][1];
		for(int i=0; i<tuples.length-1; i++) {
			if(tuples[i+1][0] <= rangeend && tuples[i+1][1] > rangeend) {
				rangeend = tuples[i+1][1];
			} 
		}
		System.out.println("end range "+rangeend);
		
	}
	
	//zero out row or column containing 0
	public static void zeroOut() {
		int[][] a = {
			{1,35,21,26},
			{0,13,24,17},
			{1,8, 0, 26},
			{1,8, 21, 0}
		};
		boolean[] row = new boolean[a.length];
		boolean[] col = new boolean[a.length];
		for(int i=0; i<a.length; i++) {
			for(int j = 0; j<a.length; j++) {
				if(a[i][j] == 0 && !row[i] && !col[j]) {
					row[i] = true;
					col[j] = true;
					for(int k=0; k<a.length; k++) {
						a[i][k] = 0;
					}
					for(int k=0; k<a.length; k++) {
						a[k][j] = 0;
					}
				}
			}
		}
	}
	
	public static void randomizeShuffleArray(int[] a) {
		int len = a.length;
		int rnd = 0;
		Random rn = new Random();

		for(int i=len-1; i>0; i--) {
			//rnd = rn.nextInt(len - i) + i;
			rnd = rn.nextInt(i+1);
			System.out.println(rnd);
			int tmp = a[rnd];
			a[rnd] = a[i];
			a[i] = tmp;
		}
		MaxHeap.printArray(a);
	}
	
	/*
	 * not finished
	 */
	public static void maxGap() {
		int[] a = new int[]{1,12,14,16,3,6,27,18};
		int N = a.length;
		int lv = a[0];
		int rv = a[0];
		int lidx = 0;
		int ridx = 0;
		int maxd = 0;
		int maxvidx = 0;
		int i = 1;
		int maxv = 0;
		while(i<N) {

			if(a[i] > rv) {
				if(a[i] -rv > maxd) {
					if(a[i] - maxv > maxd) {
						maxd = a[i] - maxv;
						ridx = i;
						rv = a[ridx];
						lidx = maxvidx;
						lv=a[lidx];
					} else {
						maxd = a[i] -rv;
						ridx = i;
						rv = a[ridx];
					}
				} 
				
			}
			else if(a[i] <= rv && a[i] > lv) {
				int diff1 = a[i]-lv;
				int diff2 = rv-a[i];
				if(diff1 > diff2) {
					ridx = i;
					rv = a[i];
					maxd = diff1;
				}else if (diff2 > diff1) {
					lidx = i;
					lv = a[i];
					maxd = diff2;
				}
			}
			if(a[i] > maxv) {
				maxv = a[i];
				maxvidx = i;
			}
			i++;
		}
		System.out.println("rv "+rv);
		System.out.println("lv "+lv);

		System.out.println("lidx "+maxd);
		
	}

	public static void main(String[] args) {

		findNumMatrix();
		findMaxSumSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
		findMaxIncreasingSubArrayLength(new int[]{2,1,9,0,4,11,7,21});
		int[] a = {-1,0,0,4,5,8,8,8,9,17,22,22,45};
		countoccur(8,a);
		//countMaxAlive();
		intersectionSorted(new int[]{1,5,8,8,11}, new int[]{1,2,3,8,11,11,25,35,45});
		partition(new int[]{2,8,7,12,4,5,6,9});
		System.out.println(isSumPresent(new int[] {1,2,3,5,7}, 14));
		System.out.println(isThreeSum0(new int[] {-1,0,1}));
		findMaxDiffBackward();
		findMaxDiffForward();
		findIntervalRange();
		mergeInPlace(new int[]{4,7,10,12,0,0,0},new int[]{13,15,18});
		randomizeShuffleArray(new int[]{1,2,3,8,11,13,25,35,45});
		maxGap();
	}
}
