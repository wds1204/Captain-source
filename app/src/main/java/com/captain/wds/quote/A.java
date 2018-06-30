package com.captain.wds.quote;

/**
 * Created by wudongsheng on 2018/5/1.
 */

public class A {
    B b;
    @Override protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("method A finalize at" + System.nanoTime());

    }
}
