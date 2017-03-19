package com.learning.btree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dynamic {
	
	
	public static int knapsackRecursive(int limit, int[] weights, int[] values, int n) {
		if(n == 0 || limit == 0) {
			return 0;
		}
		if(weights[n-1] > limit) {
			int rv = knapsackRecursive(limit, weights, values, n-1);
			return rv; //exclude if wt > limit
		} else {
			int valprev=values[n-1];
			int excludeVal = knapsackRecursive(limit-weights[n-1], weights, values, n-1);
			int includeVal = knapsackRecursive(limit, weights, values, n-1);
			return Math.max(valprev + excludeVal, includeVal);
		}
	}
	
	public static int knapsackDynamic() {
		int W = 5;
		int[] weights = {1, 4, 2,3};
		int[] values = {102, 205, 92, 87};
		int n = weights.length;

/*		int[][] table = new int[n+1][MAXWT+1];
		for(int i=0; i<=n; i++) {
			for(int j=0; j<=MAXWT; j++) {
				if(i==0 || j==0) {
					table[i][j] = 0;
				} else if(weights[i-1] <= j) {
					table[i][j] = Math.max(values[i-1] + table[i-1][j-weights[i-1]], table[i-1][j]);
					//table[i][j] = values[i-1] + table[i-1][j];
				} else {
					table[i][j] = table[i-1][j];
				}
			}
		}*/
		int [][]table = new int[n+1][W+1];
		for(int i=0; i<n+1; i++) {
			for(int j=0; j<W+1; j++) {
				if(i == 0 || j == 0) {
					table[i][j] = 0;
				}else if(weights[i-1] > j) {
					table[i][j] = table[i-1][j];
				}else{
					int lv = table[i-1][j];
					int nv = values[i-1] + table[i-1][j-weights[i-1]];
					table[i][j] = Math.max(lv,  nv);
				}
			}
		}
		//print
		for(int p=0; p<table.length; p++) {
			StringBuilder sb = new StringBuilder();
			for(int j=0; j<table[0].length; j++) {
				sb.append(table[p][j]).append(" ");
			}
			System.out.println(sb);
		}
		System.out.println("max knapsack "+table[n][W]);
		return table[n][W];
	}
	
	public static void memoizedFibonacci() {
		int N =6;
		int[] precompute = new int[N+1];
		for(int i=0; i<precompute.length; i++) {
			precompute[i] = -1;
		}
		System.out.println(memoizedFib(N, precompute));
		
	}
	public static int memoizedFib(int n, int[] precompute) {
		if(precompute[n] == -1) {
			if(n<=1) {
				precompute[n] = n;
			} else {
				precompute[n] = memoizedFib(n-1, precompute) + memoizedFib(n-2, precompute);
			}
		}
		return precompute[n];
	}
	
	/**
	 * max increasing subsequence n^2 complexity
	 */
	public static void findMaxIncreasingSubseq() {
		int[] a = {1,2,5,3,4,7,6};
		int n = a.length;
		int[] aux = new int[n];
		for(int i=0; i<n; i++) {
			int maxinc = 0;
			for(int j=0; j<i; j++) {
				if(a[j] < a[i] && aux[j] > maxinc) {
					maxinc = aux[j];
				}
				
			}
			aux[i] = maxinc+1;
		}
		int m = 0;
		for(int i=0; i<n; i++) {
			if(aux[i] > m) {
				m = aux[i];
			}
		}
		System.out.println("max sub "+m);
	}
	
	public static void findMaxIncreasingSubseqV2() {
		int[] a = {1,2,5,3,4,7,6};
		int n = a.length;
		int[] aux = new int[n];
		aux[0] = a[0];
		int len = 1;
		for(int i=1; i<n; i++) {
			if(aux[0] > a[i]) {
				aux[0] = a[i];
			}
			if(a[i] > aux[len-1]) {
				aux[len++] = a[i];
			} else {
				//subarray[index of least number in aux greater then a[i]]
				//aux[getIndex()] = a[i];
			}
		}
	}
	
	/*LCS - dynamic analysis
	 * L[a(0..m-1), b(0..n-1)] = 1+L[a(0..m-2), b(0..n-2)] if a[m-1] == b[n-1]
	 * else L[a(0..m-1), b(0..n-1)] = max(L[a(0..m-2), b(0..n-1)], L[a(0..m-1), b(0..n-2)])
	 */
	public static void lcsDynamic(String a, String b) {
		char[] A = a.toCharArray();
		char[] B = b.toCharArray();
		int[][] l = new int[A.length+1][B.length+1];
		for(int i=0; i<=A.length;i++) {
			for(int j = 0; j<=B.length; j++) {
				if(i==0 || j==0) l[i][j] = 0;
				else if(A[i-1] == B[j-1]) {
					l[i][j] = 1+l[i-1][j-1];
				} else {
					l[i][j] = Math.max(l[i][j-1], l[i-1][j]);
				}
			}
		}
		System.out.println("MAX LCS "+l[A.length][B.length]);
	}
	
	/**
	 * 2^n solution
	 * @param v
	 * @param denom
	 * @return
	 */
	public static int findMinCoins(int v, int[] denom) {
		int[] mincoins = new int[denom.length];
		for(int i=0; i<mincoins.length; i++) {
			mincoins[i] = -1;
		}
		if(v == 0) {
			return 0;
		}
		for(int i=0; i<denom.length; i++) {
			if(v >= denom[i]) {
				mincoins[i] = findMinCoins(v-denom[i], denom) + 1;
			}
		}
		int finalSol = -1;
		for(int i=0; i<mincoins.length; i++) {
			if(mincoins[i] >= 0) {
				if(finalSol == -1 || finalSol > mincoins[i] ) {
					finalSol = mincoins[i];
				}
			}
		}
		return finalSol;
	}
	
	public static int findMinCoinsDynamic() {
		int amt = 6;
		int[] denom = {1,2,3};
		int[] mincoins = new int[denom.length];
		int[] table = new int[amt+1];
		int m = denom.length;

		table[0] = 0;
		for(int i=1; i<=amt; i++) {
			table[i] = Integer.MAX_VALUE;
		}
		// Compute minimum coins required for all
	    // values from 1 to amt
		for(int k=1; k <=amt; k++) {
			//go through all coins less than i
			for(int j = 0; j<m; j++) {
				if(denom[j] <= k) {
					int sub_res = table[k-denom[j]];
					if(sub_res != Integer.MAX_VALUE && (sub_res + 1) < table[k]) {
						table[k] = sub_res + 1;
					}
				}
			}

		}

		return table[amt];
	}
	
	public static void totalWaysToMakeChange(){
		int[] s = {1,2,3};
		int n = 6;
		int m = 3;
		int[][] table = new int[n+1][m];
		for(int j=0; j<m; j++) {
			table[0][j] = 1;
		}
		for(int i=1; i<n+1; i++) {
			for(int j = 0; j<m; j++) {
				int x = i-s[j] >= 0 ? table[i-s[j]][j] : 0;
				int y = j >= 1 ? table[i][j-1] : 0;
				table[i][j] = x+y;
			}
		}
		System.out.println("ways for change "+table[n][m-1]);
	}
	
	public static void findCoinsDriver() {
		int v=11;
		int[] den = {4,2,1};
		int result = findMinCoins(v, den);
		System.out.println(result);

	}
	
	
	public static void knapRecursive() {
		int[] weights = {1, 6, 2};
		int[] values = {102, 95, 92};
		int max = knapsackRecursive(7, weights, values, 3);
		System.out.println("max knapsack "+max);
	}
	
	public static void editDistance() {
		String a = "amen";
		String b = "sarc";
		int M = a.length();
		int N = b.length();
		char A[] = a.toCharArray();
		char B[] = b.toCharArray();
		int[][] table = new int[M+1][N+1];
		for(int i=0; i<=M; i++) {
			for(int j=0; j<=N; j++) {
				if(i == 0 || j==0) {
					table[i][j] = 0;
				} else {
					int m = Math.min(table[i-1][j]+1, table[i][j-1]+1);
					int m1 = 0;
					if(A[i-1] == B[j-1]) {
						m1 = table[i-1][j-1];
					} else {
						m1 = 1+table[i-1][j-1];
					}
					int v = Math.min(m,m1);
					table[i][j] = v;
				}

			}
		}
		System.out.println("min edit "+table[M][N]);
	}
	
	public static void minCost(){
		int[][] c = {
				{1,2,3},
				{4,8,2},
				{1,5,3}
		};
		//cost[i][j] = min(c[i-1,j],c[i,j-1],c[i-1,j-1])
		/*
		 * {
		 * {1,3,6
		 * {5,9,5
		 * {6,10,8
		 */
		int M = c.length;
		int[][] res = new int[M][M];
		for(int i=0; i<M; i++) {
			for(int j=0; j<M; j++) {
				if(i==0 && j==0) {
					res[i][j] = c[i][j];
				}else if(i==0 && j > 0) {
					res[i][j] = res[i][j-1] + c[i][j];
				}else if(j==0 && i>0) {
					res[i][j] = c[i][j] + res[i-1][j];
				}else {
					int c1 = res[i-1][j-1];
					int c2 = res[i][j-1];
					int c3 = res[i-1][j];
					res[i][j] = c[i][j] + Math.min(Math.min(c1, c2), c3); 
				}
			}
		}
		MaxHeap.printArray(res);
	}
	
	public static void binomial() {
		int n = 6;
		int k = 4;
		int[][] table = new int[k+1][n+1];
		for(int j=0; j<=n; j++) {
			table[0][j] = 1;
		}
		for(int i=1; i<=k; i++) {
			for(int j=i; j<=n; j++) {
				table[i][j] = table[i-1][j-1]+table[i][j-1];
			}
		}
		System.out.println("binomial is "+table[k][n]);
	}
	
	public static void cutRod() {
		//rod of lenght = 5 ans price of each unit
		int[] price = {1,5,8,9,13};
		int N = price.length;
		int[] val = new int[N+1];
		val[0] = 0;
		for(int i=1; i<=N; i++) {
			int max = Integer.MIN_VALUE;
			for(int j=0; j<i; j++) {
				max = Math.max(max, price[j]+val[i-j-1]);
			}
			val[i] = max;
		}
		System.out.println("max val "+val[N]);
	}
	
	//total ways to climb stairs when allowed to make 1 to m hops for a H stair building
	public static void countWays() {
		//ways to climb up 7 stories if 1 to 3 steps can be taken.
		//int ways = countWaysUtil(7, 3);
		int ways = countWaysUtilDp(7, 3);
		System.out.println("ways "+ways);
	}
	
	public static int countWaysUtilDp(int H, int m) {
		int[] res = new int[H];
		res[0] = 1;
		res[1] = 1;
		for(int i=2; i<H; i++) {
			res[i] = 0;
			for(int j=1; j<=m && j<=i; j++) {
				res[i] += res[i-j];
			}
		}
		return res[H-1];
	}
	
	public static int countWaysUtil(int H, int m) {
		//ways(H,m) = ways(H-1)+..ways(H-m)
		if(H <= 1) {
			return H;
		}
		int ret = 0;
		for(int i=1; i<=m && i<=H; i++ ) {
			ret += countWaysUtil(H-i, m);
		}
		return ret;
	}
	
	public static void eggDrop() {
		//n eggs and k floors, minimize the number of trials
		//if egg is dropped from a floor x it can break or not break
		//if breaks problem reduces to x-1 and n-1
		//if doesn't break problem reduces to k-x and n
		//eggDrop(n, k) = 1 + min(max(eggDrop(n-1, x-1), eggDrop(n, k-x)))
		int trials = eggDrop(2, 100);
		System.out.println("trials for "+trials);
	}
	
	public static int eggDrop(int n, int k) {
		//int val = 1 + Math.min(Math.max(eggDrop(n-1, x-1, k), eggDrop(n, k-x, k)));
		if(k == 0 || k==1) {
			return k;
		}
		if(n==1) {
			return k;
		}
		int res = 0;
		int min = Integer.MAX_VALUE;
		int x;
		for(x=1; x<=k; x++) {
			res = 1+Math.max(eggDrop(n-1, x-1), eggDrop(n, k-x));
			if(res < min)min = res;
		}
		return min+1;
	}
	

	
	public static void main(String[] args) {
		knapRecursive();
knapsackDynamic();
memoizedFibonacci();
		findMaxIncreasingSubseq();
		findCoinsDriver();
		System.out.println("min coins "+findMinCoinsDynamic());
		lcsDynamic("AXYT", "AYZXT");
		editDistance();
		minCost();
		totalWaysToMakeChange();
		binomial();
		cutRod();
		countWays();
		eggDrop();
	}

}
