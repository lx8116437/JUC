package com.lx.juc.c_0026_01_ThreadPool;

import java.util.concurrent.*;

/**
 * 认识Callable，对Runnable进行了扩展
 * 对Callable的调用，可以有返回值
 */
public class T03_Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("开始");
                TimeUnit.SECONDS.sleep(5);
                return "hello callable";
            }
        };

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(callable); // 异步
        System.out.println("已提交,验证异步");

        System.out.println(future.get()); // 阻塞

        executorService.shutdown();


    }

}
