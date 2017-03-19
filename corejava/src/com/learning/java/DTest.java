package com.learning.java;

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

import com.learning.btree.MaxHeap;

public class DTest {
	
	//Given a sorted array and a value x, the ceiling of x is the smallest element in array greater than or equal to x, and the floor 
	//is the greatest element smaller than or equal to x. Assume than the array is sorted in non-decreasing order. 
	//Write efficient functions to find floor and ceiling of x.
	public static void floorCeil() {
		int[] a = {2,9,12,15,19,22,27,28,31,43};
		int k = 41;
		int l = 0;
		int r = a.length-1;
		int mid = 0;
		boolean b = false;
		while(l <= r) {
			mid = l+(r-l)/2;
			if(k < a[mid]) {
				r = mid - 1;
			} else if(k > a[mid]) {
				l = mid + 1;
			} else {
				b = true;
				break;
			}
		}

		if(b){
			System.out.println("flr "+a[mid]+" cel "+a[mid]);
		}else{
			if(a[0] > k) {
				System.out.println(" cel "+a[mid]);
			}else if(a[a.length-1] < k) {
				System.out.println(" flr "+a[a.length-1]);
			}else{
				System.out.println("flr "+a[r]+" cel "+a[l]);
			}
		}
	}
	
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null) {
            return -1.0;
        }
        int i=0, j=0, k=0;
        int TL = nums1.length+nums2.length;
        boolean odd = (TL % 2 != 0);
        double med = -1.0;
        int[] merged = new int[TL];
        while(i < nums1.length && j < nums2.length) {
            if(nums1[i] < nums2[j]) {
                merged[k] = nums1[i];
                med = incOrMedian(merged, k, TL, odd);
                k++;
                i++;
                if( med != -1.0) {
                    return med;
                } 
            }else{
                merged[k] = nums2[j];
                med = incOrMedian(merged, k, TL, odd);
                k++;
                j++;
                if( med != -1.0) {
                    return med;
                } 
            }
        }
        while(j < nums2.length) {
            merged[k] = nums2[j];
            med = incOrMedian(merged, k, TL, odd);
            k++;
            j++;
            if( med != -1.0) {
                return med;
            }            
        }
        while(i < nums1.length) {
            merged[k] = nums1[i];
            med = incOrMedian(merged, k, TL, odd);
            k++;
            i++;
            if( med != -1.0) {
                return med;
            }            
        }
        return med;
    }
    
    public static double incOrMedian(int[] merged, int k, int TL, boolean isOdd) {
        double rt = -1.0;
        if(isOdd) {
            if(k == TL/2) {
                return merged[k]*1.0;
            }
            return rt;
        } else {
            if(k == TL/2) {
                return (merged[k] + merged[k-1])*1.0/2;
            }
            return rt;
        }
    }
    
    public static int[] twoSum2(int[] nums, int target) {
		long st = System.nanoTime();
        try {
			if(nums == null) return null;
			int[] rv = new int[2];
			Map<Integer, Integer> set = new HashMap<>();
			for(int i=0; i<nums.length; i++) {
				if(set.containsKey(target-nums[i])) {
					rv[0] = set.get(target-nums[i]);
					rv[1] = i;
				}else{
					set.put(nums[i], i);
				}
			}
			System.out.println("idx "+nums[rv[0]]+" "+nums[rv[1]]);

			return rv;
        } finally {
			System.out.println("two s time "+(System.nanoTime()-st)*1.0/1000_000);
		}
    }
    
    public static int[] twoSum(int[] nums, int target) {
		long st = System.nanoTime();
		int[] rv = new int[2];
        try {
			if(nums == null) return null;
			DHolder[] a = new DHolder[nums.length];
			for(int i = 0; i< nums.length; i++) {
			    a[i] = new DHolder(nums[i], i);
			}
			//quicksort
			quicksort(a, 0, nums.length-1);
			int i = 0, j = a.length-1;
			while(i < j) {
			    if(a[i].data+a[j].data < target) {
			        i++;
			    } else if(a[i].data+a[j].data > target) {
			        j--;
			    } else {
			        rv[0] = a[i].idx;
			        rv[1] = a[j].idx;
			        return rv;
			    }
			}
			
			return rv;
		} finally {
			System.out.println("idx "+nums[rv[0]]+" "+nums[rv[1]]);
			System.out.println("two s time "+(System.nanoTime()-st)*1.0/1000_000);
		}
    }
    
    static class DHolder {
        int data;
        int idx;
        public DHolder(int data, int idx) {
            this.data = data;
            this.idx = idx;
        }
    }
    
    public static void quicksort(DHolder[] a, int l, int r) {
        if(l < r) {
            int p = partition(a, l ,r);
            quicksort(a, l, p-1);
            quicksort(a, p+1, r);
        }
    }
    
    public static int partition(DHolder[] a, int l, int r) {
        int pivot = a[r].data;
        int i=l, j=l;
        for(; i<r; i++) {
            if(a[i].data < pivot) {
                DHolder t = a[i];
                a[i] = a[j];
                a[j] = t;
                j++;
            }
        }
        DHolder tmp = a[r];
        a[r] = a[j];
        a[j] = tmp;
        return j;
    }
    
    public static int lengthOfLongestSubstring(String s) {
    	long st = System.nanoTime();
        if(s == null || s.isEmpty()) return 0;
        int maxl = 0;
        for(int k = 0; k< s.length()-maxl; k++) {
            Set<Character> visited = new HashSet<>();
            for(int i = k; i< s.length(); i++){
                char c = s.charAt(i);
                boolean b = visited.add(c);
                if(visited.size() > maxl){
                    maxl = visited.size();
                }
                if(!b) {
                    break;
                }
            }
            
        }
        System.out.println("max l"+maxl+" tm "+(System.nanoTime()-st)/1000);
        return maxl;
    }
    
    public static int lengthOfLongestSubstring1(String s) {
    	long st = System.nanoTime();
        if(s == null || s.isEmpty()) return 0;
        int maxl = 0;
        int ct = 0;
        int i = 0;
        Map<Character, Integer> map = new HashMap<>();
        while(i < s.length()) {
        	char c = s.charAt(i);
        	Integer prev = map.put(c, i);
        	ct++;
        	if(prev != null) {        
        		ct = Math.min(i-prev, ct);
        	}
    		if(ct > maxl) {
    			maxl = ct;
    		}
        	i++;
        }

        System.out.println("max l "+maxl+" tm "+(System.nanoTime()-st)/1000);
        return maxl;
    }
    
    public static String longestPalindrome(String s) {
        int[] pi = new int[2];
        lp(s, 0, s.length()-1, pi);
        String palin = s.substring(pi[0], pi[1]);
        System.out.println("PALINDROME "+palin);
        return palin;
    }
    
    public static void lp(String s, int l, int r, int[] pi) {
        if( l <= r ) {
            int mid = l + (r-l)/2;
            int k = mid - 1, j = mid + 1;
            while(k >= 0 && j<s.length()) {

                if(s.charAt(k) == s.charAt(j)) {
                	if(j+1-k > pi[1]-pi[0]) {
	                    pi[0] = k;
	                    pi[1] = j+1;
                	}
                } else {
                    break;
                }
                k--; j++;
            }
            lp(s, l, mid - 1, pi);
            lp(s, mid + 1, r, pi);
        }
    }
    
    public static void permuteString(String prefix, String s) {
        if(s.length() == 0) {
            System.out.println(prefix);
        }
        for(int i=0; i<s.length(); i++) {
            permuteString(prefix+s.charAt(i), s.substring(0,i)+s.substring(i+1));
        }
    }
    
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> iList = new ArrayList<>();
        List<Integer> dlist = new ArrayList<>();
        for(Integer n: nums) {
        	dlist.add(n);
        }
        List<Integer> pre = new ArrayList<>();
        permuteList(pre, dlist, iList);
        return iList;
    }
    
    public static void permuteList(List<Integer> pre, List<Integer> list, List<List<Integer>> result) {
        if(list.size()==0) {
            result.add(pre);
        }
        for(int i=0; i<list.size(); i++) {
            List<Integer> t1 = new ArrayList<>(pre);
            t1.add(list.get(i));
            List<Integer> t2 = new ArrayList<>(list.subList(0,i));
            t2.addAll(list.subList(i+1, list.size()));
            permuteList(t1, t2, result);
        }
    }
    
    static class ListNode {
    	 int val;
    	 ListNode next;
    	 ListNode(int x) { val = x; }

    }
    
    static class NodeComp implements Comparator<ListNode>{

		@Override
		public int compare(ListNode o1, ListNode o2) {
   		 if (o1 != null && o2 == null) {
			 return 1;
		 } else if (o1 == null && o2 != null) {
			 return -1;
		 } else if(o1 == null && o2 == null) {
			 return 0;
		 }
   		 else{
			 return o1.val > o2.val ? 1 : o1.val < o2.val ? -1 : 0;
		 }
		}
    	
    }
    
    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode merged = null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, new NodeComp());
        for(ListNode n : lists) {
            if(n != null) {
                pq.add(n);
            }
        }
        while(!pq.isEmpty()) {
            ListNode min = pq.poll();
            if(merged == null) {
                merged = min;
            }
            if(min.next != null) {
                pq.add(min.next);                
            }
            min.next = pq.peek();
        }
        return merged;
    }
    
    static class Holder implements Comparable<Holder>{
    	int data;
    	int i;
    	int j;
		@Override
		public int compareTo(Holder o) {
			return o != null ? (this.data > o.data) ? 1 : (this.data < o.data) ? -1 : 0 : 1;
		}
		public Holder(int data, int i, int j) {
			super();
			this.data = data;
			this.i = i;
			this.j = j;
		}
    	
    }
    
    public static int kthLargest(int[][] sa, int k) {
    	if(sa == null || sa.length == 0) { return -1; }
    	int rows = sa.length;
    	PriorityQueue<Holder> pq = new PriorityQueue<>(rows);
    	for(int i=0; i<rows; i++) {
    		pq.add(new Holder(sa[i][0], i, 0));
    	}
    	int c = 0;
    	int kth = -1;
    	while(!pq.isEmpty()) {
    		Holder p = pq.poll();
    		c++;
    		System.out.println("ranked "+c+" "+p.data);

    		if(c >= k) {
    			System.out.println("kth "+p.data);
    			kth = p.data;
    			break;
    		}
    		int ii = p.i;
    		int jj = p.j;
    		if(jj+1 < sa[ii].length) {
    			Holder n = new Holder(sa[ii][jj+1], ii, jj+1);
    			pq.add(n);
    		}
    	}
    	return kth;
    }
    
	public static void testMerge() {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(5);
		l1.next.next = new ListNode(8);
		
		ListNode l2 = new ListNode(3);
		l2.next = new ListNode(7);
		
		ListNode l3 = new ListNode(0);
		l3.next = new ListNode(9);
		l3.next.next = new ListNode(12);
		
		ListNode[] lists = new ListNode[3];
		lists[0] = l1;
		lists[1] = l2;
		lists[2] = l3;
		mergeKLists(lists);
	}
	
	public static void perms(String prefix, String s) {
		if(s.length() == 0){
			System.out.println(prefix);
		}
		for(int i=0; i<s.length(); i++) {
			perms(prefix+" "+s.charAt(i), s.substring(0,i) + s.substring(i+1));
		}
	}
	
	public static void distribSpace(String s) {
		//for(int chunk=0; chunk<s.length(); chunk++) {
		int chunk=0;
			for(int i=0; i<s.length()-chunk; i++) {
				String v = s.substring(0,i+chunk)+" "+s.substring(i+chunk);
				System.out.println(v);
			}
		//}
	}
	
	public static void findMatchedWords() {
		List<String> dict = Arrays.asList("zyy","zyx");
		List<String> out = new ArrayList<>();
		String pattern = "abc";
		char[] mapping = new char['z'+1];
		char r = 'z';
		for(char cc = 'a'; cc <= 'z'; cc++) {
			mapping[cc] = r;
			r--;
		}
		for(String s : dict) {
			if(s.length() != pattern.length()) continue;
			int len = 0;
			for(int i=0; i<s.length(); i++) {
				char cc = s.charAt(i);
				char v = mapping[cc];
				if(pattern.charAt(i) == v) {
					len++;
				}
				if(len == pattern.length()) {
					out.add(s);
					break;
				}
			}
		}
		System.out.println(out);
	}
	
	public static int strstr(String s, String p) {
		//aacdaca
		//aca
		if(s == null || p == null) return -1;
		int j=0;
		for(int i=0; i<=s.length()-p.length(); i++) {
			while(true) {
				if(s.charAt(i) == p.charAt(j)) {
					i++;
					j++;
				} else {
					i = i-j;
					j = 0;
					break;
				}
				if(j == p.length()) {
					return i-j;
				}
			}
		}
		return -1;
	}
	
	public static void overlappingIntervals() {
		int[][] iv = {{0,3},{2,6},{8,10},{15,18}};
		int[][] merged = new int[iv.length][2];
		int rct = 0;
		int[] prev = null;
		for(int i=0; i<iv.length-1; i++) {
			int[] a = prev != null ? prev : iv[i];
			int starti = a[0], endi = a[1];
			int[] b = iv[i+1];
			int startj = b[0], endj = b[1];
			int[] res = new int[2];
			if(starti <= startj && endi >= endj) {//contained iv
				res[0] = starti; res[1] = endi;
			}else if(starti <= startj && endi <= endj && endi >= startj) {//overlapped
				res[0] = starti; res[1] = endj;
			}else {//no overlap
				res[0] = startj; res[1] = endj;
				rct++;
			}
			prev = res;
			merged[rct] = prev;
		}
		MaxHeap.printArray(merged);
	}
	
	public static void nextPerm() {
		List<Integer> digits = new ArrayList<>();
		int n = 4526453;
		//2344556
		//42135
		int c = n;
		while(c > 0) {
			int d = c%10; c/= 10;
			digits.add(0,d);
		}
		Integer[] a = digits.toArray(new Integer[0]);

		for(int aa : a) {
			System.out.print(aa);
		}
	}
	
	public static void rotationPt() {
		int[] a = {2,3,5,6,8,12,0};
		//int[] a = {8,12,0,2,3,5,6};
		int l = 0; 
		int r = a.length-1;
		while(l <= r) {
			int mid = l + (r-l)/2;
			int ll = a[l]; int mm = a[mid]; int rr = a[r];
			if(mm < a[mid-1] && mm < a[mid+1]){
				System.out.println("found "+mid);
				break;
			}
			if(mm < ll && mm < rr){//in left
				r = mid - 1;
			}else if(mm > ll && mm > rr) {//in right
				l = mid + 1;
			}else if(mm > ll && mm < rr) {
				System.out.println("no rot ");
				break;
			}else if(mm == ll && mm >rr){
				System.out.println("no rot ");
				break;
			}
		}

	}

	
	public static void main(String[] args) {
		//floorCeil();
		//System.out.println(findMedianSortedArrays(new int[]{2}, new int[]{}));
		int[] rd = new int[1000000];
		Random rnd = new Random();
		for(int i = 0; i<rd.length; i++) {
			rd[i] = rnd.nextInt(rd.length);
		}
		twoSum(rd, 456752);
		twoSum2(rd, 456752);
		//457652
		//457256
		lengthOfLongestSubstring1("abbaacadad");
		longestPalindrome("abacbcaccbcbcbcb");
		//permuteString("", "abc");
		System.out.println(permute(new int[] {3,2,1}));
		testMerge();
		kthLargest(new int[][] {{10, 20,30},{15, 25, 35},{24, 29, 32}} ,7);
		perms("", "cry");
		distribSpace("great");
		findMatchedWords();
		strstr("aacdaca", "aca");
		overlappingIntervals();
		nextPerm();
		rotationPt();
	}

}
