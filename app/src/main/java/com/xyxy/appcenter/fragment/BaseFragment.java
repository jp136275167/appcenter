package com.xyxy.appcenter.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xyxy.appcenter.domain.AppInfo;
import com.xyxy.appcenter.tool.ViewUtils;
import com.xyxy.appcenter.view.LoadingPage;

import java.util.List;

/**
 * Created by Administrator on 2015/10/25.
 */
public abstract class BaseFragment extends Fragment {

    private LoadingPage loadingPage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (loadingPage == null) {
            loadingPage = new LoadingPage(getActivity()){
                @Override
                protected View CreateSuccessView() {
                    return BaseFragment.this.CreateSuccessView();
                }

                @Override
                protected LoadResult load() {
                    return BaseFragment.this.load();
                }
            };
        } else {
            ViewUtils.removePaent(loadingPage);
        }
       show();
        return loadingPage;
    }

    public void show() {
        if(loadingPage !=null){
            loadingPage.show();
        }
    }
    public LoadingPage.LoadResult checkData(List datas) {
        if (datas == null) {
            return LoadingPage.LoadResult.error;
        } else {
            if (datas.size() == 0) {
                return LoadingPage.LoadResult.empty;
            } else {
                return LoadingPage.LoadResult.success;
            }
        }
    }

    //请求服务器的状态不一样，定义为抽象 ，让子类自己去实现
    protected abstract LoadingPage.LoadResult load();
    //成功界面 不一样 定义为抽象方法 让子类去实现
    protected abstract View CreateSuccessView();

}


