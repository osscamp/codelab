package com.learning.btree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DiGraph {
	
	List<Integer>[] adj;
	private boolean[] marked;
	//used for printing paths
	int source;
	private int[] edgeTo;
	
	//for cycle
	private boolean[] isExamined;
	private Stack<Integer> cycle;

	
	public DiGraph(String data) {
		System.out.println(data);
		String[] lines = data.split("\\n");
		int sz = lines.length+5;
		adj = (ArrayList<Integer>[]) new ArrayList[sz];
		edgeTo = new int[sz];
		for(int i=0; i<sz; i++) {
			adj[i] = new ArrayList<>(5);
		}
 		//approx graph size;
		for(String s:lines) {
			String[] nodes = s.split("->");
			//System.out.println(nodes[0]);
			addEdge(Integer.valueOf(nodes[0]), Integer.valueOf(nodes[1]));
		}
		marked = new boolean[sz];
		isExamined = new boolean[sz];
	}
	
	public void validateVertex(int n) {
		if(n < 0 || n > adj.length) {
			throw new IllegalArgumentException();
		}
	}
	
	public void addEdge(int left, int right) {
		validateVertex(left);
		validateVertex(right);
		adj[left].add(right);
	}
	
	public List<Integer> adj(int v) {
		validateVertex(v);
		return adj[v];
	}
	
	//find all paths from source 
	public void findPathsSource(int v) {
		source = v;
		dfs(v);
	}
	
	//dfs between source and vertex v
    public void dfs(int v) { 
    	validateVertex(v);
        marked[v] = true;
        isExamined[v] = true;
        for (int w : adj(v)) {
        	if(cycle != null) { return; }
        	else if (!marked[w]) {
            	edgeTo[w] = v;
            	dfs(w);
            } else if(isExamined[w]) {
            	cycle = new Stack<>();
            	for(int x=v; x != w; x=edgeTo[x]) {
            		cycle.push(x);
            	}  
            	cycle.push(w);
            	cycle.push(v);
            }
        }
        isExamined[v] = false;
    }
    
    //should be called after dfs
    public boolean isPath(int v) {
    	validateVertex(v);
    	return marked[v];
    }
    
    public Iterable<Integer> pathTo(int v) {
    	validateVertex(v);
    	if(!isPath(v)) return null;
    	Stack<Integer> path = new Stack<>();
    	for(int x=v; x != source; x=edgeTo[x]) {
    		path.push(x);
    	}
    	path.push(source);
    	return path;
    }
    
    public boolean hasCycle() {
        return cycle != null;
    }
	

	public void print() {
		for(int i=0; i<adj.length; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append(i).append(":").append(adj[i]);
			System.out.println(sb);
		}
	}
	
	public static void main(String[] args) {
		StringBuilder data = new StringBuilder("1->2");
		data.append("\n2->3");
		data.append("\n2->4");
		data.append("\n4->5");
		data.append("\n1->4");
		data.append("\n4->6");
		data.append("\n6->3");
		
		//data.append("\n5->4");
		DiGraph dg = new DiGraph(data.toString());
		dg.print();
		dg.findPathsSource(1);
		boolean isPath = dg.isPath(6);
		System.out.println("isPath "+isPath);
		System.out.println(dg.pathTo(6));
		System.out.println("has cycle "+dg.hasCycle());
		if(dg.hasCycle()) {
			System.out.println(dg.cycle);
		}
	}

}
