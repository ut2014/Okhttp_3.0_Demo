package com.it5.okhttp_demo;


import com.it5.okhttp_demo.util.MyApplication;
import com.it5.okhttp_demo.util.Utils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by IT5 on 2016/7/20.
 * 在没有网络的情况下加载本地存在的缓存数据
 */
public class Okhttp_Cache {
    private final String HTTP_CACHE_FILENAME="HttpCache";
    private final String filePath=MyApplication.appContext().getCacheDir().getAbsolutePath();
    private OkHttpClient client;
    public Okhttp_Cache() {
        File httpCacheDirectory=new File(filePath,HTTP_CACHE_FILENAME);
        Cache cache=new Cache(httpCacheDirectory,10*1024);
        client=new OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .build();
    }

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR=new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse=chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control",String.format("max-age=%d", 60))
                    .build();
        }
    };

    final CacheControl.Builder builder=new CacheControl.Builder();
    private void setCacheControl_Demo(){
        builder.noCache();//不使用缓存，全部走网络
        builder.noStore();//不使用缓存，也不存储缓存
        builder.onlyIfCached();//只使用缓存
        builder.noTransform();//禁止转码
        builder.maxAge(10, TimeUnit.MILLISECONDS);//指示客户机可以接收生存期不大于指定时间的响应。
        builder.maxStale(10, TimeUnit.SECONDS);//指示客户机可以接收超出超时期间的响应消息
        builder.minFresh(10, TimeUnit.SECONDS);//指示客户机可以接收响应时间小于当前时间加上指定时间的响应。
        CacheControl cache = builder.build();//cacheControl
    }

    private void setCacheControl(){
        builder.maxAge(10,TimeUnit.MILLISECONDS);
        CacheControl cache=builder.build();

        final Request request=new Request.Builder()
//                .cacheControl(CacheControl.FORCE_CACHE)//仅仅使用缓存
//                .cacheControl(CacheControl.FORCE_NETWORK)// 仅仅使用网络
                .cacheControl(cache)
                .url("http://www.soso.com")
                .build();

        final Call call=new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.print(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String str=response.body().string();
                    System.out.print("success: "+str);
                }else {
                    System.out.print("error");
                }
            }
        });

    }

    /**
     * 拦截一个请求在网络不可用的时候使用CacheControl.FORCE_CACHE；
     */
    private void setCacheControl_interceptor(){
        OkHttpClient.Builder newBuilder = new OkHttpClient().newBuilder();
        newBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                boolean connected = Utils.isNetworkAvailable();
                if (!connected) {
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                }
                Response response = chain.proceed(request);
                return response;
            }
        });
    }

    /**
     * 怎么优先用缓存？如果缓存不存在，再请求网络！
     */
    //  Interceptor declaration
    private static final Interceptor SERVER_RESPONSE_CACHE_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            //  The response from server
            //  tolerate 28-day stale
            int maxStale = 60 * 60 * 24 * 28;
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    };

    private static final Interceptor CLIENT_REQUEST_CACHE_INTERCEPTOR = new Interceptor() {
        @Override public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            if (Utils.isNetworkAvailable()) {
                int maxAge = 60;
                request = request.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            }
            else {
                int maxStale = 60 * 60 * 24 * 28;
                request = request.newBuilder()
                        .header("Cache-Control",
                                "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            Response response = chain.proceed(request);
            return response;
        }
    };

   /* final static OkHttpClient client1 = new OkHttpClient.Builder()
            .addInterceptor(CLIENT_REQUEST_CACHE_INTERCEPTOR)
            .addNetworkInterceptor(SERVER_RESPONSE_CACHE_INTERCEPTOR)
            .build();*/

}
