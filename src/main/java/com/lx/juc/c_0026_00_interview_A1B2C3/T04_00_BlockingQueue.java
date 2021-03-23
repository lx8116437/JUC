package com.lx.juc.c_0026_00_interview_A1B2C3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class T04_00_BlockingQueue {

    static BlockingQueue<String> q1 = new ArrayBlockingQueue<String>(1);
    static BlockingQueue<String> q2 = new ArrayBlockingQueue<String>(1);

    public static void main(String[] args) {
        char[] a = "123456789".toCharArray();
        char[] b = "ABCDEFGHI".toCharArray();

        new Thread(() -> {
            for (char c : a) {
                System.out.print(c);
                try {
                    q2.put("ok");
                    q1.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            for (char c : b) {
                try {
                    q2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.print(c);

                try {
                    q1.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();


    }
}
