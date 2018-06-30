package com.captain.wds.degsinpattern.mediator;

/**
 * Created by wudongsheng on 2018/4/15.
 */
//抽象同事： 定义中介者对象的接口，它只知道中介者而不知道其他同事的对象
public abstract class Colleague {
    protected Mediator mediator;//中介者对象

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    /**
     * 同事角色的具体行为，由子类去实现
     */
    public abstract void action();
}
