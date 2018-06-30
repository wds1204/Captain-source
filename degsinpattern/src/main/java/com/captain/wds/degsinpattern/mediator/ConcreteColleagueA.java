package com.captain.wds.degsinpattern.mediator;

/**
 * Created by wudongsheng on 2018/4/15.
 */
//具体同事A:继续抽象同事类，每个具体同事类都知道本身在小范围内的行为，而不知道它在大范围内的目的
public class ConcreteColleagueA extends Colleague {
    public ConcreteColleagueA(Mediator mediator) {
        super(mediator);
    }

    @Override public void action() {
        System.out.println("ColleagueA  中介者");
    }
}
