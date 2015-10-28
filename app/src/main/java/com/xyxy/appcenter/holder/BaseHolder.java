package com.xyxy.appcenter.holder;

import android.view.View;

import com.lidroid.xutils.BitmapUtils;
import com.xyxy.appcenter.tool.BitmapHelper;

/**
 * Created by Administrator on 2015/10/28.
 */
public abstract class BaseHolder<T>{

    private View contentView;
    private T data;
    protected BitmapUtils bitmapUtils;

    public BaseHolder() {
        bitmapUtils= BitmapHelper.getBitmapUtils();
        contentView=initView();
        contentView.setTag(this);
    }

    public void setData(T data) {
        this.data = data;
        refreshView(data);
    }
    public View getContentView() {
        return contentView;
    }
    public abstract void refreshView(T data);
    protected abstract View initView();
}
