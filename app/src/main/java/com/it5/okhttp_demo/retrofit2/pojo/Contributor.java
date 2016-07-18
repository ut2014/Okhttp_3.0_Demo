package com.it5.okhttp_demo.retrofit2.pojo;

/**
 * Created by IT5 on 2016/7/18.
 */
public class Contributor {
    String login;
    int contributions;

    @Override
    public String toString() {
        return "Contributor{" +
                "login='" + login + '\'' +
                ", contributions=" + contributions +
                '}';
    }
}
