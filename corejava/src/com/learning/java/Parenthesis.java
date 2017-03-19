package com.learning.java;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Parenthesis {
	
	public static boolean validate(String p) {
		if(p == null || p.isEmpty()) {
			return false;
		}
		Set<Character> sCharSet = new HashSet<>();
		sCharSet.add('{');
		sCharSet.add('(');
		sCharSet.add('[');
		Set<Character> eCharSet = new HashSet<>();
		eCharSet.add('}');
		eCharSet.add(')');
		eCharSet.add(']');
		Stack<Character> st = new Stack<>();
		for(int i = 0; i<p.length(); i++) {
			char c = p.charAt(i);
			if(!(sCharSet.contains(c) || eCharSet.contains(c)) ) {
				return false;
			}
			else {
				if(sCharSet.contains(c)) {
					st.push(c);
				}else if((c=='}' && st.peek() == '{') 
						|| (c==']' && st.peek() == '[')
						|| (c==')' && st.peek() == '(') 
						) {
					st.pop();
				}else {
					return false;
				}
			}
		}
		return st.isEmpty();
		
	}
	
	public static void main(String[] args) {
		System.out.println(validate("[(])") );
	}

}
