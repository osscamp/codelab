package com.learning.re;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class Tree {

    static class Node {
	int v;
	Node left;
	Node right;
	Node parent;
	public Node(int v) {
		this.v = v;
	}
	public String toString() {
		return ""+v;
	}
    }

	Node root;
	
	public Node insert(Node n, int data) {
		if(n == null) {
			n = new Node(data);
			if(root == null) {
				root = n;
			}
		} else if(data < n.v) {
			n.left = insert(n.left, data);
			n.left.parent = n.left;
		}
		else if(data > n.v) {
			n.right = insert(n.right, data);
			n.right.parent = n.right;
		}
		return n;
	}

	public int findMax(Node n) {
		while(n != null && n.right != null) {
			n = n.right;
		}
		return n.v;
	}
	
	public void printInorder(Node n) {
		if(n != null) {
			printInorder(n.left);
			System.out.println(n.v);
			printInorder(n.right);
		}
	}
	
	public int getKthLargest(int k) {
		int[] tmp = new int[]{1,-1};
		getKthLargest(root, k, tmp);
		return tmp[1];
	}

	
	public void getKthLargest(Node n, int k, int[] tmp) {
		if(n != null) {
			getKthLargest(n.left, k, tmp);
			if(tmp[0] >= k && tmp[1] == -1) {
				tmp[1] = n.v;
				return;
			}
			tmp[0]++;
			getKthLargest(n.right, k, tmp);
		}
	}

	public Node buildTreeFromSortedArray(Node n, int[] a, int l, int r) {
		if( l<=r) {
			int mid = l + (r-l)/2;
			if(n == null) {
				n = new Node(a[mid]);
				if(root == null) { root = n;}
			}
			n.left = buildTreeFromSortedArray(n.left, a, l, mid-1);
			n.right = buildTreeFromSortedArray(n.right, a, mid+1, r);
		}
		return n;
	}
	
	public boolean hasPathSum(Node n, int s) {
		if(n != null) {
			s-=n.v;
			if(s==0 && n.left == null && n.right == null) {return true;}
			return hasPathSum(n.left, s) || hasPathSum(n.right, s);
		}
		return false;
	}
	
	public void printPaths(Node n, List<Integer> list) {
		if(n != null) {
			list.add(n.v);
			if(n.left == null && n.right==null) {
				System.out.println(list);
				return;	
			}
			printPaths(n.left, new ArrayList<>(list));
			printPaths(n.right, new ArrayList<>(list));
		}
	}
	
	
	public void invert(Node n) {
		if(n != null) {
			Node t = n.left;
			n.left = n.right;
			n.right = t;
			invert(n.left);
			invert(n.right);
		}
	}
	
	public void printPreorder() {
		LinkedList<Node> ll = new LinkedList<>();
		ll.addLast(this.root);
		while(ll.size() >= 1) {
			Node n = ll.removeFirst();
			if(n != null) {
				System.out.println(n.v);

				if(n.right != null) {
					ll.addFirst(n.right);
				}
				if(n.left != null) {
					ll.addFirst(n.left);
				}
			}
		}
	}

    public List<Integer> rightSideView(Node nn) {
    	LinkedList<Node> queue = new LinkedList<Node>();
        List<Integer> rv = new ArrayList<>();
        queue.addFirst(null);
        queue.addLast(nn);
        StringBuilder sb = new StringBuilder();
        while(queue.size() > 0) {
            Node n = queue.getLast();
            if( n == null) { //end of level
            	queue.removeLast();
                System.out.println(sb);
                if(queue.isEmpty()) {
                	return rv;
                }
                sb = new StringBuilder();
                queue.addFirst(null);
            }
            while(queue.getLast() != null) {
            	n = queue.removeLast();
	            sb.append(n.v).append(" ");
            	if(n.left != null) {
	            	queue.addFirst(n.left);
	            }
            	if(n.right != null) {
	                queue.addFirst(n.right);
	            } 
            }
        }
        System.out.println(rv);
        return rv;
    }
	
	public static void main(String[] args) {
		int[] a = {1,2,3,5,8,11,13};
		Tree t = new Tree();
		t.buildTreeFromSortedArray(null, a, 0, a.length-1);
		t.printInorder(t.root);
		int kl = t.getKthLargest(7);
		System.out.println("kth largest:"+kl);
		boolean ps = t.hasPathSum(t.root, 28);
		System.out.println("path sum "+ps);
		t.printPaths(t.root, new ArrayList<>());
		//t.invert(t.root);
		//t.invert(t.root);
		//t.printPaths(t.root, new ArrayList<>());
		t.rightSideView(t.root);
		t.printPreorder();

	}

}

