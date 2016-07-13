package com.it5.okhttp_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.it5.okhttp_demo.https.HttpsSSL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        getRequest();
//        postRequest();
//        download_tb();
//        Access_Header();
        get_json();
    }

    private void getRequest() {
        OkhttpDemo okhttpDemo = new OkhttpDemo();
        okhttpDemo.getRequest();
    }

    private void postRequest() {
        OkhttpDemo okhttpDemo = new OkhttpDemo();
        okhttpDemo.postRequest();
    }

    //同步下载
    private void download_tb() {
        final OkhttpDemo okhttpDemo = new OkhttpDemo();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        okhttpDemo.download_tb();
                    }
                }
        ).start();

    }
    private void Access_Header() {
        final OkhttpDemo okhttpDemo = new OkhttpDemo();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        okhttpDemo.accessHeaders(OkhttpDemo.client);
                    }
                }
        ).start();

    }

    private void get_SSl(){//ssl https
        HttpsSSL customTrust = new HttpsSSL(this);
        try {
//            customTrust.run();
            customTrust.run_all();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void get_json(){
        OkhttpJson okhttpJson=new OkhttpJson();
        try{
            okhttpJson.run_json();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
