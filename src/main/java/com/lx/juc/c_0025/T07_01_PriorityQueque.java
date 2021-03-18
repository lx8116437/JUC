package com.lx.juc.c_0025;

import java.util.PriorityQueue;

/**
 * PriorityQueue 会进行从小到大排序,非先进先出规则
 */
public class T07_01_PriorityQueque {
    public static void main(String[] args) {
        PriorityQueue<String> q = new PriorityQueue();
        q.add("c");
        q.add("a");
        q.add("b");
        q.add("d");
        q.add("f");
        q.add("e");


        for (int i = 0; i < 6; i++) {
            System.out.println(q.poll());
        }
    }
}
