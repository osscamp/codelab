package com.learning.btree;

public class TreeOps {
	
	static class HNode {
		int v;
		HNode left;
		HNode right;
		int xc;
		int yc;
		
		public HNode(int v) { this.v = v; }
		public String toString() { return v+" xc: "+xc+" yc: "+yc; }
	}
	
	private HNode root;
	private static int[] lvl = new int[1];

	public HNode insertNode(HNode t, int v) {
		if (t == null) {
			t = new HNode(v);
			if(root == null) {
				root = t;
			}
		}
		if (v < t.v) {
			t.left = insertNode(t.left, v);
		} else if (v > t.v) {
			t.right = insertNode(t.right, v);
		}
		return t;
	}
	
	public int draw(HNode t, int xcord, int ycord) {
		if(t != null) {
			//System.out.println("lvl "+lvl[0]+" xcord "+xcord);
			int xcl = draw(t.left, xcord+lvl[0], ycord);
			int xcr = draw(t.right, xcord+lvl[0], ycord);
			int xc = xcl+ (xcr - xcl)/2;
			t.xc = xc;
			t.yc = lvl[0];
			return xc;
		}else {
			lvl[0]++;
			return xcord;
		}
	}
	
	public void inorderCoord(HNode n) {
		if (n != null) {
			inorderCoord(n.left);
			System.out.println(n);
			inorderCoord(n.right);

		}
	}
	

	public static void main(String[] args) {
		TreeOps tree = new TreeOps();
		tree.insertNode(null, 12);
		tree.insertNode(tree.root, 5);
		tree.insertNode(tree.root, 16);
		tree.insertNode(tree.root, 3);
		tree.insertNode(tree.root, 8);
		tree.insertNode(tree.root, 18);
		tree.insertNode(tree.root, 13);



		tree.draw(tree.root, 0, 0);
		tree.inorderCoord(tree.root);
	}

}
