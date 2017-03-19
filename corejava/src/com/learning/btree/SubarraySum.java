package com.learning.btree;

import java.util.LinkedList;
import java.util.Queue;

public class SubarraySum {
	
	public static void subSum() {
		int[] a = {4,8,6,11,0,19,2,11,3,4,1};
		int matchsum = 21;
		Queue<Integer> ll = new LinkedList<>();
		int sum = 0;
		int sumlength = 0;
		int maxlength = 0;
		for(int i=0; i<=a.length; i++) {
			if(sum == matchsum) {
				System.out.println("found");
				if(sumlength > maxlength) {
					maxlength = sumlength;
					if(i >= a.length){
						break;
					}
				}
				if(ll.size() > 0) {
					sum -= ll.poll();
					sum+=a[i];
					ll.add(a[i]);
				}else {
					break;
				}
			}
			else if(sum < matchsum) {
				sum+=a[i];
				sumlength++;
				ll.add(a[i]);
			}else{
				while(sum > matchsum && ll.size() > 0) {
					sum -= ll.poll();
					sumlength--;
				}
				sum+=a[i];
				sumlength++;
				ll.add(a[i]);
			}
		}
		System.out.println("maxl "+maxlength);
	}
	
	public static void subSum1() {
		int[] a = {1,2,7,3,5};
		int matchsum = 12;
		Queue<Integer> ll = new LinkedList<>();
		int sum = 0;
		int sumlength = 0;
		int maxlength = 0;
		int st = 0;
		int end = 0;
		for(int i=0; i<a.length; i++) {
			sum+=a[i];
			sumlength++;
			ll.add(a[i]);
			/*if(sum == matchsum) {
				System.out.println("found");
				if(sumlength > maxlength) {
					maxlength = sumlength;
					end = i+1;
					st = end-maxlength+1;
				}
				if(ll.size() > 0) {
					sum -= ll.poll();
					sumlength--;
				}else {
					break;
				}
			}
			else {*/
				while(sum > matchsum && ll.size() > 0) {
					sum -= ll.poll();
					sumlength--;
				}
				if(sum == matchsum) {
					System.out.println("found");
					if(sumlength > maxlength) {
						maxlength = sumlength;
						end = i+1;
						st = end-maxlength+1;
					}
					if(ll.size() > 0) {
						sum -= ll.poll();
						sumlength--;
					}else {
						break;
					}
				}
			//}
		}
		System.out.println("maxl "+(maxlength)+" st "+st+" end "+end);
	}
	
	public static void main(String[] args) {
		subSum1();
	}

}
