package com.xyxy.appcenter.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.xyxy.appcenter.R;
import com.xyxy.appcenter.view.LoadingPage;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseFragment {

    @Override
    protected LoadingPage.LoadResult load() {
        return LoadingPage.LoadResult.error;
    }

    @Override
    protected View CreateSuccessView() {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.categoryfragment);
        return textView;
    }


}
