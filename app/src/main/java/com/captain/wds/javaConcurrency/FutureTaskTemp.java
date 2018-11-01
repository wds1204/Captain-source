package com.captain.wds.javaConcurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTemp {
    private final FutureTask<String> mFutureTask=new FutureTask<String>(new Callable<String>() {
        @Override
        public String call() throws Exception {
            System.out.println("call开始预加载。。。");
            Thread.sleep(3000);
            return "加载资源需要3秒";
        }
    });

    private final Thread mThread=new Thread(mFutureTask){
        @Override
        public void run() {
            super.run();
        }
    };

    private void start(){
        mThread.start();
    }
    private String getRes() throws ExecutionException, InterruptedException {
       return mFutureTask.get();
    }

    public static void main(String[] args) {

        try {
            FutureTaskTemp futureTaskTemp = new FutureTaskTemp();
            //开始预加载
            futureTaskTemp.start();
            //模拟用户操作时间两秒
            Thread.sleep(2000);
            //正在开始加载
            System.out.println(System.currentTimeMillis() +"真正开始加载资源。。。");
            String res = futureTaskTemp.getRes();
            System.out.println(res);
            System.out.println(System.currentTimeMillis() +"加载完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    ///////////////////////////////////////////////////////////////////////////
    // FutureTask类似runnable，都可以通过Thread启动，不过FutureTask可以返回执行完毕的数据
    // ，并且FutureTask的get方法支持阻塞。如果资源加载完成调用get方法直接返回，否则继续等待其
    // 加载完成在放回
    ///////////////////////////////////////////////////////////////////////////


}
