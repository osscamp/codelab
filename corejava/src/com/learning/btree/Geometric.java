package com.learning.btree;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Geometric {
	
	public static void findKPointsNearestToOrigin() {
		int[][] points = {
				{2,3},
				{4,8},
				{2,6},
				{9,5},
				{2,5},
				{4,3},
				{4,1}
		
		};
		int k = 2;
		Comparator<Integer> c = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		};

		PriorityQueue<Integer> q = new PriorityQueue<Integer>(k, c);
		for(int i=0; i<points.length; i++) {
			int val = points[i][0]*points[i][0] + points[i][1]*points[i][1];
			System.out.println(val);
			if(q.size() < k) {
				q.add(val);
			}
			else if(val < q.peek()) {
				q.poll();
				q.add(val);
			}
			
			System.out.println(q);

		}

		
	}
 	
	public static void main(String[] args) {
		findKPointsNearestToOrigin();
	}

}
