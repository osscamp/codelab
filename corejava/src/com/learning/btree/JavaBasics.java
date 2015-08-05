package com.learning.btree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class JavaBasics {
	
	public static String reverseString(String str) {
		int len = str.length();
		StringBuilder sb = new StringBuilder();
		int ctr = len-1;
		while(ctr >= 0){
			sb.append(str.charAt(ctr));
			ctr--;
		}
		return sb.toString();
	}
	
	public static String reverseByArray(String str) {
		int l = 0;
		int r = str.length() - 1;
		char[] stArray = str.toCharArray();
		while (l < r) {
			char tmp = stArray[l];
			stArray[l] = stArray[r];
			stArray[r] = tmp;
			l++;
			r--;
		}
		return String.valueOf(stArray);
	}
	
	public static char[] reverseByArray(char[] str, int l, int r) {
		if(r < str.length && l >= 0) {
			while (l < r) {
				char tmp = str[l];
				str[l] = str[r];
				str[r] = tmp;
				l++;
				r--;
			}
		}
		return str;
	}
	
	public static String reverseByRecursion(String str) {
		while (str.length() > 0) {
			return str.charAt(str.length() -1)+reverseByRecursion(str.substring(0, str.length()-1));
		}
		return "";
	}
	
	public static String reverseWords(String str) {
		String[] splitted = str.split("\\s+");
		for(int i=splitted.length-1; i>=0; i--) {
			System.out.println(splitted[i]);
		}
		return str;
	}
	
	public static void reverseWordsWithoutSplit(String str) {
		char[] arr = str.toCharArray();
		reverseByArray(arr, 0, arr.length-1);
		//System.out.println(String.valueOf(arr));

		char prev = ' ';
		int si = 0;
		for(int i=0; i<arr.length; i++) {
			char cc = arr[i];
			//tset dns yrt
			if(cc== ' ' && prev != ' ') {
				reverseByArray(arr, si, i-1);
				si = Math.min(i+1, arr.length-1);
			} else if(i > 0 && cc != ' ' && prev == ' ') {
				si = Math.min(i, arr.length-1);
			}
			else if(i==arr.length-1) {
				reverseByArray(arr, si, i);
			}
			prev = cc;
		}
		System.out.println("reversed "+String.valueOf(arr));
	}
	
	public static long fact(int n) {
		if(n >=1) {
			return n*fact(n-1);
		}
		return 1l;
	}
	
	
	public static long power(int a, int b) {
		if(a == 0 && b == 0) {
			return -1;
		}
		if(b <  0) {
			return -1;
		}
		if(b == 0) { return 1;}
		long res = (long)a;
		if(b %2 == 0) {
			res = power(a, b/2);
			return res*res;
		} else {
			res = power(a, b/2);
			return res*res*a;
		}
	}
	
	public static void fibonacci(int n) {
		int prev1 = 0;
		int prev = 1;
		for (int i=1; i<n; i++) {           
			int next = prev + prev1;
			prev1 = prev;
			prev = next;
			System.out.println(next);
		}
	}
	
	public static void countStairs(int n) {
		int[] ctr = new int[n];
		ctr[0] = 1;
		ctr[1] = 1;
		for(int i=2; i<n; i++) {
			ctr[i] = ctr[i-1]+ctr[i-2];
		}
		System.out.println("STAIRS "+ctr[ctr.length-1]);
		MaxHeap.printArray(ctr);
	}
	
	public static int fibonacciR(int n) {
		if(n==0) {
			return 0;
		} else if (n==1) {
			return 1;
		}
		return fibonacciR(n-1) + fibonacciR(n-2);
	}
	
	public static float findHarmonicSum(int terms) {
		float sum = 0.0f;
		for(int i=1; i<terms; i++) {
			sum += 1.0/i;
		}
		return sum;
	}
	
	public static boolean isPalindrome2(int number) {
		int reversed = 0;
		int copy = number;
		while(number != 0) {
			reversed = reversed*10 + number%10;
			number/=10;
		}
		return reversed==copy;
	}
	
	public static void replaceSpaces(String str) {
		String rep = str.replaceAll("\\s", "%20");
		System.out.println(rep);
	}
	
	public static void printTriangle(int numrows) {
		int ct = 1;
		for(int i=1; i<=numrows; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j=1; j<=i; j++) {
				sb.append(ct).append(" ");
				ct++;
			}	
			System.out.println(sb.toString());
		}
	}
	
	public static Set<String> permuteString(String input) {
	    Set<String> set = new HashSet<String>();
	    if(input == null) {
	    	return null;
	    }
	    if (input.length() == 0){
	    	set.add("");
	        return set;
	    }  
	    char first = input.charAt(0);
        input = input.substring(1);
        Set<String> permSet = permuteString(input);
        for (String x : permSet)
        {
            for (int i = 0; i <= x.length(); i++)
            {
                set.add(x.substring(0, i) + first + x.substring(i));
            }
        }
	    return set;
	}
	
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
	
	public static void reverseNumber(int input) {
		int n = input;
		int nlast = input;
		int rem = 0;
		int nnum = 0;
		while(n >= 1) {
			n = nlast / 10;
			rem = nlast % 10;
			nlast = n;
			nnum = (nnum*10) + rem;
		}
		System.out.println(nnum);
	}
 	
	public static void hanoi(int n, int fromPeg, int toPeg, int spare) {
		if(n == 1) {
			System.out.println(fromPeg + " -> " + toPeg);
		} else {
			hanoi(n-1, fromPeg, spare, toPeg);
			hanoi(1, fromPeg, toPeg, spare);
			hanoi(n-1, spare, toPeg, fromPeg);
		}
		
	}
	
	public static int numDigitCount(int number, int digit) {
		int ct = 0;
		int d = number;
		int ml = 1;
		int onel = 0;
		int prem = 0;
		while(d > 0) {
			int rem = d % 10;
			d = d/10;
			System.out.println("dig "+rem);
			onel = onel+((ml/10)*prem);
			if(rem >= digit) {
				ct += ml;
				if(ml > 1 && rem == digit) {
					ct -= (ml-onel);
				}
			}
			ct += d*ml;
			ml *= 10;
			prem = rem;
		}
		System.out.println("occurence "+digit+" : "+ct);
		return ct;
	}
	
	public static boolean isNumeric(String str) {
		boolean isNumeric = true;
		if(str == null || str.length() == 0) {
			return false;
		}
		int ctr = 0;
		boolean decimal = false;
		for(char ch: str.toCharArray()) {
			if(ctr == 0 && (ch == '+' || ch == '-')) {
				continue;
			}
			if(ch-'0' < 0 || ch-'0' > 9) {
				if(ch != '.' ) {
					return false;
				} else if(!decimal && ch == '.'){
					decimal = true;
				} else {
					return false;
				}
			} 
			ctr++;
		}
		return isNumeric;
	}
	
	public static void findMaxOnes() {
		int maxones = -1;
		int maxonesidx = -1;
		int[][] a = {{0,0,0,0,1},{0,0,0,0,1},{0,0,0,1,1},{0,0,0,0,1},{0,0,0,1,1}};
		int n = a.length;
		for(int i = 0; i<n; i++) {
			int[] aa = a[i];
			if(aa[0] == 1) {
				System.out.println("max ones at "+i);
				return;
			}
			int l = 0;
			int r = aa.length;
			int numones = 0;
			while(l < r) {
				int mid = (l+r)/2;
				if(aa[mid] == 1) { 
					numones += r-mid;
					r = mid; 
				} else {
					l = mid+1;
					if(maxones > mid) {
						continue;
					}
				}
				
			}
			//System.out.println("count at "+numones);

			if(numones > maxones) {
				maxones = numones;
				maxonesidx = i;
			}
		}
		System.out.println("maxones at "+maxonesidx);
	}
	
	public static String convertToHex(int number) {
		number--;
		int rem = number % 26;
		String result = "";	
		if(number - rem == 0) {
			result = ""+toChar(rem);
		} else {
			result = convertToHex((number-rem)/26)+toChar(rem);
		}
		System.out.println("16 result "+result);
		return result;
	}
	
	public static char toChar(int n){
		char[] a= "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		return a[n];
	}
	
	//doesn't work
	public static void findInMatrix() {
		char[][] arr = {
				{'n','b','c','d','e','f'},
				{'z','n','a','b','c','l'},
				{'f','l','f','a','n','c'}};
		String toFind = "bnf";
		int L = 6;
		int D = 3;
		boolean found = false;
		int matchct = 0;
		int loopct = 0;
		for(int i=0; i<D; i++) {
			for(int j=0; j<L; j++) {
				char ch = arr[i][j];
				int iMem = i;
				int jMem = j;
				if(ch == toFind.charAt(0)) {
					matchct=1;
					int p=1;
					for(p=1; p<toFind.length(); p++) {
						char nc = toFind.charAt(p);
						//check left
						if(j>0 && arr[i][j-1] == nc){ matchct++; j=j-1;}
						else if(j<L-1 && arr[i][j+1] == nc){ matchct++; j=j+1;} 
						else if(i>0 && arr[i-1][j] == nc){matchct++; i=i-1;}
						else if(i<D-1 && arr[i+1][j] == nc){matchct++; i=i+1;}
						else if(i>0 && j>0 && arr[i-1][j-1] == nc){matchct++; i=i-1; j=j-1;}
						else if(i>0 && j<L-1 && arr[i-1][j+1] == nc){matchct++; i=i-1; j=j+1;}
						else if(i<D-1 && j>0 && arr[i+1][j-1] == nc){matchct++; i=i+1; j=j-1;}
						else if(i<D-1 && j<L-1 && arr[i+1][j+1] == nc){
							matchct++; 
							i=i+1;j=j+1;
						}

						if(matchct >= toFind.length()) {
							found = true;
							break;								
						}
					}
					if(p == toFind.length()) {
						i = iMem;
						j = jMem;
					}
				}
				if(found) {
					break;
				}
			}
			if(found) {
				break;
			}
		}
		System.out.println("isFound "+found+" loops "+loopct);	
	}

	
	public static void mergeArraysRemovingCommon() {
		int[] a1 = {2,6,8,9,13,18,21,36};
		int[] a2 = {3,6,9,12,17,21,28};
		int i=0;
		int j=0;
		int k = 0;
		int L1 = a1.length;
		int L2 = a2.length;
		int[] merged = new int[L1+L2];
		while(i<L1 || j<L2) {
			if((i<L1 && j<L2 && a1[i] < a2[j])
					|| (i<L1 && j>=L2)) {
				merged[k] = a1[i];
				i++;
			}
			else if((i<L1 && j<L2 && a1[i] > a2[j])
					|| (j<L2 && i>=L1)) {
				merged[k] = a2[j];
				j++;
			}
			else if(i<L1 && j<L2 && a1[i] == a2[j]) {
				merged[k] = a2[j];
				j++;
				i++;
			}
			k++;
		}
	}
	
	public static int gcd(int p, int q) {
		if(q == 0) {
			return p;
		}
		else {
			return gcd(q, p%q);
		}
	}
	
	public static String intToBin(int N) {
		if(N <= 1) {
			return "1";
		} else {
			return intToBin(N/2) + N%2;
		}
	}
	
	public static void countwords() {
		String s = "at    ";
		int count = 0;
		int length = s.length()-1;
		char prev = ' ';

		for(int i=length; i>=0; i--) {
			char c = s.charAt(i);
			if(c != prev && prev == ' ') {
				count++;
			}
			prev = c;
		}
		System.out.println(count);
	}
	
	public static void countwords1() {
		String s = "  \n  ";
		int count = 0;
		int length = s.length()-1;
		char prev = ' ';
		Set<Character> tokens = new HashSet<>();
		tokens.add(' ');
		tokens.add('\n');
		tokens.add('\t');
		for(int i=length; i>=0; i--) {
			char c = s.charAt(i);
			if(!tokens.contains(c) && tokens.contains(prev)) {
				count++;
			}
			prev = c;
		}
		System.out.println("word ct "+count);
	}
	
	public static String convertToIP(String binaryIP) {
		if(binaryIP == null || binaryIP.length() != 32) {
			return "0";
		}
		StringBuilder ip = new StringBuilder();
		int pow = 0;
		int v = 0;
		for(int i=binaryIP.length()-1; i>=0; i--) {
			char c = binaryIP.charAt(i);
			int d = c - '0';
			if(d < 0 || d > 1) { return "0"; }
			v = v+(d*(int)Math.pow(2, pow));
			if(i % 8 == 0) {
				ip.insert(0, v);
				if(i > 0) {
					ip.insert(0,'.');
				}
				pow = -1;
				v = 0;
			}
			pow++;
		}
		System.out.println("ip "+ip);
		return ip.toString();
	}
	
	public static int sumdigits(int n) {
		int sum = 0;
		while (n > 0){
			sum += n%10;
			if(sum>=10) {
				sum = sum%10+(sum/10)%10;
			}
			n/=10;
		}
		return sum;
	}
	
	public static int sumdigits2(int n) {

		return (n%9 == 0 && n != 0) ? 9 : n%9;
	}
	
	public static void sumWithoutPlusOp(int a, int b) {
		while(b != 0) {
			int carry = a & b;
			a = a ^ b;
			b = carry << 1;
		}
		System.out.println("sum "+a);
	}
	

	public static void main(String[] args) {
		System.out.println(reverseByArray("valet"));
		System.out.println("fact 4="+fact(4));
		System.out.println(reverseByRecursion("toysand  new"));
		fibonacci(8);
		System.out.println(fibonacciR(8));
		reverseWords("a new cent");
		System.out.println("sum="+findHarmonicSum(10));
		System.out.println(isPalindrome2(532235));
		replaceSpaces("space string");
		printTriangle(4);
		
		String s = "I am student a b c";
		reverseWordsWithoutSplit(s);
		hanoi(3, 1, 3, 2);
		//System.out.println(hanoi);
		numDigitCount(118, 1);
		System.out.println("i num "+isNumeric("-2342.09"));
		findMaxOnes();
		System.out.println(permuteString("abc"));
		List<Integer> ilist = new ArrayList<>();
		ilist.add(1);
		ilist.add(2);
		ilist.add(3);
		System.out.println(permuteInts(ilist));
		System.out.println("power "+power(2, 8));
		convertToHex(31);
		findInMatrix();
		mergeArraysRemovingCommon();
		System.out.println("gcd "+gcd(49, 21));
		System.out.println(intToBin(11));
		countwords();
		countwords1();
		int a = -5>>3;
		int b = -5 >>> 3;
		System.out.println("xx "+a);
		System.out.println(b);
		int k = 20;
		int n = 15;
		System.out.println(k^n);
		
		a = 128;
		System.out.println("erth"+a);

		  a ^= a >>> 32; 
		  a ^= a >>> 16; 
		  a ^= a >>>  8; 
		  a ^= a >>>  4; 
		  a ^= a >>>  2; 
		  a ^= a >>>  1; 
		  System.out.println(a & 1);
		  
		  a = (int)Math.pow(2, 30); b=4;
		  System.out.println(a*b);
		  convertToIP("01010000000100000000000000000001");
		  System.out.println(Math.abs(Integer.MIN_VALUE+1));
		  System.out.println(sumdigits(19227887));
		  System.out.println(sumdigits2(122));
		  Random rdm = new Random();
		  System.out.println(rdm.nextInt(5-3+1)+3);
		  countStairs(10);
		  sumWithoutPlusOp(45, 5);

	}
	
}
