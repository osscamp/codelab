package com.learning.btree;

import java.util.HashSet;
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
		System.out.println(String.valueOf(arr));

		int ws = 0;
		int we = 0;
		for(int i=0; i<arr.length; i++) {
			if(arr[i] == ' '){
				we = i-1;
				if(we > ws+1) {
					reverseByArray(arr, ws, we);
				}
				ws = we+2;
			}
		}
		System.out.println(String.valueOf(arr));
	}
	
	public static long fact(int n) {
		if(n >=1) {
			return n*fact(n-1);
		}
		return 1l;
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
		for(int i=1; i<=numrows; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j=1; j<=i; j++) {
				sb.append(j).append(" ");
			}	
			System.out.println(sb.toString());
		}
	}
	
	public static Set<String> permuteString(String input) {
	    Set<String> set = new HashSet<String>();
	    if (input == "")
	        return set;
	    Character a = input.charAt(0);
	    if (input.length() > 1)
	    {
	        input = input.substring(1);
	        Set<String> permSet = permuteString(input);
	        for (String x : permSet)
	        {
	            for (int i = 0; i <= x.length(); i++)
	            {
	                set.add(x.substring(0, i) + a + x.substring(i));
	            }
	        }
	    }
	    else
	    {
	        set.add(a + "");
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
//		int helpPeg;
//		String sol1, sol2, myStep, mySol;
//		if(n == 1) {
//			return fromPeg + " -> " + toPeg + "\n";
//		} else {
//			helpPeg = 6 - fromPeg- toPeg;
//			sol1 = hanoi(n-1, fromPeg, helpPeg);
//			myStep = fromPeg + " -> " + toPeg + "\n"; 
//			sol2 = hanoi(n-1, helpPeg, toPeg); 
//			mySol = sol1 + myStep + sol2; 
//			return mySol;
//		}
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
		int[][] a = {{0,0,1,1,1},{0,0,0,0,1},{0,0,1,1,1},{0,1,1,1,1},{0,0,0,1,1}};
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
	

	public static void main(String[] args) {
		System.out.println(reverseByArray("valet"));
		System.out.println("fact 4="+fact(4));
		System.out.println(reverseByRecursion("toysand new"));
		fibonacci(8);
		System.out.println(fibonacciR(8));
		reverseWords("a new cent");
		System.out.println("sum="+findHarmonicSum(10));
		System.out.println(isPalindrome2(532235));
		replaceSpaces("space string");
		printTriangle(4);
		
		String s = "I  am student";
		reverseWordsWithoutSplit(s);
		hanoi(2, 1, 3, 2);
		//System.out.println(hanoi);
		numDigitCount(895, 9);
		System.out.println("i num "+isNumeric("-2342.09"));
		findMaxOnes();
		
	}
	
}
