package com.captain.wds.degsinpattern.iterator.abstractHandler;

/**
 * Created by wudongsheng on 2018/4/23.
 */

public abstract class AbstractHandler {
    public AbstractHandler nextHandler;//下一节点上的处理对象

    public final void handleRequest(AbstractRequest request) {
        if (getHandleLevel() == request.getRequestLevel()) {
            handle(request);
        } else {
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            } else {
                System.out.println("All of handler can not handle the request");

            }
        }
    }

    /**
     * 每个处理者对象的具体处理方式
     * @param request
     */
    protected abstract void handle(AbstractRequest request);

    /**
     * 获取处理者对象的处理级别
     * @return
     */
    protected abstract int getHandleLevel();
}
