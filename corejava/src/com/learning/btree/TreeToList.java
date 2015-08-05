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
	
    public static Node treeToList(Node node) {
        // base case: empty tree -> empty list
        if (node==null) return(null);
        
        // Recursively do the subtrees (leap of faith!)
        Node aList = treeToList(node.left);
        Node bList = treeToList(node.right);
        
        // Make the single root node into a list length-1
        // in preparation for the appending
        node.left = node;
        node.right = node;
        
        // At this point we have three lists, and it's
        // just a matter of appending them together
        // in the right order (aList, root, bList)
        aList = append(aList, node);
        aList = append(aList, bList);
        
        return aList;
    }
    
    public static Node append(Node a, Node b) {
        // if either is null, return the other
        if (a==null) return(b);
        if (b==null) return(a);
        
        // find the last node in each using the .previous pointer
        Node aLast = a.left;
        Node bLast = b.right;
        
        // join the two together to make it connected and circular
        aLast.left = b;
        b.right = aLast;
        
        bLast.left = a;
        a.right = bLast;
        
        return(a);
    }

	
	public static void main(String[] args) {
		TreeToList tl = new TreeToList();
		tl.insertNode(null, 510);
		tl.insertNode(tl.root, 220);
		tl.insertNode(tl.root, 810);
		tl.insertNode(tl.root, 920);
		Node nn = treeToList(tl.root);
		System.out.println(nn);
	}
}
