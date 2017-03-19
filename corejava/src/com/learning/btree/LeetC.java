package com.learning.btree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class LeetC {
	
	private static class Node {
		int d;
		Node left;
		Node right;
		public Node(int data) {
			this.d = data;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return ""+d;
		}
	}
	
	Node root;
	
	public void flip() {
		Node t = root;
		Stack<Node> st = new Stack<>();
		while(t != null) {
			st.add(t);
			t = t.left;
		}
		root = st.peek();
		while(!st.isEmpty()) {
			t = st.pop();
			if(!st.isEmpty()) {
				Node up = st.peek();
				t.right = up;
				t.left = up.right;
			} else {
				t.left =null;
				t.right = null;
			}
		}
		
	}
	
    public static int hammingWeight(int n) {
        int temp = n;
        int onecount = 0;
        StringBuilder sb = new StringBuilder();
        while(temp > 0) {
            int digit = temp%2;
            sb.insert(0, digit);
            if(digit == 1) {
                onecount++;
            }
            temp /= 2;
        }
        System.out.println(sb);
        return onecount;
    }
    
    public static int lengthOfLongestSubstringNonRepeatingChar(String s) {
        char[] carray = s.toCharArray();
        
        int max = Integer.MIN_VALUE;
        int len = 0;
        for(int i=0; i<carray.length; i++) {
            len = 0;
            Set<Character> cset = new HashSet<>();
            for(int j=i; j<carray.length; j++) {
                char c = carray[j];
                c = Character.toLowerCase(c);
                if(!cset.contains(c)) {
                    len++;
                    if(len > max) {
                        max = len;
                    }
                    cset.add(c);

                } else {
                    break;
                }
            }
        }
        return max;
    }
    
    public static int lengthOfLongestSubstringNonRepeatingCharON(String s) {
        char[] carray = s.toCharArray();
        
        int max = Integer.MIN_VALUE;
        int len = 0;
        int startpos = 0;
        Map<Character, Integer> cset = new HashMap<>();
        for(int i=0; i<carray.length; i++) {
        	char c = carray[i];
        	c = Character.toLowerCase(c);
        	if(!cset.containsKey(c)) {
        		len++;
                cset.put(c, i);
        	} else {
        		Integer pos = cset.get(c);
        		if(pos >= startpos) {
        			len = i-pos;
        			startpos = pos;
        		} else {
        			len++;
        		}
        		
        		cset.put(c,  i);
        	}
            if(len > max) {
                max = len;
            }
        }
        return max;
    }
    
    public static void longestPalindromeSubstring() {
    	String s = "cabcbabcbrabcba";
    	int im = 0;
    	int jm = 0;
    	char[] a = s.toCharArray();
    	int maxl = 1;
    	for(int i=0; i<a.length; i++) {
    		for(int j=i+1; j<a.length; j++) {
        		System.out.println("j "+j);
				if(maxl >= j-i+1) {
	        		System.out.println("continue "+j);

					continue;
				}
    			if(a[i] == a[j] ) {

    				boolean isp = isPalindrome(a, i, j);
    				if(isp && j-i+1 > maxl) {
    					maxl = j-i+1;
    					im = i;
    					jm = j;
    				}
    			}
    		}
    		System.out.println("i "+i);
    		if(a.length - i < maxl) {
    			break;
    		}
    	}
    	System.out.println(s.substring(im, jm+1));
    	System.out.println("maxl "+maxl);
    }
    
    public static boolean isPalindrome(char[] a, int i, int j) {
    	while(i < j) {
    		if(a[i] != a[j]) {
    			return false;
    		}
    		i++;
    		j--;
    	}
    	return true;
    }
    
    public static void sort2D() {
    	int[][] a = {{20,40,80},{5,60,90},{45,50,55}};
    	int N = 3;
    	PriorityQueue<Integer> pq = new PriorityQueue<>();

    	int j = 0;
		while( j<N ) {

	    	for(int i=0; i<N; i++) {
	    		pq.add(a[i][j]);
	    		System.out.println(pq);
	    	}
	    	j++;
	    	//pq.clear();
		}
    }
    
    public static int reverse(int x) {
        if(x >= Integer.MAX_VALUE) {
            return 0;
        }
        int copy = Math.abs(x);
        int reverse = 0;
        while(copy > 0) {
        	if((Integer.MAX_VALUE-copy%10)/10 < reverse) {
        		return 0;
        	}
            reverse = reverse*10 + copy%10;
            copy /= 10;
        }
        reverse = x < 0 ? reverse*-1 : reverse;
        return reverse;
    }
    
    public static int atoi(String str) {
        char[] cs = str.toCharArray();
        int result = 0;
        boolean start = false;
        boolean sign=false;
        boolean internalSpace = false;
        int mul = 1;
        for(int i=cs.length-1; i>=0; i--) {
        	int digit = cs[i] - '0';
            if(digit >= 0 && digit <= 9) {
                if(internalSpace) {
                	mul = 1;
                	result = 0;
                	internalSpace = false;
                }
                if(digit > (Integer.MAX_VALUE-result)/mul) {
                    result = Integer.MAX_VALUE;
                }else{
                	result = digit*mul + result;
                	start = true;
                }
                if(mul < Integer.MAX_VALUE/10) {
                    mul *= 10;
                }
            }
            else if(
            	!sign && start && 
            	((cs[i] == '+' || cs[i] == '-') && i+1<cs.length && (cs[i+1] - '0' >= 0 && cs[i+1] - '0' <= 9 )) ){
	                if(cs[i] == '-') {
	                	result = result*-1;
	                	if(result == Integer.MIN_VALUE+1) {
	                		result--;
	                	}
	                } 
                sign = true;
            } else if(start && cs[i] == ' ') {
            	internalSpace = true;
            	continue;
            } else {
            	mul=1;
            	result = 0;
            }
        }
        return result;
    }
    
    public static String longestCommonSubstring(String[] strs) {
    	StringBuilder prefix = new StringBuilder();
		if(strs == null || strs.length == 0) {
			return prefix.toString();
		}
    	int minl = Integer.MAX_VALUE;
    	int tmin = 0;
    	for(int i=0; i<minl; i++) {
    		char pch = Character.MIN_VALUE;
    		int ctr = 0;
    		for(int j=0; j<strs.length; j++) {
    			String st = strs[j];
    			if(i > st.length()-1 ) {
    				return prefix.toString();
    			}
    			char ch = st.charAt(i);
    			if( i == 0) {
	        		tmin = strs[j].length();
	        		if(tmin < minl) {
	        			minl = tmin;
	        		}

    			}
        		if(j == 0) {
        			pch = ch;
        		}
    			if(ch == pch) {
    				ctr++;
    			}
    			else {
    				return prefix.toString();
    			}
    			if(ctr == strs.length) {
    				prefix.append(ch);
    			} 
    			pch = ch;
    		}
    	}
    	return prefix.toString();
    }
    
    public static void generatePhoneCombinations() {
    	Map<String, List<Character>> dialPad = new HashMap<>();
		char start = 'a'-1;
    	for(int i=2; i<10; i++) {
    		List<Character> list = new ArrayList<>();
    		for(int j=0; j<3; j++) {
    			list.add(++start);
    		}
    		if(i==7 || i==9 ){
    			list.add(++start);
    		}

    		dialPad.put(""+i, list);
    	}
    	System.out.println(dialPad);
    	String num = "23";
    	List<String> out = new ArrayList<>();
    	for(int i=0; i<num.length(); i++) {
    		char last = num.charAt(i);
    		String digit = String.valueOf(last);
    		List<Character> nlist = dialPad.get(digit);
			List<String> nextList = new ArrayList<>();

    		for(char nc : nlist) {
    			if(out.size() > 0) {
    				for(String outc : out) {
    					StringBuilder sb = new StringBuilder(outc);
    					sb.append( nc);
    					nextList.add(sb.toString());
    				}
    			} else {   				
    				nextList.add(String.valueOf(nc));
    			}
    		}
			out = nextList;
    	}
    	System.out.println("dial pad "+out);
    }
    
    public static boolean isValid(String s) {
    	s = "([{()}])";
        if(s == null || s.trim().length() == 0){
            return true;
        }
        char[] ca = s.toCharArray();
        Stack<Character> st = new Stack<>();
        for(int i=0; i<ca.length; i++) {
            if(ca[i] == '{' || ca[i] == '(' || ca[i] == '[') {
                st.push(ca[i]);
            }
            if((ca[i] == '}' || ca[i] == ')' || ca[i] == ']') && st.size() == 0){
            	st.push(ca[i]);
                break;
            }
            char stacktop = st.peek();
            if(ca[i] == '}' && stacktop != '{') {
                break;
            }
            else if(ca[i] == '}' && stacktop == '{') {
                st.pop();
            }
            else if(ca[i] == ')' && stacktop != '(') {
                break;
            }
            else if(ca[i] == ')' && stacktop == '(') {
                st.pop();
            }
            else if(ca[i] == ']' && stacktop != '[') {
                break;
            }
            else if(ca[i] == ']' && stacktop == '[') {
                st.pop();
            }
        }
        System.out.println(st.isEmpty());
        return st.isEmpty();
    }
    
    public static boolean isValid2(String s) {
    	s = "([({})])[]";
        if(s == null || s.trim().length() == 0){
            return true;
        }
        char[] ca = s.toCharArray();
        Stack<Character> st = new Stack<>();
        for(int i=0; i<ca.length; ++i){
            if(ca[i]=='(' || ca[i]=='[' || ca[i]=='{' ){
                st.push(ca[i]);
            }
            else{
                if(st.empty() || ca[i]-st.peek()>3 || ca[i]-st.peek()<-3) {
                    break;
                }
                else {
                    st.pop();
                }
            }
        }
        System.out.println(st.isEmpty());
        return st.isEmpty();
    }
    
    public static int longestValidParentheses(String s) {
    	s = ")))))())";
    	//s = "()";
        //if(s == null) { return 0; }
        Stack<Integer> cs = new Stack<>();
        int last = -1;
        int maxl = Integer.MIN_VALUE;
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(') {
            	cs.push(i);
            }else {
            	if(cs.isEmpty()) {
            		last = i;
            	} else {
	            	cs.pop();
	            	if(cs.isEmpty()) {
	            		maxl = Math.max(i-last, maxl);
	            	}else{
	            		maxl = Math.max(i-cs.peek(), maxl);
	            	}
            	}
            }

        }
        System.out.println("max "+maxl);
        return maxl;
    }
    
    //remove dups from array in place
    public static int removeDuplicates(int[] A) {
	        if(A == null) {
	            return 0;
	        } else if(A.length <= 1) {
	            return A.length;
	        } else if(A.length == 2 && A[0] == A[1]) {
		        int[] B = new int[1];
		        System.arraycopy(A, 0, B, 0, 1);	
		        return 1;
	        }
	        int i=0;
	        int j=i+1;
	        while(j < A.length) {
	            boolean toprocess  = false;
	            while(i<A.length && j<A.length && A[i] == A[j]) {
	                A[j] = -1;
	                toprocess = true;
	                if(j == A.length - 1) {
	                	break;
	                }
	                j++;
	            }
	            if(toprocess) {
	            	if(A[i+1] != A[j]) {
	            		A[++i] = A[j];
	            	}
	            } else {
	                i++;
	                j++;
	            }
	            if(j==A.length-1) {
	            	break;
	            }
	        }
	        int al = i+1;
	        if(i==A.length -2 && A[i] != A[j]) {
	        	al = j+1;
	        }
	        int[] B = new int[al];
	        System.arraycopy(A, 0, B, 0, al);
	        MaxHeap.printArray(B);
	        return i+1;
    }
    
    public static void sortColors() {
    	int[] A = {0,1,0,1,2,1,0,0,2,1};
    	int[] aux = new int[3];
    	int[] sorted = new int[A.length];
    	for(int i=0; i<A.length; i++) {
    		aux[A[i]]++;
    	}
    	for(int i=0; i<aux.length-1; i++) {
    		aux[i+1] += aux[i];
    	}
    	for(int i=A.length-1; i>=0; i--) {
    		int idx = aux[A[i]];
    		idx = idx-1;
    		aux[A[i]]--;
    		sorted[idx] = A[i];
    		
    	}
    }
    
    public static void deleteElementFromArray() {
    	int[] a = {2};
    	int del = 3;
    	int rid = a.length-1;
    	while(rid >=0 && a[rid] == del) {
    		a[rid] = -1;
    		rid--;
    	}
    	for(int i = rid; i>=0; i--) {
    		if(a[i] == del) {
    			a[i] = a[rid];
    			a[rid] = -1;
    			rid--;
    		}
    	}
    	System.out.println("end idx "+Math.max(0, rid));
    	MaxHeap.printArray(a);
    	removeElement(new int[]{2}, 3);
    }
    
    public static int removeElement(int[] A, int elem) {
        if(A == null || A.length == 0) {
            return 0;
        }
    	int del = elem;
    	int rid = A.length-1;
    	while(rid >=0 && A[rid] == del) {
    		A[rid] = -1;
    		rid--;
    	}
    	for(int i = rid; i>=0; i--) {
    		if(A[i] == del) {
    			A[i] = A[rid];
    			A[rid] = -1;
    			rid--;
    		}
    	} 
    	return Math.max(0, rid);
    }
    
    public static int strStr(String haystack, String needle) {
        if(haystack == null || needle == null) return -1;
        int T = haystack.length();
        int P = needle.length();
        for(int i=0; i<=T-P; i++) {
            int j;
            for(j = 0; j<P; j++) {
                char t = haystack.charAt(i+j);
                char p = needle.charAt(j);
                if(t != p) {
                    j = 0;
                    break;
                }
            }
            if(j == P) {
                return i;
            }
        }
        return -1;  
    }
    
    public static void rotatedSorted() {
    	//find point of rotation
    	int[] a = {8,11,12,13,28,30,35,41,1,2,3,5};
    	int l = 0;
    	int r = a.length-1;
    	int rotationidx = 0;
    	while(l<=r) {
    		int m = (l+r)/2;
    		int lv = a[l];
    		int rv = a[r];
    		int mv = a[m];
    		if(lv > mv) {
    			//rotation in left suba
    			r = m-1;
    			if(a[m+1] < a[m] || a[m] < a[r]) {
    				rotationidx = m;
    				break;
    			}
    		} else if(rv < mv) {
    			//rotation in right
    			l = m+1;
    			if(a[m] > a[l]) {
    				rotationidx = l;
    				break;
    			}
    		} else {
    			r = m-1;
    			l = m+1;
    		}
    	}
    	System.out.println("rot idx "+rotationidx);
    }
	
	public static void main(String[] args) {
		System.out.println(hammingWeight(8));
		System.out.println("longest sub "+lengthOfLongestSubstringNonRepeatingCharON("rbmbibybvbsbwttbvribgdyb"));
		//System.out.println("longest sub - "+lengthOfLongestSubstringNonRepeatingChar("rbmbibybvbsbtbvbibyb"));
		longestPalindromeSubstring();
		sort2D();
		System.out.println(reverse(1463847412));
		System.out.println(atoi("-2147483647"));
		String[] values = new String[]
		{" remursive",
		" remote",
		" rem"};
		System.out.println(longestCommonSubstring(values));
		generatePhoneCombinations();
		isValid2("");
		longestValidParentheses("");
		removeDuplicates(new int[]{1,2,9});
		sortColors();
		deleteElementFromArray();
		System.out.println("index "+strStr("rbmbibybvbsbwttbvribgdyb ", "dyb"));
		
		LeetC t1 = new LeetC();
		t1.root = new Node(1);
		t1.root.left = new Node(2);
		t1.root.right = new Node(3);
		t1.root.left.left = new Node(4);
		t1.root.left.right = new Node(5);
		t1.flip();
		rotatedSorted();
	}

}
