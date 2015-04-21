package com.tigerjiang.jdk7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * TransferQueue是Java7新加入的一种线程安全的队列，实现了BockingQueue，也就时说这是一个特殊的阻塞队列。
 * TransferQueue目前只有一个实现—-LinkedTransferQueue，这是列表结构，意味着它是无边界的Queue。
 * 一般的队列在未满的情况下往队列中存放元素是不会发生阻塞的，而一般的LinkedBlockingQueue又是一个无边界队列，所以在存放元素不会发生阻塞。
 * 为了满足无边界队列存放元素的阻塞需求，TransferQueue就应运而生，
 * 在调用tranfer(…)方法存放元素时将发生阻塞，知道有外界要消费tranfer(…)所存放的元素，
 * 也就是说如果tranfer(…)的元素之前还有别的元素，那tranfer(…)依旧会阻塞，知道它被消费为止。
 * 
 * @author tiger
 *
 */
public class TransferQueueTest {

	public static void main(String[] args) throws InterruptedException {
		new TransferQueueTest().testTansferQueue();
	}

	public void testTansferQueue() throws InterruptedException {
		final TransferQueue<String> transferQueue = new LinkedTransferQueue<String>();
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new Runnable() {
			public void run() {
				try {
					// 此处阻塞，等待take()，poll()的发生。
					transferQueue.transfer("test000");
					System.out.println(Thread.currentThread().getName()+"子线程完成传递.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		// 此处阻塞，等待trankser(当然可以是别的插入元素的方法)的发生
		String test = transferQueue.take();
		System.out.printf("主线程完成获取 %s.\n", test);
		Thread.sleep(1000);
	}

}
