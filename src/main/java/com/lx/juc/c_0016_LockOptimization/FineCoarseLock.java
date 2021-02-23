package com.lx.juc.c_0016_LockOptimization;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * synchronized优化
 * 同步代码块中的语句越少越好
 * 比较m1和m2
 *
 * @author mashibing
 */
public class FineCoarseLock {
    int count = 0;

    synchronized void m1() {
        //do sth need not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //业务逻辑中只有下面这句需要sync，这时不应该给整个方法上锁
        count++;

        //do sth need not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void m2() {
        //do sth need not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //业务逻辑中只有下面这句需要sync，这时不应该给整个方法上锁
        //采用细粒度的锁，可以使线程争用时间变短，从而提高效率
        synchronized (this) {
            count++;
        }
        //do sth need not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
//        FineCoarseLock f = new FineCoarseLock();
//
//        List<Thread> threads = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            threads.add(new Thread(f::m1, "thread:" + i));
//        }
//
//        threads.forEach((o) -> o.start());
//
//        threads.forEach((o) -> {
//            try {
//                o.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//
//        System.out.println("m1--->" + f.count);
//
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
        // 40257 毫秒

        long start = System.currentTimeMillis();
        FineCoarseLock f = new FineCoarseLock();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(f::m2, "thread:" + i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("m2--->" + f.count);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        // 4066 毫秒
    }


}
