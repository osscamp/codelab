package com.learning.btree;

public class PrintPatterns {
	
	public static void printRTriangle(int rows) {
		for(int i = 1; i<=rows; i++) {
			StringBuilder sb = new StringBuilder();
			for(int j=0; j<i; j++) {
				sb.append("#");
			}
			System.out.println(sb.toString());
		}
	}
	
	public static void printRTriangleUpsideDown(int rows) {
		for(int i = rows; i>=1; i--) {
			StringBuilder sb = new StringBuilder();
			for(int j=0; j<i; j++) {
				sb.append("#");
			}
			System.out.println(sb.toString());
		}
	}
	
	public static void printRTriangleRHanded(int rows) {
		StringBuilder sb = new StringBuilder();
		for(int i = rows; i>=1; i--) {
			for(int j=0; j<=rows; j++) {
				if(j<i) {
					sb.insert(0, "#");
				} else {
					sb.insert(0, " ");
				}
			}
			sb.insert(0, "\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void main(String[] args) {
		printRTriangle(5);
		System.out.println("\n\n");
		printRTriangleUpsideDown(5);
		printRTriangleRHanded(5);
	}

}
