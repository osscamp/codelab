package com.learning.btree;

public class MiscQ7 {
	
	static class Item{
	    public Item(int id, int wt) {
	        this.id = id;
	        this.wt = wt;
	    }
	    int id;
	    int wt;
	    public String toString() {
	        return id+" "+wt;
	    }
	}
	public static void main(String[] args) {
	    Item[] a = new Item[]{new Item(7,21), new Item(9,25), new Item(6,28) , new Item(11,33)};
	    Item[] b = new Item[]{new Item(5,24), new Item(6,28), new Item(7,28) , new Item(8,30), new Item(2,100)};
	    //Item[] res = merge(a, b);
	    //for(Item i : res) System.out.println(i);
	    find2dPattern();
	}
	
	//wrong
	//sort by id, add duplicates and sort by weight 
	public static Item[] merge(Item[] a, Item[] b) {
	    Item[] merged = new Item[a.length + b.length];
	    int i=0,j=0,k=0;
	    while(i<a.length && j<b.length){
	        if(a[i].wt < b[j].wt) {
	            merged[k] = a[i];
	            i++;
	        } else if(b[j].wt < a[i].wt) {
	            merged[k] = b[j];
	            j++;
	        } else {
	            if(a[i].wt == b[j].wt) {
	                merged[k] =a[i];   
	                i++;
	                j++;
	            } else {
	                merged[k] = a[i];
	                merged[k+1] = b[j];
	                i++;
	                j++;
	            } 
	        }
	        k++;
	    }
    	for(; i < a.length; i++) {
    		merged[k] =a[i];
    		k++;
    	}
    	for(; j < b.length; j++) {
    		merged[k] =b[j];
    		k++;
    	}
	    return merged;
	} 
	
	public static void find2dPattern() {
		int R = 4, C = 4;
		int[][] data = {
				{7,2,8,3},
				{8,8,3,0},
				{8,9,8,8},
				{3,8,3,9},
		};
		int[][] P = {
				{8,8},
				{3,0}
		};
		int PR = P.length, PC = P[0].length;

        int mct = 0;

        for(int i=0; i<R-PR+1; i++){
            for(int j=0; j<C-PC+1; j++) {
                boolean found = true;
                mct = 0;
                for(int k=0; k<PR && i+k < R; k++){
                    for(int l=0; l<PC && j+l < C; l++){
                        int pv = P[k][l];
                        int v = data[i+k][j+l];
                        if(pv != v){
                            found = false;
                            break;
                        }
                        mct++;
                    }
                    if(!found) {
                        break;
                    }                    
                }
                if(mct == PR*PC){
                    break;
                }
            }
            if(mct == PR*PC){
                break;
            }
        }
        System.out.println("mct "+mct);
        if(mct == PR*PC){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
	}

}
