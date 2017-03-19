package com.learning.btree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class TreeMore {
	
	static class TTNode {
		TTNode left;
		TTNode right;
		int d;
		public TTNode(int d) {
			this.d = d;
		}
		@Override
		public String toString() {
			return ""+d;
		}
	}
	
	TTNode root = null;
	
	public TTNode insert(TTNode n, int data) {
		if(n == null) {
			TTNode nnode = new TTNode(data);
			if(root == null) {
				root = nnode;
			}
			return nnode;
		}
		if(data < n.d) {
			n.left = insert(n.left, data);
		} else {
			n.right = insert(n.right, data);
		}
		return n;
	}
	
	public int kthSmallest(int K, int nth, TTNode n) {		
		while(n.left != null) {
			n = n.left;			
		}
		return kthSmallestR(K, nth, n);
	}
	
	public int kthSmallestR(int K, int nth, TTNode n) {
		if(n != null) {
			if(nth == K) {
				return n.d;
			}
			if(n.left != null) {
				kthSmallestR(K, ++nth, n.left);
			}
			if(n.right != null) {
				kthSmallestR(K, ++nth, n.right);
			}
		}
		return -1;
	}
	
	public void treeToList(TTNode n, LinkedList list){
		if(n != null) {
			treeToList(n.left, list);
			list.add(n.d);
			treeToList(n.right, list);
		}
	}
	
	public void treeToAllList(TTNode n, List<LinkedList> list){
		if(n != null) {
			if(n.left != null || n.right != null) {
				list.add(new LinkedList());
			}
			treeToAllList(n.left, list);
			for(LinkedList llist : list) {
				llist.add(n.d);
			}
			treeToAllList(n.right, list);
		}
	}
	
	public void flipTreeUpsideDown(){
	    Queue<TTNode> nodes = new java.util.LinkedList<TTNode>();
	    nodes.add(root);
	    List<TTNode[]> bfsnodes = new ArrayList<>();
	    while(!nodes.isEmpty()){
	        int depth = nodes.size();
	        int i = 0;
	        TTNode[] nlevel = new TTNode[depth];

	        while(i < depth){
	            TTNode n = nodes.poll();
	            nlevel[i] = n;
	            if(n.left != null) {
	                nodes.add(n.left);
	            }
	            if(n.right != null) {
	                nodes.add(n.right);
	            }
	            i++;
	        }
	        bfsnodes.add(nlevel);
	        
	    }
	    for(int k = bfsnodes.size()-1; k>=1; k--) {
	        TTNode[] inode = bfsnodes.get(k);
	        if(k == bfsnodes.size()-1) {
	            root = inode[0];
	        }
	        if(inode.length != 2) {
	            throw new IllegalStateException("wrong # nodes");
	        }
	        TTNode[] inodePrev = bfsnodes.get(k-1);
	        inode[0].left = inode[1];
	        inode[0].right = inodePrev[0];
	        inode[0].right.left = null;
	    }

	}
	
	public static int height(TTNode n) {
		if(n != null) {
			int lh = height(n.left);
			int rh = height(n.right);
			return 1 + Math.max(lh, rh);
		}
		return 0;
	}
	
	//print binary tree in column order from top to bottom
	public void printVertical() {
		Queue<TTNode> q = new LinkedList<>();
		q.add(root);
		Map<TTNode, Integer> posMap = new HashMap<>();
		posMap.put(root, 0);
		TreeMap<Integer, List<TTNode>> tMap = new TreeMap<>();
		List<TTNode> al = new ArrayList<>();
		al.add(root);
		tMap.put(0, al);
		while(!q.isEmpty()) {
			int sz = q.size();
			while(sz > 0) {
				TTNode n = q.poll();
				int ppos = posMap.get(n);
				if(n.left != null) {
					posMap.put(n.left, ppos-1);
					q.add(n.left);
					List<TTNode> el = tMap.get(ppos-1);
					if(el != null) {
						el.add(n.left);
					}else{
						List<TTNode> al1 = new ArrayList<>();
						al1.add(n.left);
						tMap.put(ppos-1, al1);						
					}
				}
				if(n.right != null) {
					posMap.put(n.right, ppos+1);
					q.add(n.right);
					List<TTNode> el = tMap.get(ppos+1);
					if(el != null) {
						el.add(n.right);
					}else{
						List<TTNode> al1 = new ArrayList<>();
						al1.add(n.right);
						tMap.put(ppos+1, al1);						
					}
				}
				sz--;
			}
		}
		//System.out.println(posMap);
		System.out.println(tMap);
	}
	
	public void printZig() {
		TTNode n = root;
		LinkedList<TTNode> qt = new LinkedList<>();
		boolean left = true;
		qt.add(n);
		while(!qt.isEmpty()) {
			int sz = qt.size();
			StringBuilder sb = new StringBuilder();
			while(sz > 0) {
				TTNode t = left ? qt.poll() : qt.pollLast();
				sb.append(t.d).append(" ");
				if(left) {
					if(t.left != null) {qt.add(t.left);}
					if(t.right != null) {qt.add(t.right);}
				}else {
					if(t.right != null) {qt.addFirst(t.right);}
					if(t.left != null) {qt.addFirst(t.left);}
				}
				sz--;
			}
			System.out.println(sb);
			left = !left;
		}
	}
	
	public void sumleftLeaves(){
		Queue<TTNode> queue = new LinkedList<>();
		queue.add(root);
		int sum = 0;
		while(!queue.isEmpty()) {
			int sz = queue.size();
			while(sz > 0) {
				TTNode n = queue.poll();

				if(n.left != null) {
					if(n.left.left == null && n.left.right == null) {
						sum += n.left.d;
					}
					queue.add(n.left);
				} else if(n.right != null) {
					queue.add(n.right);
				}
				sz--;
			}
		}
		System.out.println("left leave sum "+sum);
	}
	
    public int evalTreeR(TTNode n) {
        if(n != null) {
          String data = ""+n.d;
          if(isNumeric(data)) {
              return Integer.valueOf(data);
          } else {
              int lval = evalTreeR(n.left);
              int rval = evalTreeR(n.right);
              String nodeval = ""+n.d;
              if(nodeval.equals("*")) {
                  return lval * rval;
              } else if(nodeval.equals("+")) {
                  return lval + rval;
              } else {
                  return lval - rval;
              }
          }
        }
        return 0;
    }
    
    public boolean isNumeric(String str)
      {
          for (char c : str.toCharArray())
          {
              if (!Character.isDigit(c)) return false;
          }
          return true;
      }
	
	public static void main(String[] args) {
		TreeMore more = new TreeMore();
		more.insert(null, 15);
		more.insert(more.root, 11);
		more.insert(more.root, 19);
		more.insert(more.root, 16);
		more.insert(more.root, 24);
		more.insert(more.root, 13);
		more.kthSmallest(3, 0, more.root);
		LinkedList ll = new LinkedList();
		more.treeToList(more.root, ll);
		//ll.print();
		ll = new LinkedList();
		List<LinkedList> listlist = new ArrayList<>();
		more.treeToAllList(more.root, listlist);
		for(LinkedList ml : listlist) {
			System.out.println();
		}
		
		more = new TreeMore();
		more.insert(null, 6);
		more.root.left = new TTNode(3);
		more.root.right = new TTNode(4);
		more.root.right.left = new TTNode(10);
		more.root.left.left = new TTNode(5);
		more.root.left.right = new TTNode(1);
		more.root.right.right = new TTNode(0);
		more.root.right.right.left = new TTNode(8);
		more.root.left.left.left = new TTNode(9);
		more.root.left.left.right = new TTNode(2);
		more.root.left.left.right.right = new TTNode(7);
		more.printVertical();
		//more.flipTreeUpsideDown();
		more.printZig();
		
		TreeMore your = new TreeMore();
		your.insert(null, 3);
		your.root.left=new TTNode(5);
		your.root.right=new TTNode(20);
		your.sumleftLeaves();
	}

}
