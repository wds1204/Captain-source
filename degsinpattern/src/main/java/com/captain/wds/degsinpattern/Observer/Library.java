package com.captain.wds.degsinpattern.Observer;


import java.util.Observable;

/**
 * Created by wudongsheng on 2018/4/2.
 */

/**
 * Library  这个图书馆是被观察者角色，当它有更新时所有的观察者（这里就是读者）
 */
public class Library extends Observable {
    public void postNewPublication(String content) {
        //标示状态活者内容发生改变
        setChanged();
        //通知所有观察者
        notifyObservers(content);
    }
}
