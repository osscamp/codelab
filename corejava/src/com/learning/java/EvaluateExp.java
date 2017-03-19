package com.learning.java;

import java.util.Stack;

public class EvaluateExp {
	
	/**
	 * evaluate fully parenthesized infix using dijkstra's 2 stack algorithm
	 */
	public static void evalExp() {
		String exp = "(((1+(31+(2*(4+3))) * (2+1)))";
		//3+21*(43)
		Stack<Character> opStack = new Stack<>();
		Stack<Integer> nStack = new Stack<>();
		for(int i=0; i<exp.length(); i++) {
			char c = exp.charAt(i);
			if(c == ' ' || c == '(') {
				continue;
			}
			else if(c == ')') {
				if(opStack.size() < 1 || nStack.size() < 2) {
					throw new IllegalArgumentException("invalid exp ");
				}
				char op = opStack.pop();
				int n1 = nStack.pop();
				int n2 = nStack.pop();
				if(op == '+') {
					nStack.push(n1+n2);
				}else if(op == '*') {
					nStack.push(n1*n2);
				}
			}
			else if(c == '*' || c == '+') {
				opStack.push(c);
			} else {
				int num = c-'0';
				while(i < exp.length()-1 && isNum(exp.charAt(i+1))) {
					num = num*10 + (exp.charAt(i+1)-'0');
					i++;
				}
				nStack.push(num);
			}
		}
		int n = nStack.peek();
		System.out.println("res "+n);
	}
	
	public static boolean isNum(char c) {
		return c-'0' >= 0 && c-'0' <= 9;
	}
	
	/**
	 * Evaluate non parenthesized exp - convert to postfix notation based on precedence and solve the expression.
	 */
	public static void convertPostfix() {
		String exp = "2+42*1+(5*(78+5))";
		//String exp = "2+3+(4+5)";
		/*
		 * algorithm - 
1.	Print operands as they arrive.
2.	If the stack is empty or contains a left parenthesis on top, push the incoming operator onto the stack.
3.	If the incoming symbol is a left parenthesis, push it on the stack.
4.	If the incoming symbol is a right parenthesis, pop the stack and print the operators until you see a left parenthesis. Discard the pair of parentheses.
5.	If the incoming symbol has higher precedence than the top of the stack, push it on the stack.
6.	If the incoming symbol has equal precedence with the top of the stack, use association. If the association is left to right, pop and print the top of the stack and then push the incoming operator. If the association is right to left, push the incoming operator.
7.	If the incoming symbol has lower precedence than the symbol on the top of the stack, pop the stack and print the top operator. Then test the incoming operator against the new top of stack.
8.	At the end of the expression, pop and print all operators on the stack. (No parentheses should remain.)
		 */
		Stack<Character> opStack = new Stack<>();
		Stack<String> pStack = new Stack<>();
		opStack.push('E');
		for(int i=0; i<exp.length(); i++) {
			char c = exp.charAt(i);
			char top = opStack.peek();

			if(c == '*') {
				if(top == 'E' || top == '(') {
					opStack.push(c);
				}else if(top == '*') {
					pStack.push(String.valueOf(opStack.pop()));
					opStack.push(c);
				}else if(top == '+') {
					opStack.push(c);
				}
			}else if(c == '+') {
				if(top == 'E' || top == '(') {
					opStack.push(c);
				}else if(top == '*') {
					pStack.push(String.valueOf(opStack.pop()));
					//can this be done instead of checking against prev op in stack - may be for multiple operands
					//pStack.push(String.valueOf(c));
					i--;
					continue;
				}else if (top == '+'){
					pStack.push(String.valueOf(opStack.pop()));
					opStack.push(c);
				}

			}else if(c == '(') {
				opStack.push(c);
			}else if(c == ')') {
				char x = opStack.pop();
				while(x != '('){
					pStack.push(String.valueOf(x));
					x = opStack.pop();
				}
			}else{
				if (isNum(c)) {
					StringBuilder nn = new StringBuilder(String.valueOf(c));
					while(i < exp.length()-1 && isNum(exp.charAt(i+1))){
						nn.append(exp.charAt(i+1));
						i++;
					}
					pStack.push(nn.toString());
				}
			}
		}
		while(opStack.size() > 1) {
			pStack.push(String.valueOf(opStack.pop()));
		}
		System.out.println(pStack);
	}


	public static void evalRPN() {
	    String s = "2 42 5 + * 3 * 11 +";
	    Stack<Integer> vals = new Stack<>();
	    for(int i=0; i<s.length(); i++) {
	        char c = s.charAt(i);
	        if(c == ' ') continue;
	        else if(isNum(c)) {
	            int num = c-'0';
	            while(i < s.length() && isNum(s.charAt(i+1))) {
	                num = num*10 + (s.charAt(i+1)-'0');
	                i++;
	            }
	            vals.push(num);
	        } else if(c == '*') {
	            if(vals.size() >= 2 ) {
	                int v1 = vals.pop();
	                int v2 = vals.pop();
	                vals.push(v1 * v2);
	            }
	        } else if(c == '+') {
	            if(vals.size() >= 2 ) {
	                int v1 = vals.pop();
	                int v2 = vals.pop();
	                vals.push(v1 + v2);
	            }
	        }
	    }
	    int result = vals.pop();
	    System.out.println("res "+result);
	}
	
	public static void main(String[] args) {
		//evalExp();
		convertPostfix();
		evalRPN();
	}

}
