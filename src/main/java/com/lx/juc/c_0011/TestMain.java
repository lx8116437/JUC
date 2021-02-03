package com.lx.juc.c_0011;

import java.util.concurrent.TimeUnit;

public class TestMain {
    int count = 0;
    synchronized void m(){
        System.out.println(Thread.currentThread().getName() + " start");
        while (true){
            count++;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(count == 5){
                int i = 1/0; //此处抛出异常，锁将被释放，要想不被释放，可以在这里进行catch，然后让循环继续
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        TestMain t = new TestMain();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        };

        new Thread(r, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r, "t2").start();
    }
}
