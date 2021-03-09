package com.lx.juc.c_0020_01_Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class T07_LockSupport_WithoutSleep {
    volatile List list = new ArrayList();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    private static Thread t2 = null, t1 = null;

    public static void main(String[] args) {
        T07_LockSupport_WithoutSleep t = new T07_LockSupport_WithoutSleep();

        t1 = new Thread(() -> {
            System.out.println("t1 启动");
            for (int i = 0; i < 10; i++) {
                System.out.println("add: " + i);
                t.add(new Object());

                if (t.size() == 5) {
                    LockSupport.unpark(t2);
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2 = new Thread(() -> {
            System.out.println("t2 启动");
            LockSupport.park();
            LockSupport.unpark(t1);
            System.out.println("t2 结束");
        }, "t2");

        t2.start();
        t1.start();

    }

}
