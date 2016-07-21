package com.it5.okhttp_demo;

import com.it5.okhttp_demo.Interceptor.LoggingInterceptors;
import com.it5.okhttp_demo.retrofit2.Retrofit_Demo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getRequest(){
        OkhttpDemo okhttpDemo=new OkhttpDemo();
        okhttpDemo.getRequest();
    }

    @Test
    public void download_tb(){
        OkhttpDemo okhttpDemo=new OkhttpDemo();
        okhttpDemo.download_tb();
    }

    @Test
    public void testGet(){
        Retrofit_Demo.simpleGET();
    }

    @Test
    public void testPOST(){
        Retrofit_Demo.SimplePOST();
    }

    @Test
    public void testPath_github(){
        Retrofit_Demo.path_GitHub();
    }

    @Test
    public void testGet_String(){

        Retrofit_Demo.Get_String();
    }
    @Test
    public void testQueryGET(){

        Retrofit_Demo.QueryGET();
    }

    @Test
    public void testPhoneGET(){
        Retrofit_Demo.phone_Query("15527530925");
    }
    @Test
    public void testPhoneDynamic(){
        Retrofit_Demo.phone_QueryDynamic("15024084173");
    }
    @Test
    public void testLogging(){
        try {
            new LoggingInterceptors().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}