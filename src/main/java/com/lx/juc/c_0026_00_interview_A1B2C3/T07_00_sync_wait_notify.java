package com.lx.juc.c_0026_00_interview_A1B2C3;

public class T07_00_sync_wait_notify {
    private static volatile boolean isFlag = false;
    static final Object o = new Object();

    public static void main(String[] args) {
        char[] a = "123456789".toCharArray();
        char[] b = "ABCDEFGHI".toCharArray();

        new Thread(() -> {
            synchronized (o) {
                while (!isFlag) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (char c : a) {
                    try {
                        System.out.print(c);
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                o.notify();
            }

        }, "t1").start();

        new Thread(() -> {
            synchronized (o) {
                for (char c : b) {
                    try {
                        System.out.print(c);
                        isFlag = true;
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }

        }, "t2").start();
    }
}
