package com.lx.juc.c_0026_00_interview_A1B2C3;

import java.util.concurrent.locks.LockSupport;

public class T02_00_LockSupport {
    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        char[] a = "123456789".toCharArray();
        char[] b = "ABCDEFGHI".toCharArray();


         t1 = new Thread(() -> {
            for (char c : a){
                System.out.print(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "t1");

         t2 = new Thread(() -> {
            for (char c : b){
                System.out.print(c);
                LockSupport.unpark(t1);
                LockSupport.park();
            }

        }, "t2");

        t1.start();
        t2.start();
    }
}
