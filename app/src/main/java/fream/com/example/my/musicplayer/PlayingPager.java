package fream.com.example.my.musicplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by my on 2016/3/19.
 */
public class PlayingPager extends AppCompatActivity {
    @Bind(R.id.back_to_last)
    ImageButton mImageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playing_pager);
        ButterKnife.bind(this);
        //设置mImageButton的监听
        initImageButton();
    }

    private void initImageButton() {
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
