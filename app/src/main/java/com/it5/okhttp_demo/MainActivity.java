package com.it5.okhttp_demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.it5.okhttp_demo.https.HttpsSSL;
import com.it5.okhttp_demo.retrofit2.Retrofit_Demo;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getRequest();
//        postRequest();
//        download_tb();
//        Access_Header();
//        get_json1();
        upload();
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

    private void get_SSl() {//ssl https
        HttpsSSL customTrust = new HttpsSSL(this);
        try {
//            customTrust.run();
            customTrust.run_all();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void get_json() {
        OkhttpJson okhttpJson = new OkhttpJson();
        try {
            okhttpJson.run_json();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void get_json1() {
        OkhttpJson okhttpJson = new OkhttpJson();
        try {
            okhttpJson.run_json1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void upload(){//上传图像
        String filePath="/sdcard/download/com.andbase/tp.png";
        if (!fileIsExists(filePath)){
            Log.e("file: ","file no found!!");
            return;
        }
        Bitmap bit=BitmapFactory.decodeFile(filePath);
        ((ImageView)findViewById(R.id.img)).setImageBitmap(bit);
        Retrofit_Demo.upload(filePath);
    }

    //判断文件是否存在
    public boolean fileIsExists(String strFile)
    {
        try
        {
            File f=new File(strFile);
            if(!f.exists())
            {
                return false;
            }

        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }
}
