package com.learning.btree;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraphCycle {
	
	boolean[] marked;
	UndirectedGraph.UGraph g;
	List<Integer> cycle;
	int[] edgeTo;
	
	public UndirectedGraphCycle(UndirectedGraph.UGraph g) {
		//assuming there are no self loops and parallel edges
		this.g = g;
		edgeTo = new int[this.g.V()];
		marked = new boolean[this.g.V()];
		for(int v = 0; v<marked.length; v++) {
			if(!marked[v])
				dfs(-1, v);
		}
	}
	
	public void dfs(int u, int v) {
		marked[v] = true;
		for(int w: g.adj[v]) {
			if(cycle != null) {
				return;
			}
			if(!marked[w]) {
				edgeTo[w] = v;
				dfs(v, w);
			}
            else if (w != u) {
                cycle = new ArrayList<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.add(x);
                }
                cycle.add(w);
                cycle.add(v);
            }
		}
	}
	
	public static void main(String[] args) {
		UndirectedGraph.UGraph graph = new UndirectedGraph.UGraph(5);
		graph.addEdge(0, 1);
		graph.addEdge(2, 1);
		graph.addEdge(0, 2);
		graph.addEdge(4, 1);
		UndirectedGraphCycle ugcycle = new UndirectedGraphCycle(graph);
		System.out.println(ugcycle.cycle);
	}

}
