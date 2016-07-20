package com.it5.okhttp_demo.retrofit2.interf;

import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by IT5 on 2016/7/19.
 */
public interface UploadServer {
    @Headers({//静态Header
            "User-Agent:Mozilla/5.0 (Linux; U; Android 4.3; zh-cn; GT-I9305 Build/JSS15J) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 (TPAGES_ANDROIDPHONE/1.0)"
            })
    @Multipart
    @POST("/service.php?mod=avatar&type=big")
        // upload is a post field
        // without filename it didn't work! I use a constant name because server doesn't save file on original name
        //@Part("filename=\"1\" ") RequestBody
        //以表单的形式上传图片
    Call<JsonObject> uploadImageM(@Part("file\";filename=\"1") RequestBody file);
//    Call<ResponseBody> uploadImage(@Part("file\"; filename=\"image.png\"") RequestBody file);

    /**
     * 以二进制流的形式上传 图片
     * @param file
     * @return
     */
    @Headers({//静态Header
            "User-Agent:Mozilla/5.0 (Linux; U; Android 4.3; zh-cn; GT-I9305 Build/JSS15J) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 (TPAGES_ANDROIDPHONE/1.0)"
    })
    @POST("/service.php?mod=avatar&type=big")
    Call<ResponseBody> uploadImage(@Body RequestBody file);
}
