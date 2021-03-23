package com.lx.juc.c_0026_00_interview_A1B2C3;

public class T03_00_cas {
    enum ReadyToRun {T1, T2}

    static volatile ReadyToRun r = ReadyToRun.T1;
    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        char[] a = "123456789".toCharArray();
        char[] b = "ABCDEFGHI".toCharArray();

        t1 = new Thread(() -> {
            for (char c : a) {
                while (r != ReadyToRun.T1){

                }
                System.out.print(c);
                r = ReadyToRun.T2;
            }
        });

        t2 = new Thread(() -> {

            for (char c : b) {
                while (r != ReadyToRun.T2) {
                }
                System.out.print(c);
                r = ReadyToRun.T1;
            }
        });

        t1.start();
        t2.start();

    }
}
