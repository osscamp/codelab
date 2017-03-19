package com.learning.btree;

import java.util.BitSet;
import java.util.Random;

public class PrincetonArray {
	
	public static int[][] copyJaggedArray(final int[][] a) {
		int[][] result = new int[a.length][];
		for(int i=0; i<a.length; i++) {
			int[] b = new int[a[i].length];
			result[i] = b;
			for(int j=0; j<a[i].length; j++) {
				result[i][j] = a[i][j];
			}
		}
		return result;
	}
	
	public static void transposeArray() {
		int[][] a = new int[][] { {1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
		int N = a.length;
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				int tmp = a[i][j];
				a[i][j] = a[j][i];
				a[j][i] = tmp;
			}
		}
		System.out.println(a);
	}
	
	public static void matrixMultiply() {
		int[][] a = new int[][]{{1,2,3},{4,5,6}};
		int[][] b = new int[][]{{5,6},{7,8}, {9,10}};
		//int[][] b = new int[][]{{5},{6}, {7}};
		int arow = a.length;
		int bcol = b[0].length;
		int acol = a[0].length;
		
		if(arow != bcol) {
			System.out.println("not possible");
			return;
		}
		int[][] result = new int[arow][arow];
		for(int i=0; i<arow; i++) {
			for(int j = 0; j<arow; j++) {
				int sum = 0;
				for(int k=0; k<acol; k++) {
					sum+=a[i][k]*b[k][j];
				}
				result[i][j] = sum;
			}
		}
		System.out.println(result);
	}
	
	//e.g. [1,2,2,1] = 2 , [1,2,3] = 0
	public static void findLargestPlateau() {
		int[] a = {1,1,1,2,2,1,1};
		int len = 0;
		int maxlen = 0;
		boolean seq = false;
		for(int i=1; i<a.length; i++) {
			if(a[i] > a[i-1]) {
				seq = true;
				len =1;
			} else if(seq && a[i] == a[i-1]) {
				len++;
			} else if(seq && a[i] < a[i-1]) {
				seq = false;
				if(len > maxlen) {
					maxlen = len;
				}
			}
		}
		System.out.println("max plateau "+maxlen);
	}
	
	public static boolean[][] hadamard(int n) {
		if(n < 1 || n % 1 != 0) {
			return null;
		}
		if(n <= 1) {
			return new boolean[][]{{true}};
		}else {
			boolean[][] arr = new boolean[n][n];
			boolean[][] child = hadamard(n/2);

			for(int i=0, it=0; i<2; i++) {
				for(int j=0, jt=0; j<2; j++) {
					for(int k=0; k<n/2; k++) {
						for(int l=0; l<n/2; l++) {
							if(i==1 && j==1) {
								arr[it+k][jt+l] = !child[k][l];
							}else{
								arr[it+k][jt+l] = child[k][l];
							}
						}
					}
					jt+=n/2;
				}
				it+=n/2;
			}
			return arr;
		}
	}
	
