package com.learning.btree;

import java.util.Stack;

public class EvalExp {
	
	public static int calculate(String s) {
		int r = 0;
		Stack<Character> st = new Stack<Character>();
		for(int i=0; i<s.length(); i++) {
			st.push(s.charAt(i));
		}
		int digit = 0;
		char prevchar = ' ';
		int num = 0;
		boolean isSum = true;
		int sum = 0;
		int braceopen = 0;
		int bsum = 0;
		//(7+3)-56
		while(!st.isEmpty()) {
			num = 0;
			char val = st.pop();
			if(val == '(') {
				braceopen = -1;
			} else if(val == ')') {
				braceopen = 1;
			} else {
				braceopen = 0;
			}
			int mul = 1;
			while(Character.isDigit(val)) {
				int tt = val - '0';
				num = tt*mul+num;
				if(!st.isEmpty()) {
					val = st.pop();
				}else {
					break;
				}
				mul*=10;
			}

			if(braceopen == -1) {
				bsum = prevchar=='-' ? bsum-num : bsum+num;
			} else if(braceopen == 1) {
				sum = prevchar=='-' ? sum-bsum : sum+bsum;
			}
			else {
				sum = prevchar=='-' ? num-sum : sum+num;
			}
			prevchar = val;
		}
		return sum;
	}
	
	
	public static void main(String[] args) {
		int v = calculate("4-44+8");
		System.out.println(v);
	}

}
