package com.lx.juc.c_0026_01_ThreadPool;

import java.io.IOException;
import java.util.concurrent.*;

public class T14_MyRejectedHandler {
    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(4, 4, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(4),
                Executors.defaultThreadFactory(),
                new MyHandler());

        for (int i = 0; i < 8; i++) {
            service.submit(new MyTask(i));
        }

        /**
         * 当线程池 最大线程数+任务队列最大容量都被占用时
         * 此时再有新任务进入,则进行拒绝策略
         */
        service.submit(new MyTask(100));

        service.shutdown();
    }

    static class MyHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("新任务进来,最大线程 + 任务队列已满,执行拒绝策略");
            System.out.println("拒绝策略开始");
            try {
                System.out.println("拒绝策略进行.....");
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("拒绝策略结束");
        }
    }

    static class MyTask implements Runnable{
        int i;
        MyTask(int num){
            i = num;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "---"  + i);
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
