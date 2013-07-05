package com.learning.btree;

import java.util.Arrays;

public class RunwayScheduling {
	
	private static int[] reservedTimes = {2, 6, 9, 13, 20, 29, 41, 49, 58, 66, 69, 73, 81};
	
	public static boolean reserveTime(int t) {
		boolean schedule = false;
		Arrays.sort(reservedTimes);
		int len = reservedTimes.length;
		if (t >= (reservedTimes[len-1] + 3)) {
			return true;
		}
		if (t < reservedTimes[0]) {return false;}
		schedule = findIndex(t, 0, len);
		System.out.println("isSche "+schedule);
		return schedule;
		
	}

	public static boolean findIndex(int t, int i1, int i2) {

		int mp = (i1 + i2)/2;
		if (i1 >= i2 || mp == i1 || mp == i2) {
			return false;
		}
		int mpVal = reservedTimes[mp];
		int mpValPrev = reservedTimes[mp-1];
		if (mpVal > t+2 && t > mpValPrev+2) {
			return true;
		} else if (t > mpVal){
			return findIndex(t, mp, i2);
		} else if (t < mpVal) {
			return findIndex(t, i1, mp);
		}
		return false;
	}
	
	public static void main(String[] args) {
		reserveTime(31);
	}
}
