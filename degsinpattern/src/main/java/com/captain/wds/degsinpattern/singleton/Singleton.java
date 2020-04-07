package com.captain.wds.degsinpattern.singleton;

/**
 * Created by wds on 2018/4/4.
 */

/**
 * 单例模式的七中实现方式
 */
public class Singleton {

    private static volatile Singleton instance;

    /* 饿汉单例模式 */

    private static final Singleton singleton = new Singleton();

    public static Singleton getSingleton() {
        return singleton;
    }

    private Singleton() {
    }

    /**
     * 懒汉式实现单例
     * <p>
     *     产生不必要的开销，即便是instance已经实例化了Singleton，每次都会进行同步,
     *     产生了不必要的开销
     *
     * </p>
     *
     * @return
     */
    public static synchronized Singleton getInstance1() {

        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    /**


    /**
     * 静态内部类单例模式
     */
    public static Singleton getInstance2() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {

        private static final Singleton instance = new Singleton();

    }

    /**
     * Double Check Lock实现单例
     * 第一层判空是避免不必要的同步开销
     * 第二层判空是避免创建重复对象
     *
     * instance 用volatile修饰原因
     * instance=new Singleton()不是一个原子操作
     * 1、先是给Singleton实例分配内存
     * 2、调用Singleton的构造函数，初始化成员字段
     * 3、将instance 对象指向分配的内存空间
     *
     * 编译器和处理器为了优化程序性能而对指令序列进行重排序，所以说在同步代码块中
     * 单线程内可能进行指令重排序，但本质as-if-serial原则，不管怎么重排序，单线程程序
     * 的执行结果不变。
     *
     * 原本1、2、3的指令可能是1、3、2，在执行完3，2为执行之前，切换到B线程，
     * 这时候instance已经是非空了，所以线程B直接取走instance，
     * 在使用的时候就错了。
     *
     * volatile禁止指令重排序
     *
     *
     *
     * @return
     */
    public static Singleton getInstance() {
        if(instance==null) {
            synchronized (Singleton.class) {
                instance=new Singleton();
            }
        }
        return instance;
    }

}
