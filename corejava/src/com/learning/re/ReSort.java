package com.learning.re;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.learning.btree.MaxHeap;
import com.learning.btree.Sort;

/**
 * 
 * @author sushukla
 *
 */
public class ReSort {
	
	public static boolean strMatch(String needle, String hayStack) {
		if(hayStack == null) throw new IllegalArgumentException();
		if(needle == null || needle.length() == 0) return false;
		for(int i=0; i<hayStack.length(); i++ ) {
			if(needle.charAt(0) == hayStack.charAt(i)) {
				int k = 1;
				i++;
				for(; k<needle.length() && i < hayStack.length() && needle.charAt(k) == hayStack.charAt(i); i++,k++){
				}
				if(k == needle.length()) {
					System.out.println("ret "+true);
					return true;
				}
			}
		}
		return false;
	}
	
	public static void dutchSort(int[] a) {
		//int[] a = {0,1,0,0,1,2,0,0};
		int lo = 0;
		int mid = 0;
		int hi = a.length-1;
		while(mid <= hi) {
			if(a[mid] == 0) {
				swap(a, lo, mid);
				lo++;
				mid++;
			} else if(a[mid] == 1) {
				mid++;
			} else {
				swap(a, mid, hi);
				hi--;
			}
		}
		MaxHeap.printArray(a);
	}
	
	public static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}
	
	public static long qsPerf() {
		int[] a = Sort.getRandomArray(100000);
		long startNanos = System.nanoTime();
		sort(a, 0, a.length-1);
		long endNanos = System.nanoTime();
		long result = (endNanos-startNanos)/1000;
		//System.out.println("time taken "+(endNanos-startNanos)/1000+" us");
		return result;
		//MaxHeap.printArray(a);
	}
	
	public static void perfDriver() {
		long totalv = 0;
		int N = 20;
		for(int i=0; i<N; i++) {
			totalv += qsPerf();
		}
		System.out.println("avg time taken "+(totalv/N));
	}
	
	public static void sort(int[] a, int l, int r) {
		if(l<r) {
			int p = partitionLoHi(a, l, r);
			sort(a, l, p-1);
			sort(a, p+1, r);
		}
	}
	
	public static int partition(int[] a, int l, int r) {
		int pivot = a[r];
		int j=l;
		for(int i=l; i<r; i++) {
			if(a[i] < pivot) {
				if(i != j) {
					int t = a[i];
					a[i] = a[j];
					a[j] = t;
				}
				j++;
			}
		}
			a[r] = a[j];
			a[j] = pivot;
		return j;
	}
	
	public static int partitionLoHi(int[] a, int l, int r) {
		//9,11,5,8,12,6
		int lo = l, hi = r+1;
		int pivot = a[l];
		while(lo < hi) {
			while(a[++lo] < pivot) {
				if(lo == r) {
					break;
				}
			}
			while(a[--hi] > pivot) {
				if(hi == l) {
					break;
				}
			}
			if(hi <= lo) {
				break;
			}
			swap(a, lo, hi);
		}
		swap(a, l, hi);
		
		return hi;
	}
	
	
    public static List<Integer> topKFrequent(int[] nums, int k) {
    	class Holder {
    		int freq;
    		int num;
			public Holder(int freq, int num) {
				super();
				this.freq = freq;
				this.num = num;
			}
    		
    	}
        Map<Integer, Integer> mm = new HashMap<>();
        PriorityQueue<Holder> pq = new PriorityQueue<>(k, new Comparator<Holder>() {

			@Override
			public int compare(Holder o1, Holder o2) {
				return Integer.valueOf(o1.freq).compareTo(Integer.valueOf(o2.freq)) ;
			}
		});
        List<Integer> keyq = new ArrayList<>();
        for(int key : nums) {
            Integer freq = mm.get(key);
            if(freq == null) {
            	freq = 0;
            }
            freq++;
            mm.put(key, freq);

        }
        for (int num : mm.keySet()) {
        	int freq = mm.get(num);
	        if(pq.size() < k) {
	        	pq.add(new Holder(freq, num));
	        }
	        else if(freq > pq.peek().freq) {
	    		pq.poll();
	    		pq.add(new Holder(freq, num));
	    	}
        }
        for(Holder h : pq) {
        	keyq.add(h.num);
        }
        System.out.println(keyq);
        return keyq;
    }
	
	public static void main(String[] args) {
		int[] aaa = new int[]{2,1,2,0,2,2};
		MaxHeap.printArray(aaa);
		//qsPerf();
		//perfDriver();
		int[] a = Sort.getRandomArray(7);
		//int[] a = {5,3,0,9,6};
		MaxHeap.printArray(a);
		sort(a, 0, a.length-1);
		MaxHeap.printArray(a);

		//a = new int[]{0,1,2};
		//dutchSort(a);

		//strMatch("ab", "larbabel");
		//topKFrequent(new int[]{1,2,2,2,3,3,1,1,3,3,3,1},2);
	}

}
