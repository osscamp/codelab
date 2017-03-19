package com.learning.btree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class TernaryTrie {
	
	class TTNode {
		TTNode left;
		TTNode right;
		TTNode mid;
		boolean isW;
		char c;
		public TTNode(char c) {
			this.c = c;
		}
	}
	
	TTNode root;
	
	public void insert(TTNode t, String s) {
		if(s == null || s.trim().equals("")) {
			return;
		}
		insert(root, s, 0);
	}
	
	public boolean search(String s) {
		TTNode ret = get(root, s ,0);
		return (ret != null && s.charAt(s.length()-1) == ret.c);
	}
	
	public TTNode insert(TTNode t, String s, int d) {
		char c = s.charAt(d);
		if(t == null) {
			t = new TTNode(c);
			if(root == null) {
				root = t;
			}
		}
		if(c < t.c) {
			t.left = insert(t.left, s, d);
		} else if(c > t.c) {
			t.right = insert(t.right, s, d);
		} else if(d < s.length()-1) {
			t.mid = insert(t.mid, s, d+1);
		}
		if(d == s.length()-1) {
			t.isW = true;
		}
		return t;
	}
	
	public TTNode get(TTNode t, String s, int d) {
		if(t == null) {
			return null;
		}
		char c = s.charAt(d);
		if(d == s.length()-1 && !t.isW) {
			return null;
		}
		if(c < t.c) {
			return get(t.left, s, d);
		} else if(c > t.c) {
			return get(t.right, s, d);
		} else if(d < s.length()-1) {
			return get(t.mid, s, d+1);
		} else {
			return t;
		}
	}
	
	public TTNode getSub(TTNode t, String s, int d) {
		if(t == null) {
			return null;
		}
		if(d > s.length()-1) {
			return null;
		}
		char c = s.charAt(d);
		if(c < t.c) {
			return getSub(t.left, s, d);
		} else if(c > t.c) {
			return getSub(t.right, s, d);
		} else if(d < s.length()-1) {
			return getSub(t.mid, s, d+1);
		} else {
			return t;
		}
	}
	
	public List<String> autoComplete(String prefix) {
		List<String> matches = new ArrayList<String>();
		TTNode snode = getSub(root, prefix, 0);
		if(snode != null) {
			if(snode.isW) {
				matches.add(prefix);
			}
			if(snode.mid != null) {
				autoCompleteR(snode.mid, prefix, matches);
			}

		}
		return matches;
	}
		
	public void autoCompleteR(TTNode t, String prefix, List<String> matches) {
		if(t != null) {
			if(t.isW) {
				matches.add(prefix+t.c);
			}
			autoCompleteR(t.left, prefix, matches);
			autoCompleteR(t.mid, prefix + t.c, matches);
			autoCompleteR(t.right, prefix, matches);
		}
	}
	
	public void autocompleteSite() throws Exception {
		TernaryTrie tn = new TernaryTrie();
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
        		tn.insert(tn.root, s);
        		//System.out.println(s);
        	}
        }
        in.close();
        System.out.println("words indexed "+totalWords);
        //System.out.println(tn.root);
        long st = System.nanoTime();
        List<String> autoList = tn.autoComplete("bank");
        long et = System.nanoTime();
        System.out.println(autoList);
        System.out.println("micro sec "+(et-st)/Math.pow(10, 3));

        
	}

	
	public static void main(String[] args) throws Exception{
		TernaryTrie tt = new TernaryTrie();
		tt.insert(tt.root, "shell");
		tt.insert(tt.root, "she");
		tt.insert(tt.root, "shop");
		tt.insert(tt.root, "sold");
		tt.insert(tt.root, "art");
		tt.insert(tt.root, "solder");
		tt.insert(tt.root, "sort");
		tt.insert(tt.root, "solid");
		tt.insert(tt.root, "solvang");
		System.out.println("isfound "+tt.search("shell"));
		List<String> words = tt.autoComplete("so");
		System.out.println(words);
		tt.autocompleteSite();
	}

}
