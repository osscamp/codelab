package com.learning.re;

public class RealTree {
	
	class RNode {
		int d;
		RNode left;
		RNode right;
		public RNode(int d) {
			this.d = d;
		}
	}
	RNode root;
	
	public RNode insertNode(RNode n, int d) {
		if(n == null) {
			n = new RNode(d);
			if(root == null) {
				root = n;
			}
			return n;
		} else {
			if(d < n.d) {
				n.left = insertNode(n.left, d);
			} else if(d > n.d) {
				n.right = insertNode(n.right, d);
			}
			return n;
		}
	}

	public void createTreeSorted() {
		int[] a = {1,3,5,8,12,19,21,24};
		int l = 0;
		int r = a.length-1;
		
	}
	
	public RNode createTreeSorted(RNode n, int[] a, int l, int r) {
		if(l < r) {
			int mid = l + (r-l)/2;
			if(n == null) {
				n = new RNode(a[mid]);
			}
			n.left = createTreeSorted(n, a, l, mid-1);
			n.right = createTreeSorted(n, a, mid+1, r);
			return n;
		}
		return null;
	}
	
	public static void main(String[] args) {
		int n=8;
		int f1 = 0;
		int f2 = 1;
		for(int i=0; i<n; i++) {
			int f = f1 + f2;
			f1 = f2;
			f2 = f;
			System.out.println(f);
		}
	}

}
