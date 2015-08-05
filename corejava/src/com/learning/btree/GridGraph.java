package com.learning.btree;

public class GridGraph {

	class Edge {
		int v;
		int w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

/*	public GridGraph(Edge[] a) {
		for (int k = 0; k < a.length; k++) {
			int v = a[k].v, w = a[k].w;
			adj[v][deg[v]++] = w;
			adj[w][deg[w]++] = v;
		}
	}

	void findPathR(int s, int t){ 
		int N = adj[s].length;

		￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼￼if (s == t) return;
		visited(s) = true;
		for(int i = 0; i < N; i++){ 
			int v = exch(adj[s], i, i+(int) Math.random()*(N-i));
		
		￼￼￼￼    if (!visited[v]) {
				searchR(v, t);
			}
		}
	}

	void findPath(int s, int t) {
		visited = new boolean[V];
		findpathR(s, t);
	}*/
}
