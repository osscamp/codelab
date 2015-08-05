package com.learning.btree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.LinkedList;

import com.learning.btree.TestTree.Node;

public class Practice {

	Node root;

	private class Node {
		int d;
		Node left;
		Node right;
		Node parent;
		int level = 0;
		Node nextRight; //for linkRight question

		public Node(int d) {
			this.d = d;
		}

		@Override
		public String toString() {
			return String.valueOf(d);
		}
	}

	public void insertTreeNode(int n) {
		insertNode(n, root);
	}

	public Node insertNode(int n, Node node) {
		if (node == null) {
			node = new Node(n);
		}

		if (n < node.d) {
			node.left = insertNode(n, node.left);
			node.left.parent = node;
			if(node.left.level == 0) {
				node.left.level = node.left.parent.level + 1;
			}
		} else if (n > node.d) {
			node.right = insertNode(n, node.right);
			node.right.parent = node;
			if(node.right.level == 0) {
				node.right.level = node.right.parent.level + 1;
			}
		}
		return node;
	}

	public void inOrderPrint(Node n) {
		if (n != null) {
			inOrderPrint(n.left);
			System.out.println(n.d+" level:"+n.level+" parent:"+n.parent);
			inOrderPrint(n.right);
		}
	}
	
	public void printBFSWithQueue() {
		List<Node> currentLevel = new ArrayList<Node>();
		List<Node> nextLevel = new ArrayList<Node>();
		currentLevel.add(root);
		while(!currentLevel.isEmpty()) {
			Iterator<Node> itr = currentLevel.iterator();
			StringBuilder sb = new StringBuilder();
			while(itr.hasNext()) {
				Node currentNode = itr.next();
				if(currentNode.left != null) {
					nextLevel.add(currentNode.left);
				}
				if(currentNode.right != null) {
					nextLevel.add(currentNode.right);
				}
				sb.append(currentNode.d).append(" ");
			}
			System.out.println(sb.toString());
			currentLevel = nextLevel;
			nextLevel = new ArrayList<Node>();
		}
	}
	
	public List<Node> rightview() {
		List<Node> rv = new ArrayList<>();
		List<Node> curr = new ArrayList<>();
		List<Node> next = new ArrayList<>();
		curr.add(root) ;
		while(!curr.isEmpty()) {
			for(int i=0; i<curr.size(); i++) {
				Node val = curr.get(i);
				if(i==curr.size()-1) {
					System.out.println("right node "+val.d);
					rv.add(val);
				}
				if(val.left != null) next.add(val.left);
				if(val.right != null) next.add(val.right);
			}
			curr = next;
			next = new ArrayList<>();
		}
		return rv;
	}
	
	public void printBFS() {
		Map<Integer, List<Node>> map = new TreeMap<Integer, List<Node>>();
		printBFS(root, map);
		for(Integer i : map.keySet()) {
			StringBuilder sb = new StringBuilder();
			List<Node> ln = map.get(i);
			for(Node nn : ln) {
				sb.append(nn.d).append(" ");
			}
			System.out.println(sb.toString());
		}
	}
	
