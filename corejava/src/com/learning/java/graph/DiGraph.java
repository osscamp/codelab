package com.learning.java.graph;

import java.util.ArrayList;
import java.util.List;

public class DiGraph {
	
	private List<Integer>[] adj;
	private int vertices;
	public DiGraph(int v){
		this.vertices = v;
		adj = new ArrayList[vertices];
		for(int i=0; i<vertices; i++){
			adj[i] = new ArrayList<>();
		}
	}
	
	public int vertices() {
		return vertices;
	}
	
	public List<Integer> adj(int v) {
		return adj[v];
	}
	
	public void addEdge(int u, int v) {
		adj[u].add(v);
	}
	
	public void removeEdge(int u, int v) {
		adj[u].remove(v);
	}
}
