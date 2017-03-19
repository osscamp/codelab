package com.learning.java.graph;

public class DirectedEdge implements Comparable<DirectedEdge>{
	
	private int from;
	private int to;
	private double wt;
	public DirectedEdge(int from, int to, double wt) {
		this.from = from;
		this.to = to;
		this.wt = wt;
	}

	public int from() { return this.from; }
	public int to() { return this.to; }
	public double wt() { return this.wt; }

	@Override
	public int compareTo(DirectedEdge o) {
		int cmp = 1;
		if(o != null) {
			cmp = this.wt > o.wt ? 1 : this.wt < o.wt ? -1 : 0;
		}
		return cmp;
	}
	
	public String toString(){  return String.format("%d-%d %.2f", from, to, wt);  }


}
