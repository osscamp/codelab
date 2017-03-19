package com.learning.java;

import java.util.Stack;

// brute force
public class Eval {
	
	public static long evaluate(String exp) {

	    //21+3*11+(15/3*2)-9+18/9-22
	    
	    if(exp == null || exp.isEmpty()) {
	    throw new IllegalArgumentException();
	    }
	    char prevOp = ' ';
	    Stack<Object> st = new Stack<Object>();
	    long totalResult = 0l;
	    for(int i=0; i<exp.length(); i++) {
	        char c = exp.charAt(i);
	        if(c-'0' >= 0 && c-'0' <= 9) {
	            int num = c-'0';
	            while(i < exp.length()-1 && ((exp.charAt(i+1)-'0' >= 0 && exp.charAt(i+1)-'0' <= 9)) ) {
	                num = num*10+exp.charAt(i+1)-'0';
	                i++;
	            }
	            st.push(new Integer(num));
	    
	        }
	        else if(c == '+' || c == '-') {
	            if(prevOp == ' ') {
	                //st.push(new Character(c));
	            } else if (prevOp == '*' || prevOp == '/') {
	                Object right = st.pop();
	                if(right instanceof Character) throw new IllegalArgumentException();
	                Integer rightVal = (Integer)right;
	                st.pop(); //pop the '*'
	                Object left = st.pop();
	                if(left instanceof Character) throw new IllegalArgumentException();
	                Integer leftVal = (Integer)left;
	                Integer interResult = (prevOp == '*') ? leftVal*rightVal : leftVal/rightVal;            
	                st.push(interResult);
	            }
	            prevOp = c;
	            st.push(c);
	        }
	        else if(c == '*') {
	            if(prevOp == ' ') {
	                //st.push(new Character(c));
	            } else if (prevOp == '/' || prevOp == '*') {
	                Object right = st.pop();
	                if(right instanceof Character) throw new IllegalArgumentException();
	                Integer rightVal = (Integer)right;
	                st.pop(); //pop the '*'
	                Object left = st.pop();
	                if(left instanceof Character) throw new IllegalArgumentException();
	                Integer leftVal = (Integer)left;
	                Integer interResult = (prevOp == '*') ? leftVal*rightVal : leftVal/rightVal;            
	                st.push(interResult);
	            }
	            prevOp = c;
	            st.push(c);
	        }
	        else if(c == '/') {
	            if(prevOp == ' ') {
	                //st.push(new Character(c));
	            } else if (prevOp == '/' || prevOp == '*') {
	                Object right = st.pop();
	                if(right instanceof Character) throw new IllegalArgumentException();
	                Integer rightVal = (Integer)right;
	                st.pop(); //pop the '/'
	                Object left = st.pop();
	                if(left instanceof Character) throw new IllegalArgumentException();
	                Integer leftVal = (Integer)left;
	                Integer interResult = (prevOp == '*') ? leftVal*rightVal : leftVal/rightVal;            
	                st.push(interResult);
	            }
	            prevOp = c;
	            st.push(c);
	        }
	    }
	    System.out.println(st);
	    prevOp = ' ';
	    while(!st.isEmpty()) {
	        Object right = st.pop();
	        if(!(right instanceof Integer)) throw new IllegalArgumentException();
	        System.out.println("rt:"+right);
	        if(prevOp == ' ') {
	        	totalResult = (Integer)right;
	        }else {
            	switch(prevOp) {
	            	case '+' :
	            		totalResult += (Integer)right;
	            		break;
	            	case '-' :
	            		totalResult = (Integer)right - totalResult;
	            		break;
	            	//only the last op can be multiply or div, previous would have been reduced	
	            	case '*' :
	            		totalResult *= (Integer)right;
	            		break;	
	            	case '/' :
	            		totalResult = (Integer)right / totalResult;
	            		break;	
            	}
	        }

	        if(!st.isEmpty()) {
	            Object op = st.pop();
	            if(!(op instanceof Character)) throw new IllegalArgumentException();
	            prevOp = (Character)op;
	        }

	        
	    }
	    System.out.println("res "+totalResult);
	    return totalResult;
	}

	
	public static void main(String[] args) {
		String exp = "21/3+7*28/1/7/2*2+1+2*2/1*5/2/10-500";
		evaluate(exp);
	}

}
