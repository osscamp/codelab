package com.learning.btree;

public class LinkedListLocal {
	
	public void setRoot(Node root) {
		this.root = root;
	}

	public static class Node {
		int data;
		Node next;
		public Node(int n){
			this.data = n;			
		}
		
		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		@Override
		public String toString() {
			return String.valueOf(data);
		}
	}
	
	Node root;
	
	public Node getRoot() {
		return root;
	}
	
	public LinkedListLocal() {}
		
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
	
	public void remove(int data) {
		Node temp = root;
		Node toRemove = null;
		while (temp.next != null) {
			if(temp.next.data == data) {
				toRemove = temp.next;
				break;
			}
			temp = temp.next;
		}
		if(toRemove == null){
			System.out.println("no node found ");
			return;
		}
		Node nnext = toRemove.next;
		temp.next = nnext;
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
	
	public int getAt(int index) {
		Node temp = root;
		int ct = 0;
		while (ct < index && temp != null) {
			temp = temp.next;
			ct ++;
		}
		if(temp == null) {
			throw new IndexOutOfBoundsException();
		}
		return temp.data;
	}
	
	public void print() {
		Node temp = root;
		while (temp != null) {
			System.out.println(temp.data);
			temp = temp.next;
		}
	}
	
	public static void main(String[] args) {
		LinkedListLocal list = new LinkedListLocal();
		list.add(21);
		list.add(32);
		list.add(43);
		list.addAt(5, 200);
		list.print();
	}

}
