package com.captain.wds.Executor;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.internal.Util;

/**
 * Created by wds on 2018-5-3.
 */

public class ExecutorTemp {
	/*===========创建一个核心线程数为5，最大线程数为10，任务缓存队列5的线程池============*/
	private static int corePoolSize = 5;
	private static int maximumPoolSize = 10;
	private static long keepAliveTime = 100;
	//执行前用于保持任务的队列5，即任务缓存队列
	final static ArrayBlockingQueue<Runnable> workQueue =new ArrayBlockingQueue<Runnable>(5);
	public static void main(String[] args) {
		Executor directExecutor = new DirectExecutor();
		directExecutor.execute(new Runnable() {

			@Override public void run() {
				System.out.println("ThreadName " + Thread.currentThread().getName() + " :直接调用");
			}
		});
		Executor taskExecutor = new ThreadTaskExecutor();

		taskExecutor.execute(new Runnable() {

			@Override public void run() {
				Thread.currentThread().getName();
				System.out.println("ThreadName " + Thread.currentThread().getName() + " :线程中调用");

			}
		});

		new SerialExecutor(directExecutor).execute(new Runnable() {

			@Override public void run() {
				System.out.println("后续任务");
			}
		});

		// try {
		// NetworkService networkService = new NetworkService(2, 3);
		// networkService.run();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		Executors.newFixedThreadPool(1);
		ExecutorService executorService = null;
		if (executorService == null) {
			executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), Util.threadFactory("OkHttp Dispatcher", false));
		}
		executorService.execute(new AsyncCall(new CallBack() {
			@Override
			public void onFailure(String error) {
//				System.out.println("error :" + error);
			}

			@Override
			public void onResponse(String content) throws IOException {
//				System.out.println("content ：" + content);
			}
		}));

		//构建一个线程池，正常线程数量为5，最大线程数据为10，等待时间200
		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
				corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MINUTES, workQueue);

		//线程池去执行15个任务
		for (int i = 0; i < 15; i++) {
			MyRunnable myRunnable = new MyRunnable(i);
			poolExecutor.execute(myRunnable);

			System.out.println("线程池中核心线程数目是：" + poolExecutor.getCorePoolSize() + "线程池中现在的线程数目是：" + poolExecutor.getPoolSize() + ",  队列中正在等待执行的任务数量为：" +
					poolExecutor.getQueue().size());
		}
		//关掉线程池
		executorService.shutdown();




	}

	static class MyRunnable implements Runnable {

		// 正在执行的任务数
		private int num;

		public MyRunnable(int num) {
			this.num = num;
		}

		@Override
		public void run() {
			System.out.println("正在执行的MyRunnable " + num);
			try {
				Thread.currentThread().sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("MyRunnable " + num + "执行完毕");

		}
	}

	static class AsyncCall implements Runnable {

		public CallBack callBack;

		public AsyncCall(CallBack callBack) {
			this.callBack = callBack;
		}

		@Override public void run() {

			try {
				if (true) {
					callBack.onResponse("成功");
				} else {
					callBack.onFailure("失败");

				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public interface CallBack {

		void onFailure(String error);

		void onResponse(String content) throws IOException;

	}
}
