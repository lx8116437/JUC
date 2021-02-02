package com.lx.juc.c_0004;

public class TestMain {
    private static int count = 10;

    public synchronized static void m(){ //这里等同于synchronized(FineCoarseLock.class)
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void mm() {
        synchronized(TestMain.class) { //考虑一下这里写synchronized(this)是否可以？
            count --;
        }
    }
}

