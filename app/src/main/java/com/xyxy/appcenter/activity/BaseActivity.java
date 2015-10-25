package com.xyxy.appcenter.activity;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.xyxy.appcenter.R;

import java.util.LinkedList;
import java.util.List;

public class BaseActivity extends FragmentActivity {
    //管理进程中运行的所有activi
    public static List<BaseActivity> activities=new LinkedList<BaseActivity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        synchronized (activities){
            activities.add(this);
        }
        init();
        initView();
        initActionBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized (activities){
            activities.remove(this);
        }
    }
    public void killAll(){
        //因为遍历的过程中 不能移除 所以复制一份activities集合
        List<BaseActivity> copy=new LinkedList<BaseActivity>(activities);
        for(BaseActivity activity:copy){
            activity.finish();
        }
    }

    protected void init() {
    }
    protected void initView() {
    }
    protected void initActionBar() {
    }
}
