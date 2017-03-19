package com.learning.btree;

import java.util.Arrays;

public class PrincetonAnalysis {
	
	public static boolean isSum(int[] a, int [] b, int sum) {
		int al=0, bl = 0;
		int ar = a.length-1; int br = b.length-1;
		while(true) {
			if(br<0 || ar<0 || bl>=b.length || al>=a.length) {
				return false;
			}
			if((a[al] + b[br]) < sum) {
				al++;
			}else if((a[al]+b[br]) > sum) {
				br--;
			}else {
				return true;
			}
			if((b[bl] + a[ar]) < sum) {
				bl++;
			}else if((b[bl]+a[ar]) > sum) {
				ar--;
			}else {
				return true;
			}
		}
	}
	
	//check if a+b+c = 0 a, b, c come from 3 different arrays.
	public static boolean is3Sum(int[] a, int[] b, int[] c) {
		Arrays.sort(a);
		Arrays.sort(b);
		for(int i=0; i<c.length; i++) {
			if(isSum(a, b, -1*c[i])) {
				return true;
			}
		}
		return false;
	}
	
	//a[i] + a[j] = a[i+j].
	public static void isConvolutionSum() {
		int[] a = {2,5,0,6,11,21,10};
		for(int i=0; i<a.length; i++) {
			for(int j=i+1; j<a.length; j++) {
				if((i+j) < a.length && a[i]+a[j] == a[i+j]) {
					System.out.println("found "+a[i+j]);
					return;
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		boolean res = is3Sum(new int[]{5, 9, 6,2}, new int[]{4,7,1,11}, new int[]{-21});
		System.out.println("result "+res);
		isConvolutionSum();
	}

}
