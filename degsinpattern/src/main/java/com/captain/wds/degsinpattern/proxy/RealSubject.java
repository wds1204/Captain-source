package com.captain.wds.degsinpattern.proxy;

/**
 * Created by wudongsheng on 2018/4/16.
 */
//真实主题类： 委托类也是被代理类，执行具体的方法
public class RealSubject extends Subject {
    @Override public void visit() {
        System.out.println("具体的 目标");

    }
}
