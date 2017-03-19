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
	
	private PriorityQueue<Integer> maxPQ;
	
	private PriorityQueue<Integer> minPQ;	
	
	public StreamingMedian() {
		maxPQ = new PriorityQueue<>(10, Comparator.reverseOrder()); //max PQ - holds values less than median
		minPQ = new PriorityQueue<>(); //min PQ - holds values > median
	}
	
	public void addNumber(int n) {
		if(n > median) {
			minPQ.add(n);
		} else if(n <= median) {
			maxPQ.add(n);
		}
		if(minPQ.size() > maxPQ.size()+1) {
			maxPQ.add(minPQ.poll());
		}
		else if(maxPQ.size() > minPQ.size()+1) {
			minPQ.add(maxPQ.poll());
		}
		int topSize = minPQ.size();
		int bottomSize = maxPQ.size();
		if(topSize > bottomSize) {
			median = minPQ.peek();
		} else if(bottomSize > topSize) {
			median = maxPQ.peek();
		} else {
			if(topSize > 0 && bottomSize > 0) {
				median = (minPQ.peek()+maxPQ.peek())/2;
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
