package com.learning.java.graph;

import java.util.PriorityQueue;
import java.util.Stack;

public class DijkstraSP {
	
	class DistHolder implements Comparable<DistHolder>{
		int idx;
		double dist;
		public DistHolder(int idx, double dist) {
			this.idx = idx;
			this.dist = dist;
		}
		@Override
		public int compareTo(DistHolder o) {
			return Double.compare(this.dist, o.dist);
		}
		
		@Override
		public boolean equals(Object o) {
			return this == o || this.idx == ((DistHolder)o).idx;
		}
		
	}
	
	private DirectedEdge edgeTo[];
	private double[] distTo;
	private PriorityQueue<DistHolder> pq;
	int s;
	
	public DijkstraSP(EdgeWeightedDigraph G, int s) {
		edgeTo = new DirectedEdge[G.vertices()];
		distTo = new double[G.vertices()];
		pq = new PriorityQueue<DistHolder>();
		this.s = s;
		for (int v = 0; v < G.vertices(); v++){
	           distTo[v] = Double.POSITIVE_INFINITY;
		}
	    distTo[s] = 0.0;
	    pq.add(new DistHolder(s, 0.0));
	    while(!pq.isEmpty()) {
	    	relax(G, pq.poll().idx);
	    }
	}
	
	private void relax(EdgeWeightedDigraph G, int v) {
		for(DirectedEdge e : G.adj(v)) {
			int w = e.to();
			if(distTo[w] > distTo[v] +e.wt()) {
				distTo[w] = distTo[v] +e.wt();
				edgeTo[w] = e;
				DistHolder temp = new DistHolder(w, distTo[w]);
				pq.remove(temp);
				temp.dist = distTo[w];
				pq.add(temp);
			}
		}
	}
	
	public double distTo(int v) {
		return distTo[v];
	}

	public boolean hasPathTo(int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}

	public Iterable<DirectedEdge> pathTo(int v) {
		if (!hasPathTo(v))
			return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
			path.push(e);
		return path;
	}
	
	public static void main(String[] args) {
		EdgeWeightedDigraph G = new EdgeWeightedDigraph();
		DijkstraSP dsp = new DijkstraSP(G, 5);
		System.out.println(dsp.pathTo(2));
	}

}
