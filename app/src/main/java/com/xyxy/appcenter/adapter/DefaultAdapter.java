package com.xyxy.appcenter.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xyxy.appcenter.holder.BaseHolder;

import java.util.List;

/**
 * Created by Administrator on 2015/10/28.
 * 让其它适配器 继承它
 */
public abstract class DefaultAdapter<T> extends BaseAdapter {
    protected List<T> datas;

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public DefaultAdapter(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder<T> holder;
        if(convertView ==null) {
            holder=getHolder();
        }else {
            holder= (BaseHolder<T>) convertView.getTag();
        }
        T info = datas.get(position);
        holder.setData(info);
        return holder.getContentView();
    }

    protected abstract BaseHolder<T> getHolder();
}
