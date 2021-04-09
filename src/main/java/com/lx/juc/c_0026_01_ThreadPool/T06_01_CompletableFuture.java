package com.lx.juc.c_0026_01_ThreadPool;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class T06_01_CompletableFuture {
    public static void main(String[] args) {
        long start, end;
        /*start = System.currentTimeMillis();

        priceTM();
        priceSN();
        priceJD();

        end = System.currentTimeMillis();
        System.out.println("use serial method call! " + (end - start));*/

        start = System.currentTimeMillis();
        CompletableFuture<Double> tm = CompletableFuture.supplyAsync(() -> priceTM());
        CompletableFuture<Double> sn = CompletableFuture.supplyAsync(() -> priceSN());
        CompletableFuture<Double> jd = CompletableFuture.supplyAsync(() -> priceJD());
        CompletableFuture.allOf(tm, sn, jd).join();

        CompletableFuture.supplyAsync(() -> priceTM())
                .thenApply(String::valueOf)
                .thenApply(str -> "price " + str)
                .thenAccept(System.out::println);


        end = System.currentTimeMillis();
        System.out.println("共计耗时: " + (end - start));

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double priceTM() {
        delay();
        return 1.0;
    }

    private static double priceSN() {
        delay();
        return 2.1;
    }

    private static double priceJD() {
        delay();
        return 3.2;
    }

    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n", time);
    }
}
