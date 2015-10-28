package com.xyxy.appcenter.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.xyxy.appcenter.R;
import com.xyxy.appcenter.tool.UiUtils;

/**
 * Created by Administrator on 2015/10/26.
 */
public class BaseListView extends ListView {
    public BaseListView(Context context) {
        super(context);
        init();
    }

    public BaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setSelector(R.drawable.nothing);  // 什么都没有
        this.setCacheColorHint(R.drawable.nothing);
        this.setDivider(UiUtils.getDrawalbe(R.drawable.nothing));
    }
}
