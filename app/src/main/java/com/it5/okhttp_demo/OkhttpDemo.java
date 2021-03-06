package com.it5.okhttp_demo;

import android.util.Log;

import com.it5.okhttp_demo.Interceptor.HttpLoggingInterceptor;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Created by IT5 on 2016/7/6.
 */
public class OkhttpDemo {
    String url = "http://www.wooyun.org";
    static HttpLoggingInterceptor loggin=new HttpLoggingInterceptor();
    static {
        loggin.setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

    final static OkHttpClient client = new OkHttpClient.Builder()
//            .addInterceptor(new LoggingInterceptor())
//            .addNetworkInterceptor(new LoggingInterceptor())
            .addNetworkInterceptor(loggin)
            .build();


    public void getRequest() {
        final Request request = new Request.Builder()
                .get()
                .tag(this)
                .url(url)
                .build();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Response response = null;
                            response = client.newCall(request).execute();
                            if (response.isSuccessful()) {
                                Log.i("WY", "打印GET响应的数据：" + response.body().string());
                                System.out.print(response.body().string());
                            } else {
                                throw new IOException("Unexpected code " + response);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }


    public void postRequest() {
        RequestBody formbody = new FormBody.Builder()
//                .add("", "")
                .build();
        final Request request = new Request.Builder()
                .url("http://www.wooyun.org")
                .post(formbody)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.i("WY", "打印POST响应的数据：" + response.body().string());
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    //同步下载
    public void download_tb() {
        final Request request = new Request.Builder()
//                .url("https://publicobject.com/helloworld.txt")
                .url("https://api.github.com/repos/square/okhttp/issues")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);
            Headers headers = response.headers();
            for (int i = 0; i < headers.size(); i++) {
//                System.out.println(headers.name(i) + ": " + headers.value(i));
//                Log.i("WY", "数据：" + headers.name(i) + ": " + headers.value(i));
            }
//            System.out.println(response.body().string());
//            Log.i("WY", "数据：" + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void accessHeaders(OkHttpClient mClient) {
        Request request = new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();
        try {
            Response response = mClient.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
           /* System.out.println("Server: " + response.header("Server"));
            System.out.println("Date: " + response.header("Date"));
            System.out.println("Vary: " + response.headers("Vary"));*/
            Log.i("WY", "数据：" + response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setAuthenticator(){
        OkHttpClient client=new OkHttpClient();
        client.newBuilder().authenticator(new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                String credential= Credentials.basic("user","password");
                return response.request().newBuilder()
                        .header("Authorization",credential)
                        .build();
            }
        });
    }

    public static void main(String... args) throws Exception {
        System.out.println("fdsa");
        new OkhttpDemo().download_tb();
    }
}
