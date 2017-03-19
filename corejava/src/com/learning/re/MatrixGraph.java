package com.learning.re;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MatrixGraph {
	
	private int vertices;
	private Set<Integer> [] adj;
	private boolean[] marked;
	private int[] id; //holds parent id
	private int[] size; //size of connected comp at id
	private int count = 0;
	
	public MatrixGraph(int v) {
		vertices = v;
		adj = (Set<Integer> [])new HashSet[v];
		for(int i=0; i< vertices; i++) {
			adj[i] = new HashSet<>();
		}
		marked = new boolean[v];
		id = new int[v];
		size = new int[v];
	}
	
	public void addEdge(int from, int to) {
		adj[from].add(to);
		adj[to].add(from);
	}
	
	/**
	 * called after adding edges
	 */
	public void init() {
		for(int index = 0; index < vertices; index++) {
			if(!marked[index]) {
				dfs(index);
				count++;
			}
		}
	}
	
	public void dfs(int index) {
		marked[index] = true;
		id[index] = count;
		if(adj[index].size() > 0) {
			size[count]++;
		}
		for(int v : adj[index]) {
			if(!marked[v]) {
				marked[v] = true;
				dfs(v);
			}
		}
	}
	
	public boolean isConnected(int from, int to) {
		return id[from] == id[to];
	}
	
	public int connectedSize(int i) {
		return size[id[i]];
	}
	
	public int connectedGroups() {
		return count;
	}
	
	public int biggestComponent() {
		int max = 0;
		for(int eachSz:size) {
			if(eachSz > max) {
				max = eachSz;
			}
		}
		return max;
	}
	
	@Override
	public String toString() {
		return Arrays.asList(adj).toString();
	}
	
	public static void main(String[] args) {
		char[][] matrix = {
				{'X','X','0','X','X'},
				{'0','X','0','X','X'},
				{'X','X','0','0','0'},
				{'0','X','0','0','0'},
				{'0','0','0','0','X'}
		};
		int N = matrix.length;
		MatrixGraph mg = new MatrixGraph(N*N);
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(j<N-1) if(matrix[i][j] == 'X' && matrix[i][j+1] == 'X') mg.addEdge(N*i+j, N*i+j+1);
				if(j>0) if(matrix[i][j] == 'X' && matrix[i][j-1] == 'X') mg.addEdge(N*i+j, N*i+j-1);
				if(i<N-1) if(matrix[i][j] == 'X' && matrix[i+1][j] == 'X') mg.addEdge(N*i+j, N*(i+1)+j);
				if(i>0) if(matrix[i][j] == 'X' && matrix[i-1][j] == 'X') mg.addEdge(N*i+j, N*(i-1)+j);
			}
		}
		mg.init();
		System.out.println(mg);
		System.out.println(mg.isConnected(1, 13));
		for(int i=0; i<N*N; i++) {
			System.out.println(mg.size[mg.id[i]]);
		}
		System.out.println("max comp "+mg.biggestComponent());
	}

}
