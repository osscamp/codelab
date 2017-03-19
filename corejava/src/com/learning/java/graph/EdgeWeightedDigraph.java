package com.learning.java.graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EdgeWeightedDigraph {
	
	private List<DirectedEdge>[] adj;
	private int vertices;
	
	public EdgeWeightedDigraph() {
		try {
			List<String> lines = Files.readAllLines(Paths.get("./tinyEWDG.txt"));
			
			boolean vertexct = false;
			for(String line: lines) {
				if(!vertexct) {
					this.vertices =  Integer.valueOf(line);
					adj = new ArrayList[this.vertices];
					for(int i=0; i<adj.length; i++) {
						adj[i] = new ArrayList<DirectedEdge>();
					}
					vertexct = true;
					continue;
				}
				String[] edges = line.split("\\s+");
				int v = Integer.valueOf(edges[0]);
				int w = Integer.valueOf(edges[1]);
				double wt = Double.parseDouble(edges[2]);
				DirectedEdge e = new DirectedEdge(v,w,wt);
				this.addEdge(e);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void addEdge(DirectedEdge e) {
		adj[e.from()].add(e);
	}
	
	public List<DirectedEdge> adj(int v) {
		return adj[v];
	}
	
	public int vertices() {
		return vertices;
	}

}
