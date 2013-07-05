package com.learning.java;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ExecutorWork {

	static class FileCounter implements Callable<Integer> {
		
		String path;
		
		public FileCounter(String path) {
			this.path = path;
		}

		@Override
		public Integer call() throws Exception {
			File file = new File(path);
			return cfiles(file);
		}
			
		public static Integer cfiles(File f) {
			if(!f.isDirectory()) {
				return 1;
			}
			int fCount = 0;
			for (File tmp: f.listFiles()) {
				fCount += cfiles(tmp);
			}
			
			return fCount;
		}
		
	}
	
	public static void main(String[] args) throws Exception, ExecutionException {
		ExecutorService exec = new ScheduledThreadPoolExecutor(2);
		Future<Integer> fInt = exec.submit(new FileCounter("/Users/sushukla/Documents/misc"));
		Future<Integer> fInt1 = exec.submit(new FileCounter("/Users/sushukla/Documents/resume"));
		Future<Integer> fInt2 = exec.submit(new FileCounter("/Users/sushukla/"));
		Integer count = fInt.get();
		System.out.println("other activity");
		//exec.awaitTermination(3000l, TimeUnit.MILLISECONDS);
		exec.shutdown();
		//int count = FileCounter.cfiles(new File("/Users/sushukla/Documents/misc/"), 0);
		System.out.println("count ="+count+"  "+fInt1.get()+" "+fInt2.get());

	}

}
