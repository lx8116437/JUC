package com.lx.juc.c_0009;

public class TestMain {
    synchronized void m1(){
        System.out.println("m1 start....");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1 end...");
    }

    synchronized void m2(){
        System.out.println("m2 start....");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 end...");
    }

    public static void main(String[] args) {
        new TestMain().m1();
    }
}
