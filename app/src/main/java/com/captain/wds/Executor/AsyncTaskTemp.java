package com.captain.wds.Executor;

import android.os.AsyncTask;

import java.net.MalformedURLException;
import java.net.URL;

public class AsyncTaskTemp {
    public static void main(String[] args) throws MalformedURLException {

        URL url1 = new URL("");
        URL url2 = new URL("");
        URL url3 = new URL("");

        new DownloadFilesTask().execute(url1, url2, url3);


        //AsyncTask中有两个线程池SERIAL_EXECUTOR（SerialExecutor）和THREAD_POOL_EXECUTOR
        //1 : SERIAL_EXECUTOR线程池 用于任务的排队
        //  mFuture

        //2 : THREAD_POOL_EXECUTOR用于真正的执行任务


    }

    private static class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {
        public DownloadFilesTask() {
            super();
        }

        @Override protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
        }

        @Override protected Long doInBackground(URL... urls) {
            return null;
        }


    }
}
