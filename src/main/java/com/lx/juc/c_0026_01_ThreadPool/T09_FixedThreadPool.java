package com.lx.juc.c_0026_01_ThreadPool;

import com.lx.juc.c_0022_RefTypeAndThreadLocal.M;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class T09_FixedThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        getPrime(1, 200000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        ExecutorService service = Executors.newFixedThreadPool(4);
        MyTask t1 = new MyTask(1, 80000); //1-5 5-10 10-15 15-20
        MyTask t2 = new MyTask(80001, 130000);
        MyTask t3 = new MyTask(130001, 170000);
        MyTask t4 = new MyTask(170001, 200000);
        Future<List<Integer>> f1 = service.submit(t1);
        Future<List<Integer>> f2 = service.submit(t2);
        Future<List<Integer>> f3 = service.submit(t3);
        Future<List<Integer>> f4 = service.submit(t4);

        start = System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        end = System.currentTimeMillis();
        System.out.println(end - start);
        service.shutdown();
    }

    static class MyTask implements Callable<List<Integer>> {
        int start;
        int end;

        MyTask(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public List<Integer> call() throws Exception {

            return getPrime(start, end);
        }
    }

    static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    static List<Integer> getPrime(int start, int end){
        List<Integer> results = new ArrayList<>();
        for (int i = start; i < end; i++) {
            if(isPrime(i)){
                results.add(i);
            }
        }
        return results;
    }

}
