package com.learning.btree;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraph {
	
	static class UGraph {
		List<Integer>[] adj;
		int vertices;
		public UGraph(int V) {
			vertices = V;
			adj = new ArrayList[V];
			for(int i=0; i<V; i++) {
				adj[i] = new ArrayList<Integer>();
			}
		}
		
		public int V() {
			return vertices;
		}
		
		public void addEdge(int v, int w) {
			adj[v].add(w);
			adj[w].add(v);
		}
		
		public List<Integer> adj(int v) {
			return adj[v];
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i<adj.length; i++) {
				sb.append(adj[i].toString());
			}
			return sb.toString();
		}
	}
	
	boolean[] marked;
	int[] size;
	int[] id;
	int count;
	
	public UndirectedGraph(UGraph g) {
		marked = new boolean[g.V()];
		size = new int[g.V()];
		id = new int[g.V()];
		for(int v=0; v<g.V(); v++) {
			if(!marked[v]) {
				dfs(g, v);
				count++;
			}
		}
	}
	
	public void dfs(UGraph g, int v) {
		marked[v] = true;
		id[v] = count;
		if(g.adj(v).size() > 0) {
			size[count]++;
		}
		for(int w : g.adj(v)){
			if(!marked[w]) {
				marked[w] = true;
				dfs(g, w);
			}
		}
	}
	
	public int id(int v) {
		return id[v];
	}
	
	public int size(int v) {
		return size[id[v]];
	}
	
	public boolean connected(int v, int w) {
		return id[v] == id[w];
	}
	
	public int connectedCompCount() {
		return count;
	}
	
	public int maxConnectedComps() {
		int max = 0;
		for(int k: size) {
			if(k > max) {
				max = k;
			}
		}
		return max;
	}
	
	//find size of connected comps given array index: (3,2)
	public int sizeOfConnectedCompAt(int v) {
		int countcomp = 0;
		int val = id[v];
		for(int d:id ) {
			if( val == d ) {
				countcomp++;
			}
		}
		return countcomp;
	}
	
	//if 1's are all connected find min rectangle containing ones
	public void minRectangleOfOnes() {
		int m;
		for(m=0; m<size.length; m++) {
			if(size[m]!=0) {
				break;
			}
		}
		int idv = id[m];
		int lasti = m;
		for(int p = m; p<id.length; p++) {
			if(idv == id[p]) {
				lasti = p;
			}
		}
		System.out.println("start "+(m/5+","+m%5)+" end "+(lasti/5+","+lasti%5));
	}
	
	public void print() {
		MaxHeap.printArray(id);
		MaxHeap.printArray(size);
	}
	
	public static void main(String[] args) {
		int N = 5;
		int[][] grid = {
			{0,0,0,0,0},
			{0,1,1,1,0},
			{0,1,0,1,0},
			{0,1,1,1,0},
			{0,0,0,0,0}
		};
		UGraph ug = new UGraph(N*N);
		boolean[] markedAdj = new boolean[N*N];
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				int v = grid[i][j];
				if(markedAdj[i*N+j] || grid[i][j] == 0) {
					continue;
				}
				int left = j>0 ? grid[i][j-1] : -1;
				int up = i>0 ? grid[i-1][j] : -1;
				int right = j<N-1 ? grid[i][j+1] : -1;
				int down = i<N-1 ? grid[i+1][j] : -1;
				if(v == left) {ug.addEdge(i*N+j, i*N+(j-1));markedAdj[i*N+(j-1)] = true;}
				if(v == up) {ug.addEdge(i*N+j, (i-1)*N+j);markedAdj[(i-1)*N+j] = true;}
				if(v == right) {ug.addEdge(i*N+j, i*N+(j+1)); markedAdj[i*N+(j+1)] = true;}
				if(v == down) {ug.addEdge(i*N+j, (i+1)*N+j);markedAdj[(i+1)*N+j] = true;}
			}
		}
		System.out.println(ug);
		UndirectedGraph graph = new UndirectedGraph(ug);
		System.out.println("connected comps "+graph.connectedCompCount());
		System.out.println("max size of connected comps of '1' "+graph.maxConnectedComps());
		int i=3, j=1;
		System.out.println("size of connected comp at ["+i+","+j+"] "+graph.sizeOfConnectedCompAt(i*N+j));
		graph.minRectangleOfOnes();
		graph.print();
		
	}
}
