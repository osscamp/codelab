package com.learning.java;

/** 
 * stack using LinkedList
 * @author sushukla
 *
 */
public class StackImpl<T> {
	
	class N {
		N next;
		N prev;
		T val;
		public N (N nxt, N prev, T v) {
			this.next = nxt;
			this.val = v;
			this.prev = prev;
		}
	}
	
	N head = null;
	
	public void add(T nn) {
		if(head == null) {
			head = new N(null, null, nn);
		}else{
			N tmp = head;
			while(tmp.next != null){
				tmp = tmp.next;
			}
			N newN = new N(null, null, nn);
			tmp.next = newN;
		}
	}

}
