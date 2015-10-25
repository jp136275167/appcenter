package com.xyxy.appcenter;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2015/10/25.
 */
public class BaseApplication extends Application {
    private static BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
    }
    public static Context getApplication(){
        return application;
    }
}
