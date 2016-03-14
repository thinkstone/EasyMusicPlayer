package fream.com.example.my.musicplayer.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by shiwei on 2016/3/13.
 */
public class MyPagerAdapter extends PagerAdapter {
    private List<View> viewlist;

    public MyPagerAdapter(List<View> viewlist){
        this.viewlist=viewlist;
    }
    @Override
    public int getCount() {
        return viewlist==null? 0:viewlist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view== (View) object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=null;
        if (viewlist!=null){
            view=viewlist.get(position);
            container.addView(viewlist.get(position));
        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (viewlist!=null){
            container.removeView(viewlist.get(position));
        }
    }
}
