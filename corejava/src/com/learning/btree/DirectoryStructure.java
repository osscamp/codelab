package com.learning.btree;

import java.util.ArrayList;
import java.util.List;


public class DirectoryStructure {
	
	static List<String[]> data = new ArrayList<>();
	
	public DirectoryStructure() {
		data.add(new String[]{"1","Electronics","0"});
		data.add(new String[]{"2","Television","1"});
		data.add(new String[]{"3","50Inch","2"});
		data.add(new String[]{"30","32Inch","3"});
		data.add(new String[]{"4","56Inch","2"});
		data.add(new String[]{"5","player","1"});
		data.add(new String[]{"6","mp3","5"});
		data.add(new String[]{"7","Clothing","0"});
		data.add(new String[]{"8","camera","1"});
		data.add(new String[]{"9","dvd","1"});
		createTree();
	}
	
	static class Node{
		List<Node> children;
		String value;
		String category;
		String parent;
		public Node(String category, String value, String parent) {
			this.category = category;
			this.value = value;
			this.parent = parent;
			this.children = new ArrayList<>();
		}
		
		public String toString() {
			return value;
		}
		
		public String build() {
			StringBuilder sb = new StringBuilder();
			sb.append("\n");
			sb.append("\t");
			sb.append(value);
			return sb.toString();
		}
	}
	
	Node root;
	
	public void createTree() {
		for(String[] dr : data) {
			if(root == null) {
				root = new Node(dr[2], "root", null);
			}
			addNode(dr[0], dr[1], dr[2]);
		}
	}
	
	public Node addNode(String category, String value, String pcat) {
		Node pnode = findNode(root, pcat);
		if(pnode != null) {
			Node newn = new Node(category, value, pcat);
			pnode.children.add(newn);
			return newn;
		}
		return null;
	}
	
	public Node findNode(Node t, String cat) {
		if(t == null) {
			return null;
		}
		if(t.category.equals(cat)) {
			return t;
		}
		for(Node nn : t.children) {
			if(nn.category.equals(cat)) {
				return nn;
			} else {
				Node val = findNode(nn, cat);
				if(val == null) {
					continue;
				} else {
					return val;
				}
			}
		}
		return null;
	}
	
	public StringBuilder printTree(Node n, StringBuilder sb, int level) {
		if(n == null) {
			return sb;
		}
		sb.append("-").append(n.value);
		sb.append("\n");
		level++;

		for(int i=0; i<n.children.size(); i++) {
			Node nn = n.children.get(i);
			for(int k=0; k<level; k++) {
				sb.append("    ");
			}
			printTree(nn, sb, level);
		}
		return sb;
	}
	
	public static void main(String[] args) {
		DirectoryStructure ds = new DirectoryStructure();
		StringBuilder b = new StringBuilder();
		ds.printTree(ds.root, b, 0);
		System.out.println(b);
		
	}

}
