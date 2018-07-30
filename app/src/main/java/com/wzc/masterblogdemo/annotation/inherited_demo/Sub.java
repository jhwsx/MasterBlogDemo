package com.wzc.masterblogdemo.annotation.inherited_demo;

@BTable(name = "sub")
public class Sub extends Super {
    private int subx;
    public int suby;

    private Sub() {
    }

    public Sub(int i) {
    }

    private int subX() {
        return 0;
    }

    public int subY() {
        return 0;
    }
}  