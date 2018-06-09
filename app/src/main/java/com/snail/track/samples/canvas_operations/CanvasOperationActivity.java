package com.snail.track.samples.canvas_operations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

import com.snail.track.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CanvasOperationActivity extends AppCompatActivity {


    @BindView(R.id.translate_x_seek_bar)
    SeekBar mTranslateXSeekBar;
    @BindView(R.id.value_x_text_view)
    TextView mValueXTextView;
    @BindView(R.id.translate_y_seek_bar)
    SeekBar mTranslateYSeekBar;
    @BindView(R.id.value_y_text_view)
    TextView mValueYTextView;
    @BindView(R.id.canvas_view)
    CanvasView mCanvasView;
    private float mProgressX;
    private float mProgressY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.canvas_operations);
        setContentView(R.layout.activity_canvas_operation);
        ButterKnife.bind(this);
        mTranslateXSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int p = progress - 100;
                mProgressX = p / 100f;
                mValueXTextView.setText(p + "%");
                mCanvasView.translate(mProgressX, mProgressY);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mTranslateYSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int p = progress - 100;
                mProgressY = p / 100f;
                mValueYTextView.setText(p + "%");
                mCanvasView.translate(mProgressX, mProgressY);
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
