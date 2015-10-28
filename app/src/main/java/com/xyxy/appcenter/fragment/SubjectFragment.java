package com.xyxy.appcenter.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.xyxy.appcenter.R;
import com.xyxy.appcenter.adapter.DefaultAdapter;
import com.xyxy.appcenter.domain.SubjectInfo;
import com.xyxy.appcenter.holder.BaseHolder;
import com.xyxy.appcenter.http.HttpHelper;
import com.xyxy.appcenter.protocol.SubjectProtocol;
import com.xyxy.appcenter.tool.BitmapHelper;
import com.xyxy.appcenter.tool.UiUtils;
import com.xyxy.appcenter.view.BaseListView;
import com.xyxy.appcenter.view.LoadingPage;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectFragment extends BaseFragment {

    private List<SubjectInfo> datas;

    @Override
    protected LoadingPage.LoadResult load() {
        SubjectProtocol protocol=new SubjectProtocol();
        datas = protocol.load(0);
        return checkData(datas) ;
    }

    @Override
    protected View CreateSuccessView() {
        BaseListView listView=new BaseListView(UiUtils.getContext());
        listView.setAdapter(new SubjectAdapter(datas));
        return listView;
    }

    class SubjectAdapter extends DefaultAdapter<SubjectInfo>{

        public SubjectAdapter(List<SubjectInfo> datas) {
            super(datas);
        }
        @Override
        protected BaseHolder<SubjectInfo> getHolder() {
            return new ViewHolder();
        }

        @Override
        protected List<SubjectInfo> onload() {
            SubjectProtocol protocol=new SubjectProtocol();
            List<SubjectInfo> load = protocol.load(datas.size());
            load.addAll(datas);
            return load;
        }
    }

    static class ViewHolder extends BaseHolder<SubjectInfo>{
        ImageView item_icon;
        TextView item_txt;
        private View contentView;

        @Override
        public void refreshView(SubjectInfo data) {
            this.item_txt.setText(data.getDes());
            BitmapUtils bitmapUtils =BitmapHelper.getBitmapUtils();
            bitmapUtils.display(this.item_icon, HttpHelper.URL+"image?name="+data.getUrl());
        }
        @Override
        protected View initView() {
            contentView=View.inflate(UiUtils.getContext(), R.layout.item_subject,null);
            this.item_icon= (ImageView) contentView.findViewById(R.id.item_icon);
            this.item_txt= (TextView) contentView.findViewById(R.id.item_txt);
            contentView.setTag(this);
            return contentView;
        }

    }
}