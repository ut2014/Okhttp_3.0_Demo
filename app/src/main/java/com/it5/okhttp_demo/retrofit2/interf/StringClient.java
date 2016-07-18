package com.it5.okhttp_demo.retrofit2.interf;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by IT5 on 2016/7/18.
 */
public interface StringClient {
    //方法的返回值是String,需要StringConverter转换器Converter把Response转换为String.
    @GET("/")
    Call<String> getString();
}
