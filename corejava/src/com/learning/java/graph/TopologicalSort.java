package com.learning.java.graph;

import java.util.Stack;

/**
 * DAG topological sort
 * @author sushukla
 *
 */
public class TopologicalSort {
		
	boolean[] marked;
	int[] edgeTo;
	Stack<Integer> reversePO;
	public TopologicalSort(DiGraph G) {
		marked = new boolean[G.vertices()];
		edgeTo = new int[G.vertices()];
		DirectedCycle dirCycle = new DirectedCycle(G);
		if(!dirCycle.hasCycle()) {
			reversePO = new Stack<>();
			for(int v=0; v<G.vertices(); v++) {
				if(!marked[v]){
					dfs(G, v);
				}
			}
		}
	}
	
	public void dfs(DiGraph G, int v) {
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			}
		}
		reversePO.push(v);
	}
	
	public Stack<Integer> order() {
		return reversePO;
	}

}
