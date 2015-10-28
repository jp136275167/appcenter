package com.xyxy.appcenter.fragment;


import android.support.v4.app.Fragment;
import android.view.View;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;
import com.xyxy.appcenter.R;
import com.xyxy.appcenter.adapter.ListBaseAdapter;
import com.xyxy.appcenter.domain.AppInfo;
import com.xyxy.appcenter.protocol.NewProtocol;
import com.xyxy.appcenter.tool.BitmapHelper;
import com.xyxy.appcenter.tool.UiUtils;
import com.xyxy.appcenter.view.BaseListView;
import com.xyxy.appcenter.view.LoadingPage;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewFragment extends BaseFragment {

    private List<AppInfo> datas;
    private BitmapUtils bitmapUtils;
    @Override
    protected LoadingPage.LoadResult load() {
        NewProtocol protocol = new NewProtocol();
        datas = protocol.load(0);
        return checkData(datas);
    }

    @Override
    protected View CreateSuccessView() {
        BaseListView listView = new BaseListView(UiUtils.getContext());
        listView.setAdapter(new ListBaseAdapter(datas) {
            @Override
            protected List<AppInfo> onload() {
                NewProtocol protocol=new NewProtocol();
                List<AppInfo> load = protocol.load(datas.size());
                datas.addAll(load);
                return load;
            }
        });
        bitmapUtils = BitmapHelper.getBitmapUtils();
        listView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
        bitmapUtils.configDefaultLoadFailedImage(R.mipmap.ic_default);
        bitmapUtils.configDefaultLoadingImage(R.mipmap.ic_default);
        return listView;
    }



}
