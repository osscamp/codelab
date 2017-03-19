package com.learning.java.graph;

public class Edge implements Comparable<Edge>{
	
	private int v;
	private int w;
	private double wt;
	
	public Edge(int v, int w, double wt) {
		this.v = v;
		this.w = w;
		this.wt = wt;
	}
	
	public int either() {
		return v;
	}
	
	public int other(int val) {
		if(val == this.v) return w;
		else if(val == this.w) return v;
		else throw new IllegalArgumentException("non existing vertex");
	}
	
	public double weight() { return wt; }
	
	public int compareTo(Edge other) {
		int ret = 1;
		if(other != null) {
			return this.wt > other.wt ? 1 : this.wt == other.wt ? 0 : -1;
		}
		return ret;
	}
	
	public String toString(){  return String.format("%d-%d %.2f", v, w, wt);  }

}
