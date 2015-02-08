package com.learning.btree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestTree {

	static class Node {
		Node(int n) {
			this.v = n;
		}

		int v;
		Node left;
		Node right;
		Node parent;
		
		@Override
		public String toString() {
			return String.valueOf(v);
		}
		
		public StringBuilder prettyPrintSO(StringBuilder prefix, boolean isTail, StringBuilder sb) {
		    if(right!=null) {
		        right.prettyPrintSO(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), left == null, sb);
		    }
		    sb.append(prefix).append(isTail ? "└── " : "┌── ").append(v).append("\n");
		    if(left!=null) {
		        left.prettyPrintSO(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
		    }
		    return sb;
		}

		public String prettyPrintSO() {
		    return this.prettyPrintSO(new StringBuilder(), true, new StringBuilder()).toString();
		}
	}

	Node root;

	public Node insertNode(Node t, int nv) {
		if (t == null) {
			t = new Node(nv);
			if (root == null) {
				root = t;
			}
		}
		if (nv < t.v) {
			t.left = insertNode(t.left, nv);
			t.left.parent = t;
		} else if (nv > t.v) {
			t.right = insertNode(t.right, nv);
			t.right.parent = t;
		}
		return t;
	}
	
	public Node searchNode(int x) {
		return searchNode(root, x);
	}
	
	public Node searchNode(Node p, int x) {
		if(p == null) {
			return null;
		}
		if(x < p.v) {
			return searchNode(p.left, x);
		}else if(x > p.v) {
			return searchNode(p.right, x);
		}else {
			return p;
		}
	}

	public void inOrderPrint(Node n) {
		if (n != null) {
			inOrderPrint(n.left);
			System.out.println(n.v);
			inOrderPrint(n.right);
		}
	}
	
	public void postOrderPrint(Node n) {
		if (n != null) {
			postOrderPrint(n.left);
			postOrderPrint(n.right);
			System.out.println(n.v);
		}
	}
	
	public void iterativeInOrder(Node n) {
		Stack<Node> stack = new Stack<>();
		while (n != null || !stack.isEmpty()) {
			if (n != null) {
				stack.push(n);
				n = n.left;
			} else if (!stack.isEmpty()) {
				n = stack.pop();
				System.out.println(n.v);
				n = n.right;
			}
		}
	}
	
	public void printBFSIterative() {
		List<Node> currentLevel = new ArrayList<Node>();
		List<Node> nextLevel = new ArrayList<Node>();
		currentLevel.add(root);
		while(!currentLevel.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for(Node currentNode : currentLevel) {
				if(currentNode.left != null) {
					nextLevel.add(currentNode.left);
				}
				if(currentNode.right != null) {
					nextLevel.add(currentNode.right);
				}
				sb.append(currentNode.v).append(" ");
			}
			System.out.println(sb.toString());
			currentLevel = nextLevel;
			nextLevel = new ArrayList<Node>();
		}
	}
	
	public int heightR(Node n) {
		if(n == null) {
			return -1;
		}
		int hl = heightR(n.left);
		int hr = heightR(n.right);
		return 1 + Math.max(hl, hr);
	}
	
	public void isBalanced(Node n, AtomicBoolean b) {
		if(n != null) {
			int heightL = heightR(n.left);
			int heightR = heightR(n.right);
			if(Math.abs(heightL-heightR) > 1) {
				b.set(false);
				return;
			}
		}
		System.out.println(b.get());
	}
	
	public int countNodes(Node n) {
		if(n != null) {
			return 1+countNodes(n.left)+countNodes(n.right);
		}
		return 0;
	}
	
	public int countLeaf(Node n) {
		if(n != null) {
			if(n.left == null && n.right == null) {
				return 1;
			}
			return countLeaf(n.left) + countLeaf(n.right);
		}
		return -1;	
	}
	
	public void prettyPrintSO() {
		System.out.println(root.prettyPrintSO());
	}

	public void prettyPrint(Node n) {
		int treeh = heightR(n);
		int spaces = treeh * 5;
		int rspaces = spaces;

		for (int i = 1; i <= treeh + 1; i++) {
			StringBuilder ws = new StringBuilder();
			for (int ii = 1; ii <= spaces; ii++) {
				ws.append(" ");
			}
			spaces = spaces - (treeh - i) * 2;
			rspaces = spaces + (treeh - i) * 2;
			StringBuilder rspaceb = new StringBuilder();
			for (int ii = 1; ii <= rspaces; ii++) {
				rspaceb.append(" ");
			}
			System.out.println(prettyPrintLevel(n, i, ws, rspaceb));

		}
	}

	public StringBuilder prettyPrintLevel(Node n, int level, StringBuilder ws,
			StringBuilder rsp) {
		if (n == null) {
			return ws;
		}
		if (level == 1) {
			ws.append(n.v);
		} else if (level > 1) {
			prettyPrintLevel(n.left, level - 1, ws, rsp);
			prettyPrintLevel(n.right, level - 1, ws.append(rsp), rsp);
		}
		return ws;
	}

	public void treeFromSortedArray() {
		int[] srt = { 1, 3, 5, 7, 9, 10, 34/*, 56, 63, 78, 123, 134, 156, 171,
				185, 191, 204, 206, 210*/ };
		int l = 0;
		int r = srt.length - 1;
		sortInsert(srt, l, r);

	}

	public void sortInsertRaw(int[] arr, int l, int r) {
		int mid = (l + r) / 2;
		if (l >= mid) {
			insertNode(root, arr[mid]);
			return;
		} else if (r <= mid) {
			insertNode(root, arr[mid]);
			// return;
		}
		insertNode(root, arr[mid]);
		sortInsertRaw(arr, l, mid);
		sortInsertRaw(arr, mid, r);
	}
	
	public Node sortInsert(int[] arr, int l, int r) {
		if(l > r) {
			return null;
		}
		int mid = l+(r-l) / 2;
		Node n = new Node(arr[mid]);
		if (root == null) {
			root = n;
		}
		n.left = sortInsert(arr, l, mid-1);
		n.right = sortInsert(arr, mid+1, r);
		return n;
	}

	public Node delete(Node n, int vd) {
		if (n == null) {
			return null;
		}
		if (vd < n.v) {
			n.left = delete(n.left, vd);
		} else if (vd > n.v) {
			Node delR = delete(n.right, vd);
			n.right = delR;
		} else {
			if (n.left == null) {
				return n.right;
			} else if (n.right == null) {
				return n.left;
			} else {
				// replace with max from left subtree
//				n.v = findMax(n.left);
//				n.left = delete(n.left, n.v);
				
				// replace with min from right subtree
				n.v = findMin(n.right);
				n.right = delete(n.right, n.v);
			}
		}
		return n;
	}
	
	public Node findSuccessor(Node n) {
		if(n == null) {
			return n;
		}
		if(n.right != null) {
			n = n.right;
			while(n.left != null) {
				n = n.left;
			}
			return n;
		}
		Node up = n.parent;
		while(up != null && n == up.right) {
			n = up;
			up = up.parent;
		}
		return up;
	}
	
	public Node findSuccessorWithoutParent(int x) {
		Node nn = searchNode(x);
		if(nn.right != null) {
			return findMinNode(nn.right);
		}
		return findSuccessorR(root, nn, null);
	}
	
	public Node findSuccessorR(Node n, Node x, Node p) {
		if(n == x) {
			return p;
		}
		if(x.v < n.v) {
			return findSuccessorR(n.left, x, n);
		} else  {
			return findSuccessorR(n.right, x, p);
		}
		//return p;
	}
	
	public void leftRotate(Node n) {
		if(n == null || n.right == null) return;
		Node x = n;
		Node y = n.right;
		if(x.parent != null) {
			if(x == x.parent.left) x.parent.left = y;
			else x.parent.right = y;
		}
		y.parent = x.parent;
		x.right = y.left;
		y.left = x;		

		x.parent = y;
		if(x.right != null){x.right.parent = x;}
		if(y.parent == null) { root = y; }

	}
	
	public void rightRotate(Node n) {
		if(n == null || n.left == null) return;
		Node y = n;
		Node x = n.left;
		if(y.parent != null) {
			if(y == y.parent.left) y.parent.left = x;
			else y.parent.right = x;
		}
		x.parent = y.parent;
		y.left = x.right;
		x.right = y;		

		y.parent = x;
		if(y.left != null){y.left.parent = y;}
		if(x.parent == null) { root = x; }

	}
	
	private int findMax(Node p) {
		while (p.right != null)
			p = p.right;
		return p.v;
	}
	
	private int findMin(Node p) {
		while (p.left != null)
			p = p.left;
		return p.v;
	}
	
	private Node findMinNode(Node p) {
		while (p.left != null)
			p = p.left;
		return p;
	}

	public static void main(String[] args) {
		Node tn = new Node(15);
		TestTree tt = new TestTree();
		tt.root = tn;
		tt.insertNode(tt.root, 25);
		tt.insertNode(tt.root, 5);
		tt.insertNode(tt.root, 28);
		tt.insertNode(tt.root, 17);
		tt.insertNode(tt.root, 16);
		tt.insertNode(tt.root, 21);
		tt.insertNode(tt.root, 35);
		tt.insertNode(tt.root, 31);
		tt.insertNode(tt.root, 2);
		// tt.inOrderPrint(tt.root);

		tt.prettyPrint(tt.root);
		tt.prettyPrintSO();
		System.out.println("Tree Height:" + tt.heightR(tt.root));
		//tt.printLevelOrder(tt.root);

		//TestTree tt1 = new TestTree();
		//tt1.treeFromSortedArray();
		//tt1.prettyPrint(tt1.root);
		
		tt.leftRotate(tt.root);
		//tt.delete(tt.root, 15);
		tt.prettyPrint(tt.root);
		tt.prettyPrintSO();
		System.out.println("Tree Height:" + tt.heightR(tt.root));
		tt.leftRotate(tt.root.left);
		tt.prettyPrint(tt.root);
		tt.prettyPrintSO();
		System.out.println("Tree Height:" + tt.heightR(tt.root));
		tt.rightRotate(tt.root.left);
		tt.prettyPrint(tt.root);
		tt.prettyPrintSO();
		tt.printBFSIterative();
		tt.insertNode(tt.root, 30);
		tt.insertNode(tt.root, 29);
		tt.isBalanced(tt.root, new AtomicBoolean(true));
		tt.prettyPrintSO();
		Node successor = tt.findSuccessorWithoutParent(16);
		System.out.println("suc "+successor);
		tt.postOrderPrint(tt.root);
		
		Node ttn = new Node(151);
		TestTree ttt = new TestTree();
		ttt.root = ttn;
		ttt.insertNode(ttt.root, 25);
		ttt.insertNode(ttt.root, 512);
		ttt.insertNode(ttt.root, 280);
		ttt.insertNode(ttt.root, 780);
		ttt.delete(ttt.root, 151);
	}

}
