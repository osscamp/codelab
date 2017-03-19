package com.learning.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * substring search using brute force, KMP, Boyer-Moore, Rabin karp methods
 * @author sushukla
 *
 */
public class SubstringSearch {
	
	public static int substrBrute(String p, String s) {
		if(p == null || s == null) {
			return -1;
		}
		int S = s.length();
		int P = p.length();
		for(int i=0; i<S-P; i++) {
			int j = 0;
			while(j < P && p.charAt(j) == s.charAt(i+j)) {
				j++;
			}
			if(j==P) {
				return i;
			}
		}
		return -1;
	}
	
	public static int subStrBackup(String p, String s) {
		if(p == null || s == null) {
			return -1;
		}
		int S = s.length();
		int P = p.length();
		for(int i=0, j=0; i<S && j<P; i++) {
			if(s.charAt(i) == p.charAt(j)) {
				j++;
			} else {
				i -= j;
				j=0;
			}
			if( j== P) {
				return i-P+1;
			}
		}
		return -1;
	}
	
	public static int boyerMooreSubstr(String s, String p) {
		if(s == null || p == null || p.length() > s.length()) return -1;
		//create index for pattern
		int P = p.length()-1;
		int S = s.length()-1;
		char[] id = new char[256];
		Arrays.fill(id, Character.MAX_HIGH_SURROGATE);
		for(int i=0; i<=P; i++) {
			char pc = p.charAt(i);
			id[pc] = (char) (i);
		}
		int i=P, j=P;
		while(i <= S) {
			for(; i>=0 && j>= 0 && s.charAt(i) == p.charAt(j); i--,j--) {
			}
			if(j < 0) {
				return i+1;
			}
			int pidx = id[s.charAt(i)]; //do not include match > j
			if(pidx <= j && pidx != Character.MAX_HIGH_SURROGATE) {
				i += (P-pidx);
			} else {
				i += P+1;
			}
			j = P;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int v = SubstringSearch.substrBrute("actg", "acgtacggactgca");
		System.out.println("Match ind "+v);
		v = SubstringSearch.subStrBackup("act", "acgactgca");
		System.out.println("substr bk "+v);
		//v = SubstringSearch.boyerMooreSubstr("actg", "");
		v = SubstringSearch.boyerMooreSubstr("AABAABAAAA", "AABAAA");
		System.out.println("BM sub "+v);
		v = SubstringSearch.boyerMooreSubstr("BAABAABABABB", "ABAB");
		System.out.println("BM sub last "+v);
		//"A A B A A B A A A A";
		//"A A B A A A"
	}

}
