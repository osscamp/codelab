package com.learning.btree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JustifiedWordsCounter {
	
	//given a list of words and MXN columns and rows. find the number of words that can be fit in given rows and columns
	public static void justifiedCount() {
		int M = 9;
		int N = 2;
		List<String> wordList = new ArrayList<>();
		wordList.addAll(Arrays.asList("sir","search","into","it"));
		int totalLenRep = 0;
		for(String s : wordList) {
			totalLenRep+=s.length();
			totalLenRep++;
		}
		int factor = Math.min(1, (totalLenRep / (M*N) ));
		int[] p = new int[Math.max(wordList.size()*2*factor, M*N)];
		int k = 0;
		int pf = 0;
		while(pf < factor) {
			for(int i=0; i<wordList.size(); i++) {

				p[k] = k>0 ? i+1 : i;
				k=k+1;
				p[k] = p[k] + wordList.get(i).length();
				k=k+1;
			}
			pf++;
		}
		MaxHeap.printArray(p);
		int l=0, r=p.length-1;
		while(l < r) {
			int mid = l+(r-l)/2;
			if(M < p[mid]) {
				r = mid-1;
			} else if(M > p[mid]) {
				l = mid+1;
			} else{
				System.out.println("matched");
			}
		}
		System.out.println("l "+l+" r "+r);
		
	}
	
	public static void main(String[] args) {
		justifiedCount();
	}

}
