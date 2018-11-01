package com.captain.wds.javaConcurrency;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTemp {
    ///////////////////////////////////////////////////////////////////////////
    // CountDownLatch一个灵活的闭锁实现，包含一个计数器，该计数器初始化一个正数，表述需要
    // 等待的事件数量。countDown方法递减计数器，表示有一个事件发生，而await方法等待计数器
    // 到达0，表示所有需要等待的事情已经完成。
    ///////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(5);
        new Thread(()->{
            try {
                Thread.sleep(1000);
                System.out.print("等一秒。。。");
                countDownLatch.countDown();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                Thread.sleep(2000);
                System.out.print("等两秒。。。");
                countDownLatch.countDown();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                Thread.sleep(3000);
                System.out.print("等三秒。。。");
                countDownLatch.countDown();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(4000);
                System.out.print("等四秒。。。");
                countDownLatch.countDown();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(5000);
                System.out.print("等五秒。。。");
                countDownLatch.countDown();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        countDownLatch.await();

        System.out.print("统一执行");
    }
}
