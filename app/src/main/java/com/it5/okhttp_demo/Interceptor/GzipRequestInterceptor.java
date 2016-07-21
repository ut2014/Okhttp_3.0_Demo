package com.it5.okhttp_demo.Interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

/**
 * Created by IT5 on 2016/7/20.
 * this interceptor compresses the http request body.many webserver
 * can't handler this!
 */
public class GzipRequestInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest=chain.request();
        if (originalRequest.body()==null||originalRequest.header("Content-Encoding")!=null) {
            return chain.proceed(originalRequest);
        }
        Request compressRequest=originalRequest.newBuilder()
                .header("Contnt-Encoding","gzip")
                .method(originalRequest.method(),gzip(originalRequest.body()))
                .build();

        return chain.proceed(compressRequest);
    }

    private RequestBody gzip(final RequestBody body){
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return body.contentType();
            }

            @Override
            public long contentLength() throws IOException {
                return -1;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                BufferedSink gzipSink= Okio.buffer(new GzipSink(sink));
                body.writeTo(gzipSink);
                gzipSink.close();
            }
        };
    }
}
