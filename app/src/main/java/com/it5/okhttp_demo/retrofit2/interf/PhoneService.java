package com.it5.okhttp_demo.retrofit2.interf;

import com.it5.okhttp_demo.retrofit2.pojo.PhoneResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by IT5 on 2016/7/19.
 */
public interface PhoneService {
    @GET("/apistore/mobilenumber/mobilenumber")
    Call<PhoneResult> getResult(
            @Header("apikey")String apikey,
            @Query("phone")String phone
    );
}
