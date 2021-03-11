package com.lx.juc.c_0023;

import java.util.Arrays;

/**
 * 线程安全的单例模式：
 *
 * 阅读文章：http://www.cnblogs.com/xudong-bupt/p/3433643.html
 *
 * 更好的是采用下面的方式，既不用加锁，也能实现懒加载
 *
 * @author lx
 */
public class Singleton {
    private Singleton() {
        System.out.println("Singleton");
    }

    private static class Inner {
        private static Singleton s = new Singleton();
    }

    public static Singleton getSingleton() {
        return Inner.s;
    }

    public static void main(String[] args) {
        Thread[] s = new Thread[100];
        for (int i = 0; i < s.length; i++) {
            s[i] = new Thread(() -> {
                System.out.println(Singleton.getSingleton());
            });
        }

        Arrays.asList(s).forEach(o -> {
            o.start();
        });
    }
}
