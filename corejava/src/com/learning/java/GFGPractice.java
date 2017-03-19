package com.learning.java;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.learning.btree.MaxHeap;

public class GFGPractice {
	
	public static void checkParens() {
		String s = "{{}()}()({[]})";
		Stack<Character> st1 = new Stack<>();
		Stack<Character> st2 = new Stack<>();
		Stack<Character> st3 = new Stack<>();
		for(int i=0; i<s.length(); i++){
			char c = s.charAt(i);
			if(c=='{'){
				st1.push(c);
			}else if(c == '(') {
				st2.push(c);
			}else if(c == '[') {
				st3.push(c);
			}else if(c == '}') {
				st1.pop();
			}else if(c == ')') {
				st2.pop();
			}else if(c == ']') {
				st3.pop();
			}
		}
		if(st1.isEmpty() && st2.isEmpty() && st3.isEmpty()) {
			System.out.println("matched parens ");
		}
	}
	
	public static void lcs() {
		String s1 = "AGGTAB";
		String s2 = "GXTXAYB";
		int lcsLen = lcs(s1,s2);
		System.out.println("lcs "+lcsLen);
		lcsDynamic(s1, s2);
	}
	
	public static int lcs(String s1, String s2) {
		int l1 = s1.length()-1;
		int l2 = s2.length()-1;
		if(l1 < 0 || l2 < 0){
			return 0;
		}
		if(s1.charAt(l1) == s2.charAt(l2)) {
			return 1+lcs(s1.substring(0,l1), s2.substring(0, l2));
		}
		else if(s1.charAt(l1) != s2.charAt(l2)) {
			return Math.max(lcs(s1.substring(0,l1), s2) , lcs(s1, s2.substring(0,l2)));
		}
		return 0;
	}
	
	public static void lcsDynamic(String s1, String s2) {
		int M = s1.length();
		int N = s2.length();
		int[][] table = new int[M+1][N+1];
		for(int i=1; i<M+1; i++){ 
			for(int j=1; j<N+1; j++) {
				if(i==0 || j==0) table[i][j] = 0;
				else if(s1.charAt(i-1) == s2.charAt(j-1)) {
					table[i][j] = table[i-1][j-1] + 1;
				} else {
					table[i][j] = Math.max(table[i-1][j], table[i][j-1]);
				}
			}
		}
		System.out.println(table[M][N]);
	}
	
	public static void magicN() {
		//n^2-(n-1)
		int nth = 31;
		int ni = (int)Math.ceil(Math.sqrt(4*nth+2.0)/2.0);
		System.out.println("ni "+ni);
		int row = (int)Math.ceil(nth/ni)+1;
		int col = nth % ni;
		if(col == 0) {
			col = ni;
		}
		int j = col;
		int ctr = 0;
		int result = 0;
		while(ctr < row) {
			System.out.println("j "+j);
			result += Math.pow(5, j);
			if(j >= ni) {
				j = 0;
			}
			j++;
			ctr++;
			
		}
		System.out.println(result);
	}
	
    public static void trap() {
    	//int[] height = {0,2,0,1,2,5,4,2,4};
    	int[] height = {0,1,0,2,1,0,1,3,2,0,2,1};
        int left=0; int right=height.length-1;
        int res=0;
        int maxleft=0, maxright=0;
        while(left<=right){
            if(height[left]<=height[right]){
                if(height[left]>=maxleft) maxleft=height[left];
                else res+=maxleft-height[left];
                left++;
            }
            else{
                if(height[right]>=maxright) maxright= height[right];
                else res+=maxright-height[right];
                right--;
            }
        }
        System.out.println("res "+res);
    }

    public static void generic() {   	
    }
    
    public static void ph() {
    	char[][] d = {
    			{},
    			{},
    			{'A','B','C'},
    			{'D','E','F'},
    			{'G','H','I'}
    	};
    	String sNum = "234";
    	phPerm(d, sNum, 0, "");
    }
    
    public static void phPerm(char[][] d, String sNum, int depth,  String s) {
    	if(depth >= sNum.length()) {
    		System.out.println(s);
    		return;
    	}
    	char c = sNum.charAt(depth);
    	int idx = c-'0';
    	char[] l = d[idx];
    	for(int i=0; i<l.length; i++) {
    		phPerm(d, sNum, depth+1, s+l[i]);
    	}
    	
    }
    
    public static void reverse(int[] a, int l, int r){
    	if(l < 0 || r > a.length-1) return;
    	while(l < r) {
    		int t = a[l];
    		a[l] = a[r];
    		a[r] = t;
    		l++;
    		r--;
    	}
    }
    
