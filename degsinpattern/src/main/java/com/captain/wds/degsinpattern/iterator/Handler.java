package com.captain.wds.degsinpattern.iterator;

/**
 * Created by wudongsheng on 2018/4/23.
 */

public abstract class Handler {
    protected Handler successor;//下一节点的处理对象

    /**
     * 请求处理
     * @param condition 请求条件
     */
    public abstract void handlerRequest(String condition);
}
