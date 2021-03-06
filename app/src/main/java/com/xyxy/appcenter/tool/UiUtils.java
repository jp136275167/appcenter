package com.xyxy.appcenter.tool;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.xyxy.appcenter.activity.BaseApplication;

/**
 * Created by Administrator on 2015/10/25.
 */
public class UiUtils {
    public static String[] getstringArray(int tab_names) {

        return getResource().getStringArray(tab_names);
    }
    public static Context getContext(){
        return BaseApplication.getApplication();
    }
    public static Resources getResource(){
        return BaseApplication.getApplication().getResources();
    }

    public static void runOnUiThread(Runnable runnable) {
        if(android.os.Process.myTid() == BaseApplication.getMainTid()){
            runnable.run();
        }else {
            BaseApplication.getHandler().post(runnable);
        }
    }

    public static Drawable getDrawalbe(int id) {
        return getResource().getDrawable(id);
    }

    public static int getDimens(int home_picture_height){
        return (int)getResource().getDimension(home_picture_height);
    }
}
