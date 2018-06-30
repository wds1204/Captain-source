package com.captain.wds.set;

/**
 * Created by wds on 2018-5-28.
 */

public class MyContainerIterator {

    private  MyContainer container;
    private int current = 0;

    public MyContainerIterator(MyContainer container) {

        this.container = container;
    }

    public boolean hasNext() {
        return current < container.size;
    }

    public Object next() {
        return container.items[current++];
    }

}
