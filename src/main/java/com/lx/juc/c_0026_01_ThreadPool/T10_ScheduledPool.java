package com.lx.juc.c_0026_01_ThreadPool;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时执行任务线程池
 */
public class T10_ScheduledPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);

        // command：执行线程
        // initialDelay：初始化延时
        // period：两次开始执行最小间隔时间
        // unit：计时单位
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }, 0, 500, TimeUnit.MILLISECONDS);
    }
}
