package com.xyxy.appcenter.tool;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by Administrator on 2015/10/25.
 */
public class ViewUtils {
    //先找到 爹 在通过爹去移除孩子
    public static void removePaent(View v){
        ViewParent parent = v.getParent();
        if(parent instanceof ViewGroup){
            ViewGroup group= (ViewGroup) parent;
            group.removeView(v);
        }
    }
}
