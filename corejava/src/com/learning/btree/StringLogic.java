package com.learning.btree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class StringLogic {

	public static char findFirstNonRepeating(String s) {
		Map<Character, Integer> map = new LinkedHashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			Integer ct = map.get(c);
			if (ct == null) {
				map.put(c, 1);
			} else {
				map.put(c, ++ct);
			}

		}
		System.out.println(map);
		Iterator<Character> kr = map.keySet().iterator();
		while(kr.hasNext()) {
			char nonRepeating = kr.next();
			int ct = map.get(nonRepeating);
			if(ct ==1) {
				return nonRepeating;
			}
		}
		return '0';
	}
	
	public static String replaceBlanks(String s) {
	    if(s == null || s.trim().isEmpty()) {
	        return s;
	    }
	    int spaceCount = 0;
	    for(int i=0; i<s.length(); i++) {
	        char c = s.charAt(i);
	        if(c == ' ') {
	            spaceCount++;
	        }
	    }
	    int resultLen = s.length()+(2*spaceCount);
	    char[] result = new char[resultLen];
	    int k = resultLen - 1;
	    for(int i=s.length()-1; i>=0 && k>=0; i--) {
	        char c = s.charAt(i);
	        if(c == ' ') {
	            result[k--] = '0';
	            result[k--] = '2';
	            result[k--] = '%';
	        } else {
	            result[k--] = c;
	        }
	    }
	    String res = new String(result);
	    System.out.println(res);
	    return res;
	}
	
	public static void reverseWords(String s) {
	    if(s == null || s.isEmpty()) {
	        return;
	    }
	    char[] a = s.toCharArray();
	    reverseRange(a, 0, a.length-1);
	    char prev = ' ';
	    int ws = 0;
	    int we = 0;
	    for(int i=0; i<a.length; i++) {
	        if(prev == ' ' && a[i] != ' ') {
	            ws = i;
	        } 
	        else if((prev != ' ' && a[i] == ' ') || (i>=a.length - 1)) {
	            we = i>=a.length - 1 ? i : i-1;
	            reverseRange(a, ws, we);
	        }
	        prev = a[i];
	    }
	    String rev = new String(a);
	    System.out.println(rev);
	            
	}

	public static void reverseRange(char[] a, int l, int r) {
	    while(l < r) {
	        char tmp = a[l];
	        a[l] = a[r];
	        a[r] = tmp;
	        l++;
	        r--;
	    }
	}
	
	public static boolean matchParens(String s) {
	    if(s == null || s.isEmpty()) {
	        return false;
	    }
	    Stack<Character> stack = new Stack<>();
	    for(int i=0; i<s.length(); i++) {
	        char cc = s.charAt(i);
	        if(cc == '(') {
	            stack.push(cc);
	        }
	        else if(cc == ')' && !stack.isEmpty()) {
	            char stc = stack.peek();
	            if(stc == '(') {
	                stack.pop();
	            } else {
	                stack.push(cc);
	            }
	        } else if(cc == ')' && stack.isEmpty()) {
	            return false;
	        }
	    }        
	    if(!stack.isEmpty()) {
	        return false;
	    }       
	    return true;   
	            
	}
	
	public static char maxoccurrence(String s) {
	    if(s == null || s.isEmpty()) {
	        return ' ';
	    }
	    Map<Character, Integer> map = new HashMap<>();
	    char maxFreqChar = ' ';
	    int maxFreq = 0;
	    for(int i=0; i<s.length(); i++) {
	        char cc = s.charAt(i);
	        if(!map.containsKey(cc)) {
	            map.put(cc, 1);
	        }
	        else {
	            Integer count = map.get(cc);
	            count++;
	            map.put(cc, count);
	            if(count > maxFreq) {
	                maxFreq = count;
	                maxFreqChar = cc;
	            }
	        }
	            
	    }        
	    System.out.println("max freq char "+maxFreqChar+" freq "+maxFreq);
	    return maxFreqChar;   
	            
	}
	
	public static void printAllSub() {
		String s = "newton";
		for(int i=0; i<s.length(); i++) {
			for(int j=i+1; j<s.length(); j++) {
				System.out.println(s.substring(i, j));
			}
		}
	}
	
	public static void printSumInString() {
		String sNum = "121c11aa00";
		int sum = 0;
		for(int i=0; i<sNum.length(); i++) {
			char c = sNum.charAt(i);
			int num = 0;
			int digit = c-'0';
			while(digit >= 0 && digit <= 9) {
				num = num*10+digit;
				if(i>=sNum.length()-1) {
					break;
				}
				digit = sNum.charAt(++i)-'0';
			}
			sum+=num;
			num = 0;
		}
		System.out.println(sum);
	}
	
	public static void printAnagrams() {
		long start = System.nanoTime();
		List<String> words = Arrays.asList(new String[]{"mere","dear","read","undefinability","unidentifiably","tropines","proteins","repoints","threshold","warn"});
		Map<String, List<String>> anagrams = new HashMap<> ();
		for(String s : words) {
			char[] a = s.toCharArray();
			Arrays.sort(a);
			String sorted = String.valueOf(a);
			if(anagrams.containsKey(sorted)){
				anagrams.get(sorted).add(s);
			}else{
				List<String> al = new ArrayList<>();
				al.add(s);
				anagrams.put(sorted, al);
			}
		}
		System.out.println("t1 "+(System.nanoTime() - start)/1000+" ms ");
		for(String key : anagrams.keySet()){
			if(anagrams.get(key).size()>1) {
				System.out.println("anagrams "+anagrams.get(key));
			}
		}
	}
	
	public static void printAnagramsUsingPrimeFactors() {
		long start = System.nanoTime();
		List<String> words = Arrays.asList(new String[]{"mere","dear","read","undefinability","unidentifiably","tropines","proteins","repoints","threshold","warn"});
		Map<Long, List<String>> anagrams = new HashMap<> ();
		int[] primes = {2, 41, 37, 47, 3, 67, 71, 23, 5, 101, 61, 17, 19, 13, 31, 43, 97, 29, 11, 7, 73, 83, 79, 89, 59, 53};
		for(String s : words) {
			long product = 1l;
			//BigInteger val = new BigInteger(String.valueOf(product));
			for(int i = 0; i<s.length(); i++) {
				char ch = s.charAt(i);
				if(ch - 'a' < 0 || ch - 'z' > 25 ) {
					System.out.println("char out of range");
					return;
				} else {
					int v = primes[(ch-'a')];
					if (v != 0 && product > Long.MAX_VALUE / v) {
						System.out.println("multiplication overflow");
						return;
					}
					product *= v;
				}
			}
			if(anagrams.containsKey(product)){
				anagrams.get(product).add(s);
			}else{
				List<String> al = new ArrayList<>();
				al.add(s);
				anagrams.put(product, al);
			}
			//System.out.println("pr "+product);
		}
		System.out.println("t2 "+(System.nanoTime() - start)/1000+" ms ");
		for(Long key : anagrams.keySet()){
			if(anagrams.get(key).size()>1) {
				System.out.println("anagrams "+anagrams.get(key));
			}
		}
	}
	
	public static void printCompound() {
		List<String> a = Arrays.asList(new String[]{"p","s","se","se","set","t","u","up","ups","upse","upset"});
		//List<String> a = Arrays.asList(new String[]{"abcabcabcd","ab","c","a","bc"});
		Collections.sort(a);
		Set<String> compound = new HashSet<>();
		int loopct = 0;
		for(int i=0; i<a.size()-1; i++) {
			int j = i;
			String s1 = a.get(i);
			String s2 = "";
			while(++j < a.size() && a.get(j).startsWith(s1) && a.get(j).length() > s1.length()) {
				loopct++;
				s2 = a.get(j);
				String toSearch = s2.substring(s1.length(), s2.length());
				int idx = Collections.binarySearch(a, toSearch);
				if(idx >= 0) {
					compound.add(s2);
				}
			}
		}
		System.out.println(compound+" loopct "+loopct);
 	}
	
	//https://www.hackerrank.com/challenges/build-a-string
	public static void stringCost() {
		String s = "aabaacaba";
		int cost = 0;
		StringBuilder cs = new StringBuilder();
		Set<String> pre = new HashSet<>();
		for(int i=0; i<s.length(); i++) {
			int cl = cs.length();
			if(cl < 2) {
				cs.append(s.charAt(i));
				cost+=4;
				if(cs.length()>1){
					pre.add(cs.toString());
				}
			}else{
				kgrams(s, i, pre);
				StringBuilder ahead = new StringBuilder();
				boolean pstate = false;
				int si = i;
				for(int k=i; k<s.length(); k++){
					ahead.append(s.charAt(k));
					if(ahead.length() > 1) {
						boolean cstate = pre.contains(ahead.toString());
						if(cstate) i=k;
						if(pstate && !cstate){//prev substr match
							break;
						}
						pstate = cstate;
					}
				}
				if(pstate) {
					cost+=5;
					cs.append(s.substring(si, i+1));
				}else{
					cost+=4;
					cs.append(s.charAt(i));
				}
			}
			
		}
		System.out.println("cost "+cost+" "+cs);
	}
	
	public static void kgrams(String s, int cidx, Set<String> pre) {
		if(cidx < 1 || cidx >= s.length()) {
			return;
		}
		char c = s.charAt(cidx);
		StringBuilder sb = new StringBuilder();
		sb.append(c);
		for(int i = cidx - 1; i>=0; i--) {
			sb.insert(0,s.charAt(i));
			pre.add(sb.toString());				
		}
/*		int k = 2;
		Set<String> kg = new TreeSet<>();
		for(; k<=s.length();k++){
			for(int i=0; i<=s.length()-k; i++) {
				StringBuilder sb = new StringBuilder(k);
				for(int j=i; j<i+k; j++){
					sb.append(s.charAt(j));
				}
				kg.add(sb.toString());
			}
		}
		System.out.println(kg.size());
		return kg;*/
	}
	
	public static void checkPaalin(){
		String s = "bab";
		int l = 0; int r = s.length()-1;
		boolean pal = true;
		while(l < r) {
			if(s.charAt(l) != s.charAt(r)){
				pal = false;
				break;
			}
			l++;
			r--;
		}
		if(pal) System.out.println("found palindrome");
		else System.out.println("not a palindrome");


	}
	
	//longest prefix suffix
	public static void lps() {
		String w = "abaaba";
	    char c = w.charAt(0);
	    int k = 0;
	    boolean found = false;
	    for(int i=1; i< w.length(); i++) {
	        char cnext = w.charAt(i);
	        if(cnext == c){
	            k = 1;
	            while(i+k < w.length() && w.charAt(k) == w.charAt(i+k)){
	                k++;
	            }
	            if(i+k == w.length()){
	            	found = true;
	                break;
	            }
	        }
	    }
	    if(found)
	    	System.out.println(k);
	    else
	    	System.out.println(0);	
	}
	
	public static void main(String[] args) {
		String test = "aabbcrca";
		char nr = findFirstNonRepeating(test);
		System.out.println("NON rep "+nr);
		String replaceSpace = "  Try ";
		replaceBlanks(replaceSpace);
		reverseWords("Test this out  sentence-new");
		boolean isMatched = matchParens("()()()(()(())(()))");
		System.out.println("isMatched "+isMatched);
		maxoccurrence("abracadabrabrbrbrb");
		printAllSub();
		printSumInString();
		printAnagrams();
		printAnagramsUsingPrimeFactors();
		printCompound();
		stringCost();
		checkPaalin();
	}
}
