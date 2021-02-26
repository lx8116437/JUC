package com.lx.juc.c_0020;

import java.util.concurrent.CountDownLatch;

public class T06_TestCountDownLatch {
    public static void main(String[] args) {
        usingJoin();
        usingCountDownLatch();
    }

    private static void usingCountDownLatch(){
        Thread[] thread = new Thread[100];
        CountDownLatch latch = new CountDownLatch(thread.length);
        for (int i = 0; i < thread.length; i++) {
//            System.out.println("latchThread: " + thread[i].getName());
            thread[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) {
//                    System.out.println("latchResult: " + result);
                    result += j;
                }
                latch.countDown();
            });
        }

        for (int i = 0; i < thread.length; i++) {
            thread[i].start();
        }

        for (int i = 0; i < thread.length; i++) {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("latch end");
    }

    private static void usingJoin() {
        Thread[] thread = new Thread[100];
        for (int i = 0; i < thread.length; i++) {
//            System.out.println("joinThread: " + thread[i].getName());
            thread[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) {
//                    System.out.println("joinResult: " + result);
                    result += j;
                }
            });
        }
        for (int i = 0; i < thread.length; i++) {
            thread[i].start();
        }

        for (int i = 0; i < thread.length; i++) {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("end join");
    }
}
