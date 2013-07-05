package com.learning.btree;

public class LinkedList {
	
	static class Node {
		int data;
		Node next;
		public Node(int n){
			this.data = n;			
		}
		
		@Override
		public String toString() {
			return String.valueOf(data);
		}
	}
	
	Node root;
	
	public LinkedList() {}
		
	public void add(int data) {
		Node newNode = new Node(data);
		if (root == null) {
			root = newNode;
			return;
		}
		Node temp = root;
		while (temp.next != null) {
			temp = temp.next;
		}
		temp.next = newNode;
	}
	
	public void addAt(int index, int data) {
		Node newNode = new Node(data);
		Node temp = root;
		int ct = 0;
		while (ct < index && temp.next != null) {
			temp = temp.next;
			ct ++;
		}
		Node nnext = temp.next;
		temp.next = newNode;
		newNode.next = nnext;
		
	}
	
	public void print() {
		Node temp = root;
		while (temp != null) {
			System.out.println(temp.data);
			temp = temp.next;
		}
	}
	
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.add(21);
		list.add(32);
		list.add(43);
		list.addAt(5, 200);
		list.print();
	}

}
