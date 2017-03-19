package com.learning.java.graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class SymbolGraph {
	
	private Graph G;
	Map<String, Integer> keyMap;
	Map<Integer, String> reverseMap;
	private boolean[] marked;
	private int[] edgeTo;
	private String source;
	private boolean bfs;
	
	public SymbolGraph() {
		try {
			List<String> rList = Files.readAllLines(Paths.get("./routes.txt"));
			int i = 0;
			keyMap = new HashMap<>();
			reverseMap = new HashMap<>();
			for(String routeLn : rList) {
				String[] rr = routeLn.split("\\s+");
				if(rr.length != 2) throw new IllegalArgumentException("invalid file format");
				for(String rKey : rr) {
					if(!keyMap.containsKey(rKey)){
						keyMap.put(rKey, i);
						reverseMap.put(i,  rKey);
						i++;
					}
				}
			}
			G = new Graph(keyMap.size());
			for(String routeLn : rList) {
				String[] rr = routeLn.split("\\s+");
				G.addEdge(keyMap.get(rr[0]), keyMap.get(rr[1]));
			}
			marked = new boolean[G.vertices()];
			edgeTo = new int[G.vertices()];
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initSource(String source, boolean bfs) {
		this.source = source;
		this.bfs = bfs;
		if(bfs){
			bfs(G, keyMap.get(source));
		} else {
			dfs(G, keyMap.get(source));
		}
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
	
	public void bfs(Graph G, int v) {
		Queue<Integer> q = new LinkedList<>();
		marked[v] = true;
		q.add(v);
		while(!q.isEmpty()) {
			int u = q.poll();
			for(int w : G.adj(u)) {
				if(!marked[w]) {
					marked[w] = true;
					edgeTo[w] = u;
					q.add(w);
				}
			}
		}
	}
	
	public boolean isConnected(String v) {
		return marked[keyMap.get(v)];
	}
	
	public Stack<String> path(String dest) {
		if(!isConnected(dest)) return null;
		Stack<String> rst = new Stack<>();
		for(int x=keyMap.get(dest); x!=keyMap.get(source); x = edgeTo[x]) {
			rst.push(reverseMap.get(x));
		}
		rst.push(source);
		return rst;
	}
	
	public static void main(String[] args) {
		SymbolGraph sg = new SymbolGraph();
		sg.initSource("JFK", false);
		boolean match = sg.isConnected("DFW");
		System.out.println(match);
		System.out.println("pth DFS "+sg.path("LAS"));
		
		SymbolGraph sgBFS = new SymbolGraph();
		sgBFS.initSource("JFK", true);
		match = sgBFS.isConnected("DFW");
		System.out.println(match);
		System.out.println("pth BFS "+sgBFS.path("LAS"));
	}

}
