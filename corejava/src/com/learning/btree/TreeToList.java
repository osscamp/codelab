package com.learning.btree;

public class TreeToList {

	private static class Node {
		Node(int n) {
			this.d = n;
		}

		int d;
		Node left;
		Node right;
		@Override
		public String toString() {
			return String.valueOf(d);
		}
	}
	Node root = null;
	
	public Node insertNode(Node n , int data) {
		if(n == null) {
			n = new Node(data);
			if(root == null) {
				root = n;
			}
			return n;
		}
		if(data < n.d) {
			n.left = insertNode(n.left, data);
		} else if(data > n.d){
			n.right = insertNode(n.right, data);
		}
		return n;
	}
	
	public static void main(String[] args) {
		TreeToList tl = new TreeToList();
		tl.insertNode(null, 510);
		tl.insertNode(tl.root, 220);
		tl.insertNode(tl.root, 810);
		tl.insertNode(tl.root, 920);
	}
}
