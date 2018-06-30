package com.captain.wds.okhttp;

import com.captain.wds.okhttp.interceptor.AddCookiesInterceptor;
import com.captain.wds.okhttp.interceptor.SavaCookiesInterceptor;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * Created by wds on 2018/4/3.
 */

public class RetrofitManger {
    public static final String BASE_URL = "https://api.github.com/";
    public void getRe() {

        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(new AddCookiesInterceptor())
                .addInterceptor(new SavaCookiesInterceptor())
                .build();

        new Retrofit.Builder().baseUrl(BASE_URL).client(client).build();

        Request request = new Request.Builder().url("").build();
        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {

            }

            @Override public void onResponse(Call call, Response response) throws IOException {

            }
        });


    }
}
