package com.lx.juc.c_0026_01_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 认识ExecutorService,阅读API文档
 * 认识submit方法，扩展了execute方法，具有一个返回值
 */
public class T02_ExecutorService {
    public static void main(String[] args) {
        /**
         * 线程池7个参数
         * corePollSize  核心线程数
         * maximumPollSize  最大线程数
         * keepAliveTime  保持活动时间
         * timeUnit     活动时间单位
         * workQueue    任务队列
         * threadFactory  线程工厂
         * rejectedExecutorHandler  拒绝策略
         */
        ExecutorService e = Executors.newCachedThreadPool();
    }
}
