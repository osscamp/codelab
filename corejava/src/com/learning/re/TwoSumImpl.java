package com.learning.re;

import java.util.SortedSet;
import java.util.TreeSet;
//if test vol is very high, it can be made O(1) by storing all possible sums so far
//if both are moderate, add could be O(log n) and lookup O(n);
//if 
public class TwoSumImpl{
    private SortedSet<Integer> sset = new TreeSet<Integer>();
    public void store(int input){
    //store in a sorted set O(logn)
    	sset.add(input);
    }
    
    //test is O(n).
    public boolean test(int val) {
        int l = 0;
        int r = Math.max(0, sset.size() - 1); 
        Integer[] a = sset.toArray(new Integer[r+1]);
        while(l < r) {
            if(val < a[l] + a[r]) {
                r--;
            } else if(val >  a[l] + a[r]) {
                l++;
            } else {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
    	TwoSumImpl tsImpl = new TwoSumImpl();
    	tsImpl.store(10);
    	tsImpl.store(5);
    	tsImpl.store(-5);
    	System.out.println("contains sum "+tsImpl.test(5));
	}
}          
