package com.learning.btree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

public class MiscQ3 {
	
	public static void evaluate() {
		String str = "2+134+4*52+4*3+6";
		//String str = "+55*1+6";
		int addresult = 0;
		int multiplyresult = 1;
		char prevop = '+';
		for(int i=0; i<str.length(); i++) {
			char ch = str.charAt(i);
			int conv = ch - '0';
			boolean isDigit = (conv >= 0 && conv <= 9);
			int n = 0;
			while (isDigit) {
				n = n*10+conv;
				i++;
				if(i < str.length()) {
					ch = str.charAt(i);
					conv = ch - '0';
					isDigit = (conv >= 0 && conv <= 9);
				} else {
					isDigit = false;
				}
			}
			if(i == str.length()) {
				if(prevop == '*') {
					addresult += multiplyresult*n;
				} else {
					addresult+=n;
				}
			}
			else if(ch == '+' && prevop == '+') {
				addresult+=n;
				prevop = ch;
			} else if(ch == '*' && prevop == '+') {
				multiplyresult *= n;
				prevop = ch;
			} else if(ch == '*' && prevop == '*') {
				multiplyresult*=n;
				prevop = ch;
			} else if(ch == '+' && prevop == '*') {
				multiplyresult*=n;
				addresult =multiplyresult+addresult;
				multiplyresult = 1;
				prevop = ch;				
			}
		}
		System.out.println(addresult);
	}
	
	public static void getLongestConsecutiveChars() {
		String st = "thiiis iis aa teeeest sennteennnnnce";
		int maxrepeat = 0;
		int repeat = 1;
		char maxrepeatingchar = Character.MIN_VALUE;
		for(int i=0; i<st.length()-1; i++) {
			if(st.charAt(i) == st.charAt(i+1)) {
				repeat++;
				if(repeat > maxrepeat) {
					maxrepeat = repeat;
					maxrepeatingchar = st.charAt(i);
				}
			}
			else {
				repeat =1;
			}
		}
		System.out.println("occurence "+maxrepeat+" char "+maxrepeatingchar);
	}
	
	public static void getAnagramClasses() throws Exception {
		FileReader reader = new FileReader(new File("words.txt"));
		BufferedReader br = new BufferedReader(reader);
		String line = null;
		Map<Integer, List<String>> anagrams = new HashMap<>();
		while((line = br.readLine()) != null) {
			String word = line.trim();
			int score = wordScore(word);
			List<String> anwords = anagrams.get(score);
			if(anwords != null) {
				anwords.add(word);
			} else {
				anwords = new ArrayList<>();
				anwords.add(word);
				anagrams.put(score, anwords);
			}
		}
		System.out.println(anagrams);
		br.close();
	}
	
	public static int wordScore(String word) {
		int score = 0;
		char[] a = word.toCharArray();
		for(char c : a) {
			int num = (int)(c-'0');
			//if(num > 0) {
				score+=Math.abs(num);
			//}
		}
		return score;
	}
	
	public static void rotateArray() {
		char[] data = {'w','e','r','e','w','i','t','h','a','l'};
		int rotateby = 4;
		rotateby = data.length-rotateby;
		int l = 0;
		int r = rotateby-1;
		revrange(data, l, r);
		l = rotateby;
		r = data.length-1;
		revrange(data, l, r) ;
		revrange(data, 0, r);
		StringBuilder sb = new StringBuilder();
		
		for(char c: data) {
			sb.append(c);
		}
		System.out.println(sb);
	}
	
	public static void revrange(char[] data, int l, int r) {
		while(l < r) {
			char tmp = data[l];
			data[l] = data[r];
			data[r] = tmp;
			l++;
			r--;
		}
	}
	
	public static int maxSumSubArr() {
		int[] a = {3, 7,2,4,-5,6,-7,-4,-8,10,11};
		int maxsofar = 0;
		int maxendhere = 0;
		for(int i=0; i<a.length; i++) {
			maxendhere = Math.max(maxendhere+a[i], 0);
			maxsofar = Math.max(maxsofar, maxendhere);
		}
		return maxsofar;
	}
	
