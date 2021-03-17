package com.lx.juc.c_0025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 按照时间调度任务
 */
public class T07_DelayQueue {
    static BlockingQueue<MyTask> q = new DelayQueue<>();


    static class MyTask implements Delayed {
        long runningTime;
        String name;

        public MyTask(long runningTime, String name) {
            this.name = name;
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)){
                return -1;
            }
            if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)){
                return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return  name + " " + runningTime;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        MyTask t1 = new MyTask(start + 5000, "t1");
        MyTask t2 = new MyTask(start + 3000, "t2");
        MyTask t3 = new MyTask(start + 3500, "t3");
        MyTask t4 = new MyTask(start + 2500, "t4");
        MyTask t5 = new MyTask(start + 1500, "t5");

        q.put(t1);
        q.put(t2);
        q.put(t3);
        q.put(t4);
        q.put(t5);


        System.out.println(q);

        for (int i = 0; i < 5; i++) {
            System.out.println(q.take());
        }
    }
}
