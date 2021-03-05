package com.lx.juc.c_0020;

import java.util.concurrent.Exchanger;

/**
 * Exchanger 是 JDK 1.5 开始提供的一个用于两个工作线程之间交换数据的封装工具类，
 * 简单说就是一个线程在完成一定的事务后想与另一个线程交换数据，则第一个先拿出数据的线程会一直等待第二个线程，
 * 直到第二个线程拿着数据到来时才能彼此交换对应数据
 *
 */
public class T12_TestExchanger {
    static Exchanger<String> exchanger = new Exchanger<>();
    public static void main(String[] args) {

        new Thread(() -> {
            String s = "T1";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        },"线程name1").start();

        new Thread(() -> {
            String s = "T2";
            try {
               s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        },"线程name2").start();


    }
}
