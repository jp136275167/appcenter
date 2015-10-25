package com.xyxy.appcenter.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xyxy.appcenter.R;
import com.xyxy.appcenter.tool.UiUtils;
import com.xyxy.appcenter.tool.ViewUtils;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChoiceFragment extends BaseFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    protected LoadResult load() {
        return LoadResult.success;
    }

    protected View CreateSuccessView() {
        TextView textView=new TextView(UiUtils.getContext());
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(20);
        textView.setText("加载成功！");
        return textView;
    }


}
