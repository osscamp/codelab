package com.learning.java;

import com.learning.btree.LinkedListLocal;
import com.learning.btree.LinkedListLocal.Node;

//set containing ints 0 - N
public class IntSet {
	
	//create k (k~20%N) buckets. keep a linked list for each bucket. keep mapping of hash ranges to buckets in array
	private int range;
	private int k;
	private LinkedListLocal[] buckets;
	
	public IntSet(int range) {
		this.range = range;
		k = Math.max(1, this.range/5);
		buckets = new LinkedListLocal[k];
	}
	
	public boolean add(int n) {
		int bucket = n%k;
		LinkedListLocal ll = buckets[bucket];
		if(ll == null) {
			ll = new LinkedListLocal();
			ll.add(n);
			buckets[bucket] = ll;
			return true;
		} else {
			Node t = ll.getRoot();
			while(t != null) {
				if(t.getData() == n) {
					return false;
				}
				t = t.getNext();
			}
			ll.add(n);
			return true;
		}
	}
	
	public boolean remove(int n) {
		int bucket = n%k;
		LinkedListLocal ll = buckets[bucket];
		if(ll == null) {
			return false;
		} else {
			Node t = ll.getRoot();
			while(t != null) {
				if(t.getNext() == null && t.getData() == n) {
					ll.setRoot(null);
				}
				else if(t.getNext().getData() == n) {
					t.setNext(t.getNext().getNext());
					return true;
				}
				t = t.getNext();
			}
			return false;
		}
	}
	
	public boolean exists(int n) {
		int bucket = n%k;
		LinkedListLocal ll = buckets[bucket];	
		if(ll == null) {
			return false;
		} else {
			Node t = ll.getRoot();
			while(t != null) {
				if(t.getData() == n) {
					return true;
				}
				t = t.getNext();
			}
			return false;		
		}
	}
	
	public static void main(String[] args) {
		IntSet iset = new IntSet(500);
		iset.add(27);
		iset.add(29);
		iset.add(291);
		boolean aa = iset.add(27);
		iset.remove(27);
		 aa = iset.add(27);
		 //iset.add(127);
		System.out.println("exist "+iset.exists(27));
	}

}
