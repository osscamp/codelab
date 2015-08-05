package com.learning.btree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


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
				
				// replace with min from right subtree
				n.v = findMin(n.right);
				n.right = delete(n.right, n.v);
			}
		}
		return n;
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
	
	public void iterativePreOrder() {
		Node n = root;
		if(n == null) { return; }
		Stack<Node> stack = new Stack<>();
		stack.push(n);
		Node prev = null;
		while(!stack.isEmpty()) {
			n = stack.peek();
			if(prev == null || prev.left == n || prev.right == n) {
				if(n.left != null) {
					stack.push(n.left);
				} else if(n.right != null) {
					stack.push(n.right);
				} else {
					stack.pop();
					System.out.println(n);
				}
			} else if(n.left == prev){
                if(n.right != null){
                    stack.push(n.right);
                }else{
                    stack.pop();
                    System.out.println(n);
                }
 
            //go up the tree from right node 
            //after coming back from right node, process parent node and pop stack. 
            }else if(n.right == prev){
                stack.pop();
                System.out.println(n);
            }
 
            prev = n;
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
	
	public void printBFSIterativePretty() {
		List<Node> currentLevel = new ArrayList<Node>();
		List<Node> nextLevel = new ArrayList<Node>();
		currentLevel.add(root);
		int height = heightR(root) + 1;
		int nodespace = 8;
		StringBuilder sb = new StringBuilder();

		int width = height*nodespace*2;
		int insertloc = width;
		double divisor = 1.0;
		int currHeight = 0;
		while(!currentLevel.isEmpty()) {
			sb = new StringBuilder(width);
			sb.append(' ');
			//for(int i=0; i<width; i++) {
			while(sb.length() <= width) {
				sb.append(sb);
			}
			divisor *= 2;
			double segment = width/divisor;
			insertloc = (int)segment;
			for(int i=0; i<currentLevel.size(); i++) {
				Node currentNode = currentLevel.get(i);
				
				if(currentNode != null) {
					nextLevel.add(currentNode.left);
					nextLevel.add(currentNode.right);
					sb.insert(insertloc, currentNode.v);
				} else {
					//add dummy left and right nodes to create empty spaces
					nextLevel.add(null);
					nextLevel.add(null);
				}
				insertloc = Math.min((int)(insertloc+2*segment), width);
			}
			currHeight++;
			System.out.println(sb.toString());
			//System.out.println("\n");
			currentLevel = nextLevel;
			nextLevel = new ArrayList<Node>();
			if(currHeight >= height) {
				currentLevel.clear();
				break;
			}
		}

	}
	
	public void printBFSWithQueue() {
		java.util.LinkedList<Node> ll = new LinkedList<>();
		ll.addLast(root);
		ll.addLast(null);
		Node in = null;
		StringBuilder sb = new StringBuilder();
		while(!ll.isEmpty()) {
			if(ll.peekFirst() == null) {
				if(ll.size() == 1) {
					break;
				}
				ll.removeFirst();
				System.out.println(sb);
				sb = new StringBuilder();
				ll.addLast(null);
				continue;
			}
			in = ll.pollFirst();
			sb.append(in.v).append(" ");
			if(in.left != null) {
				ll.addLast(in.left);
			}
			if(in.right != null) {
				ll.addLast(in.right);
			}
		}
	}
	
	public void printBFSZigZag() {
		List<Node> curr = new ArrayList<>();
		List<Node> next = new ArrayList<>();
		curr.add(root);
		int ctr = 1;
		while(!curr.isEmpty()) {
			StringBuilder sb = new StringBuilder();

			for(int i=curr.size()-1; i>=0; i--) {
				Node t = curr.get(i);
				sb.append(t.v).append(" ");		
				if(ctr %2 == 0) {
					if(t.left != null)	next.add(t.left);
					if(t.right != null) next.add(t.right);
				} else {
					if(t.right != null) next.add(t.right);						
					if(t.left != null)	next.add(t.left);
				}
			}
			
			ctr++;
			System.out.println(sb);
			curr = next;
			next = new ArrayList<>();
		}
	}
	
	public void printBFSReverseIterative() {
		List<Node> currentLevel = new ArrayList<Node>();
		List<Node> nextLevel = new ArrayList<Node>();
		List<List<Node>> result = new ArrayList<>();
		currentLevel.add(root);
		while(!currentLevel.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			List<Node> alist = new ArrayList<>();
			for(Node currentNode : currentLevel) {
				if(currentNode.left != null) {
					nextLevel.add(currentNode.left);
				}
				if(currentNode.right != null) {
					nextLevel.add(currentNode.right);
				}
				sb.append(currentNode.v).append(" ");
				alist.add(currentNode);
			}
			//System.out.println(sb.toString());
			result.add(0, alist);
			currentLevel = nextLevel;
			nextLevel = new ArrayList<Node>();
		}
		System.out.println(result);
	}
	
	public int heightR(Node n) {
		if(n == null) {
			return -1;
		}
		int hl = heightR(n.left);
		int hr = heightR(n.right);
		return 1 + Math.max(hl, hr);
	}
	
	public int minHeight(Node n) {
		if(n == null) {
			return -1;
		}
		int hl = minHeight(n.left);
		int hr = minHeight(n.right);
		return 1+Math.min(hl, hr);
	}
	
	public boolean isBalanced(Node n) {
		return n == null || (
				isBalanced(n.left) &&
				isBalanced(n.right) &&
				(Math.abs(heightR(n.left) - heightR(n.right)) <= 1)) ;

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
		if(l <= r) {
			int mid = l+(r-l) / 2;
			Node n = new Node(arr[mid]);
			if (root == null) {
				root = n;
			}
			n.left = sortInsert(arr, l, mid-1);
			n.right = sortInsert(arr, mid+1, r);
			return n;
		}
		return null;
	}
	
	public void createTreeFromInorderArray() {
		int[] inorder = {4,5,6,7,12,13,16,19};
		int[] preorder = {12,7,5,4,6,16,13,19};
		TestTree pp = new TestTree();
		int l = 0;
		int r = inorder.length;
		buildTree(l, r, preorder, inorder);

	}
	
	static int preidx1 = 0;
	
	public Node buildTree(int l, int r, int[] preorder, int[] inorder) {
		if(l > r || preidx1 > preorder.length-1) {
			return null;
		}
		
		int v = preorder[preidx1++];
		Node newNode = new Node(v);
		if(l == r) {
			return newNode;
		}
		//binary search allowed for BST only
		int ridx = BinarySearch.binarySearchIndex(inorder, v, l, r);
		
		if(root == null) {
			root = newNode;
			
		}
		newNode.left = buildTree(l, ridx-1, preorder, inorder);
		newNode.right = buildTree(ridx+1, r, preorder, inorder);
		return newNode;
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
	
	public Node findSuccessorWithoutParent(int toFind) {
		Node nn = searchNode(toFind);
		if(nn.right != null) {
			return findMinNode(nn.right);
		}
		Node succ = findSuccessorR(root, nn, null);
		return succ;
	}
	
	public Node findSuccessorR(Node temp, Node toFind, Node parent) {
		if(temp == toFind) {
			return parent;
		}
		if(toFind.v < temp.v) {
			Node succ = findSuccessorR(temp.left, toFind, temp);
			return succ;
		} else  {
			Node succ = findSuccessorR(temp.right, toFind, parent);
			return succ;
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
	
	public void printPathsNoParent(Node n, List<Integer> path) {
		if(n == null) {
			return;
		}
		path.add(n.v);
		if(n.left == null && n.right == null) {
			System.out.println(path);
			return;
		} else {
			printPathsNoParent(n.left, new ArrayList<Integer>(path));
			printPathsNoParent(n.right, new ArrayList<Integer>(path));
		}

	}
	
	public void listPaths(Node n, List<Node> path, List<List<Node>> allPaths) {
		if(n == null) {
			return;
		}
		path.add(n);
		if(n.left == null && n.right == null) {
			System.out.println(path);
			allPaths.add(path);
			return;
		} else {
			listPaths(n.left, new ArrayList<>(path), allPaths);
			listPaths(n.right, new ArrayList<>(path), allPaths);
		}

	}
	
	public boolean hasPathSum(Node n, int rem) {
		if(n == null) {
			return false;
		}
		rem -= n.v;
		if(n.left == null && n.right == null) {
			return rem == 0;
		}else {
			return hasPathSum(n.left, rem) || hasPathSum(n.right, rem) ;
		}
		
	}
	
	public Node lowestCommonAncestorBST(Node r, Node a, Node b) {
		if(r == null) {
			return null;
		}
		if(a.v < r.v && b.v < r.v) {
			return lowestCommonAncestorBST(r.left, a, b);
		}
		if(a.v > r.v && b.v > r.v) {
			return lowestCommonAncestorBST(r.right, a, b);
		}
		return r;
	}
	public boolean isBST(Node n) {
		return isBST(n, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public boolean isBST(Node n, int min, int max) {
		if(n == null) {
			return true;
		}
		if(n.v <= max && n.v >= min) {
			 return isBST(n.left, min, n.v) && isBST(n.right, n.v, max);
		}
		return false;
	}
	
	public Node invert(Node n) {
		if(n != null && (n.left != null || n.right != null)) {
			invert(n.left);
			Node tmp = n.left;
			n.left = n.right;
			n.right = tmp;
			invert(n.right);
		}
		return n;
	}
	
	public int getKthLargest(int k) {
		//passes an array instead of global variable to hold visited counts
		int[] visitct = new int[2];
		getKthLargest(root, visitct, k);
		return visitct[1];
	}
	
	private void getKthLargest(Node n, int[] visitct, int k) {

		if(n != null) {
			getKthLargest(n.right, visitct, k);
			visitct[0]++;
			System.out.println("tree "+n.v+" vist "+visitct[0]);
			if(visitct[0] == k) {
				visitct[1] = n.v;
				return;
			}
			getKthLargest(n.left, visitct, k);
		}
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
		tt.insertNode(tt.root, 7);
		tt.insertNode(tt.root, 28);
		tt.insertNode(tt.root, 17);
		tt.insertNode(tt.root, 16);
		tt.insertNode(tt.root, 21);
		tt.insertNode(tt.root, 35);
		tt.insertNode(tt.root, 26);
		tt.insertNode(tt.root, 2);
		tt.insertNode(tt.root, 1);
		tt.insertNode(tt.root, 3);
		tt.insertNode(tt.root, 36);
		tt.insertNode(tt.root, 31);
		// tt.inOrderPrint(tt.root);
		tt.printBFSWithQueue();
		tt.printBFSZigZag();
		tt.printBFSReverseIterative();

		tt.printBFSIterativePretty();
		int V = 73;
		System.out.println("path sum pres "+tt.hasPathSum(tt.root, V));


		tt.prettyPrintSO();
		System.out.println("Tree Height:" + tt.heightR(tt.root));
		System.out.println("Min Tree Height:" + tt.minHeight(tt.root));
		
		tt.leftRotate(tt.root);
		tt.prettyPrintSO();
		System.out.println("Tree Height:" + tt.heightR(tt.root));
		tt.leftRotate(tt.root.left);
		tt.prettyPrintSO();
		System.out.println("Tree Height:" + tt.heightR(tt.root));
		tt.rightRotate(tt.root.left);
		tt.prettyPrintSO();
		
		tt.insertNode(tt.root, 30);
		tt.insertNode(tt.root, 29);
		tt.prettyPrintSO();
		Node successor = tt.findSuccessorWithoutParent(7);
		System.out.println("suc "+successor);
		//tt.postOrderPrint(tt.root);
		tt.lowestCommonAncestorBST(tt.root, tt.root.left.left.left, tt.root.left.right.left);
		
		Node ttn = new Node(24);
		TestTree ttt = new TestTree();
		ttt.root = ttn;
		ttt.insertNode(ttt.root, 11);
		ttt.insertNode(ttt.root, 28);
		ttt.insertNode(ttt.root, 4);
		ttt.insertNode(ttt.root, 16);
		ttt.insertNode(ttt.root, 13);
/*		ttt.insertNode(ttt.root, 19);*/
		ttt.delete(ttt.root, 151);
		ttt.iterativePreOrder();
		int kth = ttt.getKthLargest(3);
		System.out.println("kth largest "+kth);
		
		TestTree at = new TestTree();
		at.sortInsert(new int[]{1, 2, 3,4, 5,6,7}, 0, 6);
		//at.root.right.right = new Node(6);
		System.out.println(at.isBST(at.root));
		System.out.println("is balanced "+at.isBalanced(at.root));
		at.printBFSIterativePretty();
		at.invert(at.root);
		at.printBFSIterativePretty();
		
		TestTree fromInorder = new TestTree();
		fromInorder.createTreeFromInorderArray();
		//fromInorder.printBFSIterativePretty();

	}

}
