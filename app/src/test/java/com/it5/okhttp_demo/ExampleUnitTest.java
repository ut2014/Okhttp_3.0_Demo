package com.it5.okhttp_demo;

import com.it5.okhttp_demo.https.HttpsUtils;

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
    public void testHttps(){

    }
}