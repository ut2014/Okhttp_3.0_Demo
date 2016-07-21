package com.it5.okhttp_demo.retrofit2;


import android.util.Log;

import com.it5.okhttp_demo.retrofit2.interf.GitHub;
import com.it5.okhttp_demo.retrofit2.interf.PageService;
import com.it5.okhttp_demo.retrofit2.interf.PhoneService;
import com.it5.okhttp_demo.retrofit2.interf.PhoneServiceDynamic;
import com.it5.okhttp_demo.retrofit2.interf.QueryGET;
import com.it5.okhttp_demo.retrofit2.interf.SimpleGET;
import com.it5.okhttp_demo.retrofit2.interf.SimplePOST;
import com.it5.okhttp_demo.retrofit2.interf.StringClient;
import com.it5.okhttp_demo.retrofit2.interf.UploadServer;
import com.it5.okhttp_demo.retrofit2.pojo.Contributor;
import com.it5.okhttp_demo.retrofit2.pojo.PhoneResult;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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


    public static void QueryGET(){
        String url="http://tieba.baidu.com";
        Map<String, String> map=new HashMap<>();
        map.put("gender","male");
        map.put("address","sz");

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(StringConverter.FACTORY)
                .build();
        QueryGET queryGET=retrofit.create(QueryGET.class);
        Call<String> call=queryGET.getString("laiqurufeng", 22, map);
        try {
            Response<String>body=call.execute();
            System.out.print(body.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void phone_Query(String phone){
        final String BASE_URL="http://apis.baidu.com";
        final String API_KEY="8e13586b86e4b7f3758ba3bd6c9c9135";
        //1.创建Retrofit对象
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //2.创建访问API的请求
        PhoneService service=retrofit.create(PhoneService.class);
        Call<PhoneResult>call=service.getResult(API_KEY,phone);
        //3.发送请求
        try {
            Response<PhoneResult>response=call.execute();
            //4.处理结果
            if (response.isSuccessful()) {
                PhoneResult result=response.body();
                if (result!=null) {
                    PhoneResult.RetDataEntity entity = result.getRetData();
                    System.out.print(entity.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*call.enqueue(new Callback<PhoneResult>() {
            @Override
            public void onResponse(Call<PhoneResult> call, Response<PhoneResult> response) {
                //4.处理结果
                if (response.isSuccessful()) {
                    PhoneResult result=response.body();
                    if (result!=null) {
                        PhoneResult.RetDataEntity entity = result.getRetData();
                        System.out.print(entity.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<PhoneResult> call, Throwable t) {

            }
        });*/
    }


    public static void phone_QueryDynamic(String phone){
        final String BASE_URL="http://apis.baidu.com";
        //1.创建Retrofit对象
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //2.创建访问API的请求
        PhoneServiceDynamic service=retrofit.create(PhoneServiceDynamic.class);
        Call<PhoneResult>call=service.getResult(phone);
        //3.发送请求
        try {
            Response<PhoneResult>response=call.execute();
            //4.处理结果
            if (response.isSuccessful()) {
                PhoneResult result=response.body();
                if (result!=null) {
                    PhoneResult.RetDataEntity entity = result.getRetData();
                    System.out.print(entity.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static OkHttpClient getOkhttpClient(){
        final String cookie="o86B_2132_saltkey=ZlurOOcm; o86B_2132_lastvisit=1467604630; ECM_ID=715ed3d72119b89eb9a33efec0c3b8bcb8029c16; PHPSESSID=dr49auvp45q4gv4feohr5f5l80; o86B_2132_name=TP304342; o86B_2132_auth=8002JmGNf9BxGBDuhLtPHuLiXNdsE9TU%2B4fJVdOBiOIAsaf85OKSakO3ETuZSb6FX5bl0Io15UxeEJ2Ul%2F7x74vhPxQ; Hm_lvt_3858d85a858cc0997bf162c3fb49c93b=1467608513; Hm_lpvt_3858d85a858cc0997bf162c3fb49c93b=1467616160; o86B_2132_sid=bM6A1P; o86B_2132_lastact=1467615882%09portal.php%09";
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
//                                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//                                .addHeader("Accept-Encoding", "gzip, deflate")
//                                .addHeader("Connection", "keep-alive")
//                                .addHeader("Accept", "***")
                                .addHeader("Cookie", cookie)
                                .build();
                        return chain.proceed(request);
                    }

                })
                .build();

        return httpClient;
    }
    public static void upload(String fpath) { // path to file like /mnt/sdcard/myfile.txt
        String baseurl="http://dev.123go.net.cn";
        File file = new File(fpath);

        // please check you mime type, i'm uploading only images
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        /*RequestBody rbody=new MultipartBody.Builder()
                .addPart(requestBody).build();

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        */

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkhttpClient())
                .build();
        // 添加描述
        /*String descriptionString = "hello, 这是文件描述";
        RequestBody description =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), descriptionString);*/
        UploadServer server=retrofit.create(UploadServer.class);
        Call<ResponseBody> call = server.uploadImage(requestBody);
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody jsonObject=response.body();
                System.out.print(jsonObject.toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    private void  intercep() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                //请求定制：添加请求
                Request.Builder requestBuilder = original.newBuilder()
                        .header("APIKEY", "API_KEY");
                //请求体定制：统一添加token参数
                if (original.body() instanceof FormBody) {
                    FormBody.Builder newFormBody = new FormBody.Builder();
                    FormBody oidFormBody = (FormBody) original.body();
                    for (int i = 0; i < oidFormBody.size(); i++) {
                        newFormBody.addEncoded(oidFormBody.encodedName(i), oidFormBody.encodedValue(i));
                    }
                    newFormBody.add("token", "API_TOKEN");
                    requestBuilder.method(original.method(), newFormBody.build());
                }
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
    }

}
