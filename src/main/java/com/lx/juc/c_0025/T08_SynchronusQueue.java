package com.lx.juc.c_0025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 手递手交换,没有现成等待获取,使用put会一直阻塞,因为SynchronousQueue容量为0
 */
public class T08_SynchronusQueue { //容量为0
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue q = new SynchronousQueue();

//        new Thread(() ->{
//            try {
//                System.out.println(q.take());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();

        q.put("aaa"); //阻塞等待消费者消费
//        q.add("aaa");

        System.out.println(q.size());
    }
}
