package com.captain.wds.degsinpattern.iterator.abstractHandler;

/**
 * Created by wudongsheng on 2018/4/23.
 */

public abstract class AbstractRequest {

    private Object obj;//处理对象

    public AbstractRequest(Object obj) {
        this.obj = obj;
    }

    /**
     * 获取处理的内容对象
     *
     * @return 具体的内容对象
     */
    public Object getObj() {
        return obj;
    }

    /**
     * 获取请求级别
     *
     * @return 请求级别
     */
    public abstract int getRequestLevel();
}
