package com.xyxy.appcenter.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xyxy.appcenter.holder.BaseHolder;
import com.xyxy.appcenter.holder.MoreHolder;
import com.xyxy.appcenter.manager.ThreadManager;
import com.xyxy.appcenter.tool.UiUtils;

import java.util.List;

/**
 * Created by Administrator on 2015/10/28.
 * 让其它适配器 继承它
 */
public abstract class DefaultAdapter<T> extends BaseAdapter {
    protected List<T> datas;
    private static final int DEFAULT_ITEM=0;
    private static final int MORE_ITEM=1;
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
        return datas.size()+1;  //最后一个条目 加载状态
    }
//类型个数
    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount()+1;
    }
//条目的类型
    @Override
    public int getItemViewType(int position) {
        if(position==datas.size()){
            return MORE_ITEM;
        }
        return getInnerItemViewType(position);
    }

    private int getInnerItemViewType(int position) {
        return DEFAULT_ITEM;
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
        BaseHolder holder=null;
        switch (getItemViewType(position)){
            case MORE_ITEM:
                if(convertView == null){
                    holder=getMoreHolder();
                }else {
                    holder= (BaseHolder) convertView.getTag();
                }
                break;
            case DEFAULT_ITEM:
                if(convertView == null){
                    holder=getHolder();
                }else {
                    holder= (BaseHolder) convertView.getTag();
                }
                if(position<datas.size()){
                    holder.setData(datas.get(position));
                }
                break;
        }
        return holder.getContentView();
//        if(convertView ==null) {
//            if(getItemViewType(position)==MORE_ITEM){
//                holder=getMoreHolder();
//            }else {
//                holder = getHolder();  //普通条目holder
//            }
//        }else {
//            if(getItemViewType(position)== DEFAULT_ITEM) {
//                holder = (BaseHolder) convertView.getTag();
//            }else {
//                holder=getMoreHolder();
//            }
//        }
  //      T info = datas.get(position);
    //    holder.setData(info);
      //  return holder.getContentView();
    }
    private MoreHolder holder;
    private BaseHolder getMoreHolder() {
        if(holder !=null){
            return holder;
        }else {
           holder=new MoreHolder(this);
            return holder;
        }
    }



    public void loadMore() {
        ThreadManager.getInstance().createLongPool().execute(new Runnable() {
            @Override
            public void run() {
              final List<T> newData=onload();
                    UiUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(newData == null){
                                holder.setData(MoreHolder.LOAD_ERROR);
                            }else if(newData.size() == 0){
                                holder.setData(MoreHolder.HAS_NO_MORE);
                            }else {
                                holder.setData(MoreHolder.HAS_MORE);
                                datas.addAll(newData);
                                notifyDataSetChanged();
                            }
                        }
                    });
                }
        });
    }

    protected abstract List<T> onload();
    protected abstract BaseHolder<T> getHolder();

}
