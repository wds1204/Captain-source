package com.captain.wds.degsinpattern.proxy;

/**
 * Created by wudongsheng on 2018/4/16.
 */

public class Client {
    public static void main(String[] args) {
        new ProxySubject(new RealSubject()).visit();

    }
}
