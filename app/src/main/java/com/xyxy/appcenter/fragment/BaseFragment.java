package com.xyxy.appcenter.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.xyxy.appcenter.R;
import com.xyxy.appcenter.tool.UiUtils;
import com.xyxy.appcenter.tool.ViewUtils;

/**
 * Created by Administrator on 2015/10/25.
 */
public abstract class BaseFragment extends Fragment {
    public static final int STATE_UNKOWN=0;
    public static final int STATE_LOADING=1;
    public static final int STATE_ERROR=2;
    public static final int STATE_EMPTY=3;
    public static final int STATE_SUCCESS=4;
    public  int state=STATE_UNKOWN;

    private View loadingView; //加载中的界面
    private View errorView; //错误界面
    private View emptyView; //空界面
    private View successView;//成功界面
    private FrameLayout frameLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (frameLayout == null) {
            frameLayout = new FrameLayout(UiUtils.getContext());
            init();
        } else {
            ViewUtils.removePaent(frameLayout);
        }
       // show();
        return frameLayout;
    }

    //根据服务器的数据 切换状态
    public void show() {
        if(state ==STATE_ERROR || state== STATE_EMPTY){
            state=STATE_LOADING;
        }
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(2000);
                final LoadResult result=load();
                if(getActivity() !=null){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(result !=null){
                                state=result.getValue();
                                //state=2+new Random().nextInt(3);
                                showPage();
                            }
                        }
                    });
                }
            }
        }.start();
        // 一定要调用此方法 不然会出现 点击 重新加载 不加载的问题
        showPage();
    }



    //根据不同的数据显示不同的界面
    protected void showPage() {
        if (loadingView != null) {
            loadingView.setVisibility(state == STATE_UNKOWN || state == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        }
        if (errorView != null) {
            errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        }
        if (emptyView != null) {
            emptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
        }
        if (state == STATE_SUCCESS) {
            successView = CreateSuccessView();
            if (successView != null) {
                frameLayout.addView(successView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                successView.setVisibility(View.VISIBLE);
            }
        }else {
            if(successView !=null){
                successView.setVisibility(View.INVISIBLE);
            }
        }

    }

    public enum  LoadResult{
        error(2),empty(3),success(4);
        int value;
        LoadResult(int value){
            this.value=value;
        }
        public int getValue(){
            return value;
        }
    }

    //在FrameLayout 中添加4种不同的界面


    protected void init() {
        loadingView=CreateLoadingView();
        if(loadingView !=null){
            frameLayout.addView(loadingView,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        errorView=CreateErrorView();
        if(errorView !=null){
            frameLayout.addView(errorView,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        emptyView=CreateEmptyView();
        if(emptyView !=null){
            frameLayout.addView(emptyView,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        showPage();
    }

    private View CreateEmptyView() {
        View view = View.inflate(UiUtils.getContext(), R.layout.loadpage_empty, null);
        return view;
    }

    private View CreateErrorView() {
        View view = View.inflate(UiUtils.getContext(), R.layout.loadpage_error, null);
        Button reload = (Button) view.findViewById(R.id.btn_reload);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        return view;
    }

    private View CreateLoadingView() {
        View view = View.inflate(UiUtils.getContext(), R.layout.loadpage_loading, null);
        return view;
    }
    //请求服务器的状态不一样，定义为抽象 ，让子类自己去实现
    protected abstract LoadResult load();
    //成功界面 不一样 定义为抽象方法 让子类去实现
    protected abstract View CreateSuccessView();

}


