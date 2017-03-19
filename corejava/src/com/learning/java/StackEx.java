package com.learning.java;

import java.util.LinkedList;
import java.util.Queue;

public class StackEx {
	
	public static void josephusQueue() {
		int N = 10;
		int M = 4;
		Queue<Integer> st = new LinkedList<>();
		for(int i=0; i<N; i++){
			st.add(i);
		}
		while(st.size() > 1) {
			for(int i=0; i<M-1; i++){
				int deq = st.poll();
				st.add(deq);
			}
			System.out.println(st.poll());
		}
		System.out.println("final "+st.poll());
	}
	
	public static void main(String[] args) {
		josephusQueue();
	}

}
