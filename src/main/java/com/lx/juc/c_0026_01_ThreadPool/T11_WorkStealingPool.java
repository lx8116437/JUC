package com.lx.juc.c_0026_01_ThreadPool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T11_WorkStealingPool {
    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newWorkStealingPool();
        // 返回的是可用的计算资源，而不是CPU物理核心数，对于支持超线程的CPU来说，单个物理处理器相当于拥有两个逻辑处理器，能够同时执行两个线程。
        System.out.println(Runtime.getRuntime().availableProcessors());

        service.submit(new R(1000));
        service.submit(new R(2000));
        service.submit(new R(2000));
        service.submit(new R(2000));
        service.submit(new R(2000));

        System.in.read();
    }

    static class R implements Runnable{

        int time;
        public R(int t){
            this.time = t;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(time + " " + Thread.currentThread().getName());
        }
    }
}
