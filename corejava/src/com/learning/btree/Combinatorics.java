package com.learning.btree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Combinatorics {
	
	public static void printPermutations(String prefix, String s) {
		int N = s.length();
		if(N==0) {
			System.out.println(prefix);
		}
		else {
			for(int i=0; i<N; i++) {
				printPermutations(prefix+s.charAt(i), s.substring(0, i)+s.substring(i+1));
			}
		}
	}
	
	public static void printNPRPerms(String prefix, String s, int r) {
		if(r == 0) {
			System.out.println(prefix);
		} else {
			for(int i=0; i<s.length(); i++) {
				printNPRPerms(prefix+s.charAt(i), s.substring(0,i)+s.substring(i+1), r-1);
			}
		}
	}
	
	public static void listPerms(List<Integer> prefix, List<Integer> s, List<List<Integer>> out) {
		if(s.size() == 0) {
			out.add(prefix);
			return;
		} else {
			for(int i=0; i<s.size(); i++) {
				List<Integer> newp = new ArrayList<>();
				newp.addAll(prefix);
				newp.add(s.get(i));
				List<Integer> next = new ArrayList<>();
				next.addAll(s.subList(0, i));
				next.addAll(s.subList(i+1, s.size()));
				listPerms(newp, next, out);
			}
		}
	}
	

	
    public static void swap(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    public static void printAllCombinations(String prefix, String s) {
    	System.out.println(prefix);
        for (int i = 0; i < s.length(); i++) {
        	printAllCombinations(prefix + s.charAt(i), s.substring(i + 1));
        }
    }
    
    public static void printAllPalindromeCombinations(String prefix, String s) {
    	if(isPalindrome(prefix)) {
    		System.out.println(prefix);
    	}
        for (int i = 0; i < s.length(); i++) {
        	printAllPalindromeCombinations(prefix + s.charAt(i), s.substring(i + 1));
        }
    }
    
    public static boolean isPalindrome(String s) {
    	int i=0;
    	int j = s.length()-1;
    	while(i<j) {
	    	if(s.charAt(i) == s.charAt(j)) {
	    		i++;
	    		j--;
	    	} else {
	    		return false;
	    	}
    	}
    	return true;
    }
    
    public static void getAllCombinationsSet() {
    	List<Integer> prelist = new ArrayList<>();
    	List<Integer> slist = Arrays.asList(new Integer[]{10 ,1 ,2 ,7 });
    	List<List<Integer>> allList = new ArrayList<>();
    	allCombinationsSet(prelist, slist, allList);
    	System.out.println(allList);
    }
        
    public static void allCombinationsSet(List<Integer> prelist, List<Integer> slist, List<List<Integer>> allList) {
    	System.out.println(prelist);
    	//allList.add(prelist);
        for (int i = 0; i < slist.size(); i++) {
        	List<Integer> sub1 = new ArrayList<>();
        	sub1.addAll(prelist);
        	sub1.add(slist.get(i));
        	allCombinationsSet(sub1, slist.subList(i+1, slist.size()), allList);
        }
    }
    
    public static void printNCRCombinations(String prefix, String s, int r) {
    	if(r == 0) {
    		System.out.println(prefix);
    	} else {
	        for (int i = 0; i < s.length(); i++) {
	        	printNCRCombinations(prefix + s.charAt(i), s.substring(i + 1), r-1);
	        }
    	}
    }
    
    public static void printNCRIntArray(){
    	List<Integer> a = Arrays.asList(new Integer[]{5,4,12,14,2,8,3,6});
    	List<List<Integer>> a1 = new ArrayList<>();
    	printNCRIntArray(new ArrayList<Integer>(), new ArrayList<>(a), a1, 3);
    	System.out.println(a1);
    }
    
    //find nCk combination, sum of whose values are divisible by 17
    public static boolean printNCRIntArray(List<Integer> prefix, List<Integer> inList, List<List<Integer>> allList, int r) {
    	if(r == 0) {
    		System.out.println(prefix);
    		int sum = 0;
    		for(int v : prefix) {
    			sum += v;
    		}
    		if(sum % 17 == 0) {
    			System.out.println("found divisible "+sum);
        		allList.add(prefix);
    			return true;
    		}
    	} else {
	        for (int i = 0; i < inList.size(); i++) {
	        	List<Integer> subA = new ArrayList<>(prefix);
	        	subA.add(inList.get(i));
	        	boolean ismatch = printNCRIntArray(subA, inList.subList(i+1, inList.size()), allList, r-1);
	        	if(ismatch) {
	        		return ismatch;
	        	}
	        } 
    	}
    	return false;
    }
    
    //print all values that sum to N
    public static void printPartitions(int N) {
    	printPartitions("", N, N);
    }
    
    public static void printPartitions(String prefix, int n, int max) {
    	if(n == 0) {
    		System.out.println(prefix);
    		return;
    	}
    	for(int i=Math.min(n,  max); i>=1; i--) {
    		printPartitions( prefix+' '+i, n-i, i);
    	}
    }
    
    public static void getPartitionList(int N) {
    	List<Integer> prelist = new ArrayList<>();
    	List<List<Integer>> plist = new ArrayList<>();
    	getPartitionList(N, N, prelist, plist);
    	System.out.println(plist);
    }
    
    public static void getPartitionList(int n, int max, List<Integer> prelist, List<List<Integer>> plist) {
    	if(n == 0) {    		
    		plist.add(new ArrayList<Integer>(prelist));
    		prelist = new ArrayList<>();
    		return;
    	}
    	for(int i=Math.min(n,  max); i>=1; i--) {
        	List<Integer> sub1 = new ArrayList<>();
        	sub1.addAll(prelist);
    		sub1.add(i);
    		getPartitionList(n-i, i, sub1, plist);
    	}
    }
    
    //iterative solution for permutation
    //quickperm algorithm
    public static void permuteIter(String s) {
        // Print initial string, as only the alterations will be printed later
        System.out.println(s);   
        char[] a = s.toCharArray();
        int n = a.length;
        int[] p = new int[n];  // Weight index control array initially all zeros. same size of the char array.
        int i = 1; //start swap index
        while (i < n) {
            if (p[i] < i) { //if the weight index is bigger or the same it means that we have already switched between these i,j (one iteration before).
                int j = ((i % 2) == 0) ? 0 : p[i];//Lower bound index. i.e: if string is "abc" then j index will always be 0.
                swap(a, i, j);
                // Print current
                System.out.println(join(a));
                p[i]++; //Adding 1 to the specific weight that relates to the char array.
                i = 1; //if i was 2 (for example), after the swap we now need to swap for i=1
            }
            else { 
                p[i] = 0;//Weight index will be zero because one iteration before, it was 1 (for example) to indicate that char array a[i] swapped.
                i++;//i index will have the option to go forward in the char array for "longer swaps"
            }
        }
    }

    private static String join(char[] a) {
        StringBuilder builder = new StringBuilder();
        builder.append(a);
        return builder.toString();
    }
    
	public static void printParenthesis() {
		int n = 4;
		printit(n/2, n/2, "");
	}
	
	public static void printit(int l, int r, String s) {
		if(l ==0 && r==0){
			System.out.println(s);
		}
		if(l > r) {
			return;
		}if(l>0) {
			printit(l-1, r, s+"(");
		}
		if(r>0) {
			printit(l, r-1, s+")");
		}

	}

	//AB CD result
	//ABCD, ACBD, ACDB, CABD, CDAB, CADB
	public static void printInterleaved() {
		String s1 = "AB";
		String s2 = "DC";
		printIR(s1, s2, "", s1.length()+s2.length());
	}
	
	public static void printIR(String s1, String s2, String prefix, int N) {
		if(prefix.length() == N) {
			System.out.println(prefix);
		}
		if(s1.length() > 0) {
			printIR(s1.substring(1), s2, prefix+s1.charAt(0), N);
		}
		if(s2.length() > 0) {
			printIR(s1, s2.substring(1), prefix+s2.charAt(0), N);
		}
	}
	
	public static void permutePhone() {
		List<String> values = Arrays.asList("ABC", "DEF");
		permutePhone(values, "", 0);
	}
	
	public static void permutePhone(List<String> values, String perm, int depth) {
		if(depth == values.size()) {
			System.out.println("PP "+perm);
			return;
		} else {
			String val = values.get(depth);
			for(int i=0; i<val.length(); i++) {
				permutePhone(values, perm+val.charAt(i), depth+1);
			}
		}
	}
	
    public static void permutePhone2() {
    	char[][] dialpad = {
    			{},
    			{},
    			{'A','B','C'},
    			{'D','E','F'},
    			{'G','H','I'}
    	};
    	String sNum = "23";
    	permutePhone2(dialpad, sNum, "", 0);
    }
    
    public static void permutePhone2(char[][] dialpad, String sNum, String perm, int depth) {
    	if(depth >= sNum.length()) {
    		System.out.println("pphonen "+perm);
    		return;
    	}
    	char c = sNum.charAt(depth);
    	int idx = c-'0';
    	char[] l = dialpad[idx];
    	for(int i=0; i<l.length; i++) {
    		permutePhone2(dialpad, sNum, perm+l[i], depth+1);
    	}
    	
    }
	
	public static void climbStairs(int N, String prefix, String s, int n) {
		if(n == 0) {
			System.out.println(prefix);
		}else{
			for(int i=0; i<s.length(); i++) {
				climbStairs(N, prefix+s.charAt(i), s.substring(i+1), n-1);
			}
		}
	}
	
    public static void largestDivisibleSubset(int[] nums) {
        int len = nums.length;
        for(int i=len; i>=0; i--) {
            List<Integer> encl = new ArrayList<>();
            List<Integer> in = new ArrayList<>();
            for(int ii : nums) { in.add(ii); }
            listCombinations(new ArrayList<>(), in, encl, i);
            if(encl.size() > 0) {
                System.out.println(encl);
                break;
            }
        }
        
    }
    
    public static void listCombinations(List<Integer> preList, List<Integer> s, List<Integer> divComb, int r) {
    	if(divComb.size() > 0) return;
        if(r == 0) {
            boolean allDivisible = true;
            for(int i=0; i<preList.size()-1; i++) {
                for(int j=i+1; j<preList.size(); j++) {
                    int i1 = preList.get(i);
                    int i2 = preList.get(j);
                    int a = Math.min(i1, i2);
                    int b = Math.max(i1, i2);
                    if(b % a != 0) {
                        allDivisible = false;
                    }
                }
            }
            if(allDivisible) {
                divComb.addAll(preList);
                return;
            }
        } else {
            for(int i=0; i<s.size() && divComb.size()==0; i++){
            	List<Integer> tList = new ArrayList<>(preList);
            	tList.add(s.get(i));
                listCombinations(tList, s.subList(i+1, s.size()), divComb, r-1);
            }
        }
    }
    
    public static void printSpaces(){
    	/*
    	 * Input:  str[] = "ABC"
		 * Output: (A B C)(A BC)(AB C)(ABC)
		 * ABCD
		 * (A B C D)(AB CD)(A BC D)(ABC D)(A BCD)(ABCD)
    	 */
    	printSp1(new StringBuilder("ABCDE"), "ABCDE", 0);
    }
    
    public static void printSp(StringBuilder sb, String s){
    	for(int i=0; i<sb.length()-1 && i>=0; i++) {
    		char c1 = sb.charAt(i+1);
    		if(c1 != ' ' ) {
    			sb.insert(i+1, ' ');
    			i++;
    			System.out.println(sb);
    		}else {
    			sb.deleteCharAt(i+1);
    			System.out.println(sb);

    		}
    	}
    	//System.out.println(sb);
    	if(sb.length() != s.length()) {
    		printSp(sb, s);
    	}
    }
    
    public static void printSp1(StringBuilder sb, String s, int idx){
    	if(sb.length() == s.length() && idx > 0 || (idx+1 >= sb.length())) {
    		return;
    	}
		char c1 = sb.charAt(idx+1);
		if(c1 != ' ' ) {
			sb.insert(idx+1, ' ');
			System.out.println(sb);
			printSp1(sb, s, idx+2);
		}else {
			sb.deleteCharAt(idx+1);
			System.out.println(sb);
			printSp1(sb, s, idx+1);
		}
    	//System.out.println(sb);

    }
    
    public static void findAllPalindromePermutations() {
    	String s = "abbaccddr";
    	//string can be palindrome if all chars in pairs or all pairs except one.
    	//construct a half string by taking one from each pair
    	//for every permutation, reverse the string, append the reverse and singlr char if present
    	Map<Character, Integer> fr = new HashMap<>();
    	for(int i=0; i<s.length(); i++) {
    		char c = s.charAt(i);
    		if(fr.get(s.charAt(i)) == null) {
    			fr.put(c, 1);
    		}else{
    			fr.put(c, fr.get(c)+1);
    		}
    	}
    	int oddFreqCount = 0;
    	StringBuilder halfString = new StringBuilder();
    	Character oddOne = null;
    	for(char c : fr.keySet()) {
    		int v = fr.get(c);
    		if(v % 2 != 0) {
    			oddFreqCount++;
    			oddOne = c;
    		}
    		int ctr = v/2;
    		for(int i=0; i<ctr; i++) {
    			halfString.append(c);
    		}
    	}
    	if(oddFreqCount > 1) {System.out.println("palin can't be formed"); return;}
    	System.out.println("halfs "+halfString);
    	printPalindromePerms("", halfString.toString(), oddOne);
    	
     }
    
	public static void printPalindromePerms(String prefix, String str, Character oddOne) {
		if(str.length() == 0) {
			//System.out.println(prefix);
			StringBuilder perm = new StringBuilder(prefix);
			if(oddOne != null) {
				perm.append(oddOne);
			}
			for(int i=prefix.length()-1; i>=0; i--) {
				perm.append(prefix.charAt(i));
			}
			System.out.println(perm);
		}else {
			for(int i=0; i<str.length(); i++) {
				printPalindromePerms(prefix+str.charAt(i), str.substring(0, i)+str.substring(i+1), oddOne);
			}
		}
	}

	
	public static void main(String[] args) {
		List<String> nprvalues = new ArrayList<>();
		printPermutations("", "N");
		
		System.out.println("NPR "+nprvalues);


        //printAllCombinations("", "123");
        printNCRCombinations("", "1234", 2);

        printPartitions(6);
        getPartitionList(5);
        printAllPalindromeCombinations("", "abba");
        printNCRIntArray();
        printNPRPerms("", "NPRS",2);
        permuteIter("NPR");
        printInterleaved();
        printParenthesis();
        List<List<Integer>> out = new ArrayList<>();
        List<Integer> pre = new ArrayList<>();
        listPerms(pre, Arrays.asList(new Integer[]{1,2,3}), out);
        System.out.println(out);
        permutePhone();
        climbStairs(2, "", "123", 3);
        //largestDivisibleSubset(new int[]{1,4,8,2,28,512,1024,81*81,9*36,256});
        //permutePhone2();
        printSpaces();
        getAllCombinationsSet();
        findAllPalindromePermutations();
	}

}
