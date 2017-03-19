package com.learning.java.graph;

import java.util.Stack;

public class DirectedCycle {
	
	private boolean[] marked;
	private boolean[] onStack;
	private int[] edgeTo;
	private Stack<Integer> cycle;
	private Stack<Integer> reversePost;
	public DirectedCycle(DiGraph G) {
		marked = new boolean[G.vertices()];
		edgeTo= new int[G.vertices()];
		onStack = new boolean[G.vertices()];
		for(int i=0; i<G.vertices();i++){
			if(!marked[i]) {
				dfs(G, i);
			}
		}
	}
	
	public void dfs(DiGraph G, int v) {
		marked[v] = true;
		onStack[v] = true;
		for(int w : G.adj(v)) {
			if(hasCycle()) {
				return;
			}
			if(!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			}else if(onStack[w]){
				cycle = new Stack<>();
				for(int x=v; x!=w; x=edgeTo[x]) {
					cycle.add(x);
				}
				cycle.add(w);
				cycle.add(v);
			}
		}
		reversePost.push(v);

		onStack[v] = false;
	}
	
	public Iterable<Integer> getTopological() {
		return reversePost;
	}
	
	public boolean hasCycle(){
		return cycle != null;
	}

}
