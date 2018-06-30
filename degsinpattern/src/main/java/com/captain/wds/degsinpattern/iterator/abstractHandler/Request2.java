package com.captain.wds.degsinpattern.iterator.abstractHandler;

/**
 * Created by wudongsheng on 2018/4/23.
 */

public class Request2 extends AbstractRequest {
    public Request2(Object obj) {
        super(obj);
    }

    @Override public int getRequestLevel() {
        return 2;
    }
}
