package com.captain.wds.degsinpattern.iterator.abstractHandler;

/**
 * Created by wudongsheng on 2018/4/23.
 */

public class Request1 extends AbstractRequest {
    public Request1(Object obj) {
        super(obj);
    }

    @Override public int getRequestLevel() {
        return 1;
    }
}
