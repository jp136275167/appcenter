package com.xyxy.appcenter.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;
import com.xyxy.appcenter.R;
import com.xyxy.appcenter.adapter.DefaultAdapter;
import com.xyxy.appcenter.domain.AppInfo;
import com.xyxy.appcenter.holder.BaseHolder;
import com.xyxy.appcenter.http.HttpHelper;
import com.xyxy.appcenter.protocol.ChoiceProtocol;
import com.xyxy.appcenter.tool.BitmapHelper;
import com.xyxy.appcenter.tool.UiUtils;
import com.xyxy.appcenter.view.BaseListView;
import com.xyxy.appcenter.view.LoadingPage;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChoiceFragment extends BaseFragment{
    private BitmapUtils bitmapUtils;
    protected List<AppInfo> datas;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    protected LoadingPage.LoadResult load() {
        ChoiceProtocol protocol = new ChoiceProtocol();
        datas = protocol.load(0);
        return checkData(datas);
    }



    protected View CreateSuccessView() {

        BaseListView listview = new BaseListView(UiUtils.getContext());
        listview.setAdapter(new ChoiceAdapter(datas));
        bitmapUtils = BitmapHelper.getBitmapUtils();
        listview.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
        bitmapUtils.configDefaultLoadFailedImage(R.mipmap.ic_default);
        bitmapUtils.configDefaultLoadingImage(R.mipmap.ic_default);
        return listview;
    }



    private class ChoiceAdapter extends DefaultAdapter<AppInfo> {

        public ChoiceAdapter(List<AppInfo> datas) {
            super(datas);
        }
        @Override
        protected BaseHolder<AppInfo> getHolder() {
            return new ViewHolder();
        }
    }

    static  class  ViewHolder extends BaseHolder<AppInfo>{
        ImageView item_icon;
        TextView item_title,item_des,item_size;
        RatingBar item_rating;

        private View contentView;

        public void refreshView(AppInfo data) {

            this.item_title.setText(data.getName());
            this.item_des.setText(data.getDes());
            String size = Formatter.formatFileSize(UiUtils.getContext(), data.getSize());
            this.item_size.setText(size);
            float stars = data.getStars();
            this.item_rating.setRating(stars);
            String iconUrl = data.getIconUrl();
            bitmapUtils = BitmapHelper.getBitmapUtils();
            bitmapUtils.display(this.item_icon, HttpHelper.URL + "image?name=" + iconUrl);
        }

        @Override
        protected View initView() {
            contentView = View.inflate(UiUtils.getContext(), R.layout.item_app, null);
            this.item_icon= (ImageView) contentView.findViewById(R.id.item_icon);
            this.item_title= (TextView) contentView.findViewById(R.id.item_title);
            this.item_des= (TextView) contentView.findViewById(R.id.item_des);
            this.item_size= (TextView) contentView.findViewById(R.id.item_size);
            this.item_rating= (RatingBar) contentView.findViewById(R.id.item_rating);
            return contentView;
        }


    }

}