package fream.com.example.my.musicplayer;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.text.AndroidCharacter;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.balysv.materialmenu.MaterialMenuDrawable;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fream.com.example.my.musicplayer.fragment.FindFragment;
import fream.com.example.my.musicplayer.fragment.GuideFragment;
import fream.com.example.my.musicplayer.fragment.MyFragment;
import fream.com.example.my.musicplayer.navationviewMenu.SettingMenu;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.main_slidingPanellayout)
    SlidingPaneLayout mSlidingPanelLayout;

    @Bind(R.id.main_sliding_left)
    NavigationView navigetionView;

    @Bind(R.id.main_tablayout)
    TabLayout mTablayout;

    @Bind(R.id.main_viewpager)
    ViewPager mViewPager;
    @Bind(R.id.bottom_music_playing)
    LinearLayout mLinearLayout;

    private boolean isOpened = true;
    private Toolbar mToolBar;
    private MaterialMenuDrawable materialMenuDrawable;
    private List<String> listtitle = new ArrayList<>();
    private List<Fragment> mainFragment = new ArrayList<>();
    private ViewPagerAdapter adapter;
    private int lastX;
    private int lastY;
    private int dx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_pager);
        //设置在onCreatentView的下面；
        ButterKnife.bind(this);
        //设置ToolBar
        mToolBar = (Toolbar) findViewById(R.id.main_sliding_right_toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置侧边的面板打开和关闭时的ToolBar的动画
        initToolBar();
        //设置mSlidingpanellayout的监听
        initSlidingpanellayout();
        //设置导航栏
        initNavigationView();
        //设置viewpager的相关事件
        initViewPager();
        //设置NavigationView的Menu视图
        initMenuListener();
        //设置mLinearLayout的监听
        initLinearLayout();
    }

    private void initLinearLayout() {
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,PlayingPager.class);
                startActivity(intent);
            }
        });
    }

    private void initMenuListener() {
        navigetionView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sliding_setting:
                        Intent intentsetting = new Intent(MainActivity.this, SettingMenu.class);
                        startActivity(intentsetting);
                        break;
                    case R.id.search_song:
                        Intent intentsearch = new Intent(MainActivity.this, SettingMenu.class);
                        startActivity(intentsearch);
                        break;
                    case R.id.exit_mode:
                        System.exit(-1);
                        break;
                }
                return true;
            }
        });
    }


    private void initViewPager() {

        //向标题的list集合中添加元素
        listtitle.add("我的");
        listtitle.add("发现");
        listtitle.add("推荐");

        //向Fragment的集合中添加元素
        mainFragment.add(new MyFragment());
        mainFragment.add(new FindFragment());
        mainFragment.add(new GuideFragment());

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), listtitle, mainFragment);
        mViewPager.setAdapter(adapter);
        mTablayout.setTabMode(TabLayout.MODE_FIXED);
        //将TabLayout和viewpager关联
        mTablayout.setupWithViewPager(mViewPager);
        mTablayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));
        mTablayout.setSelectedTabIndicatorHeight(5);
    }


    private void initToolBar() {
        materialMenuDrawable = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.REGULAR);
        mToolBar.setNavigationIcon(materialMenuDrawable);

        //设置ToolBar的按钮点击事件
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isOpened) {
                    mSlidingPanelLayout.closePane();
                } else {
                    mSlidingPanelLayout.openPane();
                }
            }
        });
    }

    private void initNavigationView() {
        View navagationHeadview = LayoutInflater.from(this).inflate(R.layout.navigation_header_view, null);
        navigetionView.addHeaderView(navagationHeadview);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mTablayout.setupWithViewPager(mViewPager);
            if (mViewPager.getCurrentItem()==0) {
                switch (ev.getAction()) {
                    case MotionEvent.ACTION_SCROLL:
                        lastX = (int) ev.getX();
                        lastY = (int) ev.getY();
                        dx = (int) ev.getX() - lastX;
                        if (dx < 0) {
                            mSlidingPanelLayout.openPane();
                            return super.dispatchTouchEvent(ev);
                        } else {

                            return super.dispatchTouchEvent(ev);
                        }
                }
            }
        return super.dispatchTouchEvent(ev);
    }

    private void initSlidingpanellayout() {
        //设置SlidingPanelLayout的监听
        mSlidingPanelLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                //设置打开时的动画
                materialMenuDrawable.setTransformationOffset(
                        MaterialMenuDrawable.AnimationState.BURGER_ARROW,
                        isOpened ? 2 - slideOffset : slideOffset
                );
            }

            @Override
            public void onPanelOpened(View panel) {
                isOpened = true;
            }

            @Override
            public void onPanelClosed(View panel) {
                isOpened = false;
            }
        });
    }

    //设置viewpager的FragmentPagerAdapter事件
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<String> title = new ArrayList<>();
        private List<Fragment> listFragment = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm, List<String> title, List<Fragment> listFragment) {
            super(fm);
            this.title = title;
            this.listFragment = listFragment;
        }
        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        @Override
        public int getCount() {
            return listFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }
    }
}
