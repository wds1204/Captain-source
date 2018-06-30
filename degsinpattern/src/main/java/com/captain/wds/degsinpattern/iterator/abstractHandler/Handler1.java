package com.captain.wds.degsinpattern.iterator.abstractHandler;

/**
 * Created by wudongsheng on 2018/4/23.
 */

public class Handler1 extends AbstractHandler {
    @Override protected void handle(AbstractRequest request) {
        System.out.println("Handler1 handle request:"+request.getRequestLevel());

    }

    @Override protected int getHandleLevel() {
        return 1;
    }
}