	public void printBFS(Node n, Map<Integer, List<Node>> map) {
		if(n != null) {
			int level = n.level;
			List<Node> list = map.get(level) == null ? new ArrayList<Node>() : map.get(level);
			list.add(n);
			map.put(level, list);
			printBFS(n.left, map);
			printBFS(n.right, map);
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
	
	
	public int countNodes(Node n) {
		if(n != null) {
			return 1+countNodes(n.left)+countNodes(n.right);
		}
		return 0;
	}
	
	public boolean treeSearch(int s, Node n) {
		if(n == null) {
			return false;
		}
		if(s == n.d) {
			return true;
		}
		else if(s < n.d) {
			return treeSearch(s, n.left);
		} else {
			return treeSearch(s, n.right);
		}
	}
	
	public int max() {
		Node t = root;
		while(t.right != null) {
			t = t.right;
		}
		System.out.println(t.d);
		return t.d;
	}
	
	public int minRecursive(Node n) {
		if (n.left != null) {
			return minRecursive(n.left);
		} 
		return n.d;
	}
	
	public Node findMin(Node n) {
		if (n.left != null) {
			return findMin(n.left);
		} 
		return n;
	}
	
	public Node findSuccessor(Node n) {
		if(n == null) {
			return n;
		}
		if(n.right != null) {
			return findMin(n.right);
		}
		Node up = n.parent;
		while(up != null && n == up.right) {
			n = up;
			up = up.parent;
		}
		return up;
	}
	
	
	public Node findSuccessorNoParent(Node n) {
		if(n == null) {
			return n;
		}
		if ( n.right != null ) {
			return findMin(n.right);
		}
		Node nn = root;
		Node succ = nn;
		while(nn != null) {
			if(n.d < nn.d) {
				succ = nn;
				nn = nn.left;
			} else if(n.d > nn.d) {
				nn = nn.right;
			} else {
				break;
			}
		}
		return succ;
	}
	
	public void leftRotate(Node n) {
		Node x = n;
		Node y = n.right;
		y.parent = x.parent;
		x.right = y.left;
		y.left = x;
		x.parent = y;
		x.right.parent = x;
		if(y.parent == null) { root = y; }

	}
	
	public int countLeaf(Node n) {
		if(n != null) {
			if(n.left == null && n.right == null) {
				System.out.println("leaf "+n);
				return 1;
			}
			return countLeaf(n.left) + countLeaf(n.right);
		}
		return -1;	
	}
	
	/**
	 * Write a method evenBranches that returns the number of branch nodes in a binary tree that contain even numbers.
	 * @return
	 */
	public int evenBranches(Node n) {
		if(n != null) {
			if((n.left != null || n.right != null) && n.d % 2 == 0) {
				return 1 + evenBranches(n.left) + evenBranches(n.right);
			}
		}
		return 0;
	}
	
	public void isFull(Node n, AtomicBoolean b) {
		if(n != null) {
			if((n.left == null && n.right != null)
					|| (n.right == null && n.left != null)) {
				b.set(false);
				return;
			}				
			if (b.get()) isFull(n.left, b);
			if (b.get()) isFull(n.right, b);
		}
	}
	
	public void printPaths() {
		if(root == null) {
			System.out.println("NA");
		}
		printPaths(root);
	}
	
	public void printPaths(Node n) {
		if(n != null) {
			if(n.left == null && n.right == null) { // found a leaf node, go up the tree
				StringBuilder sb = new StringBuilder();
				sb.append(" ").append(n);
				Node p = n.parent;
				while(p != null) {
					sb.insert(0, " ").insert(1,p);
					p = p.parent;
				}
				System.out.println(sb);
			}
			printPaths(n.left);
			printPaths(n.right);
		}
	}
		
	public void printPathsNoParent(Node n, List<Integer> path) {
		if(n == null) {
			return;
		}
		path.add(n.d);
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
	
	public Node leastCommonAncestorNonBST(Node r, Node a, Node b) {
		Node left = null;
		Node right = null;
		if(r == null) {
			return null;
		}
		if(r == a || r == b) { return r; }
		left = leastCommonAncestorNonBST(r.left, a, b);
		right = leastCommonAncestorNonBST(r.right, a, b);
		if(left != null && right != null) {
			return r;
		}
		return (left != null) ? left : right;
	}
	
	public Node lcaBST(Node r, Node a, Node b) {
		if(r == null) {
			return null;
		}
		if(a.d < r.d && b.d < r.d) {
			return lcaBST(r.left, a, b);
		}
		if(a.d > r.d && b.d > r.d) {
			return lcaBST(r.right, a, b);
		}
		return r;
	}
	
	public void printBFSQueue(){
		LinkedList<Node> q = new LinkedList<>();
		q.add(root);
		StringBuilder sb = new StringBuilder();
		while(!q.isEmpty()) {
			
			Node n = q.pollLast();
			System.out.println(n.d);

			if(n.left != null) {
				q.push(n.left);
			}
			if(n.right != null) {
				q.push(n.right);
			}

		}
	}
	
	public boolean hasPathSum(Node n, int targetSum) {
		if(n != null) {
			if(n.left == null && n.right == null) { // found a leaf node, go up the tree
				int pathSum = 0;
				pathSum += n.d;
				Node p = n.parent;
				while(p != null) {
					pathSum += p.d;
					p = p.parent;
				}
				System.out.println(pathSum);
				if(pathSum == targetSum) {
					System.out.println("found path with sum : "+targetSum);
					return true;
				}
			}
			return hasPathSum(n.left, targetSum) ||
						hasPathSum(n.right, targetSum);			
		}
		return false;
	}
	
	public void linkRight() {
		Node n = root;
		LinkedList<Node> ll = new LinkedList<>();
		ll.add(n);
		ll.add(null);
		StringBuilder sb = new StringBuilder();
		while(!ll.isEmpty()) {
			Node ab = ll.peek();
			if(ab == null) {
				ll.removeFirst();
				System.out.println(sb);
				sb = new StringBuilder();
				if(ll.size() > 0) {
					ll.addLast(null);
				}
				continue;
			} else {
				ab = ll.removeFirst();
				sb.append(ab.d);
				if(ll.peek() != null) {
					ab.nextRight = ll.peek();
					sb.append("->").append(" ");
				}
				if(ab.left != null) {
					ll.add(ab.left);
				}
				if(ab.right != null) {
					ll.add(ab.right);
				}
			}
		}
	}
	
	public void printTopView(Node n) {
		Node t = n;
		StringBuilder sb = new StringBuilder();
		while (t != null) {
			sb.insert(0, t).insert(1, " ");
			t=t.left;
		} 
		t = n;
		while(t != null) {
			t= t.right;
			if(t != null) {
				sb.append(t).append(" ");
			}
		}
		System.out.println(sb);
	}
	
	public void createTreeFromInorderArray() {
		int[] inorder = {4,5,6,7,12,13,16,19};
		int[] preorder = {12,7,5,4,6,16,13,19};
		Practice pp = new Practice();
		int l = 0;
		int r = inorder.length;
		Node newnode = null;
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
		int ridx = BinarySearch.binarySearchIndex(inorder, v, 0, inorder.length-1);
		
		if(root == null) {
			root = newNode;
			
		}
		newNode.left = buildTree(l, ridx-1, preorder, inorder);
		newNode.right = buildTree(ridx+1, r, preorder, inorder);
		return newNode;
	}
	
	public boolean isSameTree(Node a , Node b) {
		if(a != null && b != null) {
			return a.d == b.d
					&& isSameTree(a.left, b.left)
					&& isSameTree(a.right, b.right);
 		} else if(a != null && b == null ) return false;
 		else if(a == null && b != null ) return false;
		return true;
	}
	
	public void bfsq(Node t) {
		LinkedList<Node> ll = new LinkedList<>();
		ll.add(t);
		ll.add(null);
		StringBuilder sb = new StringBuilder();
		while(!ll.isEmpty()) {
			if(ll.peekLast() == null) {
				System.out.println(sb);
				ll.pollLast();
				sb = new StringBuilder();
			} else {
				ll.addFirst(null);
				Node p = ll.peekLast();
				while(p != null) {
					sb.append(p.d).append(" ");
					if(p.left != null) {
						ll.addFirst(p.left);
					}
					if(p.right != null) {
						ll.addFirst(p.right);
					}
					ll.pollLast();
					p = ll.peekLast();
				}
			}
		}
	}

	public static void main(String[] args) {
		Practice practice = new Practice();
		Node r1 = practice.new Node(8);
		practice.root = r1;
		//practice.insertTreeNode(8);
		practice.insertTreeNode(22);
		practice.insertTreeNode(4);
		practice.insertTreeNode(3);
		practice.insertTreeNode(6);
		practice.insertTreeNode(12);
		practice.insertTreeNode(10);
		//practice.insertTreeNode(14);
		practice.insertTreeNode(28);
		practice.insertTreeNode(25);
		practice.insertTreeNode(38);
		practice.insertTreeNode(29);
		practice.inOrderPrint(practice.root);
		int ht = practice.heightR(practice.root);

		//practice.iterprint(practice.root);
		System.out.println(practice.treeSearch(9, practice.root));
		//System.out.println(practice.findSuccessor(practice.root.left.right.right).d);
		System.out.println("min "+practice.minRecursive(practice.root));
		//practice.leftRotate(practice.root);
		practice.inOrderPrint(practice.root);
		System.out.println("height "+ht);
		// practice.insertNode(3, null);
		practice.printBFS();
		System.out.println("Q version");
		practice.printBFSWithQueue();
		System.out.println("nodes ="+practice.countNodes(practice.root));
		System.out.println("leaves "+practice.countLeaf(practice.root));
		System.out.println("even br "+practice.evenBranches(practice.root));
		AtomicBoolean bb = new AtomicBoolean(true);
		practice.isFull(practice.root, bb);
		System.out.println("is full "+bb.get());
		practice.printPaths();
		practice.hasPathSum(practice.root, 18);
		System.out.println(practice.findSuccessor(practice.root.right.left.left));
		System.out.println(practice.findSuccessorNoParent(practice.root.right.left.left));
		//practice.printTopView(practice.root);
		practice.printBFSWithQueue();
		practice.printPathsNoParent(practice.root, new ArrayList<Integer>());
		List<Node> al = new ArrayList<>();
		List<List<Node>> allPath = new ArrayList<>();
		practice.listPaths(practice.root, al, allPath);
		System.out.println("aall path "+allPath);
		Node lca = practice.leastCommonAncestorNonBST(practice.root, practice.root.right.left.left, practice.root.right.right.left);
		System.out.println("n1 "+practice.root.right.left.left+" n2 "+practice.root.right.right.left+" lca "+lca);
		Node lcsbst = practice.lcaBST(practice.root, practice.root.right.left.left, practice.root.right.right.right.left);
		System.out.println("lcabst "+lcsbst);
		practice.printBFSQueue();
		Practice fArray = new Practice();
		fArray.createTreeFromInorderArray();
		fArray.printBFSWithQueue();
		
		Practice p1 = new Practice();
		p1.root = p1.insertNode(5, null);
		p1.insertTreeNode(3);
		p1.insertTreeNode(6);
		p1.insertTreeNode(8);
		
		Practice p2 = new Practice();
		p2.root = p2.insertNode(5, null);
		p2.insertTreeNode(3);
		p2.insertTreeNode(7);
		p2.insertTreeNode(9);
		p2.insertTreeNode(6);
		p2.insertTreeNode(1);
		p2.insertTreeNode(4);
		p2.insertTreeNode(0);
		//p2.linkRight();
		Practice p3 = new Practice();
		p3.bfsq(p3.root);
		p2.rightview();
		

		System.out.println("is sametree "+p2.isSameTree(p1.root, p2.root));
	}

}
