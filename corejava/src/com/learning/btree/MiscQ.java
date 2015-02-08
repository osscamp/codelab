package com.learning.btree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeMap;

public class MiscQ {
	
	
	int[] keys = new int[10];
	int[] vals = new int[10];
	int ctr = 0;
	int vsum = 0;
	
	public int randomWeighted(int k, int v) {
		keys[ctr] = k;
		vals[ctr] = v;
		ctr++;
		vsum += v;
		int ss = 0;
		int rdm = new Random().nextInt(vsum) + 1;
		int retkey = 0;
		for(int i = 0; i<vals.length; i++) {
			ss += vals[i];
			if(ss >= rdm) {
				retkey = i;
				break;
			}
		}
		//System.out.println("returning index "+retkey+ " key "+keys[retkey]);
		return keys[retkey];
	}
	
	public int randomWeightSample() {
		int vsum = 0;
		for(int v: vals) {
			vsum += v;
		}
		int ss = 0;
		int rdm = new Random().nextInt(vsum) + 1;
		int retkey = 0;
		for(int i = 0; i<vals.length; i++) {
			ss += vals[i];
			if(ss >= rdm) {
				retkey = i;
				break;
			}
		}
		//System.out.println("returning index "+retkey+ " key "+keys[retkey]);
		return keys[retkey];		
	}
	
	public void sumSquares(int n) {
		int sqt = n;
		int tmp = n;
		while(sqt > 0) {
			sqt = (int)Math.sqrt(tmp);
			System.out.println(sqt);
			int diff = tmp - (sqt*sqt);	
			tmp = diff; 
		}
	}
	
	public int convertToInt(String sNumber) {
		if(sNumber == null || sNumber.length() == 0) {
			return -1;
		}
		char[] carray = sNumber.toCharArray();
		int number = 0;
		for(int i=carray.length - 1, j=1; i>=0; i--,j*=10) {
			char temp = carray[i];
			int dig = temp-'0';
			if(number > Integer.MAX_VALUE - j*dig) { System.out.println("overflow ");return -1; }
			number = number+j*dig;
		}
		System.out.println("conv "+number);
		return number;
	}
	
	//TODO incomplete
	public void findCountries() {
		System.out.println("\n\n");
		int[][] m = {{1,1,0},{0,1,1},{0,1,0}};
		int N = m.length;
		int country = 1;
		int i = 0;
		int j = 0;
		int t = m[i][j];
		while(i < N && j < N) {
			if(j+1 < N && m[i][j+1] == t) {
				j++;
				if(i+1 < N && m[i+1][j] == t) {
					i++;
				}
			} else {
				country++;
				i++;
				j++;
			}
		}
		System.out.println(country);
 	}
	
	
	public void arraySum() {
		int[] a = {7,3,89,4};
		int sum = 0;
		for(int v:a) {sum += v;}
		for(int i=0; i<a.length; i++) {
			a[i] = sum - a[i]; 
		}
		System.out.println(Arrays.toString(a));
	}
	
	public void findDuplicatePairs() {
		String[][] pairs = {{"a.com","A"},{"b.com","B"},{"c.com","B"},{"d.com","A"},{"e.com","A"}};
		Map<String, String> map = new HashMap<String, String>();
		for(int i=0; i<pairs.length; i++) {
			String key = pairs[i][0];
			String val = pairs[i][1];
			map.put(key, val);
		}
		List<String> pdup = new ArrayList<String>();
		for(String key:map.keySet()) {
			List<String> dup = new ArrayList<String>();
			if(pdup.contains(key)) {continue;}
			String v = map.get(key);
			dup.add(key);
			for(String ikey:map.keySet()) {
				if(dup.contains(ikey)) {continue;}
				String iv = map.get(ikey);
				if(v.equals(iv)) {
					dup.add(ikey);
				}
			}
			System.out.println(dup);
			pdup = dup;

		}
	}
	
	public void findAppts() {
		int[][] a1 = {{1,5},{10,14},{19,20},{21,24},{27,30}};
		int[][] a2 = {{3,5},{12,15},{18,21},{23,24}};
		int l1 = a1.length;
		for(int i=0; i<l1-1&&i<a2.length-1; i++) {
			int end = Math.max(a1[i][1], a2[i][1]);
			
			int startnext = Math.min(a1[i+1][0], a2[i+1][0]);			
			//int end1 = Math.max(a1[i+1][1], a2[i+1][1]);
			
			if(end == startnext)System.out.println("start "+end+" end "+startnext);
			System.out.println("start "+(end+1)+" end "+(startnext-1));
		}
	}
	
	private static class CustomEntry implements Comparable<CustomEntry>{
		String key;
		Integer value;
		public CustomEntry(String key, Integer value) {
			this.key = key;
			this.value = value;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "("+key+","+value+")";
		}

		@Override
		public int compareTo(CustomEntry o) {
			if(o == null) {
				return 1;
			} else if (value == null) {
				
				return -1;
			} else {
				//if(key.equals(o.key) ) {return 0;}
				return value.compareTo(o.value);
			}
		}

	}
	
	public void countTopFiveWords() {
		String txt = "Now and these two now these and are these now and or and the or ";
//				"What if I need only what the K largest statuses are? "+
//				"How would you implement how them if there some millions of machines? "+
//				"How would you implement the same if how there are millions of processors on a single machine ";
		String[] arr = txt.split("\\s+");
		PriorityQueue<CustomEntry> minHeap = new PriorityQueue<>(5);
		Map<String, Integer> map = new TreeMap<String, Integer>();
		for(String s: arr) {
			s = s.toLowerCase();
			Integer it = map.get(s);
			if(it != null) {
				it = it + 1;
				map.put(s, it);
				addToHeap(minHeap, new CustomEntry(s, it));
			} else {
				map.put(s, 1);
				//addToHeap(minHeap, new CustomEntry(s, 1));
			}
		}
		System.out.println(map);
		CustomEntry e = null;
		while((e = minHeap.poll()) != null) {
			System.out.println(e.key+" freq "+e.value);
		}
		
	}
	
	public static void addToHeap(PriorityQueue<CustomEntry> minHeap, CustomEntry ce) {
		Iterator<CustomEntry> ho = minHeap.iterator();
		while(ho.hasNext()) {
			CustomEntry cee = ho.next();
			if(cee.key.equals(ce.key)) {ho.remove();break;}
		}
		if(minHeap.size() < 5) {
			minHeap.add(ce);
		} else {
			minHeap.poll();
			minHeap.add(ce);	
		}		
	}
	
	public static void main(String[] args) {
		//insertionSort(new int[]{5,2,-1,6,12,1,0});
		MiscQ t = new MiscQ();
		//t.insertNode(null, 50);


		t.randomWeighted(1, 2);
		t.randomWeighted(2, 2);
		t.randomWeighted(3, 4);
		t.randomWeighted(4, 10);
		int a4s = 0;
		for(int i=0; i<18; i++) {
			if(t.randomWeightSample() == 2) a4s++;
		}
		System.out.println(a4s+"/18");
		t.sumSquares(43);
		t.findCountries();
		t.convertToInt("2147483647");
		t.arraySum();
		t.findDuplicatePairs();
		t.findAppts();
		t.countTopFiveWords();
	}

}
