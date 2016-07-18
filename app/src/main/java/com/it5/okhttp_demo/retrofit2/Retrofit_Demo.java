package com.it5.okhttp_demo.retrofit2;


import android.util.Log;

import com.it5.okhttp_demo.retrofit2.interf.GitHub;
import com.it5.okhttp_demo.retrofit2.interf.PageService;
import com.it5.okhttp_demo.retrofit2.interf.SimpleGET;
import com.it5.okhttp_demo.retrofit2.interf.SimplePOST;
import com.it5.okhttp_demo.retrofit2.interf.StringClient;
import com.it5.okhttp_demo.retrofit2.pojo.Contributor;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *几种网络框架的比较
 1、volley
 一个简单的http异步请求库，但不支持同步，不能post大数据（上传文件时有问题）；
 2、android-async-http
 和volley一样，是异步的请求库，只不过volley使用的是httpUrlConnection，而它使用的是HttpClient。这个库已经不再适合Android;
 3、okhttp
 基于httpUrlConnection，支持同步和异步，但需要自己再封装下；
 4、retrofit；
 对 okhttp再次封装，在项目中可以直接使用。

 在项目中引用retrofit,分为几个部分

 - module对象
 - Converter的实现类
 - Service类（定义网址中不固定的部分）
 - OkHttpClient和Retrofit
 */
public class Retrofit_Demo {

    Dispatcher dispatcher =new Dispatcher(Executors.newFixedThreadPool(20));//线程池dis

    public void get(){
        dispatcher.setMaxRequests(20);
        dispatcher.setMaxRequestsPerHost(1);
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .dispatcher(dispatcher)
                .connectionPool(new ConnectionPool(100,30, TimeUnit.SECONDS))
                .build();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(ScreenConvert.FACTORY)
                .client(okHttpClient)
                .build();

        PageService pageService=retrofit.create(PageService.class);
        pageService.get("1","12","0","14","0").enqueue(new Callback<ScreenBean>() {
            @Override
            public void onResponse(Call<ScreenBean> call, Response<ScreenBean> response) {
                if(response.isSuccessful()){
                    Log.i("retrofit", "onResponse: 成功");
                }else{
                    Log.i("retrofit", "onResponse: 失败");
                }
            }

            @Override
            public void onFailure(Call<ScreenBean> call, Throwable t) {
                Log.i("retrofit", "onFailure: 失败"+t.toString());
            }
        });

    }

    public static void simpleGET(){
        String url="http://tieba.baidu.com";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .build();

        SimpleGET create=retrofit.create(SimpleGET.class);
        final  Call<ResponseBody> call=create.getResponse();
        try {
           /*错误 的调用
            Response response=create.getResponse().execute();
            System.out.print(response.body().toString());*/
            Response<ResponseBody> bodyResponse = call.execute();
            String body = bodyResponse.body().string();//获取返回体的字符串
            System.out.print(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void SimplePOST(){
        String url="http://tieba.baidu.com";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .build();
        SimplePOST create=retrofit.create(SimplePOST.class);
        final Call<ResponseBody> call=create.getResponse();
        try{
            Response<ResponseBody> response= call.execute();
            String body=response.body().string();
            System.out.print(body);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void path_GitHub(){
        String url="https://api.github.com";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHub create=retrofit.create(GitHub.class);
        /**
         * 访问这个地址返回的是一个JsonArray,JsonArray的每一个元素都有login
         * 和contributions这2个key和其对应的value.提取出来封装进POJO对象中.
         */
        Call<List<Contributor>> call=create.contributors("square", "retrofit");
        try {
            Response<List<Contributor>> response=call.execute();
            List<Contributor>list=response.body();
            for (Contributor c:list) {
                System.out.println(c.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Get_String(){
        String url="http://tieba.baidu.com";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(StringConverter.FACTORY)
                .build();
        final StringClient strClient=retrofit.create(StringClient.class);
        Call<String>call=strClient.getString();
        try {
            Response<String> str=call.execute();
            System.out.print(str.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
