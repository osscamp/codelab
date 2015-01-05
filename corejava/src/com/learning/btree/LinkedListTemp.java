package com.learning.btree;

public class LinkedListTemp {
	
	static class ListNode {
		public ListNode(int v) {
			this.v = v;
		}
		int v;
		ListNode next;
	}
	ListNode head;
	
	public LinkedListTemp addNode(int v) {
		ListNode n = new ListNode(v);
		if(head == null) {
			head = n;
			return this;
		}
		ListNode tnode = head;
		while(tnode.next  != null) {
			tnode = tnode.next;
		}
		tnode.next = n;
		return this;
	}
	
	public void deletenode(int v) {
		ListNode tnode = head;
		if(head.v == v) {
			head = head.next;
			return;
		}
		while(tnode != null && tnode.next != null) {
			if(tnode.next.v == v) {
				tnode.next = tnode.next.next;
			}
			tnode = tnode.next;
		}
	}
	
	public void insertNode(int position) {
		
	}
	
	public void kthFromLast(int k) {
		ListNode tnode = head;
		int ctr = 0;
		while(tnode != null && ctr < k) {
			tnode = tnode.next;
			ctr++;
			System.out.println(ctr);
			if(tnode == null){
				System.out.println("out of bounds");
				return;
			}
		}
		ListNode tnodeSlow = head;
		while(tnode != null) {
			tnode = tnode.next;
			tnodeSlow = tnodeSlow.next;
		}
		System.out.println(k+" from last:"+tnodeSlow.v);
	}
	
	public void printList() {
		ListNode tnode = head;
		StringBuilder sb = new StringBuilder();
		while(tnode  != null) {
			sb.append(tnode.v).append(" ");
			tnode = tnode.next;
		}
		System.out.println(sb);
	}
	
	public void reverseList() {
		ListNode tnode = head;
		ListNode two = tnode.next;
		tnode.next = null;
		while(two != null) {
			ListNode twonext = two.next;
			two.next = tnode;
			tnode = two;
			two = twonext;
		}
		head = tnode;
	}
	
	public void printSpiral(){
		int[][] a = {{1,2,3,4,21},{5,6,7,8,22},{9,10,11,12,23},{13,14,15,16,24},{31,34,38,39,41}};
		int level = 0;
		int n = a[0].length;
		int r = 0;
		int c = 0;
		while(n > r) {			
			while(c < n) {
				System.out.println(a[r][c]);
				c++;
			}
			c--;
			r++;
			while(r < n) {
				System.out.println(a[r][c]);
				r++;			
			}
			r--;
			c--;
			while(c >= level) {
				System.out.println(a[r][c]);
				c--;
			}
			c++;
			r--;
			while(r >= level) {
				System.out.println(a[r][c]);
				r--;
			}
			n--;
			r+=2;
			c++;
			level++;
		}
	}
	
	public static void main(String[] args) {
		LinkedListTemp ll = new LinkedListTemp();
		ll.addNode(2)
		.addNode(5)
		.addNode(7)
		.addNode(89)
		.addNode(23)
		.addNode(65);
		ll.deletenode(23);
		ll.printList();
		ll.kthFromLast(4);
		ll.reverseList();
		ll.printList();
		ll.printSpiral();
	}

}
