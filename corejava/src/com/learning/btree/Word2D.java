package com.learning.btree;

import java.util.Stack;

public class Word2D {
	
	char[][] m = new char[][] {
			{'v','s','e','v'},
			{'l','i','r','l'},
			{'v','e','b','i'},
			{'e','m','t','s'},
	};
		
	static class IndexHolder {
		int i;
		int j;
		boolean visited = false;
		public IndexHolder(int i, int j) {
			this.i = i;
			this.j = j;
		}
		public int getI() {
			return i;
		}
		public int getJ() {
			return j;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "("+i+","+j+")";
		}
		
	}
	
	public boolean isWord(String word) {
		int N = m.length;
		Stack<IndexHolder> st = new Stack<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				boolean dec = false;
				int sc = 0;

				if(m[i][j] == word.charAt(sc)) {
					boolean[][] visited = new boolean[N][N];
					visited[i][j] = true;
					sc++;
					int k = i;
					int l = j;
					st.push(new IndexHolder(k, l));
					int matchl = 1;
					IndexHolder h = null;
					while(k >=0 && l >= 0 && k < N && l < N)  {
						if(matchl == word.length() || sc >= word.length()) {
							System.out.println(st);
							return true;
						}
						char lc = l>0?m[k][l-1]:Character.MIN_VALUE;
						char rc = l<N-1?m[k][l+1]:Character.MIN_VALUE;
						char uc = k>0?m[k-1][l]:Character.MIN_VALUE;
						char dc = k<N-1?m[k+1][l]:Character.MIN_VALUE;
						if(lc == word.charAt(sc) && !visited[k][l-1]) {
							sc++;
							l--;
							st.push(new IndexHolder(k, l));
							visited[k][l] = true;
							matchl++;
							continue;
						}
						if(rc == word.charAt(sc) && !visited[k][l+1]) {
							sc++;
							l++;
							st.push(new IndexHolder(k, l));
							visited[k][l] = true;
							matchl++;
							continue;
						}
						if(uc == word.charAt(sc) && !visited[k-1][l]) {
							sc++;
							k--;
							st.push(new IndexHolder(k, l));
							visited[k][l] = true;
							matchl++;
							continue;
						}
						if(dc == word.charAt(sc) && !visited[k+1][l]) {
							sc++;
							k++;
							st.push(new IndexHolder(k, l));
							visited[k][l] = true;
							matchl++;
							continue;
						}
						//no match, backtrack
						if(!st.isEmpty()) {
							h = st.pop();
							h.visited = true;
							k = h.getI();
							l = h.getJ();
							matchl--;
							if(dec) {
								sc--;
							}
							dec = true;
						} else {
							break;
						}
					}
				}
				//sc = 0;
			}
		}
		return false;
	}
	
	public boolean isWordDFS(String w){
		boolean exist = false;
		for(int i=0; i<m.length; i++){
			for(int j =0;j<m.length;j++){
				exist = dfs(w, 0, i, j, new boolean[m.length][m.length]);
				if(exist) {
					System.out.println("finally "+exist+" "+i+" "+j);
					break;
				}
			}
			if(exist) break;
		}
		return exist;
	}
	
	public boolean dfs(String w, int index, int i, int j, boolean[][] marked) {
		if(isOOB(i,j)){
			return false;
		}


		char c = w.charAt(index);
		if(c==m[i][j]){
			if(marked[i][j]){
				return false;
			}else{
				marked[i][j] = true;
			}
			if(index == w.length()-1){
				System.out.println("str is found ");
				return true;
			}
/*			boolean r = dfs(w, index+1, i, j+1, marked);
			if(r) return r;
			r = dfs(w, index+1, i, j-1, marked);
			if(r) return r;
			r = dfs(w, index+1, i+1, j, marked);
			if(r) return r;
			r = dfs(w, index+1, i-1, j, marked);
			if(r) return r;*/
			return dfs(w, index+1, i, j+1, marked)
			||
			dfs(w, index+1, i, j-1, marked)
			||
			dfs(w, index+1, i+1, j, marked)
			||
			dfs(w, index+1, i-1, j, marked);
		}
		return false;
	}
	
	public boolean isOOB(int i, int j) {
		int N = m.length;
		if(i < 0 || i >= N || j < 0 || j >= N) return true;
		return false;
	}
	
	public static void main(String[] args) {
		Word2D w2d = new Word2D();
		System.out.println("isFound "+w2d.isWord("silveb"));;
		w2d = new Word2D();
		w2d.isWordDFS("silver");

	}

}
