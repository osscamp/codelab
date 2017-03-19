package com.learning.java.graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EdgeWeightedGraph {
	
	private List<Edge>[] adj;
	private int vertices;
	private int edges;
	
	public EdgeWeightedGraph(int vertices) {
		this.vertices = vertices;
		adj = new ArrayList[vertices];
		for(int i=0; i<vertices; i++) {
			adj[i] = new ArrayList<>();
		}
	}
	
	public EdgeWeightedGraph() {
		try {
			List<String> lines = Files.readAllLines(Paths.get("./tinyEWG.txt"));
			
			boolean vertexct = false;
			for(String line: lines) {
				if(!vertexct) {
					this.vertices =  Integer.valueOf(line);
					adj = new ArrayList[this.vertices];
					for(int i=0; i<adj.length; i++) {
						adj[i] = new ArrayList<Edge>();
					}
					vertexct = true;
					continue;
				}
				String[] edges = line.split("\\s+");
				int v = Integer.valueOf(edges[0]);
				int w = Integer.valueOf(edges[1]);
				double wt = Double.parseDouble(edges[2]);
				Edge e = new Edge(v,w,wt);
				this.addEdge(e);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		edges++;
	}
	
	public int vertices(){
		return vertices;
	}
	
	public int edges() {
		return edges;
	}

	public List<Edge> adj(int v) {
		return adj[v];
	}
}
