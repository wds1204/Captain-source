package com.captain.wds.degsinpattern.iterator;

import com.captain.wds.degsinpattern.iterator.abstractHandler.AbstractHandler;
import com.captain.wds.degsinpattern.iterator.abstractHandler.AbstractRequest;
import com.captain.wds.degsinpattern.iterator.abstractHandler.Handler1;
import com.captain.wds.degsinpattern.iterator.abstractHandler.Handler2;
import com.captain.wds.degsinpattern.iterator.abstractHandler.Handler3;
import com.captain.wds.degsinpattern.iterator.abstractHandler.Request1;
import com.captain.wds.degsinpattern.iterator.abstractHandler.Request2;
import com.captain.wds.degsinpattern.iterator.abstractHandler.Request3;

/**
 * Created by wudongsheng on 2018/4/23.
 */
//定义：使多个对象都有机会处理请求，从而避免了请求的发送者和接收者之间的耦合关系。
//    将这些对象连成一条链，并沿着这条链传递该请求，只到有对象处理它为止
//运用场景：对象可以处理同一个请求，但具体由那个对象处理则在运行时动态决定，需要
//         动态指定一组对象处理请求
public class IteratorPattern {
    //责任链模式
    public static void main(String[] args) {
        ConcreteHandler concreteHandler = new ConcreteHandler();
        ConcreteHandler2 concreteHandler2 = new ConcreteHandler2();
        //指向下一个节点
        concreteHandler.successor = concreteHandler2;
        //指向下一个节点
        concreteHandler2.successor = concreteHandler;

        concreteHandler.handlerRequest("ConcreteHandler2");

        /*检查3个处理者对象*/
        AbstractHandler handler1 = new Handler1();
        AbstractHandler handler2 = new Handler2();
        AbstractHandler handler3 = new Handler3();


        /*设置当前处理者对象下一个节点处理对象*/
        handler1.nextHandler = handler2;
        handler2.nextHandler = handler3;

        /*构造三个请求者对象*/
        AbstractRequest request1 = new Request1("Request1");
        AbstractRequest request2 = new Request2("Request2");
        AbstractRequest request3 = new Request3("Request3");
        /*总是从链式的首端发起请求*/
        handler1.handleRequest(request1);
        handler1.handleRequest(request2);
        handler1.handleRequest(request3);

    }

}

///////////////////////////////////////////////////////////////////////////
// 角色分析
// Handler3：抽象处理角色，声明一个请求处理的方法，并在其中保持一个对下一个处理节点Handler对象
//
// ConcreteHandler:具体处理者角色，对请求进行处理，如果不能处理则将该请求转发给下一个节点上的处理对象。
/// ////////////////////////////////////////////////////////////////////////
