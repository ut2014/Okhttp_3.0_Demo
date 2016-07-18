package com.it5.okhttp_demo;

import android.util.Log;

import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by IT5 on 2016/7/5.
 */
public class Okhttp {

    public static JSONObject uploadImage(File file) {
        try {

            final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
            RequestBody req = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("userid", "8457851245")
                    .addFormDataPart(file.getName(), file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file)).build();
            Request request = new Request.Builder()
                    .url("url")
                    .post(req)
                    .build();

            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();

            Log.d("response", "uploadImage:"+response.body().string());

            return new JSONObject(response.body().string());

        } catch (UnknownHostException | UnsupportedEncodingException e) {
            Log.e("img", "Error: " + e.getLocalizedMessage());
        } catch (Exception e) {
            Log.e("img", "Other Error: " + e.getLocalizedMessage());
        }
        return null;
    }


}
