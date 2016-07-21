package com.it5.okhttp_demo.Interceptor;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by IT5 on 2016/7/20.
 */
public final class LoggingInterceptors {
    private static final Logger logger = Logger.getLogger(LoggingInterceptors.class.getName());
    private final OkHttpClient client = new OkHttpClient();
    public LoggingInterceptors() {
        client.networkInterceptors().add(new Interceptor() {
            @Override public Response intercept(Chain chain) throws IOException {
                long t1 = System.nanoTime();
                Request request = chain.request();
                logger.info(String.format("Sending request %s on %s%n%s",
                        request.url(), chain.connection(), request.headers()));
                Response response = chain.proceed(request);
                long t2 = System.nanoTime();
                logger.info(String.format("Received response for %s in %.1fms%n%s",
                        request.url(), (t2 - t1) / 1e6d, response.headers()));
                return response;
            }
        });
    }
    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();
        Response response = client.newCall(request).execute();
        response.body().close();
    }
   /* public static void main(String... args) throws Exception {
        new LoggingInterceptors().run();
    }*/

}
