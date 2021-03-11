package com.lx.juc.c_0023_02_FromHashtableToCHM;

import java.util.HashMap;
import java.util.UUID;

public class T02_TestHashMap {
    private static HashMap<UUID, UUID> hashMap = new HashMap<>();
    private static int count = Constants.COUNT;
    private static int threadCount = Constants.THREAD_COUNT;
    private static UUID[] keys = new UUID[count];
    private static UUID[] values = new UUID[count];

    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    private static class MyThread extends Thread {
        int start;

        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < start + count / threadCount; i++) {
                hashMap.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(i * (count / threadCount));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("写: " + (end - start));
        System.out.println("写: " + hashMap.size());
    }
}
