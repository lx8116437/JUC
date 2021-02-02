package com.lx.juc.c_0007;

public class TestMain {
    public synchronized void m() {
        System.out.println(Thread.currentThread().getName() + "m start....");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "m end....");
    }

    public void n() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "n");
    }

    public static void main(String[] args) {
        TestMain t = new TestMain();
//        new Thread(() -> t.m(), "t1").start();
//        new Thread(() -> t.n(), "t2").start();
        new Thread(t::m, "t1").start();
        new Thread(t::n, "t2").start();

        //1.8之前的写法
        /*new Thread(new Runnable() {

            @Override
            public void run() {
                t.m();
            }

        });*/
    }

}
