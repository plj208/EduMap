package com.lingkj.android.edumap.net;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author panlijun
 *
 */

public class UrlIntercepter implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request build = chain.request().newBuilder()
                .addHeader("platform", "Android")
                .build();
        Response proceed = chain.proceed(build);
        return proceed;
    }
}
