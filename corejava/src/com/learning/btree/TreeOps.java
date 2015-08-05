package com.learning.btree;

import java.util.List;


public class TreeOps {
	
	static Node root = null;
	static int total = 0;
	
	public static Node createRoot(int v) {
		root = new Node(v);
		return root;
	}
	
	public static Node insertNode(Node t, int v) {
		if (t == null) {
			t = new Node(v);
		}
		if (v < t.data) {
			t.left = insertNode(t.left, v);
		} else if (v > t.data) {
			t.right = insertNode(t.right, v);
		}
		return t;
	}
	
	public static void inorderWalk( Node n ) {
		if ( n != null ) {
			inorderWalk(n.left);
			System.out.println(n.data);
			inorderWalk(n.right);
		}
	}
	
	public static int getTotal(Node n) {
		if (n != null) {
			total += n.data;
			getTotal (n.left);
			getTotal(n.right);
		}

		return total;
	}
	
	public static int searchTree (Node n, int v) {
		if (n.data == v) {
			return n.data;
		} else if ( v < n.data ){
			return searchTree(n.left, v);
		} else {
			return searchTree(n.right, v);
		}
	}
	
	public static Node findSuccessor(Node n) {
		if ( n.right != null ) {
			return findMin(n.right);
		} else {
			return findSuccessor(n.left);
		}
	}
	
	public static Node findMin (Node n) {
		Node in = null;
		while (n != null) {
			in = n;
			System.out.println(n.data);
			n = n.left;
		}
		return in;
	}
	
	public static StringBuilder prettyPrint (Node n, ObjectCounter ai, StringBuilder sb) {
		if ( n != null ) {
			for (int ct = 0; ct < ai.getCount(); ct++) {
				sb.append(" ");
			}
			sb.append(n.data);
			sb = prettyPrint(n.left, ai.setCount(ai.getCount() - 1), sb.append("\n"));
			//sb.deleteCharAt(sb.length()-1);
			sb = prettyPrint(n.right, ai.setCount(ai.getCount() + 2), sb);
		}	
		return sb;
	}
	
	public static int findMaxHeight(Node n) {
		if (n == null) {
			return 0;			
		}
		int leftHt = findMaxHeight(n.left);
		int rightHt = findMaxHeight(n.right);
		return (leftHt > rightHt) ? leftHt + 1 : rightHt + 1;
	}
	
	public static void main(String[] args) {
		TreeOps.createRoot(45);
		TreeOps.insertNode(root, 48);
		TreeOps.insertNode(root, 43);
//		TreeOps.insertNode(root, 20);
//		TreeOps.insertNode(root, 35);
//		TreeOps.insertNode(root, 55);
//		TreeOps.insertNode(root, 23);
//		TreeOps.insertNode(root, 49);
//		TreeOps.insertNode(root, 13);
//		TreeOps.insertNode(root, 8);
		System.out.println(TreeOps.prettyPrint(root, new ObjectCounter(findMaxHeight(root)*5), new StringBuilder()));
		//postorderWalk(root);
		System.out.println("Max height "+findMaxHeight(root));
		
		int[] inputNodes = {1,2,4,8,16,32,64,128,256,512,1024,2048,4096};
		int left = 0, right = inputNodes.length;
		int mid = (left+right)/2;
		left = mid; right = mid;
		TreeOps.createRoot(inputNodes[mid]);
//		while (left > 0 && right < inputNodes.length) {
//			TreeOps.insertNode(root, inputNodes[left--]);	
//			TreeOps.insertNode(root, inputNodes[right++]);	
//		}
		TreeOps.insertBalancedNode2(root, Integer.MIN_VALUE);
		TreeOps.insertBalancedNode2(root, Integer.MAX_VALUE);
		System.out.println(TreeOps.prettyPrint(root, new ObjectCounter(20), new StringBuilder()));
		//inorderWalk(root);
		
		root = createRoot(15);
		TreeOps.insertNode(root, 8);
		TreeOps.insertNode(root, 10);
		TreeOps.insertNode(root, 21);
		TreeOps.insertNode(root, 17);
		TreeOps.insertNode(root, 28);
		printBFS(root);
	}
	
	static int[] inputNodes = {1,2,4,8,16,32,64,128,256,512,1024,2048,4096};
	
	public static void insertBalancedNode(Node root, int i1, int i2) { 

		int mp = (i1 + i2)/2;
		if (i1 >= i2 || mp == i1 || mp == i2) {
			System.out.println("inserting "+inputNodes[mp]);
			TreeOps.insertNode(root, inputNodes[mp]);
			return;
		}
		insertBalancedNode(root, mp, i2);
		insertBalancedNode(root, i1, mp);
	}
	
	public static void insertBalancedNode2(Node root, int dummyKey) {
		int l1 = 0;
		int l2 = inputNodes.length;
		while (l1 < l2) {
			int mid = (l1+l2)/2;
			if(dummyKey <inputNodes[mid]) {
				l2 = mid;
				TreeOps.insertNode(root, inputNodes[mid]);
			} else if(dummyKey > inputNodes[mid]){
				l1 = mid + 1;
				TreeOps.insertNode(root, inputNodes[mid]);
			}
				
		}
	}
	
	public static void printBFS(Node n) {
		if(n == null) { return; }
		java.util.LinkedList<Node> q = new java.util.LinkedList<>();
		q.add(n);
		q.add(null);
		StringBuilder sb = new StringBuilder();

		while (!q.isEmpty()) {
			Node p = q.peek();
			if(p == null) {
				System.out.println(sb);
				q.remove();
				sb = new StringBuilder();
			} else {
				while(q.peek() != null) {
					p = q.pollFirst();
					sb.append(p.data).append(" ");
					if(p.left != null) { q.add(p.left); }
					if(p.right != null) { q.add(p.right); }
					
				}
				q.add(null);
			}
		}
	}

}
