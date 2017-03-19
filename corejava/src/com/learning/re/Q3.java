package com.learning.re;

public class Q3 {
	
	/**
	 * look and say pattern
	 */
	public static void pattern(){
		int n = 9;
		StringBuilder sb = new StringBuilder();
		StringBuilder sbnext = new StringBuilder();

		sb.append("11");
		System.out.println(sb);
		for(int i=1; i<n; i++) {
			char prev = sb.charAt(0);
			int ct = 1;
			for(int j=1; j<sb.length(); j++){
				char next = sb.charAt(j);
				if(next == prev) {
					ct++;
				}else{
					sbnext.append(ct).append(prev);
					ct = 1;
				}
				prev = next;
			}
			sbnext.append(ct).append(prev);
			System.out.println(sbnext);
			sb = sbnext;
			sbnext = new StringBuilder();
		}
	}
	
	public static void min(int[] a) {
	    int l = 0;
	    int r = a.length-1;
	    boolean found = false;
	    while(l <= r) {
	        int mid = l+(r-l)/2;
	        if(mid>0 && mid < a.length-1 && 
	        a[mid-1] > a[mid] &&
	        a[mid+1] > a[mid]) {
	            System.out.println("min: "+a[mid]);
	            found = true;
	            break;
	        }
	        if(a[l] > a[mid]) {//rotation in left
	            r = mid - 1;
	        }else if(a[r] < a[mid]) {//roatation in right
	            l = mid + 1;
	        }else{//no rotation
	        	if(r==a.length-1 && r>0) {
	        		if(a[r] < a[r-1]) {
	        			found = true;
	        			System.out.println("min 2: "+a[mid]);
	        			break;
	        		}
	        	}
	            break;
	        }
	    }
	    if(!found) {
	    	System.out.println("min 3: "+a[l]);
	    }
	}
	
	public static void main(String[] args) {
		pattern();
		min(new int[]{3,4,7,10,13,15,1});
	}

}
