package com.lx.juc.c_0025;

import java.util.concurrent.LinkedTransferQueue;

/**
 * 采用一种预占模式。意思就是消费者线程取元素时，如果队列不为空，则直接取走数据，若队列为空，
 * 那就生成一个节点（节点元素为null）入队，然后消费者线程被等待在这个节点上，
 * 后面生产者线程入队时发现有一个元素为null的节点，生产者线程就不入队了，
 * 直接就将元素填充到该节点，并唤醒该节点等待的线程，被唤醒的消费者线程取走元素，
 * 从调用的方法返回。我们称这种节点操作为“匹配”方式
 */
public class T09_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.transfer("aaa");

        //strs.put("aaa");


		/*new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();*/


    }
}
