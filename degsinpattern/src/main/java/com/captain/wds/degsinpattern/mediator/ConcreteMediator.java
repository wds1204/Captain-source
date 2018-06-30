package com.captain.wds.degsinpattern.mediator;

/**
 * Created by wudongsheng on 2018/4/15.
 */
//具体中介者  它从具体的同事对象接收消息，向具体同事对象发出消息
public class ConcreteMediator extends Mediator {
    @Override public void method() {
        colleagueA.action();

        colleagueB.action();
    }
}
