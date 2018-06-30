package com.captain.wds.Executor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wds on 2018-5-4.
 */

public class NetworkService implements Runnable{
    private ServerSocket serverSocket;
    private ExecutorService executorService;

    public NetworkService(int por, int poolSize) throws IOException {
        serverSocket = new ServerSocket(por);

        executorService = Executors.newFixedThreadPool(poolSize);
        Executors.newCachedThreadPool();
    }

    @Override
    public void run() {
        try {
            for (;;) {
                executorService.execute(new HandlerRunnable(serverSocket.accept()));
            }

        } catch (IOException e) {
            e.printStackTrace();
            executorService.shutdown();
        }
    }

    class HandlerRunnable implements Runnable {
        private final Socket socket;

        public HandlerRunnable(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("kaishi");
        }
    }

}
