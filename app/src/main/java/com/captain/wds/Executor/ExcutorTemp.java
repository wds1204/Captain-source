package com.captain.wds.Executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by wudongsheng on 2018/5/3.
 */

public class ExcutorTemp {
    public static void main(String[] args) {


        //同步
        new DirectExecutor().execute(new Runnable() {
            @Override public void run() {
                System.out.println("ThreadName :" + Thread.currentThread().getName());

            }
        });
        //异步
        new ThreadTaskExecutor().execute(new Runnable() {
            @Override public void run() {
                System.out.println("ThreadName :" + Thread.currentThread().getName());
            }
        });

        ExecutorService executorService = Executors.newCachedThreadPool();
        /*===============Future代表了异步计算的结果，包含了一系列方法来对结果进行操作=================*/
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override public String call() throws Exception {
                return "wds";
            }

        });

        try {
            String result = future.get();
            System.out.println("result :" + result);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Callable<String> callable = new Callable<String>(){
            @Override public String call() throws Exception {
                return "cl";
            }
        };


        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            String result = futureTask.get();
            System.out.println("result :" + result);


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
