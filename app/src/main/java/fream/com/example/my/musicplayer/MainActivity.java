package fream.com.example.my.musicplayer;

import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fream.com.example.my.musicplayer.adapter.MyPagerAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private List<View> viewList;
    private ViewPager viewpager;
    private TextView textlocal;
    private TextView textonline;
    private ImageView imageunderline;
    private MyPagerAdapter adapter;
    private int offerset;
    private int one;
    private int curPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_pager);

        //初始化视图
        initview();

        //计算下划线的位置
        initUnderline();

        //初始viewpager视图,并将之添加到list中
        LayoutInflater inflater=LayoutInflater.from(this);
        initViewPager(inflater);

        //初始化监听
        initlistener();

    }

    private void initUnderline() {
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //屏幕宽度
        int screenwidth=dm.widthPixels;
        //图片宽度
        int bitmapwidth = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher).getWidth();
        //开始偏移量
        offerset=(screenwidth/4-bitmapwidth)/2;
        //第二个图片的
        one=offerset*3+bitmapwidth;
        //初始化imageunderline的位置
        imageunderline.setX(offerset);
    }

    private void initViewPager(LayoutInflater inflater) {
        viewList=new ArrayList<>();;
        viewList.add(inflater.inflate(R.layout.local_music, null));
        viewList.add(inflater.inflate(R.layout.online_music,null));
        adapter=new MyPagerAdapter(viewList);
        viewpager.setAdapter(adapter);
        //默认选中第一页
        viewpager.setCurrentItem(0);
    }
    private void initlistener() {
        textlocal.setOnClickListener(this);
        textonline.setOnClickListener(this);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                TranslateAnimation animation = null;
                switch (position) {
                    case 0:
                        if(curPos == 1) {
                            animation = new TranslateAnimation(one,0,0,0);
                        }else {
                            animation = new TranslateAnimation(0,one,0,0);
                        }
                        break;
                    case 1:
                        if(curPos == 0) {
                            animation = new TranslateAnimation(0,one,0,0);
                        }else {
                            animation = new TranslateAnimation(one,0,0,0);
                        }
                        break;
                }
                curPos = position;
                animation.setFillAfter(true);
                animation.setDuration(300);
                imageunderline.startAnimation(animation);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initview() {
        viewpager=(ViewPager)findViewById(R.id.vp_viewpager);
        imageunderline=(ImageView)findViewById(R.id.iv_underline);
        textlocal=(TextView)findViewById(R.id.tv_local_music);
        textonline=(TextView)findViewById(R.id.tv_online_music);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tv_local_music:
                viewpager.setCurrentItem(0);
                break;
            case R.id.tv_online_music:
                viewpager.setCurrentItem(1);
                break;
        }
    }
}
