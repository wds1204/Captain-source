package com.captain.wds.degsinpattern.iterator;

/**
 * Created by wudongsheng on 2018/4/23.
 */

public class ConcreteHandler extends Handler {
    @Override public void handlerRequest(String condition) {
        if (condition.equals("ConcreteHandler")) {
            System.out.println("ConcreteHandler handler");

        } else {
            successor.handlerRequest(condition);
        }

    }
}
