package com.learning.re;

import java.util.ArrayList;
import java.util.List;

/**
 * merge sorted intervals by 1st val
 * @author sushukla
 *
 */
public class MergeIntervals {
	
	static class Interval {
		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
		int start;
		int end;
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "{"+start+","+end+"}";
		}
	}

	public static List<Interval> mergeIntervals(List<Interval> sortedIntervals) {
		if(sortedIntervals == null || sortedIntervals.isEmpty()) {
			return sortedIntervals;
		}
		List<Interval> mergedList = new ArrayList<>();
		Interval prev = sortedIntervals.get(0);
		for(int i=1; i<sortedIntervals.size(); i++) {
			Interval next = sortedIntervals.get(i);
			if(next.start >= prev.start && next.start <= prev.end) {
				int mstart = Math.min(prev.start, next.start);
				int mend = Math.max(prev.end, next.end);
				Interval merged = new Interval(mstart, mend);

				prev = merged;
			} else {
				mergedList.add(prev);
				prev = next;
			}
		}
		return mergedList;
	}
	
	public static void main(String[] args) {
		List<Interval> list = new ArrayList<>();
		list.add(new Interval(1,3));
		list.add(new Interval(3,6));
		list.add(new Interval(6,8));
		list.add(new Interval(8,13));
		list.add(new Interval(10,12));
		list.add(new Interval(14,18));
		list.add(new Interval(17,19));
		list.add(new Interval(21,22));
		list.add(new Interval(23,24));
		List<Interval> ml = mergeIntervals(list);
		System.out.println(ml);
	}
}
