package com.learning.re;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import com.learning.btree.MaxHeap;
import com.learning.java.SubstringSearch;

public class Prac {
	
	public static String convertBin(String prefix, int n) {
		if(n <= 0) {
			System.out.println(prefix);
			return prefix;
		}
		else{
			
			return convertBin(n%2+prefix,n/2);
		}
	}
	
	public static char toChar(int n) {
		return Character.valueOf((char)('A'+Math.max(n-1,0)));
	}
	
	public static void uniques() {
		int[] a = {1,2,1,1,3,3,5,2,6,4};
		Set<Integer> vals = new HashSet<>();
		Set<Integer> uniques = new HashSet<>();
		for(int i=0; i<a.length; i++) {
			boolean add = vals.add(a[i]);
			if(add) {
				uniques.add(a[i]);
			}else{
				uniques.remove(a[i]);
			}
		}
		System.out.println(uniques);
		
	}
	
	public static void uniquesSorted() {
		int[] a = {1,2,2,5,7,7,8,8};
		if(a.length == 0) { return; }
		if(a.length == 1) { MaxHeap.printArray(a); return; }
		Set<Integer> uniques = new HashSet<>();
		for(int i=0; i<a.length-1; i++) {
			if(a[i] != a[i+1]) {
				uniques.add(a[i+1]);
				if(i == 0) {
					uniques.add(a[i]);
				}
			}else{
				uniques.remove(a[i+1]);
			}
		}
		System.out.println(uniques);
	}
	
	public static void uniquesSortedPrint() {
		//assume prev and next sentinels at start and end of array
		int[] a = {1,1,3,5,5,8};
		if(a.length == 0) { return; }

		int prev = a[0]-1;
		int next = a[0];
		int cur = a[0];
		for(int i=0; i<=a.length; i++) {
			if(i==0) { 
				prev = -1;
				continue;
			}
			else if(i == a.length) {
				cur = a[i-1];
				next = -1;
			}
			else{
				cur = a[i-1];
				next = a[i];
			}
			if(prev != cur && next != cur) {
				System.out.println("uniq "+cur);
			}
			prev = cur;			
		}
 	}
	
	public static void isRotation() {
		String s = "azbarb";
		String r = "barbaz";
		int j = 0, ridx = 0;
		for(int i=0; i<s.length(); ) {
			while(i < s.length() && s.charAt(i) == r.charAt(j)) {
				i++;
				j++;
			}
			if(j == s.length()) {
				System.out.println("identical "); return;
			}else {
				ridx = j;
				if(i == s.length()) {
					break;
				}
				j=0;
				i = i-j+1;
			}
		}
		j = ridx;
		System.out.println(" j "+j+" ");
		int i = 0;
		while(j < r.length() && s.charAt(i) == r.charAt(j)) {
			i++;
			j++;
		}
		if(j == s.length()) {
			System.out.println("full rotation");
		}else{
			System.out.println("not a rotation");
		}
	}
	
	public static void commonDishes(){
	    Map<String, List<String>> ingr = new HashMap<>();
	    ingr.put("Pasta", Arrays.asList("Tomato Sauce", "Onions", "Garlic"));
	    ingr.put("Curry", Arrays.asList("Chicken", "Curry Sauce"));
	    ingr.put("Fried Rice", Arrays.asList("Rice", "Onions", "Nuts"));
	    ingr.put("Salad", Arrays.asList("Spinach", "Nuts"));
	    ingr.put("Sandwich", Arrays.asList("Cheese", "Bread"));
	    ingr.put("Quesadilla", Arrays.asList("Chicken", "Cheese", "Fig"));
	    ingr.put("Frittata", Arrays.asList("Rice", "Onions", "Fig"));
	    
	    Map<String, List<String>> dish = new HashMap<>();
	    Set<String> keys = ingr.keySet();
	    for(String key : keys) {
	        List<String> eIngr = ingr.get(key);
	        for(String each : eIngr) {
	            List<String> v = dish.get(each);
	            if(v == null) {
	            	List<String> list = new ArrayList<String>();
	            	list.add(key);
	                dish.put(each, list);
	            }else{
	                v.add(key);
	            }
	        }
	    }
	    Set<String> dkeys = dish.keySet();
	    for(String key : dkeys) {
	    	if(dish.get(key).size() > 1) {
	    		System.out.println(key+" "+dish.get(key));
	    	}
	    }
	}
	
