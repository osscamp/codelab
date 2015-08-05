package com.learning.btree;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class FinalPractice {
	
	static class Wrapper {
		String key;
		Integer value;
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "{"+key+":"+value+"}";
		}
	}
	
	static class EntryComparator implements Comparator<Wrapper> {

		@Override
		public int compare(Wrapper o1, Wrapper o2) {
			if(o1 == null && o2 == null) {
				return 0;
			} else if(o1 == null && o2 != null) {
				return -1;
			} else if(o1 != null && o2 == null) {
				return 1;
			} else {
				return o1.value.compareTo(o2.value);
			}		
			
		}
		
	}
	
	public static void top5words() {
		String text = "The servers   that make up the ZooKeeper service must all know about each other. They maintain an in-memory "
				+ "image of state, along with a transaction logs and snapshots in a persistent store. As long as a majority of the "
				+ "servers are available, the ZooKeeper service will be available."+
                      " Clients connect to a single ZooKeeper server. The client maintains a TCP connection through which it sends "
                      + "requests, gets responses, gets watch events, and sends heart beats. If the TCP connection to the server breaks, "
                      + "the client will connect to a different server.";
		
		Map<String, Integer> terms = new HashMap<>();
		char prev = ' ';

		StringBuilder tmp = new StringBuilder();
		for(int i=0; i<text.length(); i++) {
			char cur = text.charAt(i);
			if(prev == ' ' && cur != ' ') {
				tmp = new StringBuilder();
				tmp.append(Character.toLowerCase(cur));
			} else if(prev != ' ' && cur == ' ' || i == text.length()-1) {
				String val = tmp.toString();
				Integer ct = terms.get(val);
				if(ct == null) {
					terms.put(val, 1);
				} else {
					terms.put(val, ++ct);
				}
				Wrapper tempentry = new Wrapper();
				tempentry.key = val;
				tempentry.value = terms.get(val);
				tmp = new StringBuilder();
			}
			else {
				tmp.append(Character.toLowerCase(cur));
			}
			prev = cur;
		}
		
		Set<String> keys = terms.keySet();
		Iterator<String> ki = keys.iterator();
		PriorityQueue<Wrapper> pq1 = new PriorityQueue<>(20, new EntryComparator());

		while(ki.hasNext()) {
			String key = ki.next();
			Integer val = terms.get(key);
			Wrapper w = new Wrapper();
			w.key = key;
			w.value = val;
			if(pq1.size() < 5) {
				pq1.add(w);					
			} else if(w.value > pq1.peek().value){
				pq1.poll();
				pq1.add(w);
			} 		
			
		}
		
		System.out.println(terms);
		System.out.println(pq1);		

	}

	
	public static void main(String[] args) {
		top5words();
	}

}
