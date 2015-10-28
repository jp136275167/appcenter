package com.xyxy.appcenter.holder;

import android.view.View;
import android.widget.RelativeLayout;

import com.xyxy.appcenter.R;
import com.xyxy.appcenter.adapter.DefaultAdapter;
import com.xyxy.appcenter.tool.UiUtils;

/**
 * Created by Administrator on 2015/10/28.
 */
public class MoreHolder extends BaseHolder<Integer> {
    public static final int HAS_NO_MORE=0; //没有额外的数据
    public static final int LOAD_ERROR=1; //加载失败
    public static final int HAS_MORE=2; //有额外的数据

    public RelativeLayout rl_more_loading,rl_more_error;


    private DefaultAdapter adapter;

    public MoreHolder(DefaultAdapter adapter) {
        this.adapter = adapter;
    }
//根据数据 修改界面
    @Override
    public void refreshView(Integer data) {
        rl_more_error.setVisibility(data == LOAD_ERROR ?View.VISIBLE:View.GONE);
        rl_more_loading.setVisibility(data== HAS_MORE?View.VISIBLE:View.GONE);

    }

    @Override
    public View getContentView() {
        loadMore();
        return super.getContentView();
    }

    private void loadMore() {
        adapter.loadMore();
    }

    @Override
    protected View initView() {
        View view=View.inflate(UiUtils.getContext(), R.layout.load_more, null);
        rl_more_loading= (RelativeLayout) view.findViewById(R.id.rl_more_loading);
        rl_more_error= (RelativeLayout) view.findViewById(R.id.rl_more_error);
        return view;
    }
}
