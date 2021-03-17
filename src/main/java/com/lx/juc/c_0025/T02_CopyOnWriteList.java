package com.lx.juc.c_0025;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写时复制容器 copy on write
 * 多线程环境下，写时效率低，读时效率高
 * 适合写少读多的环境
 *
 * @author lx
 */
public class T02_CopyOnWriteList {
    public static void main(String[] args) {

        // 读取快 写慢
        List list = new CopyOnWriteArrayList();
//        List list = new ArrayList();
//        List list = new Vector();

        Random r = new Random();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        list.add("a" + r.nextInt(10000));
                    }
                }
            };

            threads[i] = new Thread(task);
        }
        runTime(threads);
        System.out.println(list.size());
    }

    private static void runTime(Thread[] threads) {
        long start = System.currentTimeMillis();
        Arrays.asList(threads).forEach(t -> t.start());
        Arrays.asList(threads).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }


}
