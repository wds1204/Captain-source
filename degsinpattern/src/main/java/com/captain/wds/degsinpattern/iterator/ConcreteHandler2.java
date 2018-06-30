package com.captain.wds.degsinpattern.iterator;

/**
 * Created by wudongsheng on 2018/4/23.
 */

public class ConcreteHandler2 extends Handler {
    @Override public void handlerRequest(String condition) {
        if (condition.equals("ConcreteHandler2")) {
            System.out.println("ConcreteHandler2 handler");
        } else {
            successor.handlerRequest(condition);
        }
    }
}
