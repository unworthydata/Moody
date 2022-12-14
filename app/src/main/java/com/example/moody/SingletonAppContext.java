package com.example.moody;

import android.app.Application;
import android.content.Context;

public class SingletonAppContext extends Application {
    private static SingletonAppContext instance;

    public static SingletonAppContext getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
        // or return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
