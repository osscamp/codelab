package com.learning.btree;

import java.util.HashSet;
import java.util.Set;

public class Scratch {
	
	public static void reverseNumber(int input) {
		int n = input;
		int nlast = input;
		int rem = 0;
		int nnum = 0;
		while(n >= 1) {
			n = nlast / 10;
			rem = nlast % 10;
			nlast = n;
			nnum = (nnum*10) + rem;
		}
		System.out.println(nnum);
	}
	
	public static Set<String> permuteString(String input) {
	    Set<String> set = new HashSet<String>();
	    if (input == "")
	        return set;
	    Character a = input.charAt(0);
	    if (input.length() > 1)
	    {
	        input = input.substring(1);
	        Set<String> permSet = permuteString(input);
	        for (String x : permSet)
	        {
	            for (int i = 0; i <= x.length(); i++)
	            {
	                set.add(x.substring(0, i) + a + x.substring(i));
	            }
	        }
	    }
	    else
	    {
	        set.add(a + "");
	    }
	    return set;

	}

	public static void main(String[] args) {
		reverseNumber(113);
		System.out.println(permuteString("abcd"));
	}
}
