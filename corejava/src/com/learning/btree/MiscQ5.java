package com.learning.btree;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MiscQ5 {
	
	public static void findRotatedIndex() {
		int[] a = {4,5,6,7,8,9,1,2,3};
		//modified bsearch
	}
	
	public static void isThreeSumO(){
		int[] a = {-1,-3,4,5,-2};
		Map<Integer, int[]> pairSum = new HashMap<>();
		for(int i=0; i<a.length-1; i++) {
			for(int j=i+1; j<a.length; j++) {
				pairSum.put(a[i]+a[j], new int[]{a[i],a[j]});
			}
		}
		for(int v:a) {
			int[] vs = pairSum.get(v*-1);
			if(vs != null) {
				StringBuilder sb = new StringBuilder();
				sb.append(",").append(vs[0]).append(",").append(vs[1]);
				System.out.println("three sum 0 found "+v+sb);
				//break;
			}
		}
	}
	
	public static void findNumberLeastDiff() {
		int[] a = {4,11,15,18,21,25};
		//modified bsearch
		int N = 22;

		int l=0;
		int r = a.length-1;
		int absmindiff = Integer.MAX_VALUE;
		int leastDiffN = -1;
		if(N > a[a.length-1]) {
			System.out.println("least diff N "+a[r]);
			return;
		}
		if(N < a[0]) {
			System.out.println("least diff N "+a[0]);
			return;
		}
		while(l <= r) {
			int mid = l+(r-l)/2;
			int diff = Math.abs(N-a[mid]);
			if(diff < absmindiff) {
				absmindiff = diff;
				leastDiffN = a[mid];
			}
			if(a[mid] < N) {
				l = mid+1;
			} else if(a[mid] > N){
				r = mid-1;
			} else {
				leastDiffN = a[mid];
				System.out.println("zero diff found ");
				break;
			}
		}
		System.out.println("least diff n "+leastDiffN);
	}
	
	public static void getMaxProfit() {
		int[] txns = {11,50,52,10,45,24,38};
		int ctr = txns.length-1;
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int maxdiff = Integer.MIN_VALUE;

		for(int i = ctr; i>=0; i--) {
			if(txns[i] > max) {
				max = txns[i];
				min = max;
				continue;
			}
			if(txns[i] < min) {
				min = txns[i];
			}
			int diff = max-min;
			if(diff > maxdiff) {
				maxdiff = diff;
			}
		}
		System.out.println("maxdiff "+maxdiff);
	}

	public static void reservoirSampling() {
		int[] a = {2,5,3,1,7,8,9,12,17};
		int k = 3;
		int[] res = new int[k];
		for(int i=0; i<k; i++) {
			res[i] = a[i];
		}
		int j = 0;
		Random rdm = new Random();
		for(int i=k; i<a.length; i++) {
			j = rdm.nextInt(i+1);
			System.out.println("random j "+j);
			if(j < k) {
				res[j] = a[i];
			}
		}
		System.out.println("reservoir ");
		MaxHeap.printArray(res);
	}
	
	public static void reservoirSamplingIndex() {
		int[] a = {2,5,3,1,7};
		int k = 1;
		int[] res = new int[k];
		for(int i=0; i<k; i++) {
			res[i] = i;
		}
		int j = 0;
		Random rdm = new Random();
		for(int i=k; i<a.length; i++) {
			j = rdm.nextInt(i+1);
			System.out.println("random j "+j);
			if(j < k) {
				res[j] = i;
			}
		}
		System.out.println("reservoir idx");
		MaxHeap.printArray(res);
	}
	
	public static void reservoirSamplingBitset() {
		long start = System.nanoTime();
		int totalSize = 4000000;
		BitSet b = new BitSet(totalSize);
		int i=0;
		int k = 2000000;
		int[] res = new int[k];
		for(; i<k; i++) {
			res[i] = i;
			b.set(i);
		}
		int j = 0;
		Random rdm = new Random();
		int compares = 0;
		for(; i<totalSize; i++) {
			j = rdm.nextInt(i+1);
			compares++;
			if(j < k) {
				b.clear(res[j]);
				b.set(i);
				res[j] = i;
			}
		}

/*		for(int p=0; p<totalSize; p++) {
			if(b.get(p)) {
				//System.out.println("set bit "+p);
			}
		}*/
		long end = System.nanoTime();
		System.out.println("compares 1 "+compares+" micros "+(end-start)/1000);
	}
	
	public static void reservoirSamplingNewBitset() {
		long start = System.nanoTime();
		int totalSize = 4000000;
		BitSet randomBitSet = new BitSet();
		Random randomGenerator = new Random();
		int i = 0;
		int compares = 0;

		while(i < 2000000) {
			int ridx = randomGenerator.nextInt(totalSize);
			if(randomBitSet.get(ridx)) {
				compares++;
				continue;
			}
			randomBitSet.set(ridx);
			compares++;
			i++;
		}

/*		for(int p=0; p<totalSize; p++) {
			if(randomBitSet.get(p)) {
				//System.out.println("set bit new "+p);
			}
		}*/
		long end = System.nanoTime();
		System.out.println("compares 2 "+compares+" micros "+(end-start)/1000+" card "+randomBitSet.cardinality());
	}
	
	public static void convertRoman() {
		Map<Character, Integer> vals = new HashMap<>();
		vals.put('I', 1);
		vals.put('V', 5);
		vals.put('X', 10);
		vals.put('L', 50);
		vals.put('C', 100);
		String roman = "LXXXVII";
		int number = 0;
		int pnum = 0;
		for(int i=roman.length()-1; i>=0; i--) {
			char rom = roman.charAt(i);
			int romv = vals.get(rom);
			if(romv >= pnum) {
				number += romv;
			}else {
				number -= romv;
			}
			pnum = romv;
		}
		System.out.println("Decimal is "+number);
	}
	
	public static void flatten() {
		List<Object> list = new ArrayList<>();
		list.add(1);
		List<Object> idata = new ArrayList<>();
		idata.add(2);
		idata.add(3);
		list.add(idata);

		List<Object> idata2 = new ArrayList<>();
		idata2.add(4);
		List<Object> idata3 = new ArrayList<>();
		idata3.add(5);
		idata3.add(6);
		idata2.add(idata3);
		idata2.add(7);
		list.add(idata2);
		List<Integer> reslist = new ArrayList<>();
		flattenR( list, reslist);
		System.out.println(reslist);
	}
	
	private static void flattenR(List<Object> list, List<Integer> reslist) {
		for(int i=0; i<list.size(); i++) {
			Object o = list.get(i);
			if(o instanceof Integer) {
				reslist.add((Integer)o);
			} else if (o instanceof List) {
				List<Object> sub = (List<Object>)o;
				flattenR(sub, reslist);
			}
		}
	}
	
	public static void sumMinSubArray() {
		int[] a = {2,5,13,11,20,16,15,21};
		int key = 35;
		int rsum = 0;
		int startid = 0;
		int endid = 0;
		int prev = 0;
		int minsublength = -1;
		for(int i=0; i<a.length; i++) {
			System.out.println("rsum "+rsum+" st "+startid);
			int curr = a[i];
			rsum+=a[i];
			if(curr > prev && rsum < key) {
				startid = i;
				rsum -= prev;
			}
			if(rsum > key) {
				endid = i;
				if(endid - startid > minsublength) {
					minsublength = endid - startid + 1 ;
				}
				System.out.println("rsum "+rsum+" minsub "+minsublength);
				startid = i;
				minsublength = 0;
				rsum = a[i];
				//break;
			}
			prev = curr;
		}
		System.out.println("start "+startid+" end "+endid);
	}
	
	public static void isContinuousSubSeqSumPresent() {
		int[] a = {12,3,5,23,2,9,13};
		int target = 8;
		int sum = 0;
		int startpos = 0;
		for(int i=0; i<a.length; i++) {
			if(a[i] > target) {
				startpos = i+1;
				sum = 0;
				continue;
			}
			else {
				sum+=a[i];
				if(sum == target) {
					System.out.println("sum "+target+" present between index "+startpos+" end "+i);
					return;
				}
			}
		}
	}
		
	public static void main(String[] args) {
		isThreeSumO();
		findNumberLeastDiff();
		getMaxProfit();
		reservoirSampling();
		convertRoman();
		flatten();
		isContinuousSubSeqSumPresent();
		reservoirSamplingBitset();
		reservoirSamplingNewBitset();
		//sumMinSubArray();
	}

}
