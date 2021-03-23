package com.lx.juc.c_0026_00_interview_A1B2C3;

import java.util.concurrent.atomic.AtomicInteger;

public class T05_00_AtomicInteger {
    static AtomicInteger ai = new AtomicInteger(1);

    public static void main(String[] args) {
        char[] a = "123456789".toCharArray();
        char[] b = "ABCDEFGHI".toCharArray();

        new Thread(() -> {
            for (char c : a){
                while (ai.get() != 1){}
                System.out.print(c);
                ai.set(2);
            }
        }, "t1").start();

        new Thread(() -> {
            for (char c : b){
                while (ai.get() != 2){}
                System.out.print(c);
                ai.set(1);
            }
        }, "t2").start();
    }
}