	public static void sqrt2() {
		double n = 10.0;
		double precision = 1e-16;
		double est = n;
		while(est*est - n > precision*est*est ) {
			est = (n/est + est)/2.0;
		}
		System.out.println("sqrt "+est);
	}
	
	public static void shuffle() {
		int[] a = {1,5,7};
		//fisher-yates shuffle
		Random rd = new Random();
		for(int i=a.length-1; i>= 0; i--) {
			int rnum = rd.nextInt(i+1);
			int tmp = a[i];
			a[i] = a[rnum];
			a[rnum] = tmp;
		}
		MaxHeap.printArray(a);
	}
	
	//[[1,1],2,[1,[2,1]]] sum = 17 ((1+1)*(depth=2) + 2*(depth=1) + 1*2 + (2+1)*3)
	public static void nestedSum() {
		List<Object> n1 = new ArrayList<>();
		List<Object> nn1 = Arrays.asList(1,1);
		Integer nn2 = 2;
		List<Object> nn3 = Arrays.asList(1,Arrays.asList(2,1));
		n1.add(nn1); n1.add(nn2); n1.add(nn3);
		int sum = 0;
		sum = nsum(n1, 0);
		System.out.println("sum "+sum);
		
	}
	
	public static int nsum(Object o, int depth) {
		int sum = 0;
		if(o instanceof List) {
			for(Object oo :((List<Object>)o) ){
				sum += nsum(oo, depth+1);
			}
			return sum;
		}else{
			return ((Integer)o).intValue()*depth;
		}
	}
	
	public static void range() {
		int[] a = {1,2,3,3,3,3,3,3,3};
		int key = 3;
		int l = 0;
		int r = a.length-1;
		while(l <= r) {
			int mid = l+(r-l)/2;
			if(key < a[mid]) {
				r = mid -1;
			}else if(key > a[mid]) {
				l = mid+1;
			}else{
				int ll = l;
				int rr = mid;
				while(ll <= rr) {
					int mmid = ll+(rr-ll)/2;
					if(key > a[mmid]) {
						ll = mmid+1;
					}else{
						rr = mmid -1;
					}
				}
				System.out.println("left final "+ll);
				ll = mid;
				rr = r;
				while(ll <= rr) {
					int mmid = ll+(rr-ll)/2;
					if(key < a[mmid]) {
						rr = mmid - 1;
					}else{
						ll = mmid + 1;
					}
				}
				System.out.println("right final "+rr);
				break;
			}
		}
	}
	
	public static void closestPoints() {
		List<Point> plist = Arrays.asList(new Point(2,5),new Point(3,3),new Point(4,2),new Point(6,1),new Point(4,1));
		//closest 2 points from 0,0
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		for(int i=0; i<plist.size(); i++) {
			Point p = plist.get(i);
			int dist = p.x*p.x+p.y*p.y;
			if(pq.size() < 2) {
				pq.add(dist);
			}else {
				if(pq.peek() > dist) {
					pq.poll();
					pq.add(dist);
				}
			}
		}
		System.out.println(pq);
	}
	
	public static void sortedSq(){
		int[] a = {-7,-6,-2,-1,2,3,4,5};
		int r = a.length-1;
		while(r>=0){
			int rsq = a[r]*a[r];
			int lsq = a[0]*a[0];
			if(r == 0) {
				a[r] = rsq;
			}
			else if(lsq < rsq) {
				a[r] = rsq;
			}else if(lsq >= rsq) {
				int t = a[r];
				a[r] = lsq;
				a[0] = t;
				if(r>1 && Math.abs(a[1]) > a[0]) {
					int tmp = a[0];
					a[0] = a[1];
					a[1] = tmp;
				}
			}
			r--;

		}
		MaxHeap.printArray(a);
	}
	
