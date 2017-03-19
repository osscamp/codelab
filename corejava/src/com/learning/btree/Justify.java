package com.learning.btree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Justify {

	/*
	 * Implement the 'full justification' form of typographic alignment, given
	 * some arbitrary text and a margin length. See here:
	 * https://en.wikipedia.org/wiki/Typographic_alignment#Justified
	 */
	public static void justify() {
		String para = "Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining";
		List<String> text = Arrays.asList(para.split("\\s+"));//Arrays.asList("this","is","a","test","string","ten","cent","20");
		int W = 22;
		List<StringBuilder> jtextList = new ArrayList<StringBuilder>();
		List<String> lineWords = new ArrayList<>();
		int linecharct = 0;
		for(int i=0; i<text.size(); i++) {
			String s = text.get(i).trim();
			if((linecharct+s.length()) + (lineWords.size()-1) < W) {
				lineWords.add(s);
				linecharct += s.length();
			} else {
				StringBuilder v = lineJustify(lineWords, linecharct, W);
				jtextList.add(v);
				lineWords = new ArrayList<>();
				lineWords.add(s);
				linecharct = s.length();
			}
		}
		StringBuilder v = lineJustify(lineWords, linecharct, W);
		jtextList.add(v);
		for(StringBuilder ss :jtextList) {
			System.out.println(ss);
		}
	}
	
	public static StringBuilder lineJustify(List<String> list, int charct, int W) {
		StringBuilder sb = new StringBuilder();
		if(list.size() == 0) {
			return sb;
		}
		int wordct = list.size();
		int spacect = wordct - 1;

		int used = charct+spacect;
		used = Math.min(W, used);
		int extraspace = W-used;
		if(extraspace == 0) {
			for(int i=0; i<list.size(); i++){
				sb.append(list.get(i));
				if(i < list.size()-1) {
					sb.append(' ');
				}
			}
			return sb;
		}
		if(wordct == 1) {
			int lsp = extraspace / 2;
			int rsp = extraspace - lsp;
			for(int i=0; i<lsp; i++) {
				sb.insert(0, ' ');
			}
			sb.append(list.get(0));
			for(int i=0; i<rsp; i++) {
				sb.append(' ');
			}
		} else{
			int spaceslots = wordct - 1;
			int slotspacect = extraspace/spaceslots;
			int remspace = extraspace%spaceslots;
			int k = 0;
			for(int i=0; i<list.size(); i++) {
				sb.append(list.get(i));
				if(i >= list.size() - 1) break;
				sb.append(' ');
				for(int j=0; j<slotspacect; j++) {
					sb.append(' ');
				}
				if(k < remspace) {
					sb.append(' ');
					k++;
				}
			}
		}
		return sb;
	}
	
	public static void main(String[] args) {
		justify();
	}

}
