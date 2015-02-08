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
	
	public static long power(int a, int b) {
		if(a == 0 && b == 0) {
			return -1;
		}
		if(b <  0) {
			return -1;
		}
		if(b == 0) { return 1;}
		long res = (long)a;
		for(int i=0; i<b; i++) {
			res *= a;
			if((Long.MAX_VALUE/res) < a) {
				return -1;
			}
		}
		System.out.println("pow "+res);
		return res;
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
		for(int kk=0; kk<k; kk++) {
			int min = a[0];
			int p=0;
			for (int i=0; i<a.length; i++){
				if(skipi.contains(i)) {
					continue;
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
	
	//partition the array at end , if pivot == k, kth is found
	public static void findKthLargest(int[] a, int k) {
		int len = a.length;
		int pivot = a[len - 1];
		int i = 0;
		int j = i+1;
		while(i < len && j < len) {
			if(a[j] < pivot) {
				int tmp = a[j];
				a[j] = a[i];
				a[i] = tmp;
				i++;
			}
			j++;
		}
		int tmp = a[i];
		a[i] = pivot;
		a[len - 1] = tmp;
		System.out.println("pivot pos "+i);
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
	
	public static boolean isSumPresent(int[] a, int sum) {
		int lp = 0;
		int rp = a.length - 1;
		int rs = 0;
		while(lp < rp) {
			rs = a[lp] + a[rp];
			if(rs < sum) {
				lp++;
			} else if (rs > sum) {
				rp--;				
			} else {
				return true;
			}
			
		}
		return false;
	}
	
	public static boolean isThreeSum0(int[] a) {
		if(a == null) {
			return false;
		}
		Arrays.sort(a);
		int nn = 0;
		for(nn=0; nn<a.length - 2 && a[nn] < 0; nn++) {
			int one = a[nn];
			if(one > 0) return false;
			int img = -1*one;
			if(isSumPresent(a, img)) {
				return true;
			}
		}
		if(a[nn] == 0 && nn+2 < a.length &&(a[nn] == 0 && a[nn+1] == 0 && a[nn+2]==0)) {
			return true;
		}
		return false;
	}
	
	public static int findMaxDiff(int[] a) {
		int max = Integer.MIN_VALUE;
		int maxdiff = Integer.MIN_VALUE;
		for(int i=a.length - 1; i>=0; i--) {
			if(a[i] > max) {
				max = a[i];
			}
			else {
				int diff = max - a[i];
				if(diff > maxdiff) {
					maxdiff = diff;
				}
			}
		}
		return maxdiff;
	}
	
	public static void main(String[] args) {
		int[] arr = {4,2,9,7,1,3,8,12,6};
		//int[] arr = {5,4,5};
		//int val = partition(arr,0,arr.length-1);
		//System.out.println("parted at "+val);
		//quickSortArray(arr);
		print(arr);
		power(2, 5);
		printSeries(6);
		printSeries2(6);
		partitionArray(arr);
		print(arr);
		findLeastk(5, arr);
		String str = "We are students";
		char[] del = {'a', 'e', 'i', 'o', 'u'};
		deleteChars(str, del);
		System.out.println(isSumPresent(new int[] {1,2,3,5,7}, 14));
		System.out.println(isThreeSum0(new int[] {-1,0,1}));
		System.out.println(findMaxDiff(new int[]{9, 11, 5, 7, 16, 1, 4, 2}));
		findKthLargest(new int[]{9, 11, 5, 7, 16, 1, 4, 8}, 6);
	}

}
