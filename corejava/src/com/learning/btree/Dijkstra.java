package com.learning.btree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
	
	static class Vertex implements Comparable<Vertex> {
		
		public final String name;
		public Edge[] adjacencies;
		public double minDistance = Double.POSITIVE_INFINITY;
		public Vertex previous;
		
		public Vertex(String name) {
			this.name = name;
		}

		@Override
		public int compareTo(Vertex o) {
			return Double.compare(minDistance, o.minDistance);
		}
		
		@Override
		public String toString() {
			return name;
		}
		
	}
	
	static class Edge {
		public final Vertex target;
		public final double weight;
		public Edge(Vertex target, double weight) {
			this.target = target;
			this.weight = weight;
		}		
	}
	
	public static void computePaths(Vertex source) {
		source.minDistance = 0.0;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<>();
		vertexQueue.add(source);
		while(!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();
			for(Edge e: u.adjacencies) {
				Vertex v = e.target;
				double weight = e.weight;
				double distanceU = u.minDistance + weight;
				if(distanceU < v.minDistance) {
					vertexQueue.remove(v);
					v.minDistance = distanceU;
					v.previous = u;
					vertexQueue.add(v);
				}
			}
		}
	}
	
	public static List<Vertex> getShortestPathTo(Vertex target) {
		List<Vertex> path = new ArrayList<>();
		for(Vertex vertex = target; vertex != null; vertex = vertex.previous) {
			path.add(vertex);
		}
		Collections.reverse(path);
		return path;
	}
	
	public static void main(String[] args) {
        Vertex v0 = new Vertex("Redvile");
		Vertex v1 = new Vertex("Blueville");
		Vertex v2 = new Vertex("Greenville");
		Vertex v3 = new Vertex("Orangeville");
		Vertex v4 = new Vertex("Purpleville");
		v0.adjacencies = new Edge[] { new Edge(v1, 5), new Edge(v2, 10),
				new Edge(v3, 8) };
		v1.adjacencies = new Edge[] { new Edge(v0, 5), new Edge(v2, 3),
				new Edge(v4, 7) };
		v2.adjacencies = new Edge[] { new Edge(v0, 10), new Edge(v1, 3) };
		v3.adjacencies = new Edge[] { new Edge(v0, 8), new Edge(v4, 2) };
		v4.adjacencies = new Edge[] { new Edge(v1, 7), new Edge(v3, 2) };
		
		Vertex[] vertices = { v0, v1, v2, v3, v4 };
		computePaths(v0);
		for (Vertex v : vertices) {
			System.out.println("Distance to " + v + ": " + v.minDistance);
			List<Vertex> path = getShortestPathTo(v);
			System.out.println("Path: " + path);
		}
	}

}
