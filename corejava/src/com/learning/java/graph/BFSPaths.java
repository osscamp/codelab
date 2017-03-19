package com.learning.java.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFSPaths {
	
	boolean[] marked;
	int[] edgeTo;
	int[] distTo;
	int source;
	
	public BFSPaths(Graph G, int s) {
		marked = new boolean[G.vertices()];
		edgeTo = new int[G.vertices()];
		distTo = new int[G.vertices()];
		this.source = s;
		bfs(G, s);
	}
	
	public void bfs(Graph G, int v) {
		Queue<Integer> ll = new LinkedList<>();
		marked[v] = true;
		ll.add(v);
		while(!ll.isEmpty()) {
			int u = ll.poll();
			for(int w : G.adj(u)) {
				if(!marked[w]) {
					edgeTo[w] = u;
					marked[w] = true;
					distTo[w] = distTo[u] + 1;
					ll.add(w);
				}
			}
		}
	}
	
	public boolean isConnected(int v) {
		return marked[v];
	}
	
	public Stack<Integer> getPath(int v) {
		if(!isConnected(v)) return null;
		Stack<Integer> paths = new Stack<>();
		for(int x=v; x!=source; x = edgeTo[x]) {
			paths.push(x);
		}
		paths.push(source);
		return paths;
	}
	
	public int distTo(int v) {
		return distTo[v];
	}
	
	public static void main(String[] args) {
		Graph G = new Graph();
		BFSPaths bfsPaths = new BFSPaths(G, 3);
		System.out.println(bfsPaths.getPath(6));
		System.out.println(bfsPaths.distTo(6));
	}

}
