package com.lx.juc.c_0026_01_ThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 认识future
 * 异步
 */
public class T06_00_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(10);
            return 1000;
        }); //new Callable () { Integer call();}
        new Thread(task).start();
        System.out.println("开始");
        System.out.println(task.get()); // 阻塞
        System.out.println("结束");
    }

}
