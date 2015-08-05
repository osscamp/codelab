package com.learning.btree;

import java.util.HashMap;
import java.util.Map;

public class Banner {
	
	Map<Character, char[][]> data = new HashMap<>();
	
	public Banner() {
		char[][] alpha = new char[6][6];
		alpha[0][0] = '#';
		alpha[0][5] = '#';
		alpha[1][0] = '#';
		alpha[1][4] = '#';
		alpha[2][0] = '#';
		alpha[2][1] = '#';
		alpha[2][2] = '#';
		alpha[3][0] = '#';
		alpha[3][4] = '#';
		alpha[4][0] = '#';
		alpha[4][5] = '#';
		data.put('k', alpha);
	}
	
	public char[][] getBigChar(char c) {
		return data.get(c);
	}
	
	public void printBanner(char[][] c) {
		for(int k=0; k<c.length; k++) {
			StringBuilder sb = new StringBuilder();
			for(int l=0; l<c.length; l++) {
				sb.append(c[k][l]+" ");
				
			}
			System.out.println(sb);
		}
	}
	
	public static void main(String[] args) {
		Banner banner = new Banner();
		banner.printBanner(banner.getBigChar('k'));
	}

}
