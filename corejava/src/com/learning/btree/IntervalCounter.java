package com.learning.btree;

import java.util.Set;
import java.util.TreeSet;

//intervals sorted in decreasing order by end time
public class IntervalCounter {
	static class Interval implements Comparable<Interval> {
		int from;
		int to;
		public Interval(int from, int to) {
			this.from = from;
			this.to = to;
		}
		@Override
		public int compareTo(Interval o) {
			if(o != null) {
				if(o.to == this.to) {
					return o.from <= this.from ? -1 : 1;
				} else {
					return o.to < this.to ? -1 : 1;
				}
			}
			return -1;
		}
		public String toString() {
			return "{"+from+","+to+"}";
		}
	}
	Set<Interval> intervals = new TreeSet<>();
	
	public void addInterval(int from, int to) {
		Interval interval = new Interval(from, to);
		intervals.add(interval);
	}
	
	public int getTotalCoveredLength() {
		
		Interval[] iarr = intervals.toArray(new Interval[intervals.size()]);
		int intervalst = iarr[0].from;
		int intervalend = iarr[0].to;
		int totalinterval = intervalend-intervalst;
		int psum = totalinterval;
		for(int i=0; i<iarr.length-1; i++) {
			int nextstart = iarr[i+1].from;
			int nextend = iarr[i+1].to;
			if(nextend > intervalst && nextstart <  intervalst) {
				intervalst = nextstart;
				System.out.println(intervalst+" "+intervalend);
				totalinterval -= psum;
				psum = (intervalend-intervalst);
				totalinterval += psum;

			}
			else if(nextstart < intervalst) {
				//no overlap
				System.out.println(intervalst+" "+intervalend);
				intervalst = nextstart;
				intervalend = nextend;
				psum = (intervalend-intervalst);
				totalinterval += psum;
			}

		}
		return totalinterval;
	}
	
	public static void main(String[] args) {
		IntervalCounter ic = new IntervalCounter();
		ic.addInterval(10,11);
		ic.addInterval(6, 9);
		ic.addInterval(4,7);
		ic.addInterval(5,6);
		ic.addInterval(1,5);
		ic.addInterval(13,14);


		System.out.println(ic.intervals);
		int total = ic.getTotalCoveredLength();
		System.out.println(total);
	}

}
