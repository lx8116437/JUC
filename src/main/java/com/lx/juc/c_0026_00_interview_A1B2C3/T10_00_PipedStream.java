package com.lx.juc.c_0026_00_interview_A1B2C3;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.TimeUnit;

public class T10_00_PipedStream {
    public static void main(String[] args) throws IOException {
        char[] a = "123456".toCharArray();
        char[] b = "ABCDEF".toCharArray();

        PipedInputStream inputStream1 = new PipedInputStream();
        PipedInputStream inputStream2 = new PipedInputStream();
        PipedOutputStream outputStream1 = new PipedOutputStream();
        PipedOutputStream outputStream2 = new PipedOutputStream();

        inputStream1.connect(outputStream2);
        inputStream2.connect(outputStream1);

        String msg = "Your Turn";

        new Thread(() -> {
            byte[] buffer = new byte[9];


            try {
                for (char c : a) {
                    inputStream1.read(buffer);
                    if (new String(buffer).equals(msg)) {
                        System.out.print(c);
                        outputStream1.write(buffer);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            byte[] buffer = new byte[9];
            try {
                for (char c : b) {
                    System.out.print(c);
                    outputStream2.write(msg.getBytes());
                    inputStream2.read(buffer);
                    if (new String(buffer).equals(msg)){
                        continue;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "t2").start();

    }


}
