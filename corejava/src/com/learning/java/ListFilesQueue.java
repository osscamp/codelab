package com.learning.java;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class ListFilesQueue {
	
	public void listFiles(String dir) {
		Queue<File> fileQ = new LinkedList<>();
		File df = new File(dir);
		if(!df.exists() || !df.isDirectory()) {
			throw new IllegalArgumentException();
		}
		fileQ.add(df);
		while(!fileQ.isEmpty()) {
			File dirFile = fileQ.poll();
			StringBuilder sb = new StringBuilder();
			for(File f : dirFile.listFiles()) {
				if(f.isDirectory()) {
					fileQ.add(f);
				}else {
					sb.append(f.getName()).append(",");
				}
			}
			System.out.println(sb.toString());
		}
	}
	
	public static void main(String[] args) {
		ListFilesQueue lfq = new ListFilesQueue();
		lfq.listFiles("/tmp/D");
	}

}
