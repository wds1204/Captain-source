package com.captain.wds.set;

/**
 * Created by wds on 2018-5-28.
 */

public class IteratorTemp {
    public static void main(String[] args) {
        MyContainer container = new MyContainer();
        container.add("Java");
        container.add("Android");
        MyContainerIterator iterator = container.iterator();
        while (iterator.hasNext()) {
            String e = (String) iterator.next();

            System.out.println("元素 ：" + e);
        }
    }
}
