package com.it5.okhttp_demo;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by IT5 on 2016/7/11.
 */
public class OkHttp_Main {
    final OkHttpClient client = new OkHttpClient();
    public void accessHeaders(){
        Request request=new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();
        try {
            Response response=client.newCall(request).execute();
            if (!response.isSuccessful())throw new IOException("Unexpected code " + response);
            System.out.println("Server: " + response.header("Server"));
            System.out.println("Date: " + response.header("Date"));
            System.out.println("Vary: " + response.headers("Vary"));
//            Log.i("WY", "数据：" +response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String... args) throws Exception {
        System.out.println("fdsa");
        new OkHttp_Main().accessHeaders();
    }

}
