package com.lx.juc.c_0025;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class T06_ArrayBlockingQueue {
    static BlockingQueue<String> q = new ArrayBlockingQueue(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
                q.put("a" + i);
        }

        q.put("a10"); //满了就会等待，程序阻塞
//        q.add("a10"); // 满了抛出异常
//        q.offer("a10"); // 满了则不新增不抛异常
//        q.offer("a10", 1, TimeUnit.SECONDS); // 如果满了则等待指定时间,超出时间后还没有位置则结束

        System.out.println(q);
    }
}
