package com.lx.juc.c_0020_01_Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class T08_Semaphore {
    volatile List list = new ArrayList();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    private static Thread t2 = null, t1 = null;

    public static void main(String[] args) {
        T08_Semaphore t = new T08_Semaphore();
        Semaphore s = new Semaphore(1);

        t1 = new Thread(() -> {
            try {
                s.acquire();
                System.out.println("t1 启动");
                for (int i = 0; i < 5; i++) {
                    System.out.println("add: " + i);
                    t.add(new Object());
                }

                s.release();
                t2.start();
                t2.join();

                for (int i = 5; i < 10; i++) {
                    System.out.println("add: " + i);
                    t.add(new Object());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }, "t1");


        t2 = new Thread(() -> {
            try {
                s.acquire();
                System.out.println("t2 结束");
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                s.release();
            }
        }, "t2");

        t1.start();
    }
}
