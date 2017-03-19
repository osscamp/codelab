package com.learning.btree;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class MergeKSorted {
	
	/**
	 * helper object to store min value of each array in a priority queue, 
	 * the kth array and the index into kth array
	 * @author sushukla
	 *
	 */
	static class PQNode implements Comparable<PQNode>{
		int value;
		int kthArray = 0;
		int kthArrayIndex = 0;
		
		public PQNode(int value, int kthArray, int kthArrayIndex) {
			this.value = value;
			this.kthArray = kthArray;
			this.kthArrayIndex = kthArrayIndex;
		}
		@Override
		public int compareTo(PQNode o) {
			if(o != null) {
				return Integer.valueOf(value).compareTo(Integer.valueOf(o.value));
			}
			else return 0;
		}
		
		@Override
		public String toString() {
			return value+" "+kthArray+" "+kthArrayIndex;
		}
	}
	
	//merge k sorted arrays of any length.
	/**
	 * {3,4,6,9},
	 * {4,6,12,17},
	 * {5,9,17,21},
	 * {1,4,6,8}
	 * @param sortedArrays
	 */
	public static void intersectKSorted(int[][] sortedArrays) {
		int k = sortedArrays.length;
		int resultCtr = 0;
		PriorityQueue<PQNode> pq = new PriorityQueue<>();
		PQNode prevNode = null;
		int intersectSize = Integer.MAX_VALUE;
		for(int i=0; i<k; i++) {
			int[] kthArray = sortedArrays[i];
			if(kthArray.length < intersectSize) { //can't be more than the minimum length array
				intersectSize = kthArray.length;
			}
			if(kthArray.length == 0) {
				break;
			}
			else {
				PQNode temp = new PQNode(kthArray[0], i, 0);
				pq.add(temp);
			}
		}
		int[] result = new int[intersectSize];
		Map<Integer, Integer> matchSet = new HashMap<>();
		boolean firstMatch = true;
		boolean streamFinished = false;
		while(!pq.isEmpty()) {
			System.out.println(pq);
			PQNode temp = pq.poll();
			int[] kthArray = sortedArrays[temp.kthArray];
			if(temp.kthArrayIndex+1 < kthArray.length) {
				pq.add(new PQNode(sortedArrays[temp.kthArray][temp.kthArrayIndex+1], temp.kthArray, temp.kthArrayIndex+1));
			} else {
				streamFinished = true;
				System.out.println("st finished "+temp.kthArrayIndex);
			}
			System.out.println(pq);

			if(prevNode != null && temp != null && temp.value == prevNode.value) {
				matchSet.put(temp.kthArray, matchSet.get(temp.kthArray) != null? matchSet.get(temp.kthArray)+1 : 1);
				if(firstMatch){
					matchSet.put(prevNode.kthArray, matchSet.get(prevNode.kthArray) != null? matchSet.get(prevNode.kthArray)+1 : 1);
					firstMatch = false;
				}
				if(matchSet.size() == k) {	
					result[resultCtr] = temp.value;
					resultCtr++;
					if(resultCtr >= result.length) {
						break;
					}
					Set<Integer> zeroKeys = new HashSet<>();
					for(int key : matchSet.keySet()) {
						int decVal = matchSet.get(key)-1;
						if(decVal == 0) {
							zeroKeys.add(key);
						}
						matchSet.put(key, decVal);
						
					}
					matchSet.keySet().removeAll(zeroKeys);
					if(matchSet.size() == 0) {
						firstMatch = true;
					}
				}
			}else{
				System.out.println("clear stream end "+streamFinished);
				matchSet.clear();
				firstMatch = true;				
			}

			prevNode = temp;
		}
		print(result);
	}
	
	public static void mergeKSorted(int[][] sortedArrays) {
		int k = sortedArrays.length;
		int resultCtr = 0;
		int totalSize = 0;
		PriorityQueue<PQNode> pq = new PriorityQueue<>();
		for(int i=0; i<k; i++) {
			int[] kthArray = sortedArrays[i];
			totalSize+=kthArray.length;
			if(kthArray.length > 0) {
				PQNode temp = new PQNode(kthArray[0], i, 0);
				pq.add(temp); 
			}
		}
		int[] result = new int[totalSize];
		while(!pq.isEmpty()) {
			PQNode temp = pq.poll();
			int[] kthArray = sortedArrays[temp.kthArray];
			result[resultCtr] = temp.value;
			resultCtr++;			
			temp.kthArrayIndex++;
			if(temp.kthArrayIndex < kthArray.length) {
				temp = new PQNode(kthArray[temp.kthArrayIndex], temp.kthArray, temp.kthArrayIndex);
				pq.add(temp);
			}

		}
		print(result);
	}
	
	public static void print(int[] a) {
		StringBuilder sb = new StringBuilder();
		for(int v : a) {
			sb.append(v).append(" ");
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) {
		 int[][] sortedA = {
			 {1,3,3,3,5,7,8,11,18},
			 {0,3,3,3,5,5,18},
			 {3,3,5,5,8,10,13,16,18,67,76},
		 };
		 intersectKSorted(sortedA);
		 //mergeKSorted(sortedA);
	}

}
