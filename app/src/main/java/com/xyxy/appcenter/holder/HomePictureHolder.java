package com.xyxy.appcenter.holder;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.xyxy.appcenter.R;
import com.xyxy.appcenter.http.HttpHelper;
import com.xyxy.appcenter.tool.UiUtils;

import java.util.List;

/**
 * Created by Administrator on 2015/10/28.
 */
public class HomePictureHolder extends BaseHolder<List<String>>{

    private ViewPager viewPager;
    private List<String> datas;
    @Override
    public void refreshView(List<String> datas) {
        this.datas=datas;
        viewPager.setAdapter(new ChoiceAdapter());
    }

    @Override
    protected View initView() {
        FrameLayout frameLayout=new FrameLayout(UiUtils.getContext());
        frameLayout.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,UiUtils.getDimens(R.dimen.home_picture_height)));
        viewPager=new ViewPager(UiUtils.getContext());
        viewPager.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,UiUtils.getDimens(R.dimen.home_picture_height)));
        frameLayout.addView(viewPager);
        return frameLayout;

    }
    class ChoiceAdapter extends  PagerAdapter{

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           // super.destroyItem(container, position, object);
            container.removeView((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView=new ImageView(UiUtils.getContext());
            bitmapUtils.display(imageView, HttpHelper.URL+"image?name="+datas.get(position));
            container.addView(imageView);
            return imageView;
        }
    }

}
