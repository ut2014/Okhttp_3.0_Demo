package com.it5.okhttp_demo.retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by IT5 on 2016/7/18.
 */
public class ScreenConvert implements Converter<ResponseBody,ScreenBean>{

    static final ScreenConvert.Factory FACTORY=new ScreenConvert.Factory(){

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            if (type==ScreenBean.class)return  new ScreenConvert();
            else return null;
        }
    };
    @Override
    public ScreenBean convert(ResponseBody value) throws IOException {
        //values.string 把服务器上请求的数据，转换成string格式
        return null;
    }
}
