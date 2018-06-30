package com.captain.wds.classLoad;

/**
 * Created by wds on 2018-5-8.
 */

public class ClassTemp {
    public static void main(String[] args) {
        TestClass t = new TestClass();

    }
    ///////////////////////////////////////////////////////////////////////////
    // <p>
    //   1.因为new用到了TestClass.class,所有会找到TestClass.class文件，并加载到内存中
    //   2.执行该类中的static代码块，如果有的话，给TestClass.class类进行初始化
    //   3.在堆内存中开辟空间分配内存地址
    //   4.在堆内存中建立对象的特有属性，并进行默认初始化
    //   5.对属性进行显示初始化
    //   6.对对象进行构造代码块初始化
    //   7.对对象进行与之对应的构造函数进行初始化
    //   8.将内存地址赋值给栈内存中的t变量
    // </p>
    ///////////////////////////////////////////////////////////////////////////
}
