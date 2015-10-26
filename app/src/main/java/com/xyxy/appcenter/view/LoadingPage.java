package com.xyxy.appcenter.view;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.xyxy.appcenter.R;
import com.xyxy.appcenter.manager.ThreadManager;
import com.xyxy.appcenter.tool.UiUtils;

/**
 * Created by Administrator on 2015/10/25.
 */
//把 baseFragment中对FrameLayout操作的代码 提取到loadingpage中
public abstract class  LoadingPage extends FrameLayout {

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

    public LoadingPage(Context context) {
        super(context);
        init();
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    protected void init() {
        loadingView=CreateLoadingView();
        if(loadingView !=null){
            this.addView(loadingView,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        errorView=CreateErrorView();
        if(errorView !=null){
            this.addView(errorView,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        emptyView=CreateEmptyView();
        if(emptyView !=null){
            this.addView(emptyView,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
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
                this.addView(successView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                successView.setVisibility(View.VISIBLE);
            }
        }else {
            if(successView !=null){
                successView.setVisibility(View.INVISIBLE);
            }
        }

    }

    //根据服务器的数据 切换状态
    public void show() {
        if(state ==STATE_ERROR || state== STATE_EMPTY){
            state=STATE_LOADING;
        }
        ThreadManager.getInstance().createLongPool().execute(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                final LoadResult result=load();
                UiUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(result !=null){
                            state=result.getValue();
                            showPage();
                        }
                    }
                });
            }
        });

        // 一定要调用此方法 不然会出现 点击 重新加载 不加载的问题
        showPage();
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

    protected abstract View CreateSuccessView();
    protected abstract LoadResult load();
}
