package com.learning.btree;

import java.util.HashMap;
import java.util.Map;

public class GridOperations {
	
	static class GridUnion {
		int[] id = null;
		public GridUnion(int N) {
			id = new int[N];
			for(int i=0; i<N; i++) {
				id[i] = i;
			}
		}
		
		public int root(int v) {
			while(v != id[v]) {
				v = id[v];
			}
			return v;
		}
		
		public int getValue(int i) {
			if(i >= 0 && i < id.length) {
				return id[i];
			}
			return -1;
		}
		
		public void union(int base, int add) {
			int a = root(base);
			int b = root(add);
			id[b] = a;
		}
		
		public boolean isConnected(int p, int q) {
			return root(p) == root(q);
		}
		
		public void printAll() {
			StringBuilder sb = new StringBuilder();
			for(int i: id) {
				sb.append(i).append(" ");
			}
			System.out.println(sb);
		}
		
		public void printRoots() {
			StringBuilder sb = new StringBuilder();
			for(int i: id) {
				sb.append(root(i)).append(" ");
			}
			System.out.println(sb);
		}
	}
	
	public static void findConnectedOnes() {
		int[][] a = {
				{1,1,0,1},
				{1,0,1,1},
				{0,1,1,0},
				{1,0,1,1}};
		int D = a.length;
		int invoc = 0;

		int N = D*D;
		GridUnion grid = new GridUnion(N);
		for(int i=0; i<D; i++) {
			for(int j=0; j<D; j++) {
				//System.out.println(" "+i+" "+j);
				//grid.printAll();
				if(i*D+j != grid.getValue(i*D+j)) {
					//System.out.println("continue ");
					continue;
				}
				invoc++;
				int v = a[i][j];
				int left = j>0 ? a[i][j-1] : -1;
				int up = i>0 ? a[i-1][j] : -1;
				int right = j<D-1 ? a[i][j+1] : -1;
				int down = i<D-1 ? a[i+1][j] : -1;
				if(left == v) grid.union(i*D+j, i*D+(j-1));
				if(up == v) grid.union(i*D+j, (i-1)*D+j);
				if(right == v) grid.union(i*D+j, i*D+(j+1));
				if(down == v) grid.union(i*D+j, (i+1)*D+j);

			}
		}
		grid.printAll();
		System.out.println(grid.isConnected(3, 10));
		Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
		for(int i=0; i<D; i++) {
			for(int j=0; j<D; j++) {
				int root = grid.root(i*D+j);
				if(counts.get(root) == null) counts.put(root, 1);
				else counts.put(root, new Integer(counts.get(root)+1));
			}
		}
		System.out.println(counts);
		int biggest = 0;
		for(int k : counts.keySet()) {
			if(counts.get(k) > biggest) { biggest = counts.get(k); }
		}
		System.out.println("biggest number of connected ones "+biggest);
		System.out.println("inv "+invoc);
	}
	
	public static void finPathOnes() {
		int[][] a = {{1,1,0,1},{1,0,1,1},{0,1,1,0},{1,0,1,1}};

		System.out.println(find_path(4, a, 0,0));
	}
	
	public static int find_path(int N, int[][] matrix, int i, int j) {
	    if (matrix[i][j] == 0) return 0;
	    if (i == N-1 && j == N-1) return 1;
	    return i+1<N && find_path(N, matrix, i+1, j)>0 ||
	           j+1<N && find_path(N, matrix, i, j+1)>0 ? 1 : 0;
	}
	
	public static void main(String[] args) {
		findConnectedOnes();
		finPathOnes();
	}

}
