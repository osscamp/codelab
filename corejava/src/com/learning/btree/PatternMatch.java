package com.learning.btree;


public class PatternMatch {
	public static boolean matches(String p, String s) {
		//append a sentinel 
		p += "\0";
		s += "\0";
		int N = p.length();
		boolean[] states = new boolean[N+1];
		boolean[] old = new boolean[N+1];
		old[0] = true;
		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			states = new boolean[N+1];
			for(int j=0; j<N; j++) {
				char pc = p.charAt(j);
				if (old[j] && (pc == '*')) old[j+1] = true;
				
				if(old[j] && (pc==c)) states[j+1] = true;
				if(old[j] && (pc=='.')) states[j+1] = true;
				if(old[j] && (pc=='*')) states[j+1] = true;
				if(old[j] && (pc=='*')) states[j] = true;
			}
			old = states;
		}
		return states[N];
	}
	
	public static boolean matchRecursive(String pattern, String s) {
		if (pattern.isEmpty()) {
			return s.isEmpty();
		}
		if (s.isEmpty()) {
			if (pattern.charAt(0) == '*') {
				return matchRecursive(pattern.substring(1), s);
			} else {
				return false;
			}
		}
		char s0 = s.charAt(0);
		char p0 = pattern.charAt(0);
		if (p0 == '.' || p0 == s0 ) {
			return matchRecursive(pattern.substring(1), s.substring(1));
		}
		if (p0 == '*') {
			if (!matchRecursive(pattern, s.substring(1))) {
				return matchRecursive(pattern.substring(1), s);
			}
			return true;
		}
		return false;
	}
 
    public static void main(String[] args) {
    	boolean result = matches("s*p", "ssissip");
    	System.out.println("regex match "+result);
    	
    	boolean resultRecur = matchRecursive("s*p", "ssissip");
    	System.out.println("recursive regex match "+resultRecur);
    }
}
