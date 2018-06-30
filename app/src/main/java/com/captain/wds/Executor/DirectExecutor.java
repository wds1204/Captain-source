package com.captain.wds.Executor;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

/**
 * Created by wds on 2018-5-3.
 */

public class DirectExecutor implements Executor {

	@Override public void execute(@NonNull Runnable command) {
		command.run();
	}
}
