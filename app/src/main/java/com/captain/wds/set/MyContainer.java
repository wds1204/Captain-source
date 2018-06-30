package com.captain.wds.set;

/**
 * Created by wds on 2018-5-28.
 */

public class MyContainer {
    Object[] items=new Object[10];
    int size = 0;

    public MyContainerIterator iterator() {
        return new MyContainerIterator(this);
    }

    public void add(Object e) {
        items[size++] = e;

    }
}
