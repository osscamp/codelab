package com.learning.btree;

public class PrintNumbers {
	static Object mutex = new Object();

	static class NumberPrinter implements Runnable {

		public Integer upto;
		public Integer start;

		public NumberPrinter(int start, int n) {
			upto = n;
			this.start = start;
		}

		@Override
		public void run() {
			for (int i = start; i <= upto; i+=2) {
				synchronized (mutex) {
					System.out.println(i + " by "
							+ Thread.currentThread().getName());
					mutex.notifyAll();
					try {
						if(i< upto) {
							mutex.wait();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		NumberPrinter printer = new NumberPrinter(1,20);
		Thread thread1 = new Thread(printer, "thread1");
		Thread thread2 = new Thread(new NumberPrinter(2,20), "thread2");
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// thread1.start();
	}

}
