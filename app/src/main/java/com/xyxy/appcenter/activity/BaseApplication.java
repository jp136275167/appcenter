package com.xyxy.appcenter.activity;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.view.View;

/**
 * Created by Administrator on 2015/10/25.
 */
public class BaseApplication extends Application {
    private static BaseApplication application;
    private static int MainTid;
    private static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
        MainTid=android.os.Process.myTid();
        handler=new Handler();
    }
    public static Context getApplication(){
        return application;
    }

    public static int getMainTid() {
        return MainTid;
    }

    public static Handler getHandler() {
        return  handler;
    }
}
