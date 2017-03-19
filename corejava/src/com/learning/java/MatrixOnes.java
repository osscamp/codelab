package com.learning.java;

/*
 * find maximum + sign in the matrix
 */
public class MatrixOnes {
	
	int[][] m = {
	        { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
	        { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
	        { 1, 1, 1, 0, 1, 1, 0, 0, 0, 1 },
	        { 0, 0, 0, 0, 1, 1, 0, 1, 0, 0 },
	        { 1, 1, 1, 0, 1, 1, 1, 1, 1, 0 },
	        { 1, 1, 1, 1, 0, 1, 1, 0, 1, 1 },
	        { 1, 0, 0, 0, 1, 1, 0, 1, 0, 1 },
	        { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
	        { 1, 1, 0, 0, 1, 0, 1, 0, 0, 1 },
	        { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 }
	};
	
	int maxp = 0;
	int iterCount = 0;
	
	public void maxPlus(){
		int N = m.length;
		for(int i = N/2;i > 0; i--){
			for(int j = i; j > 0; j--){
				iterCount++;
				expand(i, j, N);
			}
		}
		for(int i = N/2;i > 0; i--){
			for(int j = i+1; j < N-1; j++){
				iterCount++;
				expand(i, j, N);
			}
		}
		for(int i = N/2+1; i<N-1; i++){
			for(int j = i-1; j > 0; j--){
				iterCount++;
				expand(i, j, N);
			}
		}
		for(int i = N/2+1;i < N-1; i++){
			for(int j = i; j < N-1; j++){
				iterCount++;
				expand(i, j, N);
			}
		}
		System.out.println("max plus sign "+(maxp*4+1)+" iter "+iterCount);
	}
	
	public void expand(int i, int j, int N) {
		int ii=i;
		int jj=j;
		int ctr = 0;
		if(m[ii][jj] == 1) {
			int b = 1;
			while(ii-b >= 0 && ii+b < N && jj-b >= 0 && jj+b < N && 
					m[ii+b][jj] == 1 && m[ii-b][jj] == 1 && m[ii][jj+b] == 1 && m[ii][jj-b] == 1) {
				b++;
				ctr++;
				iterCount++;
			}
		}
		if(maxp < ctr){
			maxp = ctr;
		}		
	}
	
	public static void count0()
	{
		int[][] m = {
				{0,0,0,1},
				{0,0,0,1},
				{0,0,1,1},
				{0,1,1,1}
		};
		int N = m.length;
		int j = N-1;
		int i = 0;
		int total = 0;
		while(j >= 0 && i<N) {
			if(m[i][j] == 1) {
				j--;
			}else{
				total+=(j+1);
				i++;
			}
		}
		System.out.println(total);
	}
	public static void main(String[] args) {
		MatrixOnes mone = new MatrixOnes();
		mone.maxPlus();
		//count0();
	}

}
