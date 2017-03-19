package com.learning.btree;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MinimizeString {
	static int k = 0;
	private static Map<Character, Integer> fmap = new HashMap<>();
	private static PriorityQueue<FHolder> pq = new PriorityQueue<>();
	private static PriorityQueue<FHolder> revpq = new PriorityQueue<>(Collections.reverseOrder());
	static class FHolder implements Comparable<FHolder> {
	    char c;
	    int freq;
	    public FHolder(char c, int f) {
	        this.c = c; this.freq = f;
	    }
	    public int compareTo(FHolder other) {
	        return Integer.valueOf(freq).compareTo(other.freq);
	    }
	    @Override
	    public String toString() {
	    	return c+":"+freq;
	    }
	}
	
	public static int minValue(String s) {
	    for(int i=0; i<s.length(); i++) {
	        char c = s.charAt(i);
	        Integer v = fmap.get(c);
	        if(v == null) {
	            fmap.put(c, 1);
	        } else {
	            fmap.put(c, v+1);
	        }
	    }
        System.out.println(fmap);

	    for(char key : fmap.keySet()) {
	        FHolder h = new FHolder(key, fmap.get(key));
	        if(pq.size() < k) {
	            pq.add(h);
	        } else if(h.compareTo(pq.peek()) > 0) {
	            pq.poll();
	            pq.add(h);
	        }
	    }
	    System.out.println(pq);
	    while(!pq.isEmpty()) {
	    	revpq.add(pq.poll());
	    }
	    int rk = k;
        while(!revpq.isEmpty() && rk > 0) {
            FHolder fh = revpq.poll();
            char c = fh.c;
            fh.freq--;
            revpq.add(fh);
            fmap.put(c, fh.freq);
            rk--;
        }
        System.out.println(fmap);
        int newtotal = 0;
        for(char key : fmap.keySet()) {
        	int ff = fmap.get(key);
            newtotal+=ff*ff;
        }
        return newtotal;
	}
	public static void main (String[] args) {
		String s = "coqhnwnkuewhsqmgbbuqcljjivs";
		k=26;
		if(k > s.length()) { System.out.println("0"); return;}
		int val = minValue(s);
		System.out.println("min val:"+val);
	}

}
