package com.lx.juc.c_0000;

import java.util.concurrent.TimeUnit;

public class T01_WhatIs_Thread {
    private static class T1 extends Thread {
        @Override
        public void run() {
            for (int i=0; i<10; i++){
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("T1");
            }
        }
    }

    public static void main(String[] args) {
        // 如果不调用start方法,线程不会启动,调用run方法,只是简单的方法调用,run方法执行后再执行其他方法
//        new T1().run();

        // 启动线程,无顺序执行
        new T1().start();

        for (int i=0; i<10; i++){
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("main");
        }
    }

}
