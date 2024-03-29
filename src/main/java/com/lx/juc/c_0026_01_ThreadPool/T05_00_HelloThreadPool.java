package com.lx.juc.c_0026_01_ThreadPool;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的7个参数
 * coreSize 核心线程数
 * maximumPoolSize 最大线程数
 * keepAliceTime 线程最大空闲等待时间
 * TimeUnit 等待时间单位
 * workQueue 任务队列
 * threadFactory 线程工厂
 * handler 拒绝策略
 */
public class T05_00_HelloThreadPool {
    static class Task implements Runnable {
        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "task:" + i);
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    '}';
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(4), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 8; i++) {
            tpe.execute(new Task(i));
        }

        System.out.println(tpe.getQueue());

        tpe.execute(new Task(100));

        System.out.println(tpe.getQueue());

        tpe.shutdown();

    }
}
