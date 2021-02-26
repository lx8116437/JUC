package com.lx.juc.c_0020;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class T07_TestCyclicBarrier {
    public static void main(String[] args) {
        /*CyclicBarrier cb = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("满人,发车!");
            }
        });*/
        CyclicBarrier cb = new CyclicBarrier(20, () -> System.out.println("满人,发车!"));
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    cb.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
