package com.lx.juc.c_0005;

public class TestMain implements Runnable{
    private /*volatile*/ static int count = 100;

    @Override
    public /*synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        TestMain t = new TestMain();
        for(int i=0; i<100; i++){
            new Thread(t, "THREAD" + i).start();
        }
    }
}

