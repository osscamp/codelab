package com.learning.re;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeFromAssoc {
	
	 static class Relation {
		int val;
		int p;
		boolean isLeft;
		public Relation(int val, int p, boolean isLeft) {
			super();
			this.val = val;
			this.p = p;
			this.isLeft = isLeft;
		}
		
	}
	
	static class ANode {
		int nv;
		ANode left;
		ANode right;
		public ANode(int nv) {
			super();
			this.nv = nv;
		}
	}
	
	public static class PComp implements Comparator<Relation> {

		@Override
		public int compare(Relation o1, Relation o2) {
			if(o1 != null && o2 != null) {
				if(o1.p > o2.p) {
					return 1;
				} else if(o1.p < o2.p) {
					return -1;
				}else {
					return 0;
				}
			}
			return 0;
		}
		
	}
	
	ANode root;
	
	
	public void buildTree() {
		List<Relation> tlist = new ArrayList<>();
		tlist.add(new Relation(50, -1, true));
		tlist.add(new Relation(15, 20, true));
		tlist.add(new Relation(20, 50, true));
		tlist.add(new Relation(19, 80, true));
		tlist.add(new Relation(16, 80, false));
		tlist.add(new Relation(80, 50, false));
		tlist.add(new Relation(17, 20, false));
		Collections.sort(tlist, new PComp());
		Relation rl = tlist.get(0);
		Queue<ANode> nodeQ = new LinkedList<>();
		nodeQ.add(new ANode(rl.val));
		while(!nodeQ.isEmpty()) {
			int sz = nodeQ.size();
			int i = 0;
			while(i < sz) {
				ANode an = nodeQ.poll();
				if(root == null) root = an;
				List<Relation> mrel = srchRelation(an.nv, tlist);
				for(Relation rr :mrel) {
					ANode an1 = new ANode(rr.val);
					if(rr.isLeft) {
						an.left = an1;
					}else{
						an.right = an1;
					}
					nodeQ.add(an1);
				}
				i++;
			}
			
		}
	}
	
	//could be improved to binary search
	public List<Relation> srchRelation(int parentVal, List<Relation> list) {
		List<Relation> ret = new ArrayList<>();
		for(int i=0; i<list.size(); i++) {
			Relation r = list.get(i);
			if(r.p == parentVal) {
				ret.add(r);
				if(i <list.size()-1 && list.get(i+1).p == parentVal) {
					ret.add(list.get(i+1));
					break;
				}
			}
		}
		return ret;
	}
	
	public void printBFSWithQueueSimple(){
		Queue<ANode> ll = new LinkedList<>();
		ll.add(root);
		int levelnodes = 0;
		while(!ll.isEmpty()) {
			levelnodes = ll.size();
			StringBuilder out = new StringBuilder();
			while(levelnodes > 0) {
				ANode p = ll.poll();
				out.append(" ").append(p.nv);
				if(p.left != null) {
					ll.add(p.left);
				}
				if(p.right != null) {
					ll.add(p.right);
				}
				levelnodes--;
			}
			System.out.println(out);
		}
	}
	
	public static void main(String[] args) {
		TreeFromAssoc ta  =new TreeFromAssoc();
		ta.buildTree();
		ta.printBFSWithQueueSimple();
	}

}
