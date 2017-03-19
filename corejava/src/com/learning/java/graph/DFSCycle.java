package com.learning.java.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFSCycle {

	private boolean[] marked;
	private int[] edgeTo;
	private int source;
	private boolean hasCycle;
	List<Integer> cycle;

	
	public DFSCycle(Graph G, int s) {
		marked = new boolean[G.vertices()];
		edgeTo = new int[G.vertices()];
		this.source = s;
		dfs(G, s, s);
	}
	
	public void dfs(Graph G, int v, int s) {
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(cycle != null) return;
			if(!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w, v);
			}else if(w != s) {
				hasCycle = true;
                cycle = new ArrayList<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.add(x);
                }
                cycle.add(w);
                cycle.add(v);
			}
		}
	}
	
	public boolean hasCycle()
	{
		return hasCycle;
	}
	public boolean isConnected(int v) {
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v) {
		if(!isConnected(v)) return null;
		Stack<Integer> path = new Stack<>();
		for(int x=v; x != source; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(source);
		return path;
	}
	
	public static void main(String[] args) {
		Graph G = new Graph();
		DFSCycle dfsView = new DFSCycle(G, 0);
		boolean hasCycle = dfsView.hasCycle();
		System.out.println("has Cycle "+dfsView.cycle);
		boolean conn = dfsView.isConnected(2);
		System.out.println(conn);
		Iterable<Integer> path = dfsView.pathTo(2);
		System.out.println(path);
	}
}
