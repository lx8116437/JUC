package com.lx.juc.c_0023_02_FromHashtableToCHM;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class T03_TestSynchronizedHashMap {
    private static Map<UUID, UUID> map = Collections.synchronizedMap(new HashMap<>());
    static int count = Constants.COUNT;
    static int threadCount = Constants.THREAD_COUNT;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    private static class MyThread extends Thread {
        int start;
        int group = count / threadCount;
        private MyThread(int start) {
            this.start = start;
        }
        Thread[] threads = new Thread[threadCount];
        @Override
        public void run() {
            for(int i=start; i<start + group; i++) {
                map.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {
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
        System.out.println("写: " + map.size());

        // 读
        start = System.currentTimeMillis();

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000000 ; j++) {
                    map.get(keys[10]);
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
