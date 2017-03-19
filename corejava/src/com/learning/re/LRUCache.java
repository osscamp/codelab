package com.learning.re;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * 
The task is to design and implement methods of an LRU cache. The class has two methods get and set which are defined as follows.
get(x)   : Gets the value of the key x if the key exists in the cache otherwise returns -1
set(x,y) : inserts the value if the key x is not already present. If the cache reaches its capacity it should invalidate the least recently used item before inserting the new item.
In the constructor of the class the size of the cache should be intitialized.
 */
class LRUCache {
    
    class Tuple implements Comparable<Tuple>{
    	long c; int value;
    	public Tuple(long ctr, int value){ this.c = ctr; this.value = value;}
		@Override
		public int compareTo(Tuple o) {
			return Long.valueOf(c).compareTo(o.c);
		}
		public String toString(){
			return c+":"+value;
		}
    }
    
    PriorityQueue<Tuple> pq ;
    Map<Integer, Integer> map;
    int N;
    long ctr;
 
    /*Inititalize an LRU cache with size N */
    public LRUCache(int N) {
       //Your code here
       pq = new PriorityQueue<>(N);
       map = new HashMap<>();
       this.N = N;
       ctr = 0l;
    }
    
    /*Returns the value of the key x if 
     present else returns -1 */
    public int get(int x) {
       //Your code here
    	if( map.containsKey(x)) {
    		Iterator<Tuple> ti = pq.iterator();
    		while(ti.hasNext()) {
    			Tuple t = ti.next();
    			if(t.value == x) {
    				ti.remove();
    			}
    		}
    		pq.add(new Tuple(System.nanoTime(), x));
    		return map.get(x);    	
    	}
    	else {
    		return -1;
    	}
    }
    
    /*Sets the key x with value y in the LRU cache */
    public void set(int x, int y) {
       //Your code here
    	if(map.containsKey(x)){
    		Iterator<Tuple> ti = pq.iterator();
    		while(ti.hasNext()) {
    			Tuple t = ti.next();
    			if(t.value == x) {
    				ti.remove();
    			}
    		}
    		pq.add(new Tuple(System.nanoTime(), x));  
    		map.put(x, y);
    	}else{
    		if(map.size() < N) {
    			map.put(x, y);
    			pq.add(new Tuple(System.nanoTime(), x));
    		}else{
    			int t = pq.poll().value;
    			map.remove(t);
    			map.put(x, y);
    			pq.add(new Tuple(System.nanoTime(), x));
    		}
    	}
    }
    
    static int strstr()
    {
    	String str = "For";
    	String target = "GeeksForGeeks";
       //eks geeksfor
       for(int i=0; i<target.length(); i++) {
           int j=0;
           while(i+j < target.length() && j<str.length()
           && target.charAt(i+j) == str.charAt(j)){
               j++;
           }
           if(j >= str.length()){
               return i;
           }
       }
       return -1;
       
    }
    
    public static void main(String[] args) {
		LRUCache lc = new LRUCache(4);
		lc.set(98, 2);
		System.out.println(lc.get(87));
		lc.set(90, 66);
		System.out.println(lc.get(1));
		System.out.println(lc.get(18));
		lc.set(98, 18);
		lc.set(76, 82);
		lc.set(68, 28);

		System.out.println("MM "+lc.map);
		System.out.println("pq "+lc.pq);
		System.out.println(lc.get(98));
		System.out.println(lc.get(4));
		strstr();
	}
}
