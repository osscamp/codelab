package com.learning.java.graph;

import java.util.List;
import java.util.Stack;

public class DFSPaths {
	
	private boolean[] marked;
	private int[] edgeTo;
	private int source;
	
	public DFSPaths(Graph G, int s) {
		marked = new boolean[G.vertices()];
		edgeTo = new int[G.vertices()];
		this.source = s;
		dfs(G, s);
	}
	
	public void dfs(Graph G, int v) {
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			}
		}
	}
	
	public boolean isConnected(int v) {
		return marked[v];
	}
	
	public boolean hasEdge(Graph G, int v, int w) {
		List<Integer> vlist = G.adj(v);
		List<Integer> ulist = G.adj(w);
		return vlist.contains(w) || ulist.contains(v);
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
		DFSPaths dfsView = new DFSPaths(G, 0);
		boolean conn = dfsView.isConnected(4);
		System.out.println(conn);
		Iterable<Integer> path = dfsView.pathTo(6);
		System.out.println(path);
	}

}