	public static void strNum() {
		Map<Integer, String> m = new HashMap<>();
		m.put(1,"One"); m.put(2,"Two"); m.put(3,"Three");m.put(4,"Four"); m.put(5,"Five"); m.put(6,"Six"); m.put(7,"Seven"); 
		m.put(8,"Eight"); m.put(9,"Nine"); m.put(10,"Ten"); m.put(11,"Eleven"); m.put(12,"Twelve"); m.put(13,"Thirteen"); 
		m.put(14,"Fourteen"); m.put(15,"Fifteen"); m.put(16,"Sixteen"); m.put(17,"Seventeen"); m.put(18,"Eighteen"); m.put(19,"Nineteen"); 
		m.put(20,"Twenty"); m.put(30,"Thirty"); m.put(40,"Forty"); m.put(50,"Fifty"); m.put(60,"Sixty"); m.put(70,"Seventy"); 
		m.put(80,"Eighty"); m.put(90,"Ninety"); m.put(100,"Hunderd"); m.put(1000,"Thousand"); m.put(1000000,"Million"); m.put(1000000000,"Billion"); 


		int num = 765220;
		int globalmul = 1;
		int rolledmul = 1;

		int pdigit = -1;
		Stack<String> s = new Stack<>();
		while(num > 0) {
			int d = num%10;
			String dval = m.get(d);
			String levelVal = null;
			if(globalmul > 10) {
				levelVal = m.get(globalmul);
				if(levelVal != null && d != 0) {
					s.push(levelVal);
					rolledmul = 1;
					//s.push(dval);
				}

			}if(rolledmul == 10 && d==1) {
				if(pdigit != -1 && !s.isEmpty()) {
					s.pop();
					s.push(m.get(rolledmul+pdigit));
				}
			}
			else{
				if(dval != null) {
					s.push(dval);
				}
				if(d > 0 && pdigit != -1 && !s.isEmpty()) {
					s.pop();
					if(rolledmul > 10) {
						s.push(m.get(rolledmul));
						s.push(dval);
					}else {
						s.push(m.get(d*rolledmul));
					}
				}
			}
			
			globalmul *= 10;
			rolledmul *= 10;
			pdigit = d;
			num /= 10;
			
		}
		StringBuilder sb = new StringBuilder();
		while(!s.isEmpty()) {
			sb.append(s.pop()).append(" ");
			
		}
		System.out.println(sb);
	}
	
	public static void strNum1() {
		Map<Integer, String> m = new HashMap<>();
		m.put(1,"One"); m.put(2,"Two"); m.put(3,"Three");m.put(4,"Four"); m.put(5,"Five"); m.put(6,"Six"); m.put(7,"Seven"); 
		m.put(8,"Eight"); m.put(9,"Nine"); m.put(10,"Ten"); m.put(11,"Eleven"); m.put(12,"Twelve"); m.put(13,"Thirteen"); 
		m.put(14,"Fourteen"); m.put(15,"Fifteen"); m.put(16,"Sixteen"); m.put(17,"Seventeen"); m.put(18,"Eighteen"); m.put(19,"Nineteen"); 
		m.put(20,"Twenty"); m.put(30,"Thirty"); m.put(40,"Forty"); m.put(50,"Fifty"); m.put(60,"Sixty"); m.put(70,"Seventy"); 
		m.put(80,"Eighty"); m.put(90,"Ninety"); m.put(100,"Hunderd"); m.put(1000,"Thousand"); m.put(1000000,"Million"); m.put(1000000000,"Billion"); 


		int num = 111;
		int globalmul = 1;
		int rollingmul = 1;

		int pdigit = -1;
		Stack<String> s = new Stack<>();
		while(num > 0) {
			int d = num%10;

			if(globalmul == 100 || globalmul == 1000 || globalmul == 1000000) {
				if((d == 0 || pdigit == 0 ) && !s.isEmpty()) {
					s.pop();
				}
				s.push(m.get(globalmul));
				rollingmul = 1;
			}
			int curnum = rollingmul == 1 ? d : rollingmul*d+pdigit;
			System.out.println(d+" "+globalmul+" "+rollingmul+" "+curnum);
			if(curnum < 20 && curnum > 0) {
				if(!s.isEmpty()) {
					//s.pop();
				}
				s.push(m.get(curnum));
			}else{
				if(d > 0){
					s.push(m.get(rollingmul*d));
				}
			}
			num = num/10;
			globalmul *= 10;
			rollingmul *= 10;
			pdigit = d;
		}
		StringBuilder sb = new StringBuilder();
		while(!s.isEmpty()) {
			sb.append(s.pop()).append(" ");
			
		}
		System.out.println(sb);
	}
	
	public static void swap() {
		String s = "abcd";
		char[] a = s.toCharArray();
		while(a[3] > a[0]) {
			char t = a[3];
			a[3] = a[0];
			a[0] = t;
			if(a[3] > a[2]) {
				t = a[3];
				a[3] = a[2];
				a[2] = t;				
			}
		}
		System.out.println(String.valueOf(a));
	}
	
