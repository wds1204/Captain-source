package com.captain.wds.degsinpattern.mediator;

/**
 * Created by wudongsheng on 2018/4/15.
 */
//抽象中介者：抽象中介者角色，定义了同事对象到中介者对象的接口，
// 一般以抽象类的方式实现

public abstract class Mediator {
    protected ConcreteColleagueA colleagueA;//具体的同事类A
    protected ConcreteColleagueB colleagueB;//具体的同事类B

    /**
     * 抽象中介方法，子类实现
     */
    public abstract void method();

    public void setColleagueA(ConcreteColleagueA colleagueA) {
        this.colleagueA = colleagueA;
    }

    public void setColleagueB(ConcreteColleagueB colleagueB) {
        this.colleagueB = colleagueB;
    }
}
