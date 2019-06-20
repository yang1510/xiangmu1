package com.example.wanandroid.widget;


import android.app.Application;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.example.wanandroid.dao.DaoMaster;
import com.example.wanandroid.dao.DaoSession;

public class BaseApp extends Application {
    private static BaseApp sBaseApp;
    public static DaoSession daoSession;
    public static Application mApplicationContext;
    public static int mWidthPixels;
    public static int mHeightPixels;
    public static boolean mIsLogin = false;

    @Override
    public void onCreate() {
        super.onCreate();
        sBaseApp=this;
        mApplicationContext = this;
        initDao();
        getScreenWH();
    }

    private void getScreenWH() {
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        defaultDisplay.getMetrics(metrics);
        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;
    }

    private void initDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "user.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    public static BaseApp getInstance() {
        return sBaseApp;
    }

    public static BaseApp getsBaseApp() {
        return sBaseApp;
    }
}
