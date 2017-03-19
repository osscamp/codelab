package com.learning.java.graph;

public class ConnectedCompsGraph {
	boolean[] marked;
	int[] edgeTo;
	int[] id;
	int[] size;
	int count; //count of components
	public ConnectedCompsGraph(Graph G) {
		marked = new boolean[G.vertices()];
		edgeTo = new int[G.vertices()];
		id = new int[G.vertices()];
		size = new int[G.vertices()];
		for(int i=0; i<G.vertices(); i++) {
			if(!marked[i]) {
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
				edgeTo[w] = v;
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
		Graph G = new Graph();
		ConnectedCompsGraph cc = new ConnectedCompsGraph(G);
		System.out.println("components:"+cc.numComponents());
		System.out.println("biggest component:"+cc.maxComponentSize());
	}

}
