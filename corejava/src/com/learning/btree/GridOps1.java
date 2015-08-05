package com.learning.btree;

import java.util.HashMap;
import java.util.Map;

public class GridOps1 {
	public static class QUF {
		int id[];
		public QUF (int N) {
			id = new int[N*N];
			for(int i=0; i<id.length; i++) {
				id[i] = i;
			}
		}
	
		int root(int i) {
			while(i != id[i]) {
				i = id[i];
			}
			return i;
		}
	
		void union(int a, int b) {
			int roota = root(a);
			int rootb = root(b);
			id[roota] = rootb;
		}
		
		boolean isConnected(int a, int b) {
			return (root(a) == root(b));
		}
	}
	static QUF unionFind;
	static int N;
	static int[][] data = {
		{1,2,2,3,5},
		{3,2,3,4,4},
		{2,4,5,3,1},
		{6,7,1,4,5},
		{5,1,1,2,4}};
	
	public GridOps1(int size) {
		N = size;
		unionFind = new QUF(N*N);
		
	}
	
	public void processData() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int idx = i*N+j;
				int v = data[i][j];
				int left = j>0 ? data[i][j-1] : Integer.MAX_VALUE;
				int up = i>0 ? data[i-1][j] : Integer.MAX_VALUE;
				int right = j<N-1 ? data[i][j+1] : Integer.MAX_VALUE;
				int down = i<N-1 ? data[i+1][j] : Integer.MAX_VALUE;

				if(left <= v) {unionFind.union(idx, i*N+(j-1));}

				if(right <= v) {
					unionFind.union(idx, i*N+(j+1));
				}

				if(up <= v) {
					unionFind.union(idx, (i-1)*N+j);
				}
				if(down <= v) {
					unionFind.union(idx, (i+1)*N+j);
				
				}
			}
		}
	}
		
	public int maxConnected() {
		Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int root = unionFind.root(i*N+j);
				if(counts.get(root) == null) counts.put(root, 1);
				else counts.put(root, new Integer(counts.get(root)+1));
			}
		}
		System.out.println("isconnec "+unionFind.isConnected(2, 24));
		int biggest = 0;
		for(int k : counts.keySet()) {
			if(counts.get(k) > biggest) { biggest = counts.get(k); }
		}
		System.out.println("biggest number of connected ones "+biggest);
		return biggest;
	}
		
	public static void main(String[] args) {
		GridOps1 go = new GridOps1(5);
		go.processData();
		int maxc = go.maxConnected();
		System.out.println("maxc "+maxc);
	}
}
