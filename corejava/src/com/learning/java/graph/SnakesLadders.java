package com.learning.java.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * Given a snake and ladder board of order 5x6 , find the minimum number of dice throws 
 * required to reach the destination or last cell (30th cell) from source (1st cell) .
 * solved by bfs in a directed acyclic graph. dice throw can result in number 1 to 6 so
 * edges are created between node n to node n+6. node with snake only connects to the
 * node where the tail ends. 
 * @author sushukla
 *
 */
public class SnakesLadders {
	class SlDiGraph {
		
		private List<Integer>[] adj;
		private int vertices;
		public SlDiGraph(int v){
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
	
	boolean[] marked;
	int[] edgeTo;
	SlDiGraph g;
    int source = 1;
    Stack<Integer> reversePost = new Stack<>();
    Map<Integer, Integer> vmap = new HashMap<>();
    Map<Integer, Integer> revmap = new HashMap<>();
	public SnakesLadders(int boardSize){

		g = new SlDiGraph(boardSize);
		for(int i=1; i<30; i++) {
			if(i==3 || i==5 || i==11 || i==17 || i==19 || i==20 || i==21 || i==27){
				continue;
			}
			g.addEdge(i, i+1);
			if(i+2 <= 30)
			g.addEdge(i, i+2);
			if(i+3 <= 30)
			g.addEdge(i, i+3);
			if(i+4 <= 30)
			g.addEdge(i, i+4);
			if(i+5 <= 30)
			g.addEdge(i, i+5);
			if(i+6 <= 30)
			g.addEdge(i, i+6);
		}
		
		g.addEdge(3,22);
		g.addEdge(5,8);		
		g.addEdge(11,26);
		g.addEdge(20,29);
		
		///////
		g.addEdge(17,4);
		g.addEdge(19,7);
		g.addEdge(21,9);
		g.addEdge(27,1);
		
		marked = new boolean[boardSize];
		edgeTo = new int[boardSize];
		//dfs(g, source);
		for(int i=0; i<g.vertices(); i++) {
			System.out.println(g.adj(i));
		}
		bfs(g);
		
	}
	
	public void dfs(SlDiGraph g, int v) {
		marked[v] = true;
		for(int w : g.adj(v)) {
			if(!marked[w]) {
				dfs(g, w);
				edgeTo[w] = v;
			}
		}
		reversePost.push(v);
	}
	
	public void bfs(SlDiGraph g) {
		Queue<Integer> q = new LinkedList<>();
		q.add(source);
		marked[source] = true;
		while(!q.isEmpty()) {
			Integer v = q.poll();
			for(int w : g.adj(v)) {
				if(!marked[w]) {
					marked[w] = true;
					edgeTo[w] = v;
					q.add(w);
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
	
	public static void main(String[] args) {
		SnakesLadders sl = new SnakesLadders(30+1);
	    int dest = 27;
	    Stack<Integer> paths = sl.getPath(dest);
	    System.out.println("shortest path");
	    while(!paths.isEmpty()) {
	    	System.out.print("--> "+paths.pop());
	    }

	}

}
