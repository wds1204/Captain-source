package com.captain.wds.degsinpattern.proxy;

/**
 * Created by wudongsheng on 2018/4/16.
 */
//代理类： 该类持有一个对真实主题类的引用，在其所实现的接口方法这种调用真实主题类中相应的接口方法执行，起到代理作用
public class ProxySubject extends Subject {
    private RealSubject realSubject;

    public ProxySubject(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override public void visit() {
        realSubject.visit();
    }
}