	public static void findMinMatch() {
		String str = "abbcbcba";
		Set<Character> toFind = new HashSet<>(Arrays.asList(new Character[]{'a','b','c'}));
		Map<Character, List<Integer>> idx = new HashMap<>();
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			List<Integer> indexl = idx.get(c);
			if(indexl == null) {
				indexl = new ArrayList<>();
				indexl.add(i);
				idx.put(c, indexl);
			}else{
				indexl.add(i);
			}
		}
		System.out.println(idx);
		int findl = toFind.size();
		if(findl > idx.size()) {
			System.out.println("no substr");
		}
		char zero = str.charAt(0);
		List<Integer> list = idx.get(zero);
	}
	
	//find mode of an array with elements in range 0-100
	public static void rangeMode() {
		int[] a = {0,0,11,12,13,45,23,78,4,6,98,14,15,11,19,0,11};
		int[] idx = new int[101];
		int max = -1;
		int maxid = -1;

		for(int i=0; i<a.length; i++) {
			if(a[i] < 0 || a[i] > 100) {
				continue;
			}
			if(++idx[a[i]] > max){
				max = idx[a[i]];
				maxid = a[i];
			}
		}

		System.out.println("mode "+maxid);
	}
	
	public static void arrayFreq() {
		int[] arr = {8,8,8,8,8,8,8,16,16,16,17};
		int mrange = arr[arr.length-1];
		int[] hash = new int[mrange+1];
		for(int i=0; i<arr.length; i++) {
			hash[arr[i]]++;
		}
		for(int i=0; i<hash.length; i++) {
			if(hash[i] > 0) {
				System.out.println(i+":"+hash[i]);
			}
		}
	}
	
	public static void arrayFreqLogn() {
		int[] arr = {8,8,8,8,8,8,9,16,16,16,17};
		int i=0;
		while(i<arr.length) {
			int end = findEndIdx(arr, arr[i], i, arr.length);
			System.out.println(arr[i] +":" +(end-i+1));
			i = end+1;
		}
	}
	
	public static int findEndIdx(int[] a, int n, int l, int r) {
		int idx = -1;
		while(l<r) {
			int mid = l+(r-l)/2;
			if(n < a[mid]) {
				r = mid - 1;
			} else if(n > a[mid]) {
				l = mid + 1;
			} else {
				idx = mid;
				if(idx < a.length-1 && a[idx] == a[idx+1]) {
					return findEndIdx(a, n, mid+1, r);
				} else {
					return idx;
				}
			}
		}
		return idx;
	}
	
	//unfinished
	public static void kNearest() {
		class Point1 {
			int x; int y;
			public Point1(int x, int y) {
				this.x = x;
				this.y = y;
			}
			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return "("+x+","+y+")";
			}
		}
		List<Point1> pList = new ArrayList<>();
		pList.add(new Point1(6,8));
		pList.add(new Point1(1,2));
		pList.add(new Point1(3,6));
		pList.add(new Point1(2,7));
		pList.add(new Point1(7,9));
		pList.add(new Point1(11,14));
		pList.add(new Point1(4,3));
		
		List<Point1> ysort = new ArrayList<>();
		ysort.addAll(pList);
		List<Point1> xsort = new ArrayList<>();
		xsort.addAll(pList);
		Collections.sort(ysort, new Comparator<Point1>() {

			@Override
			public int compare(Point1 o1, Point1 o2) {
				if(o1 != null && o2 != null) {
					if( o1.y > o2.y ) return -1;
					else if( o1.y < o2.y ) return 1;
					else return 0;
				}
				return 0;
			}
		});
		Collections.sort(xsort, new Comparator<Point1>() {

			@Override
			public int compare(Point1 o1, Point1 o2) {
				if(o1 != null && o2 != null) {
					if( o1.x > o2.x ) return -1;
					else if( o1.x < o2.x ) return 1;
					else return 0;
				}
				return 0;
			}
		});
		System.out.println(ysort);
		System.out.println(xsort);
		
		Point1 testPoint = new Point1(5,5);
		int k = 3;
		
		int l = 0;
		int r = ysort.size()-1;
		while(l<r) {
			int mid = l+(r-l)/2;
			Point1 midp = ysort.get(mid);
			if(midp.y > 5) {
				l = mid+1;
			} else if(midp.y < 5) {
				r = mid - 1;
			}
		}
		System.out.println("l"+l+" "+r);
		int l1 = l;
		int r1 = r;
		
		l = 0;
		r = xsort.size()-1;
		while(l<r) {
			int mid = l+(r-l)/2;
			Point1 midp = xsort.get(mid);
			if(midp.x > 5) {
				l = mid+1;
			} else if(midp.x < 5) {
				r = mid - 1;
			}
		}
		System.out.println("l"+l+" "+r);
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int yplus = l1;
		int yminus = l1;
		int xplus = l;
		int xminus = l;
		while(yplus < ysort.size() && yminus >= 0) {
			Point1 idxP = ysort.get(yplus);
			int absdis = Math.abs(idxP.y-5)+Math.abs(idxP.x-5);
			pq.add(absdis);
			yplus++;
			idxP = ysort.get(yminus);
			absdis = Math.abs(idxP.y-5)+Math.abs(idxP.x-5);
			pq.add(absdis);
			yminus--;
		} 
		while (xplus < xsort.size() && xminus >= 0) {
			Point1 idxP = xsort.get(xplus);
			int absdis = Math.abs(idxP.y-5)+Math.abs(idxP.x-5);
			pq.add(absdis);
			xplus++;

			idxP = xsort.get(xminus);
			absdis = Math.abs(idxP.y-5)+Math.abs(idxP.x-5);
			pq.add(absdis);
			xminus--;
			
		}
		System.out.println(pq);
	}
	
	public static void countSort() {
		int k = 9;
		int[] a = {1,2,2,8,5,2,1,5,4,8,3,2,2,5,1};
		int[] aux = new int[k];
		int[] sorted = new int[a.length];
		for(int i=0; i<a.length; i++) {
			aux[a[i]]++;
		}
		for(int i=1; i<aux.length; i++) {
			aux[i] = aux[i] + aux[i-1];
		}
		for(int i=a.length-1; i>=0; i--) {
			//int ai = a[i];
			//int auxai = aux[ai];
			sorted[--aux[a[i]]] = a[i];
			//aux[a[i]] = aux[a[i]] - 1;
		}
		for(int i=0; i<sorted.length; i++) {
			System.out.println("nn "+sorted[i]);
		}
		
	}
	
	public static void countsortStrings() {
		int k = 3;
		String[] a = {"hi","mi","hi","li","mi","li","hi"};
		int[] mapped = new int[a.length];
		for(int i=0; i<a.length; i++) {
			if(a[i].equals("hi")) mapped[i] = 0;
			else if(a[i].equals("mi")) mapped[i] = 1;
			else if(a[i].equals("li")) mapped[i] = 2;
		}
		int[] aux= new int[k];
		for(int i=0; i<mapped.length; i++) {
			aux[mapped[i]]++;
		}
		for(int i=1; i<aux.length; i++) {
			aux[i] += aux[i-1];
		}
		int[] sorted = new int[a.length];
		for(int i=a.length-1; i>=0; i--) {
			sorted[--aux[mapped[i]]] = mapped[i];
		}
		for(int i=0; i<sorted.length; i++) {
			if(sorted[i] == 0) a[i] = "hi" ;
			else if(sorted[i] == 1) a[i] = "mi";
			else if(sorted[i] == 2) a[i] = "li";
		}
		System.out.println(sorted);
	}
	
	//find m missing in  a sorted array from 1-9
	//unfinished
	public static void findMissing() {
		int N = 11;
		int[] a = {1,2,3,4,5,7,9,11};
		int l = 0;
		//int r = a.length-1;
		int[] missing = new int[N-a.length];
		for(int j=0; j<missing.length; j++) {
			//int l = 0;
			int r = a.length-1;
			while(l <= r) {
				int mid = l+(r-l)/2;
				if(a[mid] == (mid+1+j)) {
					l = mid + 1;
				}
				else if(a[mid] == (mid+1)+1+j) {
					l = mid;
					missing[j] = mid + 1 +j;
					break;
				} else if(a[mid] > (mid+1)+1+j ) {
/*					if(mid > 0 && a[mid] - a[mid-1] > 2) {
						for(int k=a[mid-1]+1; k<a[mid]; k++){
							missing[j++] = k;
						}
					}*/
					r--;
				}
			}
		}
		for(int i: missing) {
			System.out.println("missing "+i);
		}
	}
	
	public static void partitionByZero() {
		int[] a = {3,6,0,5,0,8,9,4,0};
		int i=0;
		int pivot = 0;
		for(int j=0; j<a.length; j++) {
			if(a[j] > pivot ) {
				int tmp = a[i];
				a[i] = a[j];
				a[j] = tmp;
				i++;
			}
		}
	}
	
	public static void genSeqV1(int N) {
		List<Integer> current = new ArrayList<>();
		List<Integer> next = new ArrayList<>();
		current.add(1);
		for(int i=1; i<=N; i++) {
			for(int j=0; j<current.size(); j++) {
				int repeat = 1;
				int thisnum = current.get(j);
				while(j+1 < current.size() && current.get(j) == current.get(j+1)) {
					repeat++;
					j++;
				}
				next.add(repeat);
				next.add(thisnum);
			}
			System.out.println(next);
			current = next;
			next = new ArrayList<>();
		}
	}
	
	public static boolean isPalindrome(String s) {
		char[] ch = s.toCharArray();
		int l=0;
		int r=ch.length-1;
		while(l < r) {
			if(Character.toLowerCase(ch[l]) == Character.toLowerCase(ch[r])) {
				l++;
				r--;
			}
			else if(!Character.isLetterOrDigit(ch[l])) { l++; }
			else if(!Character.isLetterOrDigit(ch[r])) { r--; }
			else {
				return false;
			}
		}
		return true;

	}
	
	//unfinished
	public static void find10LetterSeq() {
		//String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
		String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
		char[] ca = s.toCharArray();
		List<char[]> list = new ArrayList<>();
		for(int j=0; j<ca.length-10; j++) {
			char[] seq = new char[10];
			int i=j;
			for(int p=0; p<10 & i<ca.length; p++,i++) {
				
				seq[p] = ca[i];
			}
			list.add(seq);
			int end = i+10;
			if(end > ca.length) { continue; }
			boolean match = true;
			int k = 0;
			int tctr = i;
			while(tctr < end) {
				//match = true;
				char cacr = ca[tctr];
				char seqr = seq[k];
				if(cacr != seqr) {
					match = false;
					i++;
					tctr = i;
					k=0;
					continue;
					//break;
				}
				k++;
				tctr++;
				if(k == 9) {
					match=true;
					break;
				}
			}
			if(match) {
				StringBuilder sb = new StringBuilder();
				for(char cc : seq) {
					sb.append(cc);
				}
				System.out.println(sb);
			}

		}
	}
	
	//important
	public static boolean isOneEditDistance(String st1, String st2) {
		int distance = Math.abs(st1.length()-st2.length());
		if(distance > 1) {
			return false;
		}
		char[] ch1 = st1.toCharArray();
		char[] ch2 = st2.toCharArray();
		int minl = Math.min(ch1.length, ch2.length);
		for(int i=0; i<minl; i++) {

			if(ch1[i] != ch2[i]
					&& !(ch1.length > ch2.length && ch1[i] != ch2[i] && ch1[i+1] == ch2[i]) 
					&& !(ch1.length < ch2.length && ch1[i] != ch2[i] && ch1[i] == ch2[i+1]) ) {
				distance++;
				if(distance > 1) {
					return false;
				}
			}
		}
		return distance == 1;
	}
	
	public static void isCombination() {
		Set<String> dict = new HashSet<>();
		dict.add("hello");
		dict.add("world");
		dict.add("super");
		dict.add("man");
		
		String test = "helloand";
		boolean isExist = false;
		for(int i=1; i<=test.length(); i++) {
			isExist = false;
			String toChk = test.substring(0, i);
			if(dict.contains(toChk)) {
				isExist = true;
				test = test.substring(i);
				i = 0;
			}
		}
		System.out.println("exist "+isExist);
	}
	
	public static void findPairSums() {
		int[] a = {3,4,7,6,2,9,8,12,14};
		Map <Integer, Integer> imap = new HashMap<>();
		for(int i=0; i<a.length; i++) {
			imap.put(a[i], i);
		}
		Arrays.sort(a);
		int i = 0;
		int j = a.length - 1;
		int sum1 = 0;
		while(j -i >= 3) {
			while(i<j/2) {
				sum1 = a[i]+a[j];
				List<Integer> list = find2Sum(a, i+1, j-1, sum1);
				int listsz = list.size();
				for(int kk = 0; kk<listsz;) {
					System.out.println(imap.get(a[i])+" "+imap.get(a[j])+" , "+imap.get(list.get(kk))+" "+imap.get(list.get(kk+1))+" ");
					kk+=2;
				}
				i++;
			}
			i=0;
			j--;
		}
	}
	
	public static List<Integer> find2Sum(int[] a, int l, int r, int sum) {
		int rsum = 0;
		List<Integer> twoNums = new ArrayList<>();
		while(l < r) {
			rsum = a[l] + a[r];
			if(rsum == sum) {
				twoNums.add(a[l]);
				twoNums.add(a[r]);
				l++;
				r--;
			} else if(rsum > sum) {
				r--;
			} else if(rsum < sum) {
				l++;
			}
		}
		return twoNums;
	}
	
	public static void getValidCharSubStrings() {
		String s = "1234567";
		Set<Integer> iset = new HashSet<>();
		char[] ch = s.toCharArray();
		for(int i=0; i<ch.length; i++) {
			int num1 = ch[i] - '0';
			if(num1 > 0)iset.add(num1);
			if(i<ch.length-1) {
				int num2 = num1*10+(ch[i+1]-'0');
				if(num2 < 27) {
					iset.add(num2);
				}
			}
		}
		System.out.println("valid combs "+iset.size()+" "+iset);
	}
	
	//to be done by reservoir sampling in one pass
	public static void getRandomMax() {
		int[] a = {2,3,9,3,4,2,9,8,6,5,9,7};
		int max = Integer.MIN_VALUE;
		List<Integer> idx = new ArrayList<>();
		for(int i=0; i<a.length; i++) {
			if(a[i] > max) {
				max = a[i];
				idx.clear();
				idx.add(i);
			}else if(a[i] == max) {
				idx.add(i);
			}
		}
		int sz = idx.size();
		int rdm = new Random().nextInt(sz);
		System.out.println("random max idx ="+idx.get(rdm));
	}
	
	public static void getRandomMaxInPlaceO1() {
		int[] a = {2,3,9,3,4,2,9,8,6,5,9,7};
		int max = Integer.MIN_VALUE;
		int maxidx = 0;
		int count = 1;
		for(int i=0; i<a.length; i++) {
			if(a[i] > max) {
				max = a[i];
			}else if(a[i] == max) {
				count++;
				double rdm = new Random().nextDouble();
				if(rdm < 1.0/count) {
					maxidx = i;
				}
			}
		}
		System.out.println("Max idx O1 "+maxidx);
	}
	
	public static void pset() {
		String str = "1234";
		printPowerSet(str.length()-1, 0, "", str);
	}
	
	public static void printPowerSet(int N, int n, String sub, String orig) {
		if(n == N) {
			System.out.println(orig);
			return;
		}
		for(int k=0; k<=n+1; k++) {
			if(n+k > N) {
				break;
			}
			sub = orig.substring(k, n+k);
			for(int i=n+k; i<=N; i++) {
				String subsub = sub+orig.charAt(i);
				System.out.println(subsub);
	
			}
		}
		printPowerSet(N, n+1, sub, orig);
	}
	
	public static void partitionzeros() {
		int[] a = {1,0,2,0,0,3,4};
		int pivot = 0;
		int i = 0;
		for(int j=0; j<a.length; j++) {
			if(a[j] <= pivot) {
				int tmp = a[j];
				a[j] = a[i];
				a[i] = tmp;
				i++;
			}
		}
	}
	
	public static void reverseSentence() {
		String st = "the boy ran";
		
		char[] sub = st.toCharArray();
		//reverseIt(sub, 0, sub.length-1);
		int r = sub.length - 1;
		int l = 0;
		for(int i=sub.length-1; i>=0; i--) {
			if(sub[i] == ' ' || i==0) {
				l = i>0 ? i+1 : i;
				while(l < r) {
					char tmp = sub[l];
					sub[l] = sub[r];
					sub[r] = tmp;
					l++;
					r--;
				}
				r=i-1;
			}
		}
		System.out.println(new String(sub));
	}
	
	public static void removeDupInOrder() {
		List<String> strs = new ArrayList<>();
		strs.add("dog");
		strs.add("cat");
		strs.add("dog");
		strs.add("dog");
		strs.add("fish");
		
		Set<String> set = new LinkedHashSet<>();
		for(String s : strs) {
			set.add(s);
			
		}
		System.out.println(set);
	}
	
	//to be done with stack
	public static void findAbsolutePath() {
		String abs = "/usr/bin/mail/";
		String rel = "usr/bin/../../pqr/../abc";
		char[] a = (rel).toCharArray();
		for(int i = rel.length()-1; i>=2; i--) {
			int shiftStart = i;
			int fac = 1;
			while(shiftStart >= 2 && a[shiftStart]=='/' && a[shiftStart-1] == '.' && a[shiftStart-2] == '.') {
				shiftStart=shiftStart-3;
				fac++;
			}
			int l = 0;
			int stop = shiftStart;
			for(l = shiftStart -1; l>=0 && fac > 1 && shiftStart < i; l--) {
				if(a[l] == '/') {
					fac--;
				}
			}
			shiftStart = (l == -1) ? 0 : l+2;
			int k=0;
			int p = 0;
			boolean shifted = false;
			for(k=shiftStart, p=0; k<a.length && p+i+1 < a.length && shiftStart < i-2; k++,p++) {
				a[k] = a[p+i+1];
				a[p+i+1] = ' ';
				shifted = true;
			}

			if(shifted) {
				//i = stop;
			}
			
		}
		System.out.println(new String(a).trim());
	}
	
	//unfinished - inplace countsort
	//arrange matrix such that a[i] = a[a[i]]. elements are 0:N-1
	public static void rearrangeArray() {
		int[] a = {3,2,1,0};
/*		Map<Integer, Integer> imap = new HashMap<>();
		for(int i=0; i<a.length; i++) {
			imap.put(a[i], a[a[i]]);

		}
		for(int i=0; i<a.length; i++) {
			a[i] = imap.get(a[i]);

		}*/
		int i=0;
		int tmp1 = a[i];
		a[i] = a[a[i]];
		replace(a, i, tmp1, 0);
		MaxHeap.printArray(a);
	}
	
	public static void replace(int[] a, int idx, int replacement, int depth) {
		depth++;
		if(depth >= a.length-1) {
			return;
		}
		int id = findIdx(a, idx);
		if(id == -1) {
			return;
		}
		int tmp = a[id];
		a[id] = replacement;
		replace(a, id, tmp, depth);
	}
	
	public static int findIdx(int[] a, int value) {
		int idx = -1;
		for(int i=0; i<a.length; i++) {
			if(a[i] == value) {
				idx = i;
			}
		}
		return idx;
	}
	
	public static void rearrangeArrayV2() {
		int[] a = {1,2,0,3};
		int i = a[0];
		int val = 0;
		while (i != 0) {
			int nexti = a[i];
			a[i] = val;
			val = i;
			i = nexti;
		}
		a[0] = val;
		MaxHeap.printArray(a);
	}
	
	//k-way merge - has to be done by heap
	public static void mergeLists() {
		List<Integer> l1 = Arrays.asList(new Integer[]{23,45,57,68});
		List<Integer> l2 = Arrays.asList(new Integer[]{13,25,37,78});
		List<Integer> l3 = Arrays.asList(new Integer[]{33,46,59,91});
		List<List<Integer>> lists = new ArrayList<>();
		lists.add(l1);
		lists.add(l2);
		lists.add(l3);
		mergeSortedLists(lists);
	}
	
	public static void mergeSortedLists(List<List<Integer>> lists) {
		if(lists == null || lists.size() == 1) { return; }
		List<Integer> out = new ArrayList<>();
		

		out.addAll(lists.get(0));
		for(int k=1; k<lists.size(); k++) {
			int i=0;
			int j=0;
			List<Integer> l1 = lists.get(k);
			List<Integer> temp = new ArrayList<>();
			while(i<l1.size() || j<out.size()) {
				if((i<l1.size() && j<out.size() && l1.get(i) < out.get(j))
						|| (j >= out.size() && i<l1.size())) {
					temp.add(l1.get(i));
					i++;
				}else if((i<l1.size() && j<out.size() && out.get(j) < l1.get(i))
						|| (i >= l1.size() && j<out.size())){
					temp.add(out.get(j));
					j++;
				}
			}
			out = temp;
		}
		System.out.println(out);
	}
	
	//important
	public static void phoneNumberTrans() {
		Map<Integer, List<Character>> map = new HashMap<>();
		map.put(2, Arrays.asList(new Character[]{'A','B','C'}));
		map.put(3, Arrays.asList(new Character[]{'D','E','F'}));
		map.put(4, Arrays.asList(new Character[]{'G','H','I'}));
		map.put(5, Arrays.asList(new Character[]{'J','K','L'}));
		map.put(6, Arrays.asList(new Character[]{'M','N','O'}));
		map.put(7, Arrays.asList(new Character[]{'P','Q','R','S'}));
		map.put(8, Arrays.asList(new Character[]{'T','U','V'}));
		map.put(9, Arrays.asList(new Character[]{'W','X','Y', 'Z'}));
		int num = 23;
		int copy = num;
		List<Integer> digs = new ArrayList<>();
		
		while(copy > 0) {
			int dig = copy % 10;
			copy /= 10;
			digs.add(0, dig);
		}
		List<List<Character>> thisList = new ArrayList<>();
		
		for(int key: digs) {
			List<Character> mapped = map.get(key);
			thisList.add(mapped);
		}
		List<StringBuilder> slist = new ArrayList<StringBuilder>();
		for(int i=0;i<thisList.size(); i++) {
			List<Character> tmp = thisList.get(i);
			slist = appendChars(tmp, slist, i, digs.size());

		}
		System.out.println("size "+slist.size()+" "+slist);
	}
	
	public static List<StringBuilder> appendChars(List<Character> list, List<StringBuilder> sblist, int level, int M) {
/*		if(level >= M) {
			return sblist;
		}*/
		List<StringBuilder> tlist = new ArrayList<>();

		for(int i=0; i<list.size(); i++) {
			Character c = list.get(i);
			if(level == 0) {
				tlist.add(new StringBuilder().append(c));
			} else {
				int psize = sblist.size();

				for(int j=0; j<psize; j++) {
					StringBuilder bb = sblist.get(j);
					StringBuilder ss = new StringBuilder().append(bb).append(c);
					tlist.add(ss);
				}
			}
		}
		return tlist;

		//appendChars(list, sblist, level);
	}
	
	public static void swap(int[] a, int n1, int n2, int level) {
		if(level == a.length-1) { 
			return; 
		}
		int tmp = a[n1];
		int tmp2 = a[a[n1]];
		swap(a, tmp, tmp2, level++);
		a[n1] = tmp2;
	}

	
	public static void main(String[] args) throws Exception{
		evaluate();
		findMinMatch();
		getLongestConsecutiveChars();
		rotateArray();
		getAnagramClasses();
		maxSumSubArr();
		rangeMode();
		arrayFreq();
		arrayFreqLogn();
		kNearest();
		countSort();
		countsortStrings();
		findMissing();
		partitionByZero();
		genSeqV1(5);
		System.out.println(isPalindrome(" A man a plan a canal -- Panama!  "));
		find10LetterSeq();
		isCombination();
		//findPairSums();
		getValidCharSubStrings();
		getRandomMax();
		getRandomMaxInPlaceO1();
		pset();
		partitionzeros();
		reverseSentence();
		removeDupInOrder();
		System.out.println("1 edit dist "+isOneEditDistance("cat", "acts"));
		findAbsolutePath();
		rearrangeArray();
		mergeLists();
		phoneNumberTrans();
		rearrangeArrayV2();
	}

}
