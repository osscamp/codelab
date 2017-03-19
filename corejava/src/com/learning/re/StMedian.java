package com.learning.re;

import java.util.Stack;

import com.learning.btree.MaxHeap;

public class StMedian {
	
	public static void selSort(int[] a){
		String s = "book";
		char[] b = s.toCharArray();
		int[] freq = new int[256];
		for(int i=0; i<b.length; i++){
			char c = b[i];
			freq[c]++;
		}
		int k=0;
		for(int i=0; i<freq.length; i++){
			while(freq[i]>0){
				b[k] = (char)i;
				freq[i]--;
				k++;
			}
		}
		MaxHeap.printArray(b);
	}
	
	public static void evalExp(){
		String s = "56+8*2+(13+(1+2*14))";

		Stack<Character> opStack = new Stack<>();
		Stack<String> pfix = new Stack<>();
		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			if(c == '*' && (opStack.isEmpty() || opStack.peek() == '(')){
				opStack.push(c);
			} else if(c == '*' && opStack.peek() == '*'){
				pfix.push(""+opStack.pop());
				opStack.push(c);
			} 
			else if(c == '*'){
				opStack.push(c);
			} 
			if(c == '+' && (opStack.isEmpty() || opStack.peek() == '(')){
				opStack.push(c);
			}else if(c == '+' && opStack.peek() == '*') {
				pfix.push(""+opStack.pop());
				pfix.push(""+c);
			}else if(c == '+'){
				pfix.push(""+opStack.pop());
				opStack.push(c);
			} 
			else if(Character.isDigit(c)){
				StringBuilder sb = new StringBuilder();
				sb.append(c);
				while(i+1 < s.length() && Character.isDigit(s.charAt(i+1))){
					sb.append(s.charAt(++i));
				}
				pfix.push(sb.toString());
			}else if(c == '(') {
				opStack.push(c);
			}else if(c == ')') {
				while(!opStack.isEmpty() && opStack.peek() != '(') {
					pfix.push(""+opStack.pop());
				}
				opStack.pop();
			}
		}
		if(!opStack.isEmpty()) pfix.push(""+opStack.pop());
		System.out.println(pfix);
		evalPfix(pfix);
		
	}
	
	public static void evalPfix(Stack<String> pfix) {
		Stack<Integer> res = new Stack<>();
		Stack<String> rev = new Stack<>();
		while(!pfix.isEmpty()){
			rev.push(pfix.pop());
		}
		System.out.println(rev);
		pfix = rev;
		while(!pfix.isEmpty()){
			String v = pfix.pop();
			if(v.equals("+")){

					res.push(res.pop()+res.pop());
			}
			else if(v.equals("*")){

				res.push(res.pop()*res.pop());
			}else{
				res.push(Integer.valueOf(v));
			}
		}
		System.out.println(res.pop());
	}
	
	public static void main(String[] args) {
		int[] a = {6,1,9,4,12,5,7,16};
		selSort(a);
		evalExp();
		
	}

}
