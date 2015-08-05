package com.learning.btree;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ListPractice {
	
	private static class Node{
		int d;
		Node next;
		Node random;
		public Node(int d) {
			this.d = d;
		}
		@Override
		public String toString() {
			return ""+d;
		}
	}
	
	Node head = null;
	
	public Node insertNode(int d) {
		int ctr = 1;
		if(head == null) {
			head = new Node(d);
			return head;
		} else {
			Node temp = head;
			while(temp.next != null) {
				temp = temp.next;
				ctr++;
			}
			int rdm = new Random().nextInt(ctr);
			Node last = new Node(d);
			temp.next = last;
			temp.random = kthLast(rdm);
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
	
	public Node deleteAll(int n) {
		Node curr = head;
		Node prev = curr;
		while(curr != null) {
			if(curr.d == n) {
				if(curr == head) {
					head = curr.next;
				} else {
					prev.next = curr.next;
					curr = curr.next;
					continue; //do not make deleted node as prev
				}
			}
			prev = curr;
			curr = curr.next;
		}
		return head;
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
	
    public Node mergeTwoLists(Node l1, Node l2) {
        if(l1 == null) { 
            return l2; 
        }
        if(l2 == null) { 
            return l1; 
        }
        Node t1 = l1;
        Node t2 = l2;
        Node merged = null;
        Node mergehead = null;
        while(t1 != null && t2 != null) {
            if(t1.d < t2.d) {
                if(merged == null) {
                    merged = t1;
                    mergehead = merged;
                } else {
                    merged.next = t1;
                    merged = t1;
                }
                t1 = t1.next;
            }else {
                if(merged == null) {
                    merged = t2;
                    mergehead = merged;
                } else {
                    merged.next = t2;
                    merged = t2;
                }
                t2 = t2.next;
            }
        }
        if(t1 != null) {
            merged.next = t1;
        }
        if(t2 != null) {
            merged.next = t2;
        }
        return mergehead;
    }
    
    public Node swapPairs() {
        if(head == null) { return head; }
        Node t1 = head;
        Node t2 = t1.next;
        if(t2 == null) { return head; }
        Node t3 = t2.next;
        if(t3 == null) { 
        	t2.next = t1;
        	t1.next = null;
        	this.head = t2;
        	return head; 
        }
        boolean isHead = false;
        while(t3 != null) {
        	t2.next = t1;
        	if(!isHead) {
        		this.head = t2;
        		isHead = true;
        	}
        	//t2 = t3.next; 
        	if(t3.next != null) {
        		t2 = t3.next;
        	}
        	if(t3.next == null) {
        		t1.next = t3;
        	} else {
        		t1.next = t2; //if(t3.next == null) t1.next = t3;
        	}
        	t1 = t3;
        	if(t2 == null) {
        		break;
        	}
        	if(t3.next != null) {
        		t3 = t3.next.next;
        	} else {
        		break;
        	}
        }
        if(t3 == null && t1 != null && t2 != null) {
        	t2.next = t1;
        	t1.next = null;
        }
        return head;
    }
    
    public void removeDup() {
    	Set<Integer> nset = new HashSet<>();
    	Node t = head;
    	Node prev = head;
    	while(t.next != null) {
    		if(nset.contains(t.d)) { //duplicate
    			prev.next = t.next;
    		} else {
    			nset.add(t.d);
    		}
    		prev = t;
    		t = t.next;
    	}
    	
    }
	
	public static void main(String[] args) {
		ListPractice lp = new ListPractice();
		lp.insertNode(1);
		lp.insertNode(2);
		lp.insertNode(3);
/*		lp.insertNode(4);
		lp.insertNode(5);
		lp.insertNode(6);
		lp.insertNode(7);*/
		lp.print();
		lp.swapPairs();
		System.out.println("swappedpairs");
		lp.print();
		//lp.reverse();
		//lp.print();
		//Node mid = lp.findMid();
		//System.out.println("mid "+mid.d);
		//Node del = lp.delete(8);
		//Node del1 = lp.delete(15);
		//System.out.println(del.d+" "+del1.d);
		//lp.print();
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
		
		ListPractice dup = new ListPractice();
		dup.insertNode(3);
		dup.insertNode(11);
		dup.insertNode(6);
		dup.insertNode(9);
		dup.insertNode(3);
		dup.insertNode(14);
		dup.removeDup();
		dup.print();
		
		System.out.println("Remove all---");
		ListPractice dupp = new ListPractice();
		dupp.insertNode(3);
		dupp.insertNode(11);
		dupp.insertNode(3);
		dupp.insertNode(9);
		dupp.insertNode(3);
		dupp.insertNode(14);
		dupp.deleteAll(14);
		dupp.print();
	}

}
