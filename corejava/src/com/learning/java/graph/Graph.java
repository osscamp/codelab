package com.learning.java.graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Graph {
	
	private int vertices;
	
	private List<Integer>[] adj;
	public Graph(int numberOfVertices) {
		this.vertices = numberOfVertices;
		adj = new ArrayList[vertices];
		for(int i=0; i<vertices; i++) {
			adj[i] = new ArrayList<Integer>();
		}
	}
	
	public Graph() {
		try {
			List<String> lines = Files.readAllLines(Paths.get("./tinyG.txt"));
			
			boolean vertexct = false;
			for(String line: lines) {
				if(!vertexct) {
					this.vertices =  Integer.valueOf(line);
					adj = new ArrayList[this.vertices];
					for(int i=0; i<adj.length; i++) {
						adj[i] = new ArrayList<Integer>();
					}
					vertexct = true;
					continue;
				}
				String[] edges = line.split("\\s+");
				int v = Integer.valueOf(edges[0]);
				int w = Integer.valueOf(edges[1]);
				this.addEdge(v, w);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int vertices() {
		return vertices;
	}
	
	public List<Integer> adj(int v) {
		return adj[v];
	}
	
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}

}
