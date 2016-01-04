package com.example.ziggy.materialapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by Ziggy on 2015-12-26.
 */
public class MyApplication extends Application {
    private static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MyApplication getsInstance(){
        return sInstance;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }
}
