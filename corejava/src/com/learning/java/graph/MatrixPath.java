package com.learning.java.graph;

/**
 * Given a N X N matrix (M) filled with 1 , 0 , 2 , 3 . Your task is to find whether there 
 * is a path possible from source to destination, while traversing through blank cells only. 
 * You can traverse up, down, right and left.
A value of cell 1 means Source.
A value of cell 2 means Destination.
A value of cell 3 means Blank cell.
A value of cell 0 means Blank Wall.
Note : there is only single source and single destination.
 * @author sushukla
 *
 */
public class MatrixPath {
	
	int M;
	int source;
	int dest;
	boolean[] marked;
	int[] id;
	int[] sz;
	int count;
	
	public MatrixPath() {
		
		int[][] m = {
				{ 0 , 3 , 1 , 0 },
                { 3 , 0 , 3 , 3 },
                { 2 , 3 , 0 , 3 },
                { 0 , 3 , 3 , 3 }
                };
		M = m.length;
		Graph g = new Graph(M*M);
		for(int i=0; i<M; i++) {
			for(int j =0; j<M; j++) {
				int v = m[i][j];
				int lv = j>0 ? m[i][j-1] : -1;
				int rv = j<M-1 ? m[i][j+1] : -1;
				int uv = i>0 ? m[i-1][j] : -1;
				int dv = i<M-1 ? m[i+1][j] : -1;
				if(v >0 && lv == 3) {
					g.addEdge(M*i+j, M*i+(j-1));
				}
				if(v >0 && rv == 3) {
					g.addEdge(M*i+j, M*i+(j+1));
				}
				if(v >0 && uv == 3) {
					g.addEdge(M*i+j, M*(i-1)+j);
				}
				if(v >0 && dv == 3) {
					g.addEdge(M*i+j, M*(i+1)+j);
				}
				if(v == 1) source = M*i+j;
				if(v == 2) dest = M*i+j;
			}
			
		}
		marked = new boolean[g.vertices()];
		id = new int[g.vertices()];
		sz = new int[g.vertices()];
		for(int i=0; i<g.vertices(); i++) {
			if(!marked[i]) {
				dfs(g, i);
				count++;
			}
		}
		
	}
	
	public void dfs(Graph g, int v) {
		id[v] = count;
		sz[id[v]]++;
		marked[v] = true;
		for(int w : g.adj(v)) {
			if(!marked[w]) {
				dfs(g, w);
			}
			
		}
	}
	
	public boolean pathExists(){
		return id[source] == id[dest];
	}
	
	public static void main(String[] args) {
		MatrixPath mp = new MatrixPath();
		System.out.println("has path "+mp.pathExists());
	}

}
