package com.captain.wds.degsinpattern.Observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by wudongsheng on 2018/4/2.
 */

/**
 * 读者是观察者
 */
public class Reader implements Observer {

	public String name;

	public Reader(String name) {
		this.name = name;
	}

    @Override public void update(Observable o, Object arg) {
        System.out.println("Hi," + name + ", 图书馆 更新啦，内容 ：" + arg);

    }

    @Override public String toString() {
        return "Reader{" +
                "name='" + name + '\'' +
                '}';
    }
}
