package com.learning.btree;

public class ListPractice {
	
	private static class Node{
		int d;
		Node next;
		public Node(int d) {
			this.d = d;
		}
	}
	
	Node head = null;
	
	public Node insertNode(int d) {
		if(head == null) {
			head = new Node(d);
			return head;
		} else {
			Node temp = head;
			while(temp.next != null) {
				temp = temp.next;
			}
			Node last = new Node(d);
			temp.next = last;
			return last;
		}
	}
	
	public void print() {
		Node temp = head;		
		while(temp != null) {
			System.out.println(temp.d);
			temp = temp.next;
			
		}
	}
	
	public void reverse() {
		Node temp = head;
		Node prev = head;
		Node prev2 = head;
		while(temp != null) {
			prev2 = prev;
			prev = temp;
			temp = temp.next;
			if(prev != head) {
				prev.next = prev2;
			} else {
				head.next = null;
			}
		}	
		head = prev;
	}
	
	public Node findMid() {
		Node slow = head;
		Node fast = head;
		while(fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
	
	public Node delete(int n) {
		Node tmp = head;
		Node ret = null;
		while(tmp.next != null) {
			if(tmp.next.d == n) {
				ret = tmp.next;
				tmp.next = tmp.next.next;
				break;
			}
			tmp = tmp.next;
		}
		return ret;
	}
	
	public Node kthLast(int k) {
		Node fast = head;
		Node slow = head;
		int ctr = 0;
		while(fast.next != null && slow.next != null) {
			fast = fast.next;
			ctr++;
			if(ctr >= k) {
				slow = slow.next;
			}
		}
		if(ctr < k-1) {
			return null;
		}
		return slow;
		
	}
	
	public boolean isLoop() {
		Node fast = head;
		Node slow = head;
		while(fast.next.next != null && slow.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if(fast == slow) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		ListPractice lp = new ListPractice();
		lp.insertNode(5);
		lp.insertNode(8);
		lp.insertNode(15);
		lp.insertNode(2);
		lp.insertNode(21);
		lp.print();
		//lp.reverse();
		//lp.print();
		Node mid = lp.findMid();
		System.out.println("mid "+mid.d);
		//Node del = lp.delete(8);
		//Node del1 = lp.delete(15);
		//System.out.println(del.d+" "+del1.d);
		lp.print();
		Node kthlast = lp.kthLast(1);
		System.out.println("kthl "+kthlast.d);
		
		ListPractice loop = new ListPractice();
		loop.insertNode(4);
		loop.insertNode(6);
		Node n1 = loop.insertNode(49);
		loop.insertNode(32);
		Node n2 = loop.insertNode(21);
		n2.next = n1;
		loop.isLoop();
	}

}
