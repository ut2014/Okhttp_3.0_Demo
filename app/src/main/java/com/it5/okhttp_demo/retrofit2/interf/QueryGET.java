package com.it5.okhttp_demo.retrofit2.interf;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by IT5 on 2016/7/18.
 */
public interface QueryGET {
    @GET("/sheet")
    Call<String> getString(@Query("name") String name,
                           @Query("age")int age,
                           @QueryMap(encoded=true)
                           Map<String, String> filters
                           );
}
