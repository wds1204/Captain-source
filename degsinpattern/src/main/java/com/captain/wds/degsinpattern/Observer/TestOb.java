package com.captain.wds.degsinpattern.Observer;

/**
 * Created by wudongsheng on 2018/4/2.
 */

/**
 * 测试代码
 */
public class TestOb {
    public static void main(String[] args) {
        //被观察的角色
        Library deTechFrontier = new Library();
        /*观察者*/
        Reader reader1 = new Reader("小吴");
        Reader reader2 = new Reader("小刘");
        Reader reader3 = new Reader("小崔");

        /*将观察者注册到可观察者对象*/
        deTechFrontier.addObserver(reader1);
        deTechFrontier.addObserver(reader2);
        deTechFrontier.addObserver(reader3);
        //发布消息
        deTechFrontier.postNewPublication("您订阅的新书来了");

    }
}
