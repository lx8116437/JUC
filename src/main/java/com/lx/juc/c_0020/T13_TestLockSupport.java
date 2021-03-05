package com.lx.juc.c_0020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 *
 * LockSupport.park(); 停车阻塞线程
 * LockSupport.unpark(t); 启动唤醒指定线程
 *
 */
public class T13_TestLockSupport {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if(i == 5){
                    LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("5秒后继续执行!");
        LockSupport.unpark(t);
    }
}
