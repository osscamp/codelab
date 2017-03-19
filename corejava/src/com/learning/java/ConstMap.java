package com.learning.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import com.learning.btree.LinkedListLocal;

public class ConstMap {
	int buckets = 10;
	LinkedListLocal[] bucketHolder;
	List<Integer> randomLookup = new ArrayList<>();
	
	Random rnd = new Random();
	AtomicInteger sz = new AtomicInteger(0);
	public ConstMap(){
		bucketHolder = new LinkedListLocal[10];
	}
	
	public LinkedListLocal initBucket(int bucket) {
		LinkedListLocal ll = bucketHolder[bucket];
		if(ll == null) bucketHolder[bucket] = new LinkedListLocal();
		return bucketHolder[bucket];
	}
	
	public void add(int value) {
		int bucketNum = value%10;
		LinkedListLocal ll =  initBucket(bucketNum);
		ll.add(value);
		sz.incrementAndGet();
		randomLookup.add(value);
	}
	
	public void remove(int value) {
		int bucketNum = value%10;
		LinkedListLocal ll =  initBucket(bucketNum);
		ll.remove(value);
		sz.decrementAndGet();
		//if there are no hash collisions, removal is constant op
		
	}
	
	public int getRandom() {
		int rnum = rnd.nextInt(randomLookup.size());
		return randomLookup.get(rnum);
	}
	
	public static void main(String[] args) {
		ConstMap cm = new ConstMap();
		cm.add(7);
		cm.add(27);
		cm.add(18);
		cm.add(117);
		cm.add(24);
		System.out.println("rndm "+cm.getRandom());
	}

}