    public static int getInt(String s) {
        int v = 0;
        int mul = 1;
        for(int j=0; j<s.length(); j++){
            if(j == 0 && s.charAt(j) =='-'){
                mul = -1;
                continue;
            }
            int digit = s.charAt(j)-'0';
            v = v*10+digit;
        }
        return v*mul;        
    }
    public static void deserialize(String s) {

        if(s == null)  return;
        Stack<Integer> vstack = new Stack<>();
        List<Object> result = null;
        StringBuilder num = new StringBuilder();
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if((c-'0' >= 0 && c-'0' <= 9) || c=='-') {
                num.append(c);
            }
            else if(c == ',') {
                int val = getInt(num.toString());
                vstack.push(val);
                num = new StringBuilder();
            } else if(c == ']') {
                String nstr = num.toString().trim();
                if(nstr.length() > 0){
                    int val = getInt(nstr);
                    vstack.push(val);
                    num = new StringBuilder();
                }
                if(vstack.isEmpty()) {
                    continue;
                }
                int ival = vstack.pop();
                if(result == null) {
                    List<Object> thisLevel = new ArrayList<Object>();
                    List<Object> nl = new ArrayList<>();
                    nl.add(ival);
                    thisLevel.add(nl);
                    result = thisLevel;
                }else{
                	List<Object> thisLevel = new ArrayList<>();
                    List<Object> nl = new ArrayList<>();
                    nl.add(ival);
                    thisLevel.add(nl);
                    thisLevel.add(result);
                    result = thisLevel;
                }
            } 
        }
        String nstr = num.toString();
        if(num.toString().trim().length() > 0){
            int val = getInt(nstr);
            result = new ArrayList<>(val);
        }
        System.out.println(result);
    }
    
    public static void kSmallestPairs() {
    	int[] a = {1,3,42}; 
    	int[] b = {24,51,90,111};
    	int sz = 8;
    	//[1,3,6], [2,5,9,11]  [1,12,16] [2,5,8,9]
    	List<int[]> res = new ArrayList<>();
    	int i=0, j=0, k=0;
    	int max = -1;
    	while(k < sz) {
    		if(k==0) {
    			System.out.println(a[i]+ " " +b[j]);
    		}
    		if(i < a.length-1 && j < b.length-1){
    			int s1 = a[i]+b[j+1]; 
    			int s2 = a[i+1]+b[j];
    			max = Math.max(s1, s2);
    			if(s1 > s2) {
    				int p = i;
    				while(p < a.length-1 && a[p+1]+b[j] < s1) {
    					System.out.println(a[p+1]+" "+b[j]);
    					p++;
    				}
    				j++;
    			}else{
    				int p = j;
    				while(p < b.length-1 && a[i]+b[p+1] <= s2) {
    					System.out.println(a[i]+" "+b[p+1]);
    					p++;
    				} 
    				i++;
    			}
    		}
    		k++;
    	}
    	for(int[] ar : res) {
    		MaxHeap.printArray(ar);
    	}
    }
    
    public static void lexicalOrder() {
    	int T = 18;
    	int[] res = new int[T];
    	int c = -1;
    	int s = 1;
    	int x = 0;
    	int mul = 1;
		while(mul < T) {
    		res[++c] = mul*s;
    		if(c >= T) break;
			mul *= 10;    			
		}
		mul /= 10;
		int ps = res[c];
		int lc = 0;
		while(true) {
			for(int i=1; i<=9 && c<T-1; i++) {
				res[++c] = ps+i;
			}
			int v = res[c]+1;
			if(c < T-1) res[++c] = v/10;
			System.out.println(v/10);
			lc++;
			if(lc >= 20) {
				break;
			}
		}
    	MaxHeap.printArray(res);
    }
    
    //remove k digits from a number so it becomes smallest
    public static void removeK(int n, int k) {
    	List<Integer> ds = new ArrayList<>();
    	while(n > 0) {
    		ds.add(0, n%10);
    		n /= 10;
    	}
    	int c = 0;
		for(int i=0; i<ds.size()-1 && c < k-1; i++) {
			if(ds.get(i) >= ds.get(i+1)) {
				ds.remove(i);
				c++; i=0;
			}
		}
		int max = -1;
		int maxi = -1;
		for(int i=0; i<ds.size(); i++) {
			if(max < ds.get(i)) {
				max = ds.get(i);
				maxi = i;
			}
		}
		if(maxi != -1) {
			ds.remove(maxi);
		}
		System.out.println(ds);
    }
    
    public static void ratmazeAllPaths() {
    	int [][] m = {
    			{1,0,0,0},
    			{1,1,0,1},
    			{1,1,0,0},
    			{0,1,1,1}    	
    	};
    	int L = m.length;
    	StringBuilder sb = new StringBuilder();
    	searchmaze(m, 0, 0, L, sb, "");
    }
    
    public static void searchmaze(int[][] m, int i, int j, int L, StringBuilder sb, String s) {
    	if(valid(i,j, L) && m[i][j] == 1) {
    		sb.append(s);
    		if(i==L-1 && j==L-1) {
    			System.out.println("found "+sb);
    			sb.delete(0, sb.length());
    			return;
    		}
    		m[i][j] = 0;
    		searchmaze(m, i, j+1, L, sb, "R");
    		searchmaze(m, i, j-1, L, sb, "L");
    		searchmaze(m, i+1, j, L, sb, "D");
    		searchmaze(m, i-1, j, L, sb, "U");
    		m[i][j] = 1;
    	} 
    }
    
    public static boolean valid(int i, int j, int L) {
    	return i>= 0 && j>=0 && i<L && j<L;
    }
    
    public static void ratmaze() {
    	int [][] m = {
    			{1,0,0,0},
    			{1,1,0,1},
    			{1,1,0,0},
    			{0,1,1,1}    	
    	};
    	int L = m.length;
    	Stack<Point> pstack = new Stack<>();
    	List<List<String>> finalPaths = new ArrayList<>();
		Stack<String> spath = new Stack<>();
		int btx = 0;
		int bty = 0;

    	boolean[][] visited = new boolean[L][L];
    	int i = 0, j=0;
    	visited[0][0] = true;
    	pstack.push(new Point(0,0));
    	boolean bt = false;
    	while(!pstack.isEmpty()) {
    		//Point p = pstack.pop();
    		//i = p.x; j=p.y;
    		visited[i][j] = true;
    		/*if(i == L-1 && j== L-1) {
    			System.out.println("found path "+spath);
    			Point end = pstack.pop();
    			if(!spath.isEmpty())spath.pop();
    			i = end.x;
    			j = end.y;
    			//visited[i][j] = false;
    			System.out.println(" end path "+end);
    			bt = true;
    		}*/
    		int lv = j>0 ? m[i][j-1] : -1;
    		int rv = j<L-1 ? m[i][j+1] : -1;
    		int uv = i>0 ? m[i-1][j] : -1;
    		int dv = i<L-1 ? m[i+1][j] : -1;
    		if(dv == 1 && !visited[i+1][j]) {
    			pstack.push(new Point(i+1, j));
    			spath.push("D");
    			i++;
    			if(bt) visited[btx][bty] = false;
    			bt = false;
    		}
    		else if(lv == 1 && !visited[i][j-1]) {
    			pstack.push(new Point(i, j-1));
    			spath.push("L");
    			j--;
    			if(bt) visited[btx][bty] = false;
    			bt = false;
    		}
    		else if(rv == 1 && !visited[i][j+1]) {
    			pstack.push(new Point(i, j+1));
    			spath.push("R");
    			j++;
    			if(bt) visited[btx][bty] = false;
    			bt = false;
    		}
    		else if(uv == 1 && !visited[i-1][j]) {
    			pstack.push(new Point(i-1, j));
    			spath.push("U");
    			i--;
    			if(bt) visited[btx][bty] = false;
    			bt = false;
    		}
    		else{
        		if(i == L-1 && j== L-1) {
        			System.out.println("found path "+spath);
        			break;
        		}
        		if(bt) visited[btx][bty] = false;
    			Point deadEnd = pstack.pop();
    			btx = deadEnd.x;
    			bty = deadEnd.y;
    			if(!spath.isEmpty())spath.pop();
    			if(pstack.size() > 0) {
    				Point next = pstack.peek();
	    			i = next.x;
	    			j = next.y;
    			}
    			//visited[i][j] = false;

    			System.out.println("dead end "+deadEnd);
    			bt = true;
    		}
    	}
    }

	
	public static void main(String[] args) {
		checkParens();
		lcs();
		magicN();
		trap();
		generic();
		ph();
		deserialize("[-1,[897,[32]]]");
		kSmallestPairs();
		lexicalOrder();
		removeK(236456, 4) ;
		ratmaze();
		ratmazeAllPaths();
	}

}
