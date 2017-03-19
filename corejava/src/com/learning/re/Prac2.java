package com.learning.re;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.learning.btree.MaxHeap;

public class Prac2 {
	
	//length   | 1   2   3   4   5   6   7   8  
	//--------------------------------------------
	//price    | 1   5   8   9  10  17  17  20
	//brute force = find all combinations and find price of each set
	//dyn - find max cost at each len 1,2,3,4
	//formula - max(c[i-1]+c[1], c[i])
	public static void cutRod() {
		int L = 8;
		int[] price = {1,5,8,9,10,17,17,20};;
		int[] r = new int[L+1];
		r[0] = 0;
		for(int i=1; i<=L; i++) {
			int maxp  = 0;

			for(int j=0; j<i; j++){
				maxp = Math.max(r[i-j-1]+price[j], maxp);
			}
			r[i] = maxp;
		}
		System.out.println("max "+r[L]);
	}
	
	public static void knapsack() {
		//w = {1,2,3}
		//v = {105, 102, 101}
		//MAXWT = 4
		//WT ->
		//  0 1 2 3 4
		//0 0 0 0 0 0
		//1 0 105 105 105 105
		//2 0 105 105 207 207
		//3 0 105 105 207 207
		//formula
		//t[i][j] = t[i][j-1] if w[j-1] > j
		//t[i][j] = t[i][j] if w[i]+w[i+1] > W
		
	}
	
    public static void insertIntoSorted() {
    	int[] ar = {2,4,6,8,3};
        if(ar == null || ar.length == 0) {
            return;
        }
        int e = ar[ar.length-1];
        int i=ar.length-1;
        
        while(i>0 && ar[i-1] > e) {
            ar[i] = ar[i-1];
            i--;
            StringBuilder sb = new StringBuilder();
            for(int v : ar){
                sb.append(v).append(' ');
            }
            System.out.println(sb);
        }
        ar[i] = e;
        MaxHeap.printArray(ar);
        
    }
    
    public static void mindiff() {
    	int[] a = {5,0,18,9,3,220,15};
    	int max = -1;
    	int max2 = -1;
    	for(int i=0; i<a.length; i++) {
    		if(a[i] > max) {
    			max2 = max;
    			max = a[i];
    		}else if(a[i] > max2) {
    			max2 = a[i];
    		}
    	}
    	System.out.println(max +" "+max2);
    }
    
public static void lis(){
    int[] a = {9,7,2,11,6,8,13,17,3};
    int sz = a.length;
    int[] lis = new int[sz];

    lis[0] = a[0];
    int k = 1;
    for(int i=1; i<a.length; i++) {
        if(a[i] > lis[k-1]) {
            lis[k++] = a[i];
        }else{
            //find smallest in lis[i] > a[i]
            int rep = findreplaceidx(a[i], lis, 0, k);
            System.out.println("rep "+rep);
            if(rep == -1) rep = 0;
            lis[rep] = a[i];
        }
    }
    for(int aa : lis)
    System.out.println(aa);
    }

	public static int findreplaceidx(int n, int[] a, int l, int r) {
		int mid = 0;
	    while(l<=r) {
	        mid = l+(r-l)/2;
	        if(n < a[mid]) {
	            r = mid - 1;
	        }else if(n > a[mid]) {
	            l = mid + 1;
	        }else{
	            return mid;
	        }
	    }
	    int ceil = a[mid] >= n? mid : Math.min(a.length-1, mid+1);
	    return ceil;
	}

	public static void partition(){
		int [] a= {12,11,-13,-5,6,-7,5,-3,-6};
		int pivot = 0;
		int j=0;
		for(int i=0; i<a.length; i++) {
			if(a[i] < pivot) {
				int t = a[i];
				a[i] = a[j];
				a[j] = t;
				j++;
			}
		}
		MaxHeap.printArray(a);
	}
	
	public static boolean isPalindrome(String s) {
		int i = 0;
		int j = s.length()-1;
		while(i < j) {
			if(s.charAt(i) != s.charAt(j)) {
				return false;
			}
			
			i++;
			j--;
		}
		return true;
	}
	
	public static String reverse(String s) {
		return new StringBuilder(s).reverse().toString();
	}
	
	//["bag", "gab", "racecar", "hello"]
	//result ["bag", "gab", "racecar"]
	public static List<String> findPalindromePairs(List<String> list){
		Set<String> rs = new HashSet<>();
		Set<String> out = new HashSet<>();
		for(String liststr : list) {
			String rev = reverse(liststr);
			rs.add(rev);
			if(rs.contains(liststr)){
				out.add(liststr);
				out.add(rev);
			}
		}
		System.out.println(out);
		return new ArrayList<>(out);
	}
	
	public static void greatestProductPair() {
		int[] a = {10,3,12,19,2,0,7,8,18,15,5,24,39};
		Arrays.sort(a);
		for(int i=a.length-1; i>0; i--) {
			int val = a[i];
			int r = i-1;
			int l= 0;
			while(a[r] > val/2+1) {
				r--;
			}
			while(r > l) {
				int pr = a[r]*a[l];
				if(pr == val) {
					System.out.println("max product "+val);
					return;
				}
				else if(pr < val) {
					l++;
				}else {
					r--;
				}
			}
		}
		System.out.println("no product found");
		
	}
	
	
	
	public static void main(String[] args) {
		cutRod();
		insertIntoSorted();
		mindiff();
		lis();
		partition();
		System.out.println(isPalindrome("aca"));
		findPalindromePairs(Arrays.asList("bag", "gab", "racecar", "hello"));
		greatestProductPair();
	}

}
