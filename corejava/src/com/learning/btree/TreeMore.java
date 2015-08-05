package com.learning.btree;

import java.util.ArrayList;
import java.util.List;

public class TreeMore {
	
	static class TNode {
		TNode left;
		TNode right;
		int d;
		public TNode(int d) {
			this.d = d;
		}
	}
	
	TNode root = null;
	
	public TNode insert(TNode n, int data) {
		if(n == null) {
			TNode nnode = new TNode(data);
			if(root == null) {
				root = nnode;
			}
			return nnode;
		}
		if(data < n.d) {
			n.left = insert(n.left, data);
		} else {
			n.right = insert(n.right, data);
		}
		return n;
	}
	
	public void treeToList(TNode n, LinkedList list){
		if(n != null) {
			treeToList(n.left, list);
			list.add(n.d);
			treeToList(n.right, list);
		}
	}
	
	public void treeToAllList(TNode n, List<LinkedList> list){
		if(n != null) {
			if(n.left != null || n.right != null) {
				list.add(new LinkedList());
			}
			treeToAllList(n.left, list);
			for(LinkedList llist : list) {
				llist.add(n.d);
			}
			treeToAllList(n.right, list);
		}
	}
	
	public static void main(String[] args) {
		TreeMore more = new TreeMore();
		more.insert(null, 15);
		more.insert(more.root, 11);
		more.insert(more.root, 19);
		more.insert(more.root, 16);
		more.insert(more.root, 24);
		more.insert(more.root, 13);
		LinkedList ll = new LinkedList();
		more.treeToList(more.root, ll);
		//ll.print();
		ll = new LinkedList();
		List<LinkedList> listlist = new ArrayList<>();
		more.treeToAllList(more.root, listlist);
		for(LinkedList ml : listlist) {
			ml.print();
			System.out.println();
		}
	}

}
