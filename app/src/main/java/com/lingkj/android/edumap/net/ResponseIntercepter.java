package com.lingkj.android.edumap.net;

import com.facebook.stetho.common.LogUtil;
import com.lingkj.android.edumap.MyApp;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;

import java.io.IOException;


/**
 * @author panlijun
 */
public class ResponseIntercepter implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();
        Response response = chain.proceed(request);
        if(MyApp.DEBUG)
        {
            BufferedSource source = response.body().source();
            Buffer buffer = source.buffer();
            String s = buffer.toString();
            LogUtil.d(s);
        }
        if(request.method().equals("GET")){
            response=response
                    .newBuilder()
                    .header("Cache-Control","max-age=60")
                    .build();
        }
        return response;
    }
}
