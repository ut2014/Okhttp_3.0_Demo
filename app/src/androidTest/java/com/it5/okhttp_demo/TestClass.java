package com.it5.okhttp_demo;

import android.test.InstrumentationTestCase;
import android.util.Log;

/**
 * Created by IT5 on 2016/7/6.
 */
public class TestClass extends InstrumentationTestCase {

    public void testGetRequest() throws Exception{

        OkhttpDemo okhttpDemo=new OkhttpDemo();
        okhttpDemo.getRequest();
        Log.d("html","dfa");
    }

    public void testDownload_tb(){
        OkhttpDemo okhttpDemo=new OkhttpDemo();
        okhttpDemo.download_tb();
    }
    public void testAccess_header(){
        OkhttpDemo okhttpDemo=new OkhttpDemo();
        okhttpDemo.accessHeaders(OkhttpDemo.client);
    }
}
