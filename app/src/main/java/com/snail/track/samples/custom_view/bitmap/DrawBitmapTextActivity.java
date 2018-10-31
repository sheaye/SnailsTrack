package com.snail.track.samples.custom_view.bitmap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.snail.track.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawBitmapTextActivity extends AppCompatActivity {

    @BindView(R.id.loading_view)
    LoadingView mLoadingView;
    @BindView(R.id.seek_bar)
    SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_bitmap_text);
        ButterKnife.bind(this);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mLoadingView.setProgress(progress * 1.0f / 100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
