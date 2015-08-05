package com.learning.btree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Combinatorics {
	
	public static Set<List<Integer>> permuteInts(List<Integer> input) {
	    Set<List<Integer>> set = new HashSet<List<Integer>>();
        if (input.isEmpty()) {
        	set.add(new ArrayList<Integer>());
            return set;
        }
	    Integer a = input.get(0);
        List<Integer> rest = input.subList(1, input.size());
        Set<List<Integer>> permSet = permuteInts(rest);
        for (List<Integer> x : permSet) {
        	Set<List<Integer>> subLists = new HashSet<List<Integer>>();
            for (int i = 0; i <= x.size(); i++) {
				List<Integer> subList = new ArrayList<Integer>();
				subList.addAll(x);
				subList.add(i,a);
				subLists.add(subList);
            }
	        set.addAll(subLists);
        }

	    return set;

	}
	
	public static List<String> permute(String str) {
		List<String> ll = new ArrayList<>();
		if(str.length() <= 0) {
			ll.add("");
			return ll;
		}
		
		char zero = str.charAt(0);
		str = str.substring(1);
		List<String> ls = permute(str);
		for(String s : ls) {
			for(int i=0; i<=s.length(); i++) {
				ll.add(s.substring(0, i)+zero+s.substring(i));
			}
		}
		return ll;
	}
	
	public static void nprBrute(String str, int k) {
		List<String> llist = permute(str);
	    Set<String> pnr = new HashSet<>();	
	    for(String strs : llist){
	    	pnr.add(strs.substring(0, k));
	    }
        System.out.println("P("+str.length()+","+k+") :"+ 
        "Count ("+pnr.size()+") :- "+pnr);
	}
	
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
	
/*	public static void printNPR(char[] a, int n, int r, List<String> result) {
		if(r==0) {
			StringBuilder sb = new StringBuilder();
			for(int i=n; i<a.length; i++) {
				sb.append(a[i]);
			}
			//System.out.println(sb);
			result.add(sb.toString());
			return;
		}
		for(int i=0; i<n; i++) {
			swap(a, i, n-1);
			printNPR(a, n-1, r-1, result);
			swap(a, i, n-1);
		}
	}*/
	
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
    
    public static void allCombinationsSet(List<Integer> prelist, List<Integer> slist, Set<List<Integer>> allList) {
    	//System.out.println(prefix);
    	allList.add(prelist);
        for (int i = 0; i < slist.size(); i++) {
        	List<Integer> sub1 = new ArrayList<>();
        	sub1.addAll(prelist);
        	sub1.add(slist.get(i));
        	allCombinationsSet(sub1, slist.subList(i+1, slist.size()), allList);
        }
    }
    
    public static void getAllCombinationsSet() {
    	List<Integer> prelist = new ArrayList<>();
    	List<Integer> slist = Arrays.asList(new Integer[]{1,2,3});
    	Set<List<Integer>> allList = new HashSet<>();
    	allCombinationsSet(prelist, slist, allList);
    	System.out.println(allList);
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
    	printPartitions(N, N, "");
    }
    
    public static void printPartitions(int n, int max, String prefix) {
    	if(n == 0) {
    		System.out.println(prefix);
    		return;
    	}
    	for(int i=Math.min(n,  max); i>=1; i--) {
    		printPartitions(n-i, i, prefix+' '+i);
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
    public static void permuteIter(String s) {
        // Print initial string, as only the alterations will be printed later
        System.out.println(s);   
        char[] a = s.toCharArray();
        int n = a.length;
        int[] p = new int[n];  // Weight index control array initially all zeros. Of course, same size of the char array.
        int i = 1; //Upper bound index. i.e: if string is "abc" then index i could be at "c"
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
	
	public static void printIR(String s1, String s2, String result, int N) {
		if(result.length() == N) {
			System.out.println(result);
		}
		if(s1.length() > 0) {
			printIR(s1.substring(1), s2, result+s1.charAt(0), N);
		}
		if(s2.length() > 0) {
			printIR(s1, s2.substring(1), result+s2.charAt(0), N);
		}
	}
	
	public static void permutePhone() {
		List<String> values = Arrays.asList("ABC", "DEF");
		permutePhone(values, "", 0);
	}
	
	public static void permutePhone(List<String> values, String perm, int depth) {
		if(depth == values.size()) {
			System.out.println(perm);
			return;
		} else {
			String val = values.get(depth);
			for(int i=0; i<val.length(); i++) {
				permutePhone(values, perm+val.charAt(i), depth+1);
			}
		}
	}
	
	public static void climbStairs(int N, String prefix, String s, int n) {
		//if(n == 0) {
			System.out.println(prefix);
		//}
		for(int i=0; i<N; i++) {
			climbStairs(N, prefix+s.charAt(i), s.substring(i+1), n-1);
		}
	}

	
	public static void main(String[] args) {
		List<String> nprvalues = new ArrayList<>();
		printPermutations("", "N");
		
		System.out.println("NPR "+nprvalues);
		nprBrute("XYZW", 2);


        //printAllCombinations("", "123");
        printNCRCombinations("", "1234", 2);
        getAllCombinationsSet();

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
        //climbStairs(2, "", "123", 3);
        printPartitions(4,2, "");
	}

}
