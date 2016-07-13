package com.it5.okhttp_demo;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by IT5 on 2016/7/13.
 */
public class OkhttpJson {
    private final Gson gson = new Gson();

    public void run_json() throws Exception {
        Request request = new Request.Builder()
                .url("https://api.github.com/gists/c2a7c39532239ff261be")
//                .url("https://kyfw.12306.cn/otn/")//有证书
                .build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                Log.i("111", response.body().toString());
                /*Gist gist=gson.fromJson(response.body().charStream(),Gist.class);
                for (Map.Entry<String, GistFile> entry:gist.files.entrySet()){
                    System.out.println("111111 "+entry.getKey());
                    System.out.println(entry.getValue().content);
//                    Log.i("111",entry.getValue().content);
                }*/
            }
        });
    }


}
