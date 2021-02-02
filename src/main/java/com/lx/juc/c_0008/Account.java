package com.lx.juc.c_0008;

import java.util.concurrent.TimeUnit;

public class Account {
    String name;
    double balance;

    public synchronized void set(String name, double balance){
        this.name = name;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.balance = balance;
    }

    public synchronized double getBalance(){
        return this.balance;
    }

    public static void main(String[] args) {
        Account t = new Account();
        new Thread(() -> t.set("zhangSan", 100.0)).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(t.getBalance());

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(t.getBalance());
    }

}
