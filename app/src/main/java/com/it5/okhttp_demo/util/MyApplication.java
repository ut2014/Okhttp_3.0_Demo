package com.it5.okhttp_demo.util;

import android.app.Application;

/**
 * Created by IT5 on 2016/7/20.
 */
public class MyApplication extends Application {
    private static Application application;
    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
    }
    public static Application appContext(){
        return application;
    }
}
