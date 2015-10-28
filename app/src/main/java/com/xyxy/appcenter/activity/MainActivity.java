package com.xyxy.appcenter.activity;

import android.app.ActionBar;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.xyxy.appcenter.R;
import com.xyxy.appcenter.fragment.BaseFragment;
import com.xyxy.appcenter.fragment.FragmentFactory;
import com.xyxy.appcenter.tool.UiUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends BaseActivity implements SearchView.OnQueryTextListener {
    @InjectView(R.id.pager_tab_strip)
    PagerTabStrip pagerTabStrip;
    @InjectView(R.id.pager)
    ViewPager pager;
    //抽屉
    private DrawerLayout mDrawerLayout;
    //抽屉开关
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] tab_names;//标签的名字

    @Override
    protected void init() {
        super.init();
        tab_names= UiUtils.getstringArray(R.array.tab_names);
    }

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        pager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                BaseFragment createFragment = FragmentFactory.CreateFragment(position);
                createFragment.show();
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.mipmap.ic_drawer_am, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(getApplicationContext(), "抽屉关闭", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(getApplicationContext(), "抽屉打开", Toast.LENGTH_SHORT).show();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }


    private class MainAdapter extends FragmentStatePagerAdapter{

        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tab_names[position%tab_names.length];
        }

        @Override
        public Fragment getItem(int position) {
           return FragmentFactory.CreateFragment(position);
        }

        @Override
        public int getCount() {
            return tab_names.length;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView searchview = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchview.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_search) {
            Toast.makeText(getApplication(), "搜索", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item) || mDrawerToggle.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
