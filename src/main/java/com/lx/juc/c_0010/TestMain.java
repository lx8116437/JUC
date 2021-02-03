package com.lx.juc.c_0010;

public class TestMain {
    synchronized void m(){
        System.out.println("m start....");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m end...");
    }

    public static void main(String[] args) {
        new TT().m();
    }
}

class TT extends TestMain{
    @Override
    synchronized void m() {
        System.out.println("child m start...");
        super.m();
        System.out.println("child m start...");
    }
}
