package com.learning.java;

public class BoggleOne {
	
	char[][] m = {
			{'w','r','b'},
			{'t','u','o'},
			{'a','e','g'},	
	};
	//bot, bog, at,
	public void searchWord(String w) {
		boolean found = false;
		for(int i=0; i<m.length; i++) {
			for(int j=0; j<m.length; j++) {
				boolean[][] visited = new boolean[m.length][m.length];
				found = dfs(i,j, new StringBuilder(), visited, w, 0);
				if(found) break;
			}
			if(found) break;
		}
	}
	
	public boolean dfs(int i, int j, StringBuilder sb, boolean[][] visited, String w, int widx){
		if( i<0 || i>=m.length || j<0 || j>= m.length || visited[i][j] || widx >= w.length() || sb.length() >= w.length()) return false;
		visited[i][j] = true;
		sb.append(m[i][j]);
		if(sb.length() == w.length() && sb.toString().equals(w)) {
			System.out.println("found");
			return true;
		}else{
			if(w.charAt(widx) == m[i][j]){
				return dfs(i+1, j, sb, visited, w, widx+1) ||
				dfs(i, j+1, sb, visited, w, widx+1) ||
				dfs(i-1, j, sb, visited, w, widx+1) ||
				dfs(i, j-1, sb, visited, w, widx+1);
			}else {
				sb.deleteCharAt(sb.length()-1);
				visited[i][j] = false;
			}

		}
		return false;
	}
	
	public static void main(String[] args) {
		BoggleOne bt = new BoggleOne();
		bt.searchWord("bruogeat");
	}

}
