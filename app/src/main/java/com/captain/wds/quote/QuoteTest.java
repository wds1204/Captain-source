package com.captain.wds.quote;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/**
 * Created by wudongsheng on 2018/3/9.
 */
//强 弱 软 虚 引用
public class QuoteTest {
    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////


    public static void main(String[] args) {
        ///////////////////////////////////////////////////////////////////////////
        //<p>
        // finalize概念:当创建一个对象时，该对象会有个内部标示finalizable,
        // 当GC检查到该对象，并发现对象不可达时，则会把对象放入到finalize queue(F queue),
        // GC会在对象销毁前执行finalize方法并且清空finalizable标示
        //</p>
        ///////////////////////////////////////////////////////////////////////////

        /*===============(1) finalize 的执行顺序=================*/
        //先执行B的finalize方法
        com.captain.wds.quote.A a = new com.captain.wds.quote.A();
        a.b = new B();

        a = null;
        System.gc();
        //先执行B的finalize方法，在执行A的finalize方法,但是finalize是乱序执行的

        /*================(2) 对象再生及finalize只能执行一次 ================*/


        
        /*================================*/
        ///////////////////////////////////////////////////////////////////////////
        // 如果一个对象具有强引用，那就类似于必不可少的生活用品，垃圾回收器绝不会回收它，当内存空间不足，
        //Java虚拟机宁愿抛出outofMermoryError错误，使程序异常终止，也不会靠随意回收具有强引用的对象来解决内存不足问题
        //
        ///////////////////////////////////////////////////////////////////////////

        /*在heap堆中创建新的Object对象通过obj引用这个对象，第二句是通过obj建立o1到new Object()这个heap堆中的对象的引用
        *这两个引用都是强引用，只要存在heap中对象的引用，gc就不会收集该对象
        */
        Object obj = new Object();

        Object o1 = obj;

        obj = null;
        o1 = null;

        //对象属于那种可及的对象，由它的最强的引用决定
        String abc = new String("abc");

        SoftReference<String> abcSoftRef = new SoftReference<String>(abc);//软引用

        WeakReference<String> abcWeakRef = new WeakReference<String>(abc);//弱引用

        abc = null;//执行这行代码的时候对象不再是强可及，变成软可及的。

        abcSoftRef = null;//对象变成弱可及

    //  weakTemp();

        test();

    }
    ///////////////////////////////////////////////////////////////////////////
    //
    //  如果一个对象只具有软引用，那就类似于可有可无的生活品。如果内存空间不足，垃圾回收
    //   器就会回收这些对象的内存。软引用可用来实现内存敏感的高速缓存。
    //
    ////////////////////////////////////////////////////////////////////////////

    public static void softTemp() {
        A a = new A();
        SoftReference<A> aSoftRef = new SoftReference<>(a);

        if (aSoftRef != null) {
            a = aSoftRef.get();
        } else {
            a = new A();
            aSoftRef = new SoftReference<A>(a);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // 弱引用于软引用的区别在于，只具有弱引用的对象拥有跟短暂的生命周期，在垃圾回收器线程扫描
    // ，一旦发现持有了弱引用的对象，不管当前内存空间足够与否，他都会回收它的内存
    // 弱引用可以和一个引用队列联合使用，如果弱引用所引用的对象被垃圾回收，Java虚拟机就会吧
    // 弱引用加入到与之关联的引用队列当中
    ///////////////////////////////////////////////////////////////////////////
    public static void weakTemp() {
        String abc = new String("abc");
        WeakReference<String> abcWeakRef = new WeakReference<>(abc);
        abc = null;

        System.out.println("before gc:" + abcWeakRef.get());
        System.gc();

        System.out.println("after gc :" + abcWeakRef.get());
    }
    public static boolean isRunning = true;

    ///////////////////////////////////////////////////////////////////////////
    // 虚引用 必须和引用队列关联使用
    ///////////////////////////////////////////////////////////////////////////
    public static void test() {
        String abc = new String("abc");
        System.out.println(abc.getClass() + " @" + abc.hashCode());

        final ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
        new Thread() {
            public void run() {
                while (isRunning) {
                    Object poll = referenceQueue.poll();
                    if (poll != null) {
                        try {
                            Field rereferent = Reference.class.getDeclaredField("referent");
                            rereferent.setAccessible(true);
                            Object result = rereferent.get(poll);
                            System.out.println("gc will  collect:" + result.getClass() + " @" + result.hashCode());
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }.start();

        PhantomReference<String> abcPhRef = new PhantomReference<>(abc, referenceQueue);
        abc = null;
        try {

            Thread.currentThread().sleep(3000);
            System.gc();
            Thread.currentThread().sleep(3000);
            isRunning = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static class A {

    }

}