	public static void lsdRadix() {
		int L = 2;
		String[] strs = {"RSA","KFD","SDE","WED","HTS","HTC"};
		String[] next = new String[strs.length];
		while(L>=0) {
			int[] idx = new int[256];

			String s = null;
			char[] ca = new char[strs.length];
			for(int i=0; i<strs.length; i++) {
				s = strs[i];
				char c = s.charAt(L);
				ca[i]=c;
				idx[(int)c]++;
			}
			for(int i=1; i<idx.length; i++) {
				idx[i] += idx[i-1];
			}
			for(int i=strs.length-1; i>=0; i--) {
				next[--idx[(int)ca[i]]]  =strs[i];
			}
			strs = next;
			next = new String[strs.length];
			L--;
		}
		for(String s : strs)
		System.out.println(s);
	}
	
	public static void printMatrix() {
		int[][] a = {
				{1,2,3,4},
				{12,13,14,5},
				{11,16,15,6},
				{10,9,8,7}
		};
		int ctr = 0;
		int i = 0;
		int j = 0;
		int level = 0;
		int N = a.length;
		int M = a[0].length;
		while(ctr < M*N) {
			while(i == level && j < M-level) {
				System.out.println(a[i][j]);
				j++; ctr++;
			}
			j--;
			i++;
			while(j == M-level-1 && i<N-level) {
				System.out.println(a[i][j]);
				i++; ctr++;
			}
			i--;
			j--;
			while(i == N-level-1 && j >= level) {
				System.out.println(a[i][j]);
				j--; ctr++;
			}
			j++;
			i--;
			while(i > level && j == level) {
				System.out.println(a[i][j]);
				i--; ctr++;
			}
			level++;
			i=level;
			j=level;
			
		}
	}
	
	    public static int search() {
	        //{15,1,2,3,6,9,13}
	        //{3,6,9,13,15,1,2}
	    	int target = 1;
	    	//int [] a = {15,1,2,3,6,9,13};
	    	int [] a = {5,1,1,2,3,3,4};
	        if(a == null || a.length ==0) { return -1; }
	        int l = 0; int r = a.length - 1;

	        while( l <= r) {
	            int mid = l+(r-l)/2;
	            if(a[mid] == target) {
	            	System.out.println("find "+mid);
	                return mid;
	            } 
	            //rotation in left
	            if(a[mid] < a[l] && a[r] > a[mid]) {
	                if(a[mid] < target && target <= a[r]) {
	                    l = mid + 1;
	                }else {
	                    r = mid -1;
	                }
	            }
	            //rotation in right
	            else if(a[mid] > a[l] && a[mid] > a[r]) {
	                if(a[l] <= target && target < a[mid]) {
	                    r = mid - 1;
	                }else {
	                    l = mid + 1;
	                }                
	            }
	            //reverse
	            else if(a[mid] <= a[l] && a[mid] >= a[r]){
	            	if(target < a[mid] ) l = mid + 1;
	                else if(target > a[mid]) r = mid-1;	            	
	            }
	            //no rotation
	            else {
	                if(target <a[mid] ) r = mid -1 ;
	                else if(target > a[mid]) l = mid + 1;
	                else{
	                	System.out.println("found");
	                }
	            }
	        }
	        return -1;
	    }
	    
	public static void reduceStr(){
		String s = "aaabccbbccc";
        StringBuilder sb = new StringBuilder(s);
        for(int i=sb.length()-1; i>0; i--) {
            char c1 = sb.charAt(i);
            char c0 = sb.charAt(i-1);
            if(c1==c0) {
                sb.deleteCharAt(i);
                sb.deleteCharAt(i-1);
                i = sb.length();
            }
        }
        System.out.println("reduced "+sb);
	}
	
    public static void pangram() {
    	String v = "We promptly judged antique ivory buckles for the prize";
        int[] a = new int['z'-'a'+1];
        String ar = v;
        for (int i=0; i<ar.length(); i++){
            char c = ar.charAt(i);
            c = Character.toLowerCase(c);
            if(c == ' ')continue;
            a[c-'a']++;
        }
        int ct = 0;
        for(int i=0; i<a.length; i++){
            if(a[i]<=0){
                System.out.println("not pangram");
                break;
            } else{
                ct++;
            }
        }
        if(ct == a.length) {
            System.out.println("pangram");
        }
            
    }

	public static void main(String[] args) {
		convertBin("", 13);
		uniques();
		uniquesSorted();
		uniquesSortedPrint();
		isRotation();
		//commonDishes();
		sqrt2();
		shuffle();
		nestedSum();
		range();
		closestPoints();
		sortedSq();
		strNum1();
		swap();
		lsdRadix();
		printMatrix();
		search();
		reduceStr();
		//boyerMoore("ABB", "BAABAABABABB");
		//System.out.println("mval "+val);
		pangram();
	}

}
