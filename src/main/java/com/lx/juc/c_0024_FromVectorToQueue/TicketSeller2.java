package com.lx.juc.c_0024_FromVectorToQueue;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class TicketSeller2 {
    static Vector<String> vectors = new Vector<>();

    static {
        for (int i = 0; i < 10000; i++) {
            vectors.add("票编号:" + i);
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                while (vectors.size() > 0) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("售出-->" + vectors.remove(0));
                }

            }).start();
        }
    }
}
