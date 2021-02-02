package com.lx.juc.c_0006;

public class TestMain implements Runnable{
    private int count = 10;
    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + "count = " +count);
    }

    public static void main(String[] args) {

        for (int i=0; i<5; i++){
            TestMain t = new TestMain();
            new Thread(t, "THREAD" + i + "-->").start();
        }
    }
}
