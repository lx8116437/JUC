package com.lx.juc.c_0026_01_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单例线程池
 * 线程声明周期完成
 * 不需要自己维护消息队列
 * 有序执行
 */
public class T07_SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            final int j = i;
            service.execute(() -> {
                System.out.println(j + "xx: " + Thread.currentThread().getName());
            });
        }
    }
}
