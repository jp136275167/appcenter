package com.xyxy.appcenter.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.xyxy.appcenter.R;
import com.xyxy.appcenter.domain.AppInfo;
import com.xyxy.appcenter.http.HttpHelper;
import com.xyxy.appcenter.tool.BitmapHelper;
import com.xyxy.appcenter.tool.UiUtils;

/**
 * Created by Administrator on 2015/10/28.
 */
public class ListBaseHolder extends BaseHolder<AppInfo> {
    ImageView item_icon;
    TextView item_title,item_des,item_size;
    RatingBar item_rating;

    private View contentView;

    public void refreshView(AppInfo data) {

        this.item_title.setText(data.getName());
        this.item_des.setText(data.getDes());
        String size = Formatter.formatFileSize(UiUtils.getContext(), data.getSize());
        this.item_size.setText(size);
        float stars = data.getStars();
        this.item_rating.setRating(stars);
        String iconUrl = data.getIconUrl();
        bitmapUtils = BitmapHelper.getBitmapUtils();
        bitmapUtils.display(this.item_icon, HttpHelper.URL + "image?name=" + iconUrl);
    }

    @Override
    protected View initView() {
        contentView = View.inflate(UiUtils.getContext(), R.layout.item_app, null);
        this.item_icon= (ImageView) contentView.findViewById(R.id.item_icon);
        this.item_title= (TextView) contentView.findViewById(R.id.item_title);
        this.item_des= (TextView) contentView.findViewById(R.id.item_des);
        this.item_size= (TextView) contentView.findViewById(R.id.item_size);
        this.item_rating= (RatingBar) contentView.findViewById(R.id.item_rating);
        return contentView;
    }

}
