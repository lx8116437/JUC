package com.lx.juc.c_0024_FromVectorToQueue;

import jdk.internal.dynalink.beans.StaticClass;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class TicketSeller3 {
    static Vector<String> vector = new Vector<>();

    static {
        for (int i = 0; i < 10000; i++) {
            vector.add("票编号:"  + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                synchronized (vector){
                    while (vector.size() > 0){
                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("售出-->" + vector.remove(0));
                    }

                }
            }).start();
        }
    }
}
