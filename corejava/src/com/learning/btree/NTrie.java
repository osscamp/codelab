package com.learning.btree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
//http://sujitpal.blogspot.com/2007/02/three-autocomplete-implementations.html
class NTrie {
	
	static class NTrieNode implements Comparable<NTrieNode>{
		TreeSet<NTrieNode> c = new TreeSet<>();
		char v;
		boolean isW;
		public NTrieNode(char ch) {
			this.v = ch;
		}

		@Override
		public int compareTo(NTrieNode o) {
			if(o != null) {
				return this.v > o.v ? 1 : this.v < o.v ? -1 : 0;
			}
			return 0;
		}
		
		@Override
		public String toString() {
			String add = isW ?""+isW:"";
			return String.valueOf(this.v)+" "+c+" "+add ;
		}
	}
	
	NTrieNode root = new NTrieNode(Character.MIN_VALUE);
	
	public void addWord(String w) {
		if(w == null || w.trim().length() <= 0) {
			return;
		}
		NTrieNode n = root;
		for(int i=0; i<w.length(); i++) {
			char cc = w.charAt(i);
			NTrieNode nn = new NTrieNode(cc);
			n.c.add(nn);
			n = n.c.ceiling(nn);;
			if(i == w.length()-1) {
				n.isW = true;
			}
		}
	}
	
	public boolean isWordExist(String w) {
		NTrieNode n = root;
		for(int i=0; i<w.length(); i++) {
			char cc = w.charAt(i);
			NTrieNode nn = new NTrieNode(cc);
			if(n != null && n.c.contains(nn)) {
				n = n.c.ceiling(nn);
			} else {
				return false;
			}
			if(i == w.length()-1 && n.isW) {
				return true;
			}
		}
		return false;
	}
	
	public List<String> getWords(String s) {
		NTrieNode n = root;	
		for(int i=0; i<s.length(); i++) {
			char cc = s.charAt(i);
			NTrieNode nn = new NTrieNode(cc);
			if(n != null && n.c.contains(nn)) {
				n = n.c.ceiling(nn);
			} else {
				n = null;
			}
		}
		List<String> list = new ArrayList<>();
		if(n == null) {
			return list;
		}
		getWordsR(n, s, list);
		System.out.println("recursive "+list);
		return list;
	}
	
	public void getWordsR(NTrieNode n, String prefix, List<String> list) {
		if(n == null) {
			return;
		}
		if(n.isW) {
			list.add(prefix);
			return;
		}
		for(NTrieNode nt : n.c ){
			char cc = nt.v;
			getWordsR(nt, prefix+cc, list);
		}
	}
		
	public static void main(String[] args) {
		NTrie tn = new NTrie();
		tn.addWord("cad");
		tn.addWord("cargo");
		tn.addWord("carnival");
		tn.addWord("red");
		tn.addWord("rem");
		tn.getWords("r");


		//System.out.println(tn.root);
		try {
			//tn.autocompleteSite();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(tn.isWordExist("ca"));

	}
	
	public void autocompleteSite() throws Exception {
		NTrie tn = new NTrie();
		String url = "https://www.gutenberg.org/files/98/98-h/98-h.htm";
		URL website = new URL(url);
		URLConnection connection = website.openConnection();
		connection.setReadTimeout(100000);
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                    connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        int totalWords = 0;
        while ((inputLine = in.readLine()) != null) {
        	String[] strings = inputLine.split("[^a-zA-Z0-9']+");
        	for(String s : strings) {
        		s = s.toLowerCase();
        		totalWords++;
        		tn.addWord(s);
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
	}

}
