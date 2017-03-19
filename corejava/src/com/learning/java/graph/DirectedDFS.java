package com.learning.java.graph;

import java.util.List;

public class DirectedDFS {
	
	boolean[] marked;
	
	public DirectedDFS(DiGraph G, int source) {
		marked = new boolean[G.vertices()];
		dfs(G, source);
	}
	
	public DirectedDFS(DiGraph G, List<Integer> sources) {
		marked = new boolean[G.vertices()];
		for(int p : sources) {
			if(!marked[p]) {
				dfs(G, p);
			}
		}
	}
	
	public void dfs(DiGraph G, int v) {
		marked[v] = true;
		for(int w : G.adj(v))  {
			if(!marked[w]) {
				dfs(G, w);
			}
		}
	}
	
	public boolean marked(int k) {
		return marked[k];
	}

}
