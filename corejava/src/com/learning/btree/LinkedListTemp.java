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
	
	public void removeZeroSums() {
		ListNode temp = head;
		ListNode skipStart = null;
		int currSum = 0;
		while(temp != null) {
			currSum += temp.v;
			if(currSum == 0) {
				if(skipStart == null) {
					head = temp.next;
					skipStart = head;
				} else {
					skipStart.next = temp.next;
				}
				skipStart = temp.next;
			} if(temp.v < 0 && currSum > 0) {
				if(skipStart == null) skipStart = head;
				temp = skipStart.next;
				currSum = 0;
				skipStart = temp;
			} else { 
				temp = temp.next;
			}
		}
	}
	
	public static void main(String[] args) {
		LinkedListTemp ll = new LinkedListTemp();
		ll.addNode(1)
		.addNode(6)
		.addNode(-6)
		.addNode(8)
		.addNode(2)
		.addNode(-2)
		.addNode(16)
		.addNode(7)
		.addNode(-23);
		ll.printList();
		ll.removeZeroSums();
		ll.printList();
	}

}
