package com.it5.okhttp_demo.retrofit2.interf;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by IT5 on 2016/7/18.
 */
public interface SimplePOST {
    @POST("/android")
    Call<ResponseBody> getResponse();
}
