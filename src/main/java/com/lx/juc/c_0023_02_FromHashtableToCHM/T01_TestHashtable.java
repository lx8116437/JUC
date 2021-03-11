package com.lx.juc.c_0023_02_FromHashtableToCHM;

import java.util.Hashtable;
import java.util.UUID;

public class T01_TestHashtable {
    private static Hashtable<UUID, UUID> hashtable = new Hashtable<>();

    // 总数量
    private static int count = Constants.COUNT;
    // 线程数量
    private static int threadCount = Constants.THREAD_COUNT;

    private static UUID[] keys = new UUID[count];
    private static UUID[] values = new UUID[count];

    // 初始化 key value
    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }


    static class MyThread extends Thread {

        int start;

        int group = count / threadCount;

        public MyThread(int start){
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < start + group; i++) {
                hashtable.put(keys[i], values[i]);
            }
        }
    }


    /**
     * 1,设置开始时间
     * 2.启动线程
     * 3.记录结束时间
     * 4.计算运行毫秒数
     * @param args
     */
    public static void main(String[] args) {

        // 写
        // 开始时间
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(i * (count / threadCount));
        }

        for (Thread t : threads){
            t.start();
        }

        for (Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("写: " + (end - start));

        System.out.println("写: " + hashtable.size());

        // 读

        start = System.currentTimeMillis();

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() ->{
                for (int j = 0; j < 10000000; j++) {
                    hashtable.get(keys[10]);
                }
            });
        }


        for (Thread t : threads){
            t.start();
        }

        for (Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();

        System.out.println("读: " + (end - start));

    }

}
