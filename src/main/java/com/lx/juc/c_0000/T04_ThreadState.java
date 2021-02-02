package com.lx.juc.c_0000;

public class T04_ThreadState {

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        System.out.println(t1.getState());
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1.getState());
    }

    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println(this.getState());

            for(int i=0; i<10; i++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(i);
            }
        }
    }
}
