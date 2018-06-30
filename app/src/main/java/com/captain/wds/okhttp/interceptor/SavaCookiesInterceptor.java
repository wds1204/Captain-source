package com.captain.wds.okhttp.interceptor;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wds on 2018/4/3.
 */

public class SavaCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        if (!response.headers("set-cookie").isEmpty()) {
            List<String> cookies = response.headers("set-cookie");
            String cookie=encodeString(cookies);
            saveCookie(request.url().toString(), request.url().host(), cookie
            );

        }
        return response;
    }

    private void saveCookie(String url, String host, String cookies) {

    }

    private String encodeString(List<String> cookies) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String cookie : cookies) {
            String[] arr = cookie.split(";");

        }
        return null;
    }
}
