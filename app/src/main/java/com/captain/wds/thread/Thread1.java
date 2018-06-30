package com.captain.wds.thread;

/**
 * Created by wds on 2018-5-18.
 */

public class Thread1 extends Thread {

    private final Object o1;
    private final Object o2;

    public Thread1(Object o1, Object o2) {
        super("Thread1");
        this.o1 = o1;
        this.o2 = o2;
    }
    @Override
    public void run() {
        super.run();

        synchronized (o1) {
            System.out.println("ThreadName :" + getName() + "   锁定对象1");
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (o2) {
                System.out.println("ThreadName :" + getName() + "   锁定对象2");

                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            System.out.println("ThreadName :" + getName() + "   释放对象1");

        }

    }
}
