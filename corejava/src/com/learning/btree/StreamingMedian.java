package com.learning.btree;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StreamingMedian {
	
	private static class MaxComp implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			if(o1 != null && o2 != null) {
				return o2.compareTo(o1);
			}
			return 0;
		}
		
	}
	
	int median = 0;
	
	private PriorityQueue<Integer> bottomQ;
	
	private PriorityQueue<Integer> topQ;	
	
	public StreamingMedian() {
		bottomQ = new PriorityQueue<>(10, new MaxComp()); //max PQ
		topQ = new PriorityQueue<>(); //min PQ
	}
	
	public void addNumber(int n) {
		if(n > median) {
			topQ.add(n);
		} else if(n <= median) {
			bottomQ.add(n);
		}
		if(topQ.size() > bottomQ.size()+1) {
			bottomQ.add(topQ.poll());
		}
		else if(bottomQ.size() > topQ.size()+1) {
			topQ.add(bottomQ.poll());
		}
		int topSize = topQ.size();
		int bottomSize = bottomQ.size();
		if(topSize > bottomSize) {
			median = topQ.peek();
		} else if(bottomSize > topSize) {
			median = bottomQ.peek();
		} else {
			if(topSize > 0 && bottomSize > 0) {
				median = (topQ.peek()+bottomQ.peek())/2;
			} 
		}
	}
	
	public int median() {
		return median;
	}
	
	public static void main(String[] args) {
		StreamingMedian smedian = new StreamingMedian();
		int[] a = {4,23,45,12,11,26,5,21,31,45};
		for(int nn : a) {
			smedian.addNumber(nn);
			System.out.println("running median "+smedian.median());
		}
	}

}