	public static void print2D(boolean[][] a){
		if(a == null) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		for(int k=0; k<a.length; k++) {
			for(int l=0; l<a.length; l++) {		
				if(a[k][l]) {
					sb.append("T");
				} else {
					sb.append("F");
				}
				sb.append(" ");

			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void copyArrayDriver() {
		int[][] a = new int[][] { {1,6,4,5,6}, {}};
		copyJaggedArray(a);
	}
	
	public static void commonBirthdaySimulate() {
		int D = 365;
		boolean[] status = new boolean[D];
		Random random = new Random();
		int count = 0;
		while(true) {
			int idx = random.nextInt(D);
			//System.out.println("idx "+idx);
			if(status[idx]) {
				System.out.println("match in "+count+ " tries");
				break;
			}
			count++;
			status[idx] = true;
		}
	}
	
	public static void couponCollect() {
		int N = 10;
		boolean[] space = new boolean[N];
		int count = 0;
		int valct = 0;
		while(valct < N)	 {
			count++;
			Random rdm = new Random();
			int idx = rdm.nextInt(N);
			if(!space[idx] ){
				valct++;
			}
			space[idx] = true;
		}
		System.out.println("count for "+N+" coupons "+count);
	}
	
	public static void pascal() {
		int N = 7;
		int[][] pa = new int[N][];
		for(int i=0; i<N; i++) {
			int[] ia = new int[i+1];
			pa[i] = ia;
			for(int j=0; j<=i; j++) {
				if(j==0){
					pa[i][j] = 1;
				} else if(j==i) {
					pa[i][j] = 1;
				} else if(i > 0 && j> 0) {
					pa[i][j] = pa[i-1][j-1]+pa[i-1][j];
				}
			}
		}
		for(int i=0; i<pa.length; i++) {
			StringBuilder sb = new StringBuilder();
			for(int j=0; j<pa[i].length; j++) {
				sb.append(pa[i][j]+" ");
				
			}
			System.out.println(sb);
		}
	}
	
	public static void randomPerm(int N) {
		boolean[] a = new boolean[N];
		int count = 0;
		int loopc = 0;
		while(count < N) {
			loopc++;
			Random rnd = new Random();
			int idx = rnd.nextInt(N);
			if(!a[idx]) {
				count++;
				System.out.println(idx);
			}
			a[idx] = true;
		}
		System.out.println("count ="+loopc);
	}
	
	public static void maxOXGrid() {
		char[][] grid = new char[][] {  {'o','o','x','x'},
										{'o','o','x','0'},
										{'x','o','x','x'},
										{'o','o','o','x'}
									};
		int N = grid.length;
		//boolean[][] v = new boolean[N][N];
		int maxct = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N ; j++) {
				//v[i][j] = true;

				int ct = 0;
				int ti = i;
				int tj = j;
				if(grid[i][j] == 'x') {
					ct = 1;
					while(tj < N-1 && grid[ti][tj+1] == 'x') {
						ct++;
						maxct = ct > maxct ? ct : maxct;
						if(maxct == N) break;
						tj++;
						//v[ti][tj] = true;
					}
					if(maxct == N) break;
					ti = i;
					tj = j;
					ct = 1;
					while(ti < N-1 && grid[ti+1][tj] == 'x') {
						ct++;
						maxct = ct > maxct ? ct : maxct;
						if(maxct == N) break;
						ti++;
						//v[ti][tj] = true;
					}
					if(maxct == N) break;
					ti = i;
					tj = j;
					ct = 1;
					while(ti < N-1 && tj < N-1 && grid[ti+1][tj+1] == 'x') {
						ct++;
						maxct = ct > maxct ? ct : maxct;
						if(maxct == N) break;
						ti++;
						tj++;
						//v[ti][tj] = true;
					}
					if(maxct == N) break;
					ti = i;
					tj = j;
					ct = 1;
					while(ti < N-1 && tj > 0 && grid[ti+1][tj-1] == 'x') {
						ct++;
						maxct = ct > maxct ? ct : maxct;
						if(maxct == N) break;
						ti++;
						tj--;
						//v[ti][tj] = true;
					}
					if(maxct == N) break;
				}
			}
			if(maxct == N) break;
		}
		System.out.println("max "+maxct);
	}
	
	public static void findPeak() {
		int[][] a = new int[][]{{1,2,2},{1,1,2},{2,3,1}};
		int N = a.length;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int jl = Math.max(j-1, 0);
				int jr = Math.min(j+1, N-1);
				int iup = Math.max(i-1, 0);
				int idn = Math.min(i+1, N-1);
				boolean cl = jl == j ? true : a[i][j] > a[i][jl];
				boolean cr = jr == j ? true : a[i][j] > a[i][jr];
				boolean cup = iup == i ? true : a[i][j] > a[iup][j];
				boolean cdn = idn == i ? true : a[i][j] > a[idn][j];
				if(cl && cr && cup && cdn) {
					System.out.println("peak "+a[i][j]);
				}
			}
		}
	}
	
	public static void printMagicSquare() {
		int N = 5;
		if(N %2 == 0 || N <=0) { return; }
		int[][] a = new int[N][N];
		int i = N-1;
		int j = N/2;
		int v = 1;
		while (v <= N*N) {
			int iorig = i;
			int jorig = j;
			a[i][j] = v;
			v++;
			j = j+1 >= N ? 0 : j+1;
			i = i+1 >= N ? 0 : i+1;

			if(a[i][j] != 0) {
				i = iorig-1 < 0 ? 0 : iorig-1;
				j = jorig;
			}
		}
		for(int k=0; k<N; k++) {
			StringBuilder sb = new StringBuilder();
			for(int l=0; l<N; l++) {
				sb.append(a[k][l]+" ");
				
			}
			System.out.println(sb);
		}
	}
	
	public static void sudokuVerify() {
		int N = 9;
		int[][] s = new int[][] {
				{5 ,3 ,4 ,6 ,7 ,8 ,9 ,1 ,2},
				{6 ,7 ,2 ,1 ,9 ,5 ,3 ,4 ,8},
				{1 ,9 ,8  ,3 ,4 ,2 ,5 ,6 ,7},
				{8 ,5 ,9  ,7 ,6 ,1  ,4 ,2 ,3},
				{4 ,2 ,6  ,8 ,5 ,3  ,7 ,9 ,1},
				{7 ,1 ,3  ,9 ,2 ,4  ,8 ,5 ,6},
				{9 ,6 ,1  ,5 ,3 ,7  ,2 ,8 ,4},
				{2 ,8 ,7  ,4 ,1 ,9  ,6 ,3 ,5},
				{3 ,4 ,5  ,4 ,8 ,6  ,1 ,7 ,9},
		};
		for(int i=0; i<N; i++) {
			boolean[] b = new boolean[N];
			boolean[] b2 = new boolean[N]; // for column

			for(int j=0; j<N; j++) {
				if(s[i][j] < 1 || s[i][j] > N) System.out.println("not sudoku");
				if(b[s[i][j] -1]){
					System.out.println("not sudoku");
					return;
				} else {
					b[s[i][j] - 1] = true;
				}
				if(b2[s[j][i] -1]){
					System.out.println("not sudoku");
					return;
				} else {
					b2[s[j][i] - 1] = true;
				}
			}

		}
		System.out.println("valid sudoku");
	}
	
	public static void primeSieve(int N) {
		BitSet b = new BitSet(N+1);
		int sqrt = (int)Math.sqrt(N);
		int i = 2;
		while(i<=sqrt) {
			if(b.get(i)) {
				i++;
				continue;
			}
			int m = 2;
			while(m*i <= N) {
				b.set(m*i);
				m++;
			}
			i++;
		}
		int longestnonprime = 0;
		int npct = 0;
		//System.out.println(b);
		StringBuilder sb = new StringBuilder();
		for(int a=2; a<N; a++) {
			if(!b.get(a)) {
				//System.out.println("prime "+(a));
				sb.append(a).append(" ");
				npct = 0;
			} else {
				npct++;
				if(longestnonprime < npct) {
					longestnonprime = npct;
				}
			}
		}
		//System.out.println(sb);
		System.out.println("longest np "+longestnonprime);
	}
	
	public static void printSpiral() {
/*		int[][] a = new int[][]{
				{1,2,3,4},
				{5,6,7,8},
				{9,10,11,12},
				{13,14,15,16}
		};*/
		int[][] a = new int[][]{
				{1,2,3},
				{4,5,6},
				{7,8,9},
		};
		int N = a.length;
		int i=0;
		int j=0;
		int ct = 1;
		int level = 0;
		System.out.println(a[0][0]);
		while(ct < N*N) {
			if(i == level) {
				while(j < N-level-1) {
					j++;
					System.out.println(a[i][j]);
					ct++;

				}
			}
			if(j == N-level-1) {
				while(i < N-level-1) {
					i++;
					System.out.println(a[i][j]);
					ct++;

				}
			}
			if( i == N-level-1) {
				while(j >= level+1) {
					j--;
					System.out.println(a[i][j]);
					ct++;

				}
			}
			if(j == level) {
				while(i > level+1) {
					i--;
					System.out.println(a[i][j]);
					ct++;

				}	
			}
			level++;

		}
	}
	
	//a2+b2 = c2+d2
	public static void pairSum() {
		int SZ = 100;
		long[] powArray = new long[SZ];
		for(int i = 0; i< SZ; i++){
			int v = i+1;
			powArray[i] = v*v*v;
		}
		int countPairs = 0;
		//MaxHeap.printArray(sq);
		int N1 = 0, N2 = SZ-1, N3, N4;
		while(N2 > 0) {
			N1=0;
			while(N2-N1 > 2) {
				long ts = powArray[N1] + powArray[N2];
				N3 = N1+1; N4 = N2-1;
				while(N4 > N3) {
					if(powArray[N4]+powArray[N3] < ts) {
						N3++;
					}else if(powArray[N4]+powArray[N3] > ts) {
						N4--;
					}else{
						//System.out.println((N1+1)+" "+(N2+1)+" "+(N3+1)+" "+(N4+1)+" CUBE "+ts);
						countPairs++;
						break;
					}
				}
				N1++;
			}
			N2--;
		}
		System.out.println("quadruplet pair count:"+countPairs);
	}
	
	public static void main(String[] args) {
		copyArrayDriver();
		transposeArray();
		matrixMultiply();
		findLargestPlateau();
		print2D(hadamard(4));
		commonBirthdaySimulate();
		couponCollect();
		pascal();
		randomPerm(7);
		maxOXGrid();
		findPeak();
		printMagicSquare();
		sudokuVerify();
		primeSieve(350);
		printSpiral();
		pairSum();
	}

}
