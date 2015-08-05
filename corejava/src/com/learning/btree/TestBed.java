package com.learning.btree;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class TestBed {
	
	public static void quickSortArray(int[] qs) {
		int l = 0;
		int r = qs.length - 1;
		quickSort(qs, l, r);
	}
	
	public static void quickSort(int[] qs, int l, int r) {
		if(l < r) {
			int pivot = partition(qs, l, r);
			System.out.println(pivot+","+l+","+r);
			quickSort(qs, l, pivot-1);
			quickSort(qs, pivot+1, r);
		}
	}
	
	public static int partition(int[] qs, int l, int r) {
		if(qs.length == 0) {
			return 0;
		}
		//int r = qs.length - 1;
		int pivot = qs[r];
		int j = l;
		int i = j;
		while(j<r) {
			if(qs[j] < pivot) {
				int tmp = qs[j];
				qs[j] = qs[i];
				qs[i] = tmp;
				i++;
			}
			j++;
		}
		int tmp = qs[i];
		qs[i] = pivot;
		qs[r] = tmp;
		return i;
	}
	
	public static void print(int[] arr) {
		StringBuilder sb = new StringBuilder();
		for(int a: arr) {
			sb.append(a).append(",");
		}
		System.out.println(sb.toString());
	}
	
	public static void printSeries(int level) {
		long n1 = System.currentTimeMillis();
		if(level <= 0) {
			return;
		}
		int ct = 1;
		int div = 10;
		int v = ct/div;
		int pv = v;

		while(true) {
			if(v > 0) div *= 10;
			v = (ct++)/div;
			pv += v;
			if(pv < level) {
				//System.out.println(ct-1);				
			} else {
				//System.out.println(ct-1);	
				long n2 = System.currentTimeMillis();
				System.out.println("tt:"+(n2-n1));
				return;
			}
		}
	}
	
	public static void printSeries2(int level) {
		long n1 = System.currentTimeMillis();
		int end = (int)Math.pow(10, level);
		for(int i=0; i < end; i++) {
			//if(i == end -1)System.out.println(i);
		}
		long n2 = System.currentTimeMillis();
		System.out.println("tt:"+(n2-n1));
	}
	
	public static void partitionArray(int[] a) {
		int ctr = Math.max(0, a.length - 1);
		for(int i=0; i<ctr; i++) {
			if((a[i] % 2 == 0 && a[i+1] % 2 == 0) ||
					(a[i] % 2 != 0 && a[i+1] % 2 != 0)) {
				continue;
			}
			if (a[i] % 2 != 0 && a[i+1] % 2 == 0) {
				//swap with 1st odd;
				int j = i+1;
				while( j<=ctr ) {
					if(a[j] % 2 != 0) {
						int tmp = a[j];
						a[j] = a[i+1];
						a[i+1] = tmp;
						break;
					}
					j++;
				}
				if(j >= ctr) break;
			}
			else if (a[i] % 2 == 0 && a[i+1] % 2 != 0) {
				//swap with 1st even;
				int j = i+1;
				while( j<=ctr ) {
					if(a[j] % 2 == 0) {
						int tmp = a[j];
						a[j] = a[i+1];
						a[i+1] = tmp;
						break;
					}
					j++;
				}
				if(j >= ctr) break;
			}
		}
	}
	
	public static void findLeastk(int k, int[] a) {
		int[] aux = new int[k];
		Set<Integer> skipi = new HashSet<Integer>();
		int max = 0;
		for(int kk=0; kk<k; kk++) {
			int min = max == 0 ? a[0] : max;
			int p=0;
			for (int i=0; i<a.length; i++){
				if(skipi.contains(i)) {
					continue;
				}
				if(a[i] > max && kk==0) {
					max=a[i];
				}
				if(a[i] < min) {
					min = a[i];
					p = i;
				}
			}
			skipi.add(p);
			aux[kk] = min;
		}
		for(int jj : aux) {
			System.out.println("least k "+jj);
		}
	}
	
	public static void deleteChars(String data, char[] del) {
		StringBuilder sdata = new StringBuilder(data);
		Set<Character> delset = new HashSet<Character>(del.length);
		for(char c: del) {
			delset.add(c);
		}
		for(int i=0; i<sdata.length(); i++) {
			if(delset.contains(sdata.charAt(i))) {
				sdata.deleteCharAt(i);
			}
		}
		System.out.println(sdata);
	}
	

	
	public static void main(String[] args) {
		int[] arr = {17,26,9,7,100,45,8,12,31};
		//int[] arr = {5,4,5};
		//int val = partition(arr,0,arr.length-1);
		//System.out.println("parted at "+val);
		//quickSortArray(arr);
		print(arr);
		printSeries(6);
		printSeries2(6);
		partitionArray(arr);
		print(arr);
		findLeastk(6, arr);
		String str = "We are students";
		char[] del = {'a', 'e', 'i', 'o', 'u'};
		deleteChars(str, del);

	}

}
