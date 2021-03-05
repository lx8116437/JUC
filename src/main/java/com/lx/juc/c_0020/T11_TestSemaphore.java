package com.lx.juc.c_0020;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore 通常我们叫它信号量， 可以用来控制同时访问特定资源的线程数量，通过协调各个线程，以保证合理的使用资源。
 *
 * 可以把它简单的理解成我们停车场入口立着的那个显示屏，每有一辆车进入停车场显示屏就会显示剩余车位减1，
 * 每有一辆车从停车场出去，显示屏上显示的剩余车辆就会加1，当显示屏上的剩余车位为0时，
 * 停车场入口的栏杆就不会再打开，车辆就无法进入停车场了，直到有一辆车从停车场出去为止。
 *
 */
public class T11_TestSemaphore {

    public static void main(String[] args) {
        Semaphore s = new Semaphore(10);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    System.out.println("---------------" + Thread.currentThread().getName() + "来到停车场!");
                    if(s.availablePermits() == 0){
                        System.out.println("暂无车位请稍后!");
                    }
                    s.acquire();

                    System.out.println(Thread.currentThread().getName() + "已入库!");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                    System.out.println(Thread.currentThread().getName() + "已离场!");
                    s.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    s.release();
                }
            }).start();
        }
    }


    /*public static void main(String[] args) {
        // 容量为1
        Semaphore s = new Semaphore(1);
        // 容量为2
//        Semaphore s = new Semaphore(2);
        // 容量为2且公平
//        Semaphore s = new Semaphore(2, true);

        new Thread(() -> {
            try {
                // 获取一个令牌，在获取到令牌、或者被其他线程调用中断之前线程一直处于阻塞状态。
                s.acquire();
                System.out.println("T1 Running....");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("T1 End....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                // 释放一个令牌，唤醒一个获取令牌不成功的阻塞线程。
                s.release();
            }
        }).start();

        new Thread(() -> {
            try {
                // 获取一个令牌，在获取到令牌、或者被其他线程调用中断之前线程一直处于阻塞状态。
                s.acquire();
                System.out.println("T2 Running....");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("T2 End....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                // 释放一个令牌，唤醒一个获取令牌不成功的阻塞线程。
                s.release();
            }

        }).start();



    }*/
}
