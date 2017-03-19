package com.learning.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.learning.java.graph.DiGraph;
import com.learning.java.graph.DirectedDFS;

//match regular expression regex using NFA and Digraph
public class NFA {
	
	//NFA for ( ( A * B | A C ) D )
	char[] re;
	DiGraph G;
	int M;
	
	public NFA(String exp) {
		re = exp.toCharArray();
		M = re.length;
		G = new DiGraph(re.length+1);
		Stack<Integer> opStack = new Stack<>();

		for(int i=0; i<re.length; i++) {
			int lp = i;
			if(re[i] == '(' || re[i] == '|') {
				opStack.push(i);
			}else if(re[i] == ')') {
				int oridx = opStack.pop();
				if(re[oridx] == '|') {
					lp = opStack.pop();
					G.addEdge(lp, oridx+1);
					G.addEdge(oridx, i);
				}else {
					lp = oridx;
				}
			}
			if(i<M-1 && re[i+1] == '*') {
				G.addEdge(lp, i+1);
				G.addEdge(i+1, lp);
			}
			if(re[i] == '*' || re[i] == '(' || re[i] == ')') {
				G.addEdge(i, i+1);
			}
		}
	}
	
	public void matches(String text) {
		DirectedDFS dfs = new DirectedDFS(G, 0);
		List<Integer> reachable = new ArrayList<>();
		for(int v=0; v<G.vertices(); v++) {
			if(dfs.marked(v)) {
				reachable.add(v);
			}
		}
		for(int i=0; i<text.length(); i++) {
			List<Integer> match = new ArrayList<>();
			for(int v : reachable) {
				if(v < M) {
					if(re[v] == text.charAt(i) || re[v] == '.') {
						match.add(v+1);
					}
				}
			}
			reachable = new ArrayList<>();
			dfs = new DirectedDFS(G, match);
			for(int v=0; v < G.vertices(); v++ ){
				if(dfs.marked(v)) {
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
		//NFA nfa = new NFA("(A|B).*C");
		NFA nfa = new NFA("a.*b");
		nfa.matches("abbb");
	}
}
 

