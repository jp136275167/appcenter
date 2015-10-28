package com.xyxy.appcenter.adapter;

import com.xyxy.appcenter.domain.AppInfo;
import com.xyxy.appcenter.holder.BaseHolder;
import com.xyxy.appcenter.holder.ListBaseHolder;

import java.util.List;

/**
 * Created by Administrator on 2015/10/28.
 */
public abstract class ListBaseAdapter extends DefaultAdapter<AppInfo> {
    public ListBaseAdapter(List<AppInfo> datas) {
        super(datas);
    }

    @Override
    protected BaseHolder<AppInfo> getHolder() {
        return new ListBaseHolder();
    }

    @Override
    protected abstract List<AppInfo> onload();
}
