package com.lx.juc.c_0025;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * http://blog.csdn.net/sunxianghuang/article/details/52221913
 * http://www.educity.cn/java/498061.html
 * 阅读concurrentskiplistmap
 */
public class T01_ConcurrentMap {
    public static void main(String[] args) {
        Map map = new ConcurrentHashMap();

//        Map map = new ConcurrentSkipListMap(); //高并发并且排序 跳表

//        Map map = new Hashtable();

//        Map map = new HashMap();

//        Map map = Collections.synchronizedMap(new LinkedHashMap<>()); //Collections.synchronizedXXX

        Random r = new Random();
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);

        long start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() ->{
                for (int j = 0; j < 10000; j++) {
                    map.put("a" + r.nextInt(100000), "b" + r.nextInt(100000));
                }
                latch.countDown();
            });
        }

        Arrays.asList(threads).forEach(o -> o.start());

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(map.size());
    }
}
