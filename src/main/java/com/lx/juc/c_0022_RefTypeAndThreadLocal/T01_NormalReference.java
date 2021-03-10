package com.lx.juc.c_0022_RefTypeAndThreadLocal;

import java.io.IOException;

/**
 * 强软弱虚
 */
public class T01_NormalReference {
    public static void main(String[] args) throws IOException {
        // 强引用
        M m = new M();

        // 强引用 当对象还有引用时 gc 永远不会 回收, 只有当对象为 null时 才会被回收
        m = null;
        System.gc();
        System.in.read();
    }
}
