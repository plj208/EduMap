package com.lingkj.android.edumap.net;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.lingkj.android.edumap.MyApp;
import com.lingkj.android.edumap.utils.SystemUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author panlijun
 */

public class RxNet {

    //地址
    public static final String BASE_URL = MyApp.DEBUG ? "" : "";
    private static final int CONNECT_TIMEOUT = 5;
    private static final int READ_TIMEOUT = 10;
    private static final int WRITE_TIMEOUT = 10;
    private static final int CACHE_SIZE = 1024 * 1024 * 10;
    private IRxNet iRxNet;
    private static RxNet rxNet;
    private String cachePath;

    private RxNet(){
        cachePath = MyApp.getInstance().getCacheDir().getAbsolutePath() + "/okhttp";
        File cacheFile = new File(cachePath);

        if (!cacheFile.exists()) {
            cacheFile.mkdir();
        }


        OkHttpClient.Builder builder=new OkHttpClient.Builder()
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT,TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT,TimeUnit.SECONDS)
                .cache(new Cache(cacheFile,CACHE_SIZE))
                .cookieJar(new CookieJar() {
                    private final HashMap<String,List<Cookie>> cookieStore = new HashMap<>();
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(url.host(),cookies);

                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url.host());
                        return cookies!=null?cookies:new ArrayList<Cookie>();
                    }
                }).addInterceptor(new UrlIntercepter())
                .addNetworkInterceptor(new ResponseIntercepter());

        if (MyApp.DEBUG) {
            builder.addNetworkInterceptor(new StethoInterceptor());
        }

        OkHttpClient client=builder.build();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        iRxNet=retrofit.create(IRxNet.class);

    }


    public static RxNet getInstance() {
        if (rxNet == null) {
            synchronized (RxNet.class) {
                rxNet = new RxNet();
            }
        }
        return rxNet;
    }

    public IRxNet getIRxNet() {
        return iRxNet;
    }



    public long getCacheSize(){
        File cacheFile=new File(cachePath);
        if(cacheFile.exists()&&cacheFile.isDirectory()){
            return SystemUtils.getFolderSize(cacheFile);
        }
        return 0;
    }


    public  void cleanCache(Context context){
        File cacheFile=new File(cachePath);
        if (cacheFile.exists()&&cacheFile.isDirectory()){
            File[] files = cacheFile.listFiles();
            for (File singleFile:files
                 ) {
                singleFile.delete();

            }
        }
    }
}
