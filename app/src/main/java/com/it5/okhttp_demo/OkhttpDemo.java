package com.it5.okhttp_demo;

import android.util.Log;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by IT5 on 2016/7/6.
 */
public class OkhttpDemo {
    String url = "http://www.wooyun.org";

    final static OkHttpClient client = new OkHttpClient();

    public void getRequest() {
        final Request request = new Request.Builder()
                .get()
                .tag(this)
                .url(url)
                .build();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Response response = null;
                            response = client.newCall(request).execute();
                            if (response.isSuccessful()) {
                                Log.i("WY", "打印GET响应的数据：" + response.body().string());
                                System.out.print(response.body().string());
                            } else {
                                throw new IOException("Unexpected code " + response);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }


    public void postRequest() {
        RequestBody formbody = new FormBody.Builder()
                .add("", "")
                .build();
        final Request request = new Request.Builder()
                .url("http://www.wooyun.org")
                .post(formbody)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.i("WY", "打印POST响应的数据：" + response.body().string());
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    //同步下载
    public void download_tb() {
        final Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
//                .url("https://api.github.com/repos/square/okhttp/issues")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);
            Headers headers = response.headers();
            for (int i = 0; i < headers.size(); i++) {
//                System.out.println(headers.name(i) + ": " + headers.value(i));
                Log.i("WY", "数据：" + headers.name(i) + ": " + headers.value(i));
            }
//            System.out.println(response.body().string());
            Log.i("WY", "数据：" + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //getjson
    public void get_Json() {

    }

    public void accessHeaders(OkHttpClient mClient) {
        Request request = new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();
        try {
            Response response = mClient.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
           /* System.out.println("Server: " + response.header("Server"));
            System.out.println("Date: " + response.header("Date"));
            System.out.println("Vary: " + response.headers("Vary"));*/
            Log.i("WY", "数据：" + response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //信任所有的证书
    public OkHttpClient getCertificateClient() {
        OkHttpClient.Builder mBuilder  = new OkHttpClient.Builder();
//        mBuilder.sslSocketFactory(createSSLSocketFactory(),new TrustAllManager());
        mBuilder.sslSocketFactory(createSSLSocketFactory());
        mBuilder.hostnameVerifier(new TrustAllHostnameVerifier());
        return mBuilder.build();

        /*new OkHttpClient.Builder()
                .sslSocketFactory(createSSLSocketFactory(),new TrustAllManager())
                .build();*/
    }

    /**
     * 默认信任所有的证书
     * TODO 最好加上证书认证，主流App都有自己的证书
     *
     * @return
     */

    private static SSLSocketFactory createSSLSocketFactory() {

        SSLSocketFactory sSLSocketFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()},
                    new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return sSLSocketFactory;
    }

    private static class TrustAllManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)

                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }


}
