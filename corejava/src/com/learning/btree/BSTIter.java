package com.learning.btree;

import java.util.Iterator;
import java.util.Stack;

public class BSTIter implements Iterable<Node>{
	
	static class Node {
		int d;
		Node left;
		Node right;
		public Node(int d) {
			this.d = d;
		}
		
		@Override
		public String toString() {
			return ""+d;
		}
	}
	
	class BSTIterator<T> implements Iterator<Node> {
		
		Stack<Node> stack = new Stack<Node>();
		
		Node tempNode = root;
		
		public BSTIterator() {
			while(tempNode != null) {
				stack.push(tempNode);
				tempNode = tempNode.left;
			}
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty() || tempNode != null ;
		}

		@Override
		public Node next() {
			Node toReturn = null;
			if(!stack.isEmpty()) {
				toReturn = stack.pop();
				tempNode = toReturn.right;
			} else if(tempNode != null) {
				toReturn = tempNode;
				tempNode = tempNode.left;				
			}
			return toReturn;
		}

		@Override
		public void remove() {
			
		}
		
	}
	
	public Node root = new Node(34);
	
	public Node insert(Node n, int d) {
		if(n == null) {
			n = new Node(d);
			return n;
		}
		if(d < n.d) {
			n.left = insert(n.left, d);
		}else if(d>n.d){
			n.right = insert(n.right, d);
		}
		return n;
	}
	
	public static void main(String[] args) {
		BSTIter bst = new BSTIter();
		bst.insert(bst.root, 45);
		bst.insert(bst.root, 22);
		bst.insert(bst.root, 13);

		bst.insert(bst.root, 29);
		Iterator<Node> it = bst.iterator();
		while(it.hasNext()) {
			System.out.println("next "+it.next());
			
		}
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return new BSTIterator<Node>();
	}

}
