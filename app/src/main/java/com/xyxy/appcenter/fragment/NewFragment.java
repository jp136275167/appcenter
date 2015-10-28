package com.xyxy.appcenter.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xyxy.appcenter.R;
import com.xyxy.appcenter.view.LoadingPage;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewFragment extends BaseFragment {

    @Override
    protected LoadingPage.LoadResult load() {
        return LoadingPage.LoadResult.success;
    }

    @Override
    protected View CreateSuccessView() {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.newfragment);
        return textView;
    }


}
