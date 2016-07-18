package com.it5.okhttp_demo.retrofit2.interf;

import com.it5.okhttp_demo.retrofit2.ScreenBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by IT5 on 2016/7/18.
 */
public interface PageService {
    @GET("GetHotDownload.xml")
    Call<ScreenBean> get(@Query("pageNum") String pageNum1,
                         @Query("packNumForOnePage") String packNumForOnePage,
                         @Query("userId") String userId, @Query("codeVersion") String version,
                         @Query("classId") String classId);
}
