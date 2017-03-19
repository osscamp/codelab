package com.learning.java;

import java.util.Stack;

public class InfixToPostfix {
	
	public static int compare(char op1, char op2) {
		if(op1 == '+' && op2 == '*') {
			return -1;
		}else if(op1 == '*' && op2 == '+') {
			return 1;
		}
		return 0;
	}
	
	public static String convertInfixToPostfix(String infix) {
		//A+B*C
		String pFix = "";
		Stack<Character> opStack = new Stack<>();
		Stack<Character> pStack = new Stack<>();
		for(int i=0; i<infix.length(); i++) {
			char c = infix.charAt(i);
			if(c == '+' || c == '*') {
				if(opStack.isEmpty() || opStack.peek() == '(') {
					opStack.push(c);
				}else{
					char cc = opStack.peek();
 					if(compare(c, cc) > 0){
 						opStack.push(c);
 					}else{
 						pStack.push(opStack.pop());
 						opStack.push(c);
 					}
				}
			} else if(c == '(') {
				opStack.push(c);
			}
			else if(c == ')') {
				System.out.println(opStack);
				while(opStack.peek() != '(') {
					pStack.push(opStack.pop());
				}
				if(!opStack.isEmpty()) {
					opStack.pop();
				}
			}
			else {
				pStack.push(c);
			}
		}
		while(!opStack.isEmpty()) {
			pStack.push(opStack.pop());
		}
		StringBuilder res = new StringBuilder();
		while(!pStack.isEmpty()) {
			res.insert(0,pStack.pop());
		}
		System.out.println(res);
		return pFix;
	}
	
	public static void main(String[] args) {
		convertInfixToPostfix("A*(K+D)*B+C");
	}

}
