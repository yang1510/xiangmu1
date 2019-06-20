package com.example.wanandroid.shoucang;

import android.app.Application;

public class WAApplication extends Application {

    public static Application mApplicationContext;
    public static boolean mIsLogin=false;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext=this;
    }
}
