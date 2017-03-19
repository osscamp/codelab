package com.learning.re;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MTrie {
	
	class TNode {
		Character data;
		boolean isWord;
		Map<Character, TNode> children = new HashMap<>();
		public String toString() {
			return data+" "+isWord;
		}
	}
	
	TNode root = new TNode();
	
	public void addWord(String s) {
		TNode temp = root;
		for(int i = 0; i<s.length(); i++) {
			Character c = s.charAt(i);
			if(!temp.children.containsKey(c)) {
				TNode t = new TNode();
				t.data = c;
				t.isWord = (i==s.length()-1);
				temp.children.put(c, t);
				temp = t;
			} else {
				temp = temp.children.get(c);
				temp.isWord = (i==s.length()-1);
			}
		}
	}
	
	public TNode getMatch(String s) {
		TNode temp = root;
		for(int i = 0; i<s.length(); i++) {
			Character c = s.charAt(i);
			if(!temp.children.containsKey(c)) {
				return null;
			} else {
				temp = temp.children.get(c);
			}
		}
		return temp;
	}
	
	public Set<String> getAutoComplete(String prefix) {
		Set<String> results = new HashSet<>();
		TNode start = getMatch(prefix);
		if(start == null) {
			results.add(null);
			return results;
		} else {
			getAutoComp(start, prefix, results);
		}
		return results;
	}
	
	private void getAutoComp(TNode t, String prefix, Set<String> results) {
		if(t.isWord){
			results.add(prefix);
		}
		for(TNode tr : t.children.values()) {
			if(tr.isWord) {
				results.add(prefix+tr.data);
			}
			getAutoComp(tr, prefix+tr.data, results);
		}
	}
	
	public static void main(String[] args) {
		MTrie t = new MTrie();
		t.addWord("all");
		t.addWord("al");
		t.addWord("a");
		t.addWord("adept");
		t.addWord("amount");
		t.addWord("bite");

		Set<String> res = t.getAutoComplete("al");
		System.out.println(res);
	}
 
}
