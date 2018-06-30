package com.captain.wds.degsinpattern.mediator;

/**
 * Created by wudongsheng on 2018/4/15.
 */
//具体同事B
public class ConcreteColleagueB extends Colleague {

	public ConcreteColleagueB(Mediator mediator) {
		super(mediator);
	}

	@Override public void action() {
		System.out.println("ColleagueB 中介者");

	}
}
