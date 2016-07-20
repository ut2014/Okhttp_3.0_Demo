package com.it5.okhttp_demo.retrofit2.interf;

import com.it5.okhttp_demo.retrofit2.pojo.PhoneResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by IT5 on 2016/7/19.
 */
public interface PhoneServiceDynamic {
    @Headers("apikey:8e13586b86e4b7f3758ba3bd6c9c9135")
    @GET("/apistore/mobilenumber/mobilenumber")
    Call<PhoneResult> getResult(
            @Query("phone")String phone
    );
}
