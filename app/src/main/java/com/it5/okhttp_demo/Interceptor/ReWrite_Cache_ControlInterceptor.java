package com.it5.okhttp_demo.Interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by IT5 on 2016/7/20.
 *
 * dangerous interceptor that rewrites the servier's cache-control header
 */
public class ReWrite_Cache_ControlInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse=chain.proceed(chain.request());
        return originalResponse.newBuilder().
                header("Cache-Control","max-age=60")
                .build();
    }
}
