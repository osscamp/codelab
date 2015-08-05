package com.learning.btree;

import java.util.ArrayList;
import java.util.List;

public class NTrieArray {
	
	static char[] LOWERCASE = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	
	public static int getIndex(char c) {
		return c - 'a';
	}
	
	static class NTrieArrayNode{
		NTrieArrayNode[] children = new NTrieArrayNode[LOWERCASE.length];
		boolean isWord;
		public NTrieArrayNode() {
		}
	}
	
	NTrieArrayNode root = new NTrieArrayNode();
	
	public void addWord(String s) {
		NTrieArrayNode n = root;
		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			int idx = getIndex(c);
			if(n.children[idx] == null) {
				n.children[idx] = new NTrieArrayNode();
			}
			if(i == s.length()-1) {
				n.children[idx].isWord = true;
			}
			n = n.children[idx];

		}
	}
	
	public List<String> suggest(String s) {
		List<String> list = new ArrayList<>();
		NTrieArrayNode n = root;
		int i=0;
		for(; i<s.length(); i++) {
			char c = s.charAt(i);
			int idx = getIndex(c);
			if(n != null && n.children[idx] != null) {
				n = n.children[idx];			
			}
			else {
				break;
			}
			
		}
		words(n, new StringBuilder(s), list);
		return list;
	}
	
	public void words(NTrieArrayNode n, StringBuilder sb, List<String> list) {
		if(n != null && n != root) {//match found
			for(int k=0; k<LOWERCASE.length; k++) {
				if(n.children[k] != null ) {
					sb.append(LOWERCASE[k]);
					if(n.children[k].isWord){
						list.add(sb.toString());
						sb = new StringBuilder(sb);
					}
					n = n.children[k];
					words(n, sb, list);
				}
			}
			return;
		}		
	}
	
	public static void main(String[] args) {
		NTrieArray trieArray = new NTrieArray();
		trieArray.addWord("tryck");
		trieArray.addWord("tryi");

		//trieArray.addWord("toys");
		System.out.println(trieArray.suggest("tr"));
	}

}
