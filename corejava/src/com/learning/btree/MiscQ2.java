package com.learning.btree;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class MiscQ2 {
	
	public static List<List<Integer>> permute(List<Integer> input) {
		List<List<Integer>> output = new ArrayList<List<Integer>>();
        if (input.isEmpty()) {
            output.add(new ArrayList<Integer>());
            return output;
        }		
        Integer a = input.get(0);
		List<Integer> rest = input.subList(1, input.size());
		List<List<Integer>> perms = permute(rest);
		for(List<Integer> perm: perms) {
			List<List<Integer>> subLists = new ArrayList<List<Integer>>();
			for (int i = 0; i <= perm.size(); i++) {
				List<Integer> subList = new ArrayList<Integer>();
				subList.addAll(perm);
				subList.add(i, a);
				subLists.add(subList);
			}
			output.addAll(subLists);
			
		}
		System.out.println("sublis "+output);
		return output;
	}
	
	//wrong impl - has bug
	public static int findSubstring(String haystack, String needle) {
		char[] hay = haystack.toCharArray();
		char[] ned = needle.toCharArray();
		int index = -1;
		if(ned.length < 1) {
			return -1;
		}
		for(int i=0; i<hay.length-ned.length; i++) {
			if(ned[0] == hay[i]) {
				index = i;
				int k = i;
				for(int j=1; j<ned.length; j++) {
					k++;
					if(ned[j] != hay[k]) {
						index = -1;
						break;
					}
				}
			}
		}
		return index;
	}
	
	public static String encodeSpace(String input) {
		char[] a = input.toCharArray();
		int spacect = 0;
		for(int i=0; i<a.length; i++) {
			if(a[i] == ' ') {
				spacect++;
			}
		}
		char[] aux = new char[a.length + spacect*2];
		for(int i=a.length-1, j=aux.length-1; i>=0 && j>=0; i--,j--) {
			while(i>=0 && a[i] == ' ') {
				aux[j--] = '0';
				aux[j--] = '2';
				aux[j--] = '%';
				i--;
			}
			if(i >= 0) {
				aux[j] = a[i];
			}
		}
		System.out.println(String.valueOf(aux));
		return String.valueOf(aux);
	}
	
	public static boolean findInRowColumnSortedMatrix(int[][] a, int n) {
		boolean isFound = false;
		int N = a.length;
		int i=0;
		int j=N-1;
		while(i < N && j>0) {
			if(i<N && j >= 0 && a[i][j] == n) {
				isFound = true;
				break;
			}
			while(j >= 0 && a[i][j] > n) {
				j--;
			}
			while(i < N && a[i][j] < n) {
				i++;
			}

			
			//i++;
		}
		System.out.println("imax "+i+" jmax "+j+" isFound "+isFound);

		return isFound;
	}
	
	public static void findCommon() {
		char[] c1 = {'a','g','h','k','a','z','g','k','l','r'};
		char[] c2 = {'z','f','g','h','a','k','o','r','l'};
		char[] c3 = {'q','w','e','m','n','a','a','r','z'};
		numberOfCommonChars(new String[]{String.valueOf(c1),String.valueOf(c2),String.valueOf(c3)});
	}
	
	public static int numberOfCommonChars(String[] arrays) {
		int commonall = 0;
		int range = 'z';
		BitSet current = null;
		BitSet prev = null;
		for(String str : arrays) {
			current = new BitSet(range);
			char[] ca = str.toCharArray();
			for(char c: ca) {
				current.set(c);
			}
			if(prev != null) {
				current.and(prev);
			}
			prev = current;
		}		
		for(char k='a'; k <= 'z'; k++) {
			if(current.get(k)) {
				System.out.println("number common "+k);
				commonall++;
			}
		}
		System.out.println("number of common characters "+commonall);
		return commonall;
	}
	
	public static void mergeSortedArraysNoAux() {
		int[] a1 = {5,7,8,12,0,0};
		int[] a2 = {2,6};
		int a1filledIndex = 3;
		int l1 = a1.length;
		int l2 = a2.length;
		int a1mergedidx = l1-1;
		int a1idx = a1filledIndex;
		int a2idx = l2-1;
		while(a1idx >= 0 && a2idx >= 0) {
			if(a1[a1idx] > a2[a2idx]) {
				a1[a1mergedidx] = a1[a1idx];
				a1mergedidx--;
				a1idx--;
			} else {
				a1[a1mergedidx] = a2[a2idx];
				a1mergedidx--;
				a2idx--;			
			}
		}
		while(a1idx >= 0) {
			a1[a1mergedidx] = a1[a1idx];
			a1mergedidx--;
			a1idx--;
		}
		while(a2idx >= 0) {
			a1[a1mergedidx] = a2[a2idx];
			a1mergedidx--;
			a2idx--;
		}		
		StringBuilder sb = new StringBuilder();
		for(int i: a1) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}
	
	public static void findRangeOfNumbers() {
		int[] a = {0,1,1,3,3,3,3,3,3,3,8,8,10,23};
		findIndex(a, 0, a.length-1, 10);
	}
	
	public static int findIndex(int[] a, int l, int r, int n) {
		if(l <= r) {
			int mid = l + (r-l)/2;
			if( n == a[mid]) {
				int l1 = l;
				int r1 = mid - 1;
				int l2 = mid + 1;
				int r2 = r;
				int r1m = 0;
				int l2m = 0;
				while(l1 <= r1) {
					int mid1 = l1 + (r1-l1)/2;
					if(a[mid1] == n) {
						r1m = mid1;
						r1 = mid1 - 1;
					} else if(a[mid1] < n) {
						l1 = mid1+1;
					}
				}
				while(l2 <= r2) {
					int mid2 = l2 + (r2-l2)/2;
					if(a[mid2] == n) {
						l2m = mid2;
						l2 = mid2+1;

					} else if(a[mid2] > n) {
						r2 = mid2 - 1;

					}
				}

				System.out.println(l1+" "+r1+" "+l2+" "+r2);
				System.out.println("index range "+(r1m)+"->"+(l2m));
				return mid;
			}
			else if(n < a[mid]) {
				return findIndex(a, l, mid - 1, n);
			} else {
				return findIndex(a, mid+1, r, n);
			} 
		}
		return -1;
	}
	
	public static void wordWrap() {
		String s = "His    grip strength and right leg are improving. The left leg is improving but more slowly. He can speak, eat, etc.";
		int wrap = 15;
		s = s.trim();
		StringBuilder sb = new StringBuilder();
		List<Integer> wordB = new ArrayList<Integer>();
		wordB.add(0);
		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			if(i>0 && (c == ' ' && s.charAt(i-1) != ' '
					|| c != ' ' && s.charAt(i-1) == ' ')) {
				wordB.add(i);
			}
		}
		wordB.add(s.length());
		int lineCharLength = 0;
		boolean canIncludeNext = false;
		for(int i=1; i<wordB.size(); i+=2) {
			int i2 = wordB.get(i);
			int i1 = wordB.get(i-1);
			if(lineCharLength + (i2 - i1) <= wrap) {
				lineCharLength += (i2 - i1);
				if(i+2 < wordB.size() ) {
					int i4 = wordB.get(i+2);
					int i3 = wordB.get(i+1);
					if(lineCharLength + (i4 - i3) <= wrap) {
						canIncludeNext = true;
					} else {
						canIncludeNext = false;
					}
				}
			} else {
				lineCharLength = (i2 - i1);
				canIncludeNext = false;
			}
			if(lineCharLength <= wrap) {
				sb.append(s.substring(i1, i2));
				if(!canIncludeNext) {
					sb.append("\n");
					lineCharLength = 0;
				} else {
					sb.append(' ');
					lineCharLength++; // for space
				}
			} else {
				sb.append(s.substring(i1, i2)).append(' ');
				lineCharLength++; //for space
				if(!canIncludeNext) {
					sb.append("\n");
					lineCharLength = 0;
				}
			}
		}
		//System.out.println(wordB);
		System.out.println(sb);

	}
	
	public static boolean isNumber(String str) {
		boolean isNumeric = true;
		if(str == null || str.length() <= 0) {
			return false;
		}
		char[] ca = str.toCharArray();
		boolean dot = false;
		for(int i = 0; i<ca.length; i++) {
			if(i==0 && (ca[i] == '+' || ca[i] == '-')) {
				continue;
			}
			if(ca[i]-'0' < 0 || ca[i]-'0' > 9) {
				if(ca[i] == '.' && !dot) {
					dot = true;
				} else {
					isNumeric = false;
					break;
				}
			}
		}
		return isNumeric;
		
	}
	
	public static void selfExcludingProduct() {
		int[] a = {3,1,4,2};
		int[] ret = new int[a.length];
		long prod = 1l;
		for(int i=0; i<a.length; i++) {
			prod *= a[i];
		}
		for(int i=0; i<a.length; i++) {
			ret[i] = (int)prod/a[i];
		}
		for(int v:ret) System.out.println(v);
	}
	
	public static void getInfluencer() {
		boolean[][] followM = {{false, true, false},{false,false,false},{true,true,false}};
		int N = followM.length;
		for(int i=0; i<N; i++) {
			boolean candidate = true;
			for(int j=0; j<N; j++) {
				if(followM[i][j] == true) {
					candidate = false;
					break;
				}
			}
			if(candidate) {
				for(int k=0; k<N; k++) {
					if(k == i) {
						continue;
					}
					if(followM[k][i] == false) {
						candidate = false;
					}
				}
			}
			if(candidate) {
				System.out.println("influencer "+i+", "+i);
			}
		}
		
	}
	
	public static void main(String[] args) {
		List<Integer> ll = new ArrayList<>();
		ll.add(4);
		ll.add(12);
		ll.add(5);
		permute(ll);
		System.out.println(findSubstring("balalava", "alav"));
		encodeSpace("  a and then");
		int[][] a = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
		findInRowColumnSortedMatrix(a, 11);
		mergeSortedArraysNoAux();
		findCommon();
		//findRangeOfNumbers();
		wordWrap();
		System.out.println(isNumber("0.32"));
		selfExcludingProduct();
		getInfluencer();
	}

}
