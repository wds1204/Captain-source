package com.captain.wds.volatitleTemp;

public class VolatileTemp {

    public  volatile int race=0;

    public  void increase(){
        race++;
    }
    private final static int THREAD_COUNT=20;
    public static void main(String[] args) {
        VolatileTemp volatileTemp = new VolatileTemp();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        volatileTemp. increase();
                };
            }.start();
        }

        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(volatileTemp.race);

    }
}
