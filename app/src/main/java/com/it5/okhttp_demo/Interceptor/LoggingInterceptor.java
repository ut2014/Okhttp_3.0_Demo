package com.it5.okhttp_demo.Interceptor;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by IT5 on 2016/7/20.
 */
@SuppressWarnings("deprecation")
public class LoggingInterceptor implements Interceptor{
    private static final Logger logger = Logger.getLogger(LoggingInterceptor.class.getName());
//    private Logger logger= global;
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();
        long t1=System.nanoTime();
        logger.info(String.format("Sending request %s on %s%n%s",
                request.url(),chain.connection(),request.headers()));

        Response response=chain.proceed(request);
        long t2=System.nanoTime();
        logger.info(
                String.format("Received response for %s in %.1fms%n%s",
                        response.request().url(),
                        (t2-t1)/1e6d,
                        request.headers()));

        return response;
    }
}
