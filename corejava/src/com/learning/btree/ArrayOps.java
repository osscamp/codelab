package com.learning.btree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
	
	//kadane's algorithm
	//max increasing subsequence
	public static int[] findMaxSumSubArray() {
		int[] a = new int[]{-2, -3, 4, -1, -2, 1, 5, -3};
		int[] ids = new int[2];
		ids[0] = ids[1] = 0;
		int s=0, e=0, ct=0;
		int maxsum = Integer.MIN_VALUE;
		int max_ending_here = 0;
		for(int i = 0; i<a.length; i++) {
			max_ending_here += a[i];
            if(max_ending_here < 0) {
            	max_ending_here = 0;
            	ct = i+1;
            }
            if(max_ending_here > maxsum) {
            	maxsum = max_ending_here;
            	s = ct;
            	e = i;
            }

		}
		System.out.println("maxsum "+maxsum+" ids ["+s+" "+e+"]");
		return ids;
	}
	
	public static void findMaxSumSubArray2() {
		int[] a = new int[]{-2, -3, 4, -1, -2, 1, 5, -3};
		int max_so_far = a[0];
		int curr_max = a[0];
		for(int i=1; i<a.length; i++) {
			curr_max = Math.max(a[i],  curr_max+a[i]);
			max_so_far = Math.max(max_so_far, curr_max);
		}
		System.out.println("MAX SUM SUBA 2="+max_so_far);
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
				System.out.println("index "+j+" alive val "+alive[j]);
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
	
	//incorrect, intervals need to be sorted by start time.
	public static void findIntervalRange() {
		//sorted by start
		int[][] iv = {
				{0,3},
				{2,6},
				{2,5},
				{8,9}
		};
		List<int[]> result = new ArrayList<>();
		int[] prev = null;
		for(int i=0; i<iv.length; i++) {
			if(prev == null) {
				prev = iv[i];
				result.add(prev);
				continue;
			}else {
				int[] c = iv[i];
				int cst = c[0];
				int pst = prev[0];
				int cend = c[1];
				int pend = prev[1];
				if(cst >= pst && cst <= pend) {
					int mst = pst;
					int mend = Math.max(cend, pend);
					int[] merged = new int[]{mst, mend};
					result.remove(result.size()-1);
					result.add(merged);
					prev = merged;

				}else {
					result.add(c);
					prev = c;
				}
			}
		}
		for(int[] p : result) {
			MaxHeap.printArray(p);
		}
		
	}
	
	public static void totalCovered(){
		int[][] iv = {
				{0,1},
				{1,9},
				{3,5},
				{4,8},
				{11,14}
		};
		int total = 0;
		if(iv.length == 0 )return;
		if(iv.length==1) {
			total = iv[0][1] - iv[0][0];
			System.out.println("cov intvl "+total);
			return;
		}
		int[] p = iv[0];
		for(int i=1; i<iv.length; i++){
			int[] c = iv[i];
			//overlap
			if(p[1] >= c[0] && p[1] <= c[1]) {
				p[1] = c[1];

			}else if(p[1] < c[0]) {
				total += (p[1] - p[0]);
				p = c;
			}
			if(i == iv.length-1) {
				total += (p[1] - p[0]);
			}
		}
		System.out.println("total overlap "+total);
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
	
	public static void randomizeShuffleArray() {
		int[] a = new int[]{2,3};
		int len = a.length;
		int rnd = 0;
		Random rn = new Random();

		for(int i=len-1; i>0; i--) {
			rnd = rn.nextInt(i+1);
			System.out.println("random "+rnd+ " " + i);
			int tmp = a[rnd];
			a[rnd] = a[i];
			a[i] = tmp;
		}
		MaxHeap.printArray(a);
	}
	
	public static void rightIntersectWithDedupe() {
		int[] a = new int[] {1,5,8,8,8,8,11,17};
		int[] b = new int[] {2,5,5,8,8,13,17,29,69};
		int[] res = new int[b.length];
		int i=0,j=0,k=0;
		int prevB = -1;
		while(j<b.length ) {
			if(i<a.length && a[i] < b[j]) {
				i++;
			} else if((i<a.length && a[i] > b[j]) || (i>=a.length)) {
				if(prevB != b[j]) {
					res[k] = b[j];
					k++;
				}
				prevB = b[j];
				j++;
			} else {
				prevB = b[j];
				i++;
				j++;
			}
			
		}
		System.out.println("right inter ");
		MaxHeap.printArray(res);
				
	}
	
	public static void leftIntersectWithDedupeList() {
		List<Integer> a = Arrays.asList(new Integer[] {2,8,8,8,9});
		List<Integer> b = Arrays.asList(new Integer[] {2,5,8,8});
		List<Integer> res = new ArrayList<>();
		int prevA = -1;
		Iterator<Integer> aItr = a.iterator();
		Iterator<Integer> bItr = b.iterator();
		Integer aVal = aItr.hasNext() ? aItr.next() : null;
		Integer bVal = bItr.hasNext() ? bItr.next() : null;
		do {
			if(aVal == null) {
				break;
			}
			if(bVal != null && bVal < aVal) {
				bVal = bItr.hasNext() ? bItr.next() : null;
			} else if((bVal != null && bVal > aVal) 
					|| (!bItr.hasNext() && (bVal != null && bVal > aVal)) 
					|| (bVal == null && !bItr.hasNext())) {
				if(prevA != aVal) {
					res.add(aVal);
				}
				prevA = aVal;
				aVal = aItr.hasNext() ? aItr.next() : null;
			} else {
				prevA = aVal;
				aVal = aItr.hasNext() ? aItr.next() : null;
				bVal = bItr.hasNext() ? bItr.next() : null;
			}
			
		}
		while(aVal != null);
		System.out.println(res);
				
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
	
	public void printSpiral(){
		int[][] a = {
				{1 ,2 ,3 ,4 ,21},
				{5 ,6 ,7 ,8 ,22},
				{9 ,10,11,12,23},
				{13,14,15,16,24},
				{31,34,38,39,41}
				};
		int level = 0;
		int n = a[0].length;
		int r = 0;
		int c = 0;
		while(n > r) {			
			while(c < n) {
				System.out.println(a[r][c]);
				c++;
			}
			c--;
			r++;
			while(r < n) {
				System.out.println(a[r][c]);
				r++;			
			}
			r--;
			c--;
			while(c >= level) {
				System.out.println(a[r][c]);
				c--;
			}
			c++;
			r--;
			while(r >= level) {
				System.out.println(a[r][c]);
				r--;
			}
			n--;
			r+=2;
			c++;
			level++;
		}
	}
	
	public static void printMatrixDiagonally() {

	    int[][] m = {
	                        {1,2,3,4,5,},
	                        {18,19,20,21,6},
	                        {17,28,29,22,7},
	                        {16,27,30,23,8},
	                        {15,26,25,24,9},
	                        {14,13,12,11,10}
	                     };

	    int M = m[0].length;
	    int N = m.length;
	    int MM = M;
	    int NN = N;
	    if(M <=1 || N <= 1) { return; }
	    int ct = 0;
	    int MAX = M*N;
	    int[] res = new int[MAX];
	    int i=0, j=0;
	    int level = 0;
	    int levelct = 0;
	    while(ct < MAX) {
	        res[ct] = m[i][j];
	        if(i == level && j<M-1-level) j = Math.min(++j, M-1-level);
	        else if(j==M-1-level && i<N-1-level) i = Math.min(++i, N-1-level);
	        else if(i==N-1-level && j>level) j = Math.max(0, --j);
	        else if(j==level && i>level) i = Math.max(0, --i);
	        
	        ct++;
	        levelct++;
	        if(levelct == (MM-level)*(NN-level) - (MM-level-2)*(NN-level-2)) {
	            level++;
	            i=level;
	            j=level;
	            levelct=0;
	            MM--;
	            NN--;
	        }
	    }
	    for(int v : res) {
	        //System.out.println(v);
	    }
	}
	
	public static void sort2dMatrix() {
		//sort a 2d matrix horizontally+vertically , no dupes in a row, assume data is good
		int[][] m = {
				{8,2,15,34},
				{5,9,1,7},
				{21,16,3,19}
		};
		int XL = m[0].length;
		int YL = m.length;
		
		int[] d = new int[XL*YL];
		int k = 0;

		//sort 2d matrix into 1d array while reading using insertion sort
		int dctr = 0;
		for(int i=0; i<YL; i++) {
			for( k=0; k<XL; k++) {
				d[dctr] = m[i][k];
				int j = dctr;
				while(j>0 && d[j] < d[j-1]) {
					int t = d[j];
					d[j]= d[j-1];
					d[j-1] = t;
					j--;
				}
				dctr++;
			}
		}
		
		//MaxHeap.printArray(d);
		k = 0;
		int i=0, j=0;
		while(k < d.length) {
			if(i < YL) {
				m[i][j] = d[k];
				i++;
			}
			if(i == YL && j < XL) {
				i=0;
				j++;
			}
			k++;
		}
		StringBuilder sb = new StringBuilder(); //for printing
		for(i=0; i<YL; i++) {
			for(j=0; j<XL; j++) {
				sb.append(m[i][j]).append(' ');
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void sort2dMatrixOnePass() {
		//sort a 2d matrix horizontally+vertically , no dupes in a row, assume data is good
		int[][] m = {
				{8,2,15,34},
				{5,9,1,7},
				{21,16,3,19}
		};
		int XL = m[0].length;
		int YL = m.length;


		//sort 2d matrix while reading using insertion sort in a threaded way going from top to bottom.
		int k = 0;
		int i=0, j=0;
		while(k < XL*YL) {
			if(i < YL) {
				int p = i;
				int q = j;
				int p1 = p > 0? p-1 : q > 0 ? YL-1 : p;
				int q1 = q > 0 ? (p==0 ? q-1 : q) : q;
				int mx = (j*YL)+i;
				int ctr = 0;
				while(m[p][q] < m[p1][q1]  && !(p==0 && q==0) && ctr < mx) {
					int t = m[p][q];
					m[p][q] = m[p1][q1];
					m[p1][q1] = t;
					ctr++;
					p=p1;
					q=q1;
					p1 = p > 0? p-1 : q > 0 ? YL-1 : p;
					q1 = q > 0 ? (p==0 ? q-1 : q) : q;
				}
				i++;
			}
			if(i == YL && j < XL) {
				i=0;
				j++;
			}
			k++;
		}
		StringBuilder sb = new StringBuilder(); //for printing
		for(i=0; i<YL; i++) {
			for(j=0; j<XL; j++) {
				sb.append(m[i][j]).append(' ');
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void findMinSubarray() {
	    int[] a = {1,2,3,5,8,5,9,7,7,5,11,0,7};
	    int[] p = {5,7};
	    int minidx = Integer.MAX_VALUE;
	    int minMatchlength = Integer.MAX_VALUE;
	    for(int i=0;i<a.length;i++) {
	        if(a[i] == p[0]) {
	            for(int k=i+1,pi=1; k<a.length; k++) {
	                if(a[k] == p[pi]) {
	                    pi++;
	                    if(pi == p.length) {
	                        //match found
	                        int matchLen = k-i+1;
	                        if(matchLen < minMatchlength) {
	                            minMatchlength = matchLen;
	                            minidx = i;
	                        }

	                        if(matchLen == p.length){//contiguous match
	                    	    System.out.println("minidx "+minidx);
	                            return;
	                        }
	                        break; //break from k loop
	                    }
	                }
	            }    
	         }       
	     }
	    System.out.println("minidx "+minidx);
	 }
	
	public static void isToepliz() {
	    int[][] a = {
	    {6,7,8,2},
	    {3,6,7,8},
	    {9,3,6,7},
	    {2,9,3,6},
	    {1,2,9,3},
	    };
	    int XL = a[0].length;
	    int YL = a.length;
	    boolean isTope = true;
	    for(int i=0; i<YL-1; i++) {
	    	for(int j=0; j<XL-1; j++) {
	    		if(a[i][j] != a[i+1][j+1]) {
	    			isTope = false;
	    		}
	    	}
	    }
	    System.out.println("isT "+isTope);
	 }  
	
	public static void find2Smallest() {
		int[] a = {-5,23,-11,5,34,42,25,76,-6};
		int min = Integer.MAX_VALUE;
		int min2 = min;
		int compct = 0;
		for(int i = 0; i<a.length; i++) {
			compct+=2;
			if(a[i] < min) {
				min2 = min;
				min = a[i];
				
			}else if(a[i] < min2) {
				min2 = a[i];
				compct++;
			}
		}
		System.out.println(min+" "+min2+" "+compct);
		
	}
	
	
	//incomplete
	public static void orderByPermutation() {
		int[] a = {10,20,30,40,50,60,70};
		//int[] p = {2,6,3,5,1,4,0};
		//int[] p = {6,1,5,4,2,3,0};
		//int[] p = {6,2,1,4,3,5,0};
		//int[] p = {3, 1, 2,0,4,5,6}; //fails
		int[] p = {0,1,2,3,4,5,6};
		Random r = new Random();
		for(int m=p.length-1; m>0; m--) {
			int newidx = r.nextInt(m+1);
			int tmp = p[m];
			p[m] = p[newidx];
			p[newidx] = tmp;
		}
		MaxHeap.printArray(p);
		int replaceCt = 0;
		int seed = p[0];
		int beforecycle = 0;
		int i = 0;
		int tmp = -1;
		int ptmp = -1;
		while(replaceCt < a.length-1) {
			tmp = a[i];
			if(ptmp == -1) {
				a[i] = a[p[i]];
			} else {
				a[i] = ptmp;
			}
			replaceCt++;
			int k = search(p, i);
			i = k;
			ptmp = tmp;
			if(i == seed) {
				a[i] = tmp;
				replaceCt++;
				i = ++beforecycle;
				if(p[i] <= replaceCt) {
					i++;
				}
				seed = p[i];
				ptmp = -1;
				continue;
			}
		}
		MaxHeap.printArray(a);
	}

	public static int search(int[] a, int v) {
		for(int i = 0; i<a.length; i++) {
			if(a[i] == v) {
				return i;
			}
		}
		throw new IllegalStateException();
	}
	
	public static void waveArray() {
		//assuming sorted or use quicksort
		int[] a = {5, 8, 12, 17, 25,29};
		for(int i=0; i<a.length-1; i++) {
			int tmp = a[i];
			a[i] = a[i+1];
			a[i+1] = tmp;
			i++;
		}
		MaxHeap.printArray(a);
	}
	
	//find i<j such that a[i] > a[j]
	public static void numInversions(){
		int[] a = {2,5,4,16,11,8,12};
		int count = 0;
		for(int i=1; i<a.length; i++) {
			int j = i;
			int lcount = 0;
			while(j > 0 && a[j] < a[j-1]) {
				int tmp = a[j];
				a[j] = a[j-1];
				a[j-1] = tmp;
				lcount = 1;
				j--;
			}
			count += lcount;
		}
		System.out.println("inv "+count);
		MaxHeap.printArray(a);
	}
	
	public static void numInversions2(){
		int[] a = {8,4,2,1};
		int count = 0;
		for(int i=0; i<a.length; i++) {
			for(int j=i+1; j<a.length; j++){
				if(a[j] < a[i]) {
					count++;
				}
			}
		}
		System.out.println("inv "+count);
		MaxHeap.printArray(a);
	}
	
	public static void equilibriumPoint() {
		//can be done in o(n)
		int[] a = {15,17,9,12,3,1,6,50};
		//a[mid] = find sum below, sum above 
		int mid = a.length/2;
		for(int k=mid; k>0; k--) {
			int sl = 0;
			int sr = 0;
			for(int j=k-1; j>=0; j--) {
				sl+=a[j];
			}
			for(int j=k+1; j<a.length; j++) {
				sr+=a[j];
			}
			if(sl > 0 && sr > 0 && sl ==sr) {
				System.out.println("eqb point "+k);
				return;
			}
		}
		for(int k=mid+1; k<a.length; k++) {
			int sl = 0;
			int sr = 0;
			for(int j=k-1; j>=0; j--) {
				sl+=a[j];
			}
			for(int j=k+1; j<a.length; j++) {
				sr+=a[j];
			}
			if(sl > 0 && sr > 0 && sl ==sr) {
				System.out.println("eqb point R "+k);
			}
		}
	}
	
	public static void findNumber() {
		/*
		 * You are given an array A containing 2*N+2 positive numbers, out of which N numbers are repeated exactly 
		 * once and the other two numbers occur exactly once and are distinct. You need to find the other two numbers
		 *  and print them in ascending order.
		 */
		
		int[] a = {1,3,4,7,1,5,3,2,4,2};
/*
 * Input : 1 3 5 6, n = 6
Sum of missing integers = n*(n+1)/2 - (1+3+5+6) = 6.
Average of missing integers = 6/2 = 3.
Sum of array elements less than or equal to average = 1 + 3 = 4
Sum of natural numbers from 1 to avg = avg*(avg + 1)/2
                                     = 3*4/2 = 6
First missing number = 6 - 4 = 2

Sum of natural numbers from avg+1 to n
                                =  n*(n+1)/2 - avg*(avg+1)/2
                                =  6*7/2 - 3*4/2
                                =  15
Sum of array elements greater than average = 5 + 6 = 11
Second missing number = 15 - 11 = 4
 */
		
	}
	
	public static void findMaxValue() {
//maximum value of (A[i] – i) - (A[j] – j)
		int[] a = {3,7,4,9,6,2,11,5};
		//find max and min of alt array (a[i]-i) in 1 pass.
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i=0; i<a.length; i++) {
			int v = a[i] - i;
			System.out.println("a[i]-i:"+v);
			if(v > max) max = v;
			else if(v < min) min = v;
		}
		if(min != Integer.MIN_VALUE)
		System.out.println("max diff "+(max-min));
		else
			System.out.println("doesn't exist");
	}
	
	public static void findMinDiffAnyPair() {
		int[] a = {4,2,8,21,17,9,23};
		//sort 2,4,8,11,17,21,23
		int mindiff = Integer.MAX_VALUE;
		Arrays.sort(a);
		for(int i=a.length-1; i>0; i--) {
			if(a[i] - a[i-1] < mindiff) {
				mindiff = a[i] - a[i-1];
			}
		}
		System.out.println("mindiff "+mindiff);
	}
	
	//Given an array A of integers, find the maximum of j - i subjected to the constraint of A[i] <= A[j].
	public static void findMaximumIndexDiff(){
		int [] a = {3,5,4,2,7,9,21,0};
		int maxidxdiff = Integer.MIN_VALUE;
		for(int i=0; i<a.length; i++) {
			for(int j=i+1; j<a.length; j++) {
				int diff = j-i;
				if(a[i] < a[j] && diff > maxidxdiff) {
					maxidxdiff = j-i;
				}
			}
		}
		System.out.println("idx diff "+maxidxdiff);
	}

	
	public static void main(String[] args) {

		findNumMatrix();
		findMaxSumSubArray();
		findMaxIncreasingSubArrayLength(new int[]{2,1,9,0,4,11,7,21});
		int[] a = {-1,0,0,4,5,8,8,8,9,17,22,22,45};
		countoccur(8,a);
		countMaxAlive();
		intersectionSorted(new int[]{1,5,8,8,11}, new int[]{1,2,3,8,11,11,25,35,45});
		partition(new int[]{2,8,7,12,4,5,6,9});
		System.out.println(isSumPresent(new int[] {1,2,3,5,7}, 14));
		System.out.println(isThreeSum0(new int[] {-1,0,1}));
		findMaxDiffBackward();
		findMaxDiffForward();
		findIntervalRange();
		mergeInPlace(new int[]{4,7,10,12,0,0,0},new int[]{13,15,18});
		randomizeShuffleArray();
		rightIntersectWithDedupe();
		leftIntersectWithDedupeList();
		printMatrixDiagonally();
		sort2dMatrixOnePass();
		findMinSubarray();
		isToepliz();
		find2Smallest();
		//maxGap();
		orderByPermutation();
		waveArray();
		numInversions();
		numInversions2();
		totalCovered();
		equilibriumPoint();
		findMaxValue();
		findMinDiffAnyPair();
		findMaximumIndexDiff();
		findMaxSumSubArray();
		findMaxSumSubArray2();
	}
}
