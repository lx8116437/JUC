package com.lx.juc.c_0022_RefTypeAndThreadLocal;

import java.lang.ref.WeakReference;

public class T03_WeakReference {
    public static void main(String[] args) {
        WeakReference<M> w = new WeakReference<>(new M());
        System.out.println(w.get());
        System.gc();
        System.out.println(w.get());


        /**
         * ThreadLocal 的坑, 当对象既有强引用也有弱引用时,强引用消失,弱引用遇到gc就会被回收
         * ThreadLocal 的 set 方法 会把值放入一个线程独有的map变量中 key 是线程对象 value 是我们set的值,当key的强引用消失后被gc回收
         * 但是value不会被回收,如果不调用ThreadLocal 的 remove方法 则会存在内存泄漏问题,当泄漏过大时会导致内存溢出
         */
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set(new M());
        threadLocal.remove();

    }
}
