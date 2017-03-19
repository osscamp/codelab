package com.learning.re;

import java.util.PriorityQueue;

public class Scratch {
	
    static class Tuple implements Comparable<Tuple>{
        int val;
        int r; //row in matrix
        int c;
        public Tuple(int r, int c, int val) {
            this.r = r; this.c=c; this.val = val;
        }
        public int compareTo(Tuple o) {
            return Integer.valueOf(val).compareTo(o.val);
        }
        public String toString(){
            return ("val "+val+" c "+c+" r "+r);
        }
    }
	public static void kthSmallest(int[][] a,int n,int k){
        PriorityQueue<Tuple> pq = new PriorityQueue<>();
        for(int l=0; l<a.length; l++) {
            if(a[l].length > 0) pq.add(new Tuple(l, 0, a[l][0]));
        }
        int rtCt = 0;
            System.out.println(pq);
        while(!pq.isEmpty()){
            rtCt++;
            Tuple pt = pq.poll();
            if(rtCt >= k) {
                System.out.println("found "+pt.val);
                return;
            }
            if(a[pt.r].length > pt.c+1)
            pq.add(new Tuple(pt.r, pt.c+1, a[pt.r][pt.c+1]));
        }
    }
	
	public static void main(String[] args) {
		int[][] a = {
				{16,28,60,64},
				{22,41,63,91},
				{27,50,87,93},
				{36,78,87,94},
		};
		kthSmallest(a,4, 11);
		
	}

}
