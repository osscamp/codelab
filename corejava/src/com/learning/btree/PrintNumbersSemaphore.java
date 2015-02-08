package com.learning.btree;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class PrintNumbersSemaphore {
	
	static Semaphore os = new Semaphore(1);
	static Semaphore es = new Semaphore(0);

	static class NumberPrinter implements Callable<Void> {
		

		public Integer upto;
		public Integer start;

		public NumberPrinter(int start, int n) {
			upto = n;
			this.start = start;
		}

		@Override
		public Void call() throws Exception {
			for (int i = start; i <= upto; i+=2) {
				if(i % 2 == 0) {
					es.acquire(1);
				} else {
					os.acquire(1);
				}
				System.out.println(i + " by "
							+ Thread.currentThread().getName());
				if(i % 2 == 0) {
					os.release(1);
				} else {
					es.release(1);
				}			
			}
			return null;
		}
	}

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(2);
		service.submit(new NumberPrinter(1, 20));
		service.submit(new NumberPrinter(2, 20));
		try {
			service.awaitTermination(20, TimeUnit.MILLISECONDS);
			service.shutdown();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
