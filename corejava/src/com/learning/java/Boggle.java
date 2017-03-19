package com.learning.java;

import java.util.ArrayList;
import java.util.List;

public class Boggle {
	
	char[][] m = {
			{'G','A','Z','V'},
            {'U','E','K','R'},
            {'Q','S','E','M'},
            {'W','B','N','T'},
            };
	static String[] d = {"GEEKS", "GEE", "GEU", "QUT", "SQUEAK", "BEE", "SEA"};
	int N = m.length;
	static Tr t;
	static List<String> found = new ArrayList<>();
	
	public Boggle() {
		t = new Tr();
		for(String s: d){
			t.addWord(s);
		}
	}
	
	static class Tr {
		static class Tn{
			Tn left;
			Tn right;
			Tn mid;
			char v;
			boolean isW;
			public Tn(char c) {
				this.v = c;
			}
			public String toString(){
				return "["+v+"]";
			}
		}
		
		Tn root;
		
		public void addWord(String s) {
			addWordR(s, 0, root);
		}
		
		public Tn addWordR(String s, int depth, Tn n){
			if(depth > s.length()-1) {
				return n;
			}
			char c = s.charAt(depth);
			if(n == null) {
				n = new Tn(c);
				if(root == null) root = n;
			}
			if(c < n.v) {
				n.left = addWordR(s, depth, n.left);
			}else if(c > n.v) {
				n.right = addWordR(s, depth, n.right);
			}else{
				n.mid = addWordR(s, depth+1, n.mid);
				if(depth == s.length()-1){
					n.isW = true;
				}
			}

			return n;
		}
		public Tn findWord(String s){
			return findWordR(s, 0, root);
		}
		
		public Tn findWordR(String s, int depth, Tn n){
			if(n == null) {
				return null;
			}
			if(depth > s.length()-1) {
				return null;
			}
			char c = s.charAt(depth);
			if(c < n.v) {
				return findWordR(s, depth, n.left);
			}else if(c > n.v) {
				return findWordR(s, depth, n.right);
			}else{
				if(depth < s.length()-1){
					return findWordR(s, depth+1, n.mid);
				}else if(n.isW){
					return n;
				}
			}
			return null;
		}
		
		public Tn findSubWord(String s){
			return findSubWordR(s, 0, root);
		}
		
		public Tn findSubWordR(String s, int depth, Tn n){
			if(n == null) {
				return null;
			}
			if(depth > s.length()-1) {
				return null;
			}
			char c = s.charAt(depth);
			if(c < n.v) {
				return findSubWordR(s, depth, n.left);
			}else if(c > n.v) {
				return findSubWordR(s, depth, n.right);
			}else{
				if(depth < s.length()-1){
					return findSubWordR(s, depth+1, n.mid);
				}else if(depth == s.length()-1){
					return n;
				}
			}
			return null;
		}
		
	}
	
	public void findWords() {
		for(int i=0; i<m.length; i++) {
			for(int j =0; j<m[0].length; j++){
				boolean[][] visited = new boolean[N][N];
				StringBuilder sb = new StringBuilder();
				dfs(i,j,sb,visited);
			}
		}
	}
	
	public void dfs(int i, int j, StringBuilder s, boolean[][] visited) {
		if(i < 0 || j < 0 || j >= N || i>= N || visited[i][j]) {
			return;
		}
		else{
			visited[i][j] = true;
			s.append(m[i][j]);
			Tr.Tn node = t.findSubWord(s.toString());
			if(node != null) {
				if(node.isW) {
					found.add(s.toString());
				}
				dfs(i-1, j, s, visited);

				dfs(i, j-1, s, visited);

				dfs(i-1, j-1, s, visited);

				dfs(i-1, j+1, s, visited);

				dfs(i+1, j+1, s, visited);

				dfs(i+1, j, s, visited);

				dfs(i, j+1, s, visited);

				dfs(i+1, j-1, s, visited);
			}//else{
				if(s.length() > 0) {
					s.deleteCharAt(s.length()-1);
				}
				visited[i][j] = false;
				//return;
			//}
		}


	}
	
	public static void main(String[] args) {
		Boggle b = new Boggle();
		b.findWords();
		System.out.println(found);
	}

}
