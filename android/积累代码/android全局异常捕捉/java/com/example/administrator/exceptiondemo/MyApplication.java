package com.example.administrator.exceptiondemo;

import android.app.Application;

/**
 * Created by Administrator on 2015/12/8.
 */
public class MyApplication extends Application {


    @Override
    public void onCreate(){
        super.onCreate();
       // initErrorHandler();
    }


    private void initErrorHandler(){
        System.out.println("initErrorHandler");
        CrashHandler handler = CrashHandler.getInstance();
        handler.init(this);
    }
}
