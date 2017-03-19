package com.learning.btree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
	
	class TrieNode {
		char data;
		Map<Character, TrieNode> children = new HashMap<>();
		boolean isW;
		public TrieNode(char c) {
			this.data = c;
		}
	}
	
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode(' ');
	}
	
	public void insert(String w) {
		TrieNode temp = root;
		for(int i=0; i<w.length(); i++) {
			char c = w.charAt(i);
			TrieNode tc = temp.children.get(c);
			if(tc == null) {
				TrieNode newc = new TrieNode(c);
				if(i == w.length()-1) {
					newc.isW = true;
				}
				temp.children.put(c, newc);
				temp = newc;
			} else {
				temp = tc;
			}
		}
	}
	
	public boolean search(String w) {
		TrieNode temp = root;
		for(int i=0; i<w.length(); i++) {
			char c = w.charAt(i);	
			TrieNode tc = temp.children.get(c);
			if(tc == null) {
				return false;
			} else {
				if(i == w.length() - 1 && tc.isW) {
					return true;
				}
				temp = tc;
			}
		}
		return false;
	}
	
	public void getWords(String prefix) {
		List<String> list = new ArrayList<>();
		TrieNode temp = root;
		for(int i=0; i<prefix.length(); i++) {
			char c = prefix.charAt(i);	
			TrieNode tc = temp.children.get(c);
			if(tc == null) {
				return;
			} else {
				temp = tc;
				if(i == prefix.length() - 1) {
					break;
				}
			}
		}
		System.out.println("temp at "+temp.data);
		getWordsR(temp, prefix, list);
		System.out.println("list "+list);
	}
	
	public void getWordsR(TrieNode t, String prefix, List<String> list) {
		if(t == null) {
			return;
		}
		for(Character c : t.children.keySet()) {
			TrieNode tt = t.children.get(c);
			if(tt.isW) {
				list.add(prefix+tt.data);
			} else {
				getWordsR(tt, prefix+tt.data, list);
			}
		}
	}
	
	public void longestPrefix(String s) {
		TrieNode t = root;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<s.length(); i++) {
			char cc = s.charAt(i);
			TrieNode tt = t.children.get(cc);
			if(tt != null) {
				sb.append(cc);
				t = tt;
			}else{
				break;
			}
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws Exception{
		Trie trie = new Trie();
		trie.insert("dream");
		trie.insert("drink");
		trie.insert("deal");
		trie.insert("art");
		System.out.println("is found "+trie.search("art"));
		//trie.getWords("d");
		trie.autocompleteSite();
		

	}
	
	public void autocompleteSite() throws Exception {
		Trie tn = new Trie();
		String url = "https://www.gutenberg.org/files/98/98-h/98-h.htm";
		URL website = new URL(url);
		URLConnection connection = website.openConnection();
		connection.setReadTimeout(10000);
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                    connection.getInputStream()));
        String inputLine;

        int totalWords = 0;
        while ((inputLine = in.readLine()) != null) {
        	String[] strings = inputLine.split("[^a-zA-Z0-9']+");
        	for(String s : strings) {
        		s = s.toLowerCase();
        		totalWords++;
        		tn.insert(s);
        		//System.out.println(s);
        	}
        }
        in.close();
        System.out.println("words indexed "+totalWords);
        //System.out.println(tn.root);
        long st = System.nanoTime();
        tn.getWords("water");
        long et = System.nanoTime();
        System.out.println("micro sec "+(et-st)/Math.pow(10, 3));
        //tn.longestPrefix("driver");
        
	}

}
