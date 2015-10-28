package com.xyxy.appcenter.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;
import com.xyxy.appcenter.R;
import com.xyxy.appcenter.adapter.ListBaseAdapter;
import com.xyxy.appcenter.domain.AppInfo;
import com.xyxy.appcenter.holder.HomePictureHolder;
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
    protected List<String> pictures;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    protected LoadingPage.LoadResult load() {
        ChoiceProtocol protocol = new ChoiceProtocol();
        datas = protocol.load(0);
        pictures=protocol.getPictures();
        return checkData(datas);
    }



    protected View CreateSuccessView() {

        BaseListView listview = new BaseListView(UiUtils.getContext());
        HomePictureHolder holder=new HomePictureHolder();
        holder.setData(pictures);
        View contentView = holder.getContentView();
        contentView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        listview.addHeaderView(contentView);


        listview.setAdapter(new ListBaseAdapter(datas) {
            @Override
            protected List<AppInfo> onload() {
                ChoiceProtocol protocol=new ChoiceProtocol();
                List<AppInfo> load = protocol.load(datas.size());
                datas.addAll(load);
                return load;
            }
        });
        bitmapUtils = BitmapHelper.getBitmapUtils();
        listview.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
        bitmapUtils.configDefaultLoadFailedImage(R.mipmap.ic_default);
        bitmapUtils.configDefaultLoadingImage(R.mipmap.ic_default);
        return listview;
    }

}