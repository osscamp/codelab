package com.learning.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pressure {
	
/*	  || 0| 1| 2| 3| 4
	  ==================
	  0 ||  | x|  |  |
	  ==================
	  1 ||x |  |  |  |
	  ==================
	  2 ||  | o|  |  |x
	  ==================
	  3 ||  |  |  |  |
	  ==================
	  4 ||  |xx|  |  |

	  given: (0, 1), (4, 2), (1, 0), (1, 4), (1, 4)
	  answer: (1, 2), 11 total

	  given: (2, 2), (2, 2)
	  answer: (2, 2), 0 total


	  Brute force:
	  O(n)2 
	  O(n^2) n is the size of the matrix*/

	  public void minDistance(boolean[][] a) {
	      int SZ = a.length;
	      int minSum = Integer.MAX_VALUE;
	      int sumDist = 0;
	      Map<Integer, List<Integer>> minPoint = new HashMap<>();
	      List<Integer> finalpoint = null;
	      for(int i=0; i<SZ; i++) {
	          for(int j=0; j<SZ; j++) {
	              
	              for(int k=i; k<SZ; k++) {
	                  for(int l=j; l<SZ; l++) {
	                      if(a[k][l]) {
	                          int d = Math.abs(k-i)+Math.abs(l-j);
	                          sumDist += d;
	                          List<Integer> ll = new ArrayList<>();
	                          ll.add(i);
	                          ll.add(j);
	                          minPoint.put(d,ll);
	                      }
	                  }
	              }
	              if(sumDist < minSum) {
	                  minSum = sumDist;
	                  finalpoint = minPoint.get(minSum);
	              }
	              sumDist = 0;
	          }
	      }
	  }

}
