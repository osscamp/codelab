package com.learning.btree;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

public class CreateTree {
	
	static Properties props = new Properties();
	static Node root = null;
	
	public static void loadTree() {
		try {
			props.load(CreateTree.class.getResourceAsStream("input.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String nodes = props.getProperty("nodes");
		StringTokenizer comma =  new StringTokenizer(nodes, ",");
		int[] nodeList = new int[comma.countTokens()];
		for (int i=0; i<nodeList.length; i++) {
			nodeList[i] = Integer.valueOf(comma.nextToken());
		}
		root = buildTree(nodeList);
	}

	private static Node buildTree(int[] nodeList) {
		int midIndex = nodeList.length / 2;
		Node rootNode = new Node(nodeList[midIndex]);
		for (int i=0; i<nodeList.length; i++){
			addNode(rootNode, nodeList[i]);
		}
		return rootNode;
	}
	
	private static Node addNode(Node node, int data) {
		Node newNode = new Node(data);
		if (node == null) {
			return newNode;
		}
		if(data < node.data) {
			node.left = addNode(node.left, data);
		} else {
			node.right = addNode(node.right, data);
		}  
		return node;
	}
	
	private static void printTree(Node node) {
		while (node.left != null) {
			printTree(node.left);
		}
		System.out.println("\n"+node.data);
		while (node.right != null) {
			printTree(node.right);
		}
	}
	
	public static void main(String[] args) {
		CreateTree.loadTree();
		printTree(root);
	}

}
