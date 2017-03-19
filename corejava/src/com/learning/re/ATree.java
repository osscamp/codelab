package com.learning.re;

import java.util.concurrent.atomic.AtomicInteger;

public class ATree {
	
	static class Node {
		int v;
		Node left;
		Node right;
		public Node(int v) {
			this.v = v;
		}
		
		public String toString() {
			return ""+v;
		}
		
	}
	
	static Node root = null;
	
	public static Node insertNode(Node n, int l, int r, int[] a) {
		if( l <= r) {
			int mid = l+(r-l)/2;
			if(n == null) {
				n = new Node(a[mid]);
				if(root == null) {root = n;}				
			} 
			n.left = insertNode(n.left, l, mid-1, a);			
			n.right = insertNode(n.right, mid+1, r, a);
		}
		return n;
	}

	
	public static void kthLargest(Node n, AtomicInteger visited, AtomicInteger value,  int k) {
		//if(value.get() > 0) { return; }
		if(n != null) {

			kthLargest(n.left, visited, value, k);
			visited.incrementAndGet();
			if(visited.get() == k && value.get()==0) {
				value.set(n.v);
				return;
			}			
			kthLargest(n.right, visited, value, k);
		}
 	}
	
	public static void main(String[] args) {
		int[] a = {2,5,7,9,11,13,15,17,19};
		insertNode(null, 0, a.length-1, a);
		AtomicInteger value = new AtomicInteger(0);
		kthLargest(root,  new AtomicInteger(0), value, 8);
		System.out.println(value.get());
	}

}
