package com.lx.juc.c_0026_00_interview_A1B2C3;

import java.util.concurrent.Exchanger;

public class T12_00_Exchanger_Not_Work {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        char[] a = "123456".toCharArray();
        char[] b = "ABCDEF".toCharArray();

        new Thread(() ->{
            for (int i = 0; i < a.length; i++) {
                System.out.print(a[i]);
                try {
                    exchanger.exchange("T1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(() -> {
            for (int i = 0; i < b.length; i++) {
                System.out.print(b[i]);
                try {
                    exchanger.exchange("T2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
