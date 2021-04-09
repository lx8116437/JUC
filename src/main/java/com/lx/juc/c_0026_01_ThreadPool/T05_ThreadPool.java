package com.lx.juc.c_0026_01_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的概念
 */
public class T05_ThreadPool {
    public static void main(String[] args) throws InterruptedException {
        // 固定大小线程池
        ExecutorService service = Executors.newFixedThreadPool(5); //execute submit
        for (int i = 0; i < 6; i++) {
            service.execute(() -> {
                try {
                    // 模拟业务处理时间
                    TimeUnit.MILLISECONDS.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 输出线程名字
                System.out.println(Thread.currentThread().getName());
            });
        }

        // 输出线程信息
        System.out.println(service);
        service.shutdown();
        // 是否被终止
        System.out.println(service.isTerminated());
        // 是否关闭
        System.out.println(service.isShutdown());
        System.out.println(service);

        TimeUnit.SECONDS.sleep(5);
        // 是否被终止
        System.out.println(service.isTerminated());
        // 是否关闭
        System.out.println(service.isShutdown());
        System.out.println(service);
    }

}
