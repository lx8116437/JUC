package com.lx.juc.c_0025;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class T04_ConcurrentQueue {
    public static void main(String[] args) {
        Queue q = new ConcurrentLinkedQueue();

        for (int i = 0; i < 10; i++) {
            q.add("a" + i);
        }

        System.out.println(q);
        System.out.println(q.size());

//        q =  new ConcurrentLinkedQueue();
        // 取出队列中第一个元素  相当于 get  remove
        //检索并删除此队列的开头，如果此队列为空，则返回{@code null}。
        System.out.println(q.poll());
        System.out.println(q.size());

//        q =  new ConcurrentLinkedQueue();
        // 取出队列中第一个元素,但是不删除 相当于 get
        //检索但不删除此队列的开头，如果此队列为空，则返回{@code null}。
        System.out.println(q.peek());
        System.out.println(q.size());
    }
}
