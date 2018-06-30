package com.captain.wds.degsinpattern.proxy;

/**
 * Created by wudongsheng on 2018/4/15.
 */
//该类主要责任是声明真实主题与代理的共同接口方法，可以是抽象想类也可以是一个接口
public abstract class Subject {
    public abstract void visit();
}

