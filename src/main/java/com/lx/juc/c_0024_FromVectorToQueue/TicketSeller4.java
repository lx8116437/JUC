package com.lx.juc.c_0024_FromVectorToQueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class TicketSeller4 {
    static Queue<String> queue = new ConcurrentLinkedDeque<>();
    static {
        for (int i = 0; i < 10000; i++) {
            queue.add("票编号:" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true){
                    String s = queue.poll();
                    if(s == null){
                        break;
                    }
                    System.out.println("售出:" + s);
                }
            }).start();
        }
    }
}
