package com.learning.btree;

public class ObjectCounter {
	
	public ObjectCounter(int count) {
		super();
		this.count = count;
	}

	int count = 0;

	public int getCount() {
		return count;
	}

	public ObjectCounter setCount(int count) {
		this.count = count;
		return this;
	}

}
