package com.learning.java.graph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class PrimMST {
	
	private boolean[] marked;
	private PriorityQueue<Edge> pq;
	private Queue<Edge> mst;
	
	public PrimMST(EdgeWeightedGraph G){
		mst = new LinkedList<>();
		pq = new PriorityQueue<>(G.vertices());
		marked = new boolean[G.vertices()];
		visit(G, 0);//assume connected
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			int v = e.either();
			int w = e.other(v);
			if(marked[v] && marked[w]) continue;
			mst.add(e);
			if(!marked[v]) {
				visit(G, v);
			}
			if(!marked[w]) {
				visit(G, w);
			}
		}
		
	}
	
	private void visit(EdgeWeightedGraph G, int v){
		marked[v] = true;
		for(Edge e : G.adj(v)){
			if(!marked[e.other(v)]) {
				pq.add(e);
			}
		}
	}
	
	public Queue<Edge> mst(){
		return mst;
	}
	
	public static void main(String[] args) {
		EdgeWeightedGraph G = new EdgeWeightedGraph();
		PrimMST prim = new PrimMST(G);
		Queue<Edge> mst = prim.mst();
		System.out.println(mst);
	}

}
