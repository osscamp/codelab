package com.learning.java;

import java.util.ArrayList;
import java.util.List;

public class PolyParser {
	
	public static List<Integer> getCoeffs(String poly) {
		List<Integer> coeffs = new ArrayList<>();
		if(poly == null || poly.isEmpty()) {
			return coeffs;
		}
		//-4X^0+X^1+4X^3-3X^4
		//for non existent power return 0
		char prev = ' ';
		int pow = 0;
		int factorLevel=0;
		for(int i=0; i<poly.length(); i++) {
			char c = poly.charAt(i);
			if(c-'0' >= 0 && c-'0' <= 9 && prev != '^') {
				int number = c-'0';
				boolean isNeg = (prev == '-');
				while(i < poly.length()-1 && 
						(poly.charAt(i+1)-'0' >= 0 && poly.charAt(i+1)-'0' <= 9)) {
					number = number*10+(poly.charAt(i+1)-'0');
					prev = poly.charAt(i+1);
					i++;
				}
				if(isNeg) number *= -1;
				coeffs.add(number);
			} else if(c-'0' >= 0 && c-'0' <= 9 && prev == '^') {
				pow = c-'0';
				if(pow - factorLevel > 0) {
					for(int k=0; k<(pow - factorLevel); k++) {
						if(coeffs.size() > 0) {
							coeffs.add(coeffs.size()-1, 0);
						} else {
							coeffs.add(0);
						}
					}
				}
				factorLevel++;

			} else if(c == ' ') {
				continue;
			}
			prev = c;
		}
		return coeffs;
	}
	
	public static void main(String[] args) {
		System.out.println(getCoeffs("-51X^0+68X^4"));
	}

}
