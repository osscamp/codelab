package com.learning.java.graph;

public class LargestIsland {
	
	int[][] m = {
	        { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
	        { 1, 0, 1, 0, 0, 0, 1, 0, 1, 1 },
	        { 1, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
	        { 0, 0, 0, 0, 1, 0, 0, 1, 0, 0 },
	        { 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
	        { 1, 1, 1, 1, 0, 1, 1, 1, 1, 0 },
	        { 1, 0, 0, 0, 1, 0, 0, 1, 0, 1 },
	        { 1, 0, 1, 1, 1, 1, 0, 0, 1, 1 },
	        { 1, 1, 0, 0, 1, 0, 1, 0, 0, 1 },
	        { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 }
	};
	
	private Graph G;
		
	public LargestIsland() {
		int N = m.length;
		G = new Graph(N*N);
		marked = new boolean[G.vertices()];
		id = new int[G.vertices()];
		size = new int[G.vertices()];
		initGraph();
	}
	
	boolean[] marked;
	int[] edgeTo;
	int[] id;
	int[] size;
	int count;
	
	public void initGraph() {
		int N = m.length;
		int V = 1;
		for(int i=0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				int L = Math.max(j-1, 0);
				int R = Math.min(j+1, N-1);
				int U = Math.max(i-1, 0);
				int D = Math.min(i+1, N-1);
				if(m[i][j] == V && m[i][L] == V && L != j) G.addEdge(N*i+j, N*i+L);
				if(m[i][j] == V && m[i][R] == V && R != j) G.addEdge(N*i+j, N*i+R);
				if(m[i][j] == V && m[U][j] == V && U != i) G.addEdge(N*i+j, N*U+j);
				if(m[i][j] == V && m[D][j] == V && D != i) G.addEdge(N*i+j, N*D+j);
			}
		}
		for(int i=0; i<G.vertices(); i++) {
			if(!marked[i] && m[i/N][i%N] == V) {
				dfs(G, i);
				count++;
			}
		}
	}
	
	public void dfs(Graph G, int v) {
		marked[v] = true;
		id[v] = count;
		size[id[v]]++;
		for(int w : G.adj(v)) {
			if(!marked[w]) {
				//edgeTo[w] = v;
				dfs(G, w);
			}
		}
	}
	
	public int numComponents() {
		return count;
	}
	
	public boolean isConnected(int u, int v){
		return id[u] == id[v];
	}
	
	public int maxComponentSize() {
		int max = 0;
		for(int i : id) {
			if(size[i] > max) {
				max = size[i];
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		LargestIsland ll = new LargestIsland();
		System.out.println("components:"+ll.numComponents());
		System.out.println("biggest component:"+ll.maxComponentSize());
	}

}
