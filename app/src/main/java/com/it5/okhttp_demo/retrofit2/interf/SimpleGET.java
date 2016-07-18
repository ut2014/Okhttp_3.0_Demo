package com.it5.okhttp_demo.retrofit2.interf;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by IT5 on 2016/7/18.
 */
public interface SimpleGET {
    @GET("/") //表明是GET方式. "/"会拼接在setEndpoint(url)中url(主机地址)的后面.
    Call<ResponseBody> getResponse();
    //可以简单的理解为网络访问完把Response转换为一个对象.这里没有转换,还是Response.

}
