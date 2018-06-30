package com.captain.wds.quote;

/**
 * Created by wudongsheng on 2018/5/1.
 */

public class B {
    @Override protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("method B finalize at" + System.nanoTime());
        
    }
}
