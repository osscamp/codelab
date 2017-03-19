package com.learning.re;

import java.util.Iterator;

import com.learning.btree.MaxHeap;

public class ArrayIterator implements Iterable<int[]>{
	
	int[][] a;
	
	public ArrayIterator(int[][] a) {
		this.a = a;
		if(this.a == null) {
			throw new IllegalArgumentException();
		}
	}
	

	@Override
	public Iterator<int[]> iterator() {
		return new Iterator<int[]>(){
			
			int ct = 0;

			@Override
			public boolean hasNext() {
				return ct < a.length;
			}

			@Override
			public int[] next() {
				int[] v = a[ct];
				while(ct < a.length-1 && v.length == 0) {
					ct++;
					v = a[ct];
				}
				return a[ct++];
			}
			
		};
	}
	
	public static void main(String[] args) {
		int[][] data =  {{}, {1,2,3}, {4,5}, {}, {}, {6,7}, {8}, {9,10}, {}, {12}};
		ArrayIterator ai = new ArrayIterator(data);
		Iterator<int[]> itr = ai.iterator();
		StringBuilder sb = new StringBuilder();
		while(itr.hasNext()) {
			for(int i : itr.next()) {
				sb.append(i).append(" ");
			}
		}
		System.out.println(sb.toString());
	}

}
