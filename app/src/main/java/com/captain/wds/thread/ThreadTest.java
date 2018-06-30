package com.captain.wds.thread;

import java.util.Vector;

/**
 * Created by wudongsheng on 2018/4/17.
 */

public class ThreadTest implements Runnable{

    volatile static boolean isCancle;
    public static void main(String[] args) {
        //NEW:创建状态，线程创建之后，但是还没有启动
        //RUNNABLE：运行状态，处于运行状态的线程，但有可能处于等待状态，例如等待CPU、IO等。
        //WAITING:等待状态，一般是调用了wait()、join()、LockSupport.spark等方法。
        //TIMED_WAITING：超时等待状态，也就是带时间的等待状态。一般是调用
        //BLOCKED：阻塞状态，等待锁的释放
        //TERMINATED：终止状态，一般是线程完成任务后退出或者异常终止
        Vector<String> vector = new Vector<>();

        
       // volatile 修饰的变量对所有线程都可见

//        ThreadTest sys = new ThreadTest();
//        Thread t1 = new Thread(sys);
//        Thread t2 = new Thread(sys);
//        t1.start();
//        t2.start();


        /*===========线程死锁============*/
        Object o1 = new Object();
        Object o2 = new Object();
        new Thread1(o1, o2).start();
        new Thread2(o1, o2).start();

    }
    int num1 = 10;
    int num2 = 10;
    int num3 = 10;
    @Override
    public void run() {

        synchronized(this) {
            while(num1 > 0) { //只有得到锁的线程才能访问
                System.out.println(Thread.currentThread().getName() + "访问num1=" + num1--);
            }
        }
        synchronized(this) { //只有得到锁的线程才能访问，即使它还没有开始访问这儿，因为同步锁的作用对象是对象中的所有同步块
            while(num2 > 0) {
                System.out.println(Thread.currentThread().getName() + "访问num2=" + num2--);
            }
        }
        while(num3 > 0) { //未得到锁的线程可访问此资源（非同步块）
            System.out.println(Thread.currentThread().getName() + "访问num3=" + num3--);
        }

    }
}
