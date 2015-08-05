package com.learning.btree;

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
		int MAXWT = 7;
		int[] weights = {1, 6, 2,3};
		int[] values = {102, 185, 92, 87};
		int n = weights.length;

		int[][] table = new int[n+1][MAXWT+1];
		for(int i=0; i<=n; i++) {
			for(int j=0; j<=MAXWT; j++) {
				if(i==0 || j==0) {
					table[i][j] = 0;
				} else if(weights[i-1] <= j) {
					table[i][j] = Math.max(values[i-1] + table[i-1][j-weights[i-1]], table[i-1][j]);
				} else {
					table[i][j] = table[i-1][j];
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
		System.out.println("max knapsack "+table[n][MAXWT]);
		return table[n][MAXWT];
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
	
	public static int findMinCoinsDynamic(int amt, int[] denom) {
		int[] mincoins = new int[denom.length];
		int[] table = new int[amt+1];

		table[0] = 0;
		for(int k=1; k <=amt; k++) {
			for(int i=0; i<denom.length; i++) {
				mincoins[i] = -1;
			}
			for(int i=0; i<denom.length; i++) {
				if(k >= denom[i]) {
					mincoins[i] = table[k-denom[i]] + 1;
				}
			}
			table[k] = -1;
			for(int i=0; i<denom.length; i++) {
				if(mincoins[i] > 0) {
					if(table[k] == -1 || table[k] > mincoins[i] ) {
						table[k] = mincoins[i];
					}
				}
			}
		}

		return table[amt];
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
		String a = "samen";
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
	
	public static void main(String[] args) {
		knapRecursive();
knapsackDynamic();
memoizedFibonacci();
		findMaxIncreasingSubseq();
		findCoinsDriver();
		System.out.println(findMinCoinsDynamic(18, new int[]{5,2,1}));
		lcsDynamic("AXYT", "AYZXT");
		editDistance();
	}

}
