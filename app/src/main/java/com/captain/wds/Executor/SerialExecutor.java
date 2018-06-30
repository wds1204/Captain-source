package com.captain.wds.Executor;

import android.support.annotation.NonNull;

import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wds on 2018-5-3.
 */
// 实现对任务调度，符合任务
public class SerialExecutor implements Executor {

	final Queue<Runnable>	tasks	= new LinkedBlockingQueue<Runnable>();

	Runnable				active;

	Executor				executor;

	public SerialExecutor(Executor executor) {
		this.executor = executor;
	}

	@Override public void execute(@NonNull final Runnable command) {

		tasks.offer(new Runnable() {

			@Override public void run() {
				try {
					System.out.println("1");
					command.run();
					System.out.println("2");
				} finally {
					System.out.println("3");
					scheduleNext();
					System.out.println("4");
				}
			}
		});
		if (active == null) {
			System.out.println("5");
			scheduleNext();
			System.out.println("6");
		}

	}

	protected synchronized void scheduleNext() {
		if ((active = tasks.poll()) != null) {
			System.out.println("11111");
			executor.execute(active);
			System.out.println("22222");
		}
	}

}
