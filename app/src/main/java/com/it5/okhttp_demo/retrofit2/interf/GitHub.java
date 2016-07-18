package com.it5.okhttp_demo.retrofit2.interf;

import com.it5.okhttp_demo.retrofit2.pojo.Contributor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by IT5 on 2016/7/18.
 */
public interface GitHub {
    //实现原理是通过正则表达式进行替换.
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributors(@Path("owner") String owner, @Path("repo") String repo);
}
