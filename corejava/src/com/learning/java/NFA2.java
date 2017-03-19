package com.learning.java;

import java.util.ArrayList;
import java.util.List;

import com.learning.java.graph.DiGraph;
import com.learning.java.graph.DirectedDFS;

public class NFA2 {

	DiGraph G = null;
	
	String pat = null;
	
	int M;

	public NFA2(String pat) {
		this.pat = pat;
		this.M = pat.length();
		this.G = new DiGraph(M+1);

		for(int i=0; i<M; i++) {
			char c = pat.charAt(i);
			if(i<M-1 && pat.charAt(i+1) == '*') {
				G.addEdge(i, i+1);
				G.addEdge(i+1, i);				
			}
			if(c == '*'){
				G.addEdge(i, i+1);
			}
			if(i>0 && c == '?' && i<M+1){//preceding term optional
				G.addEdge(i-1, i+1);
				G.addEdge(i, i+1);
			}
		}

	}
	
	public void isMatch(String s) {
		DirectedDFS ddfs = new DirectedDFS(G, 0);
		List<Integer> reachable = new ArrayList<>();
		for(int i=0; i<G.vertices(); i++) {
			if(ddfs.marked(i)) {
				reachable.add(i);
			}
		}
		for(int i=0; i<s.length(); i++) {
			List<Integer> match = new ArrayList<>();
			for(int v : reachable) {
				if(v < M) {
					if(pat.charAt(v) == s.charAt(i) || pat.charAt(v) == '.'){
						match.add(v+1);
					}
				}
			}
			reachable = new ArrayList<>();
			ddfs = new DirectedDFS(G, match);
			for(int v=0; v<G.vertices(); v++) {
				if(ddfs.marked(v)) {
					reachable.add(v);
				}
			}
		}
		for(int k : reachable) {
			if (k == M) {
				System.out.println("RE MATCHED SUCCESS ");
			}
 		}
	}
	
	public static void main(String[] args) {
		String s = "abob";
		String p = "a*ob";
		NFA2 nf2 = new NFA2(p);
		nf2.isMatch(s);
	}

}
