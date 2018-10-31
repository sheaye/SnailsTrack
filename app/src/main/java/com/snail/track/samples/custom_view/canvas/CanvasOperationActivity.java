package com.snail.track.samples.custom_view.canvas;

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
    @BindView(R.id.scale_x_seek_bar)
    SeekBar mScaleXSeekBar;
    @BindView(R.id.scale_x_text_view)
    TextView mScaleXTextView;
    @BindView(R.id.scale_y_seek_bar)
    SeekBar mScaleYSeekBar;
    @BindView(R.id.scale_y_text_view)
    TextView mScaleYTextView;
    @BindView(R.id.rotate_seek_bar)
    SeekBar mRotateSeekBar;
    @BindView(R.id.rotate_text_view)
    TextView mRotateTextView;
    @BindView(R.id.skew_x_seek_bar)
    SeekBar mSkewXSeekBar;
    @BindView(R.id.skew_x_text_view)
    TextView mSkewXTextView;
    @BindView(R.id.skew_y_seek_bar)
    SeekBar mSkewYSeekBar;
    @BindView(R.id.skew_y_text_view)
    TextView mSkewYTextView;
    private float mProgressX;
    private float mProgressY;
    private float mScaleY = 1.0f;
    private float mScaleX = 1.0f;
    private int mRotateAngle;
    private float mSkewX;
    private float mSkewY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.canvas_operations);
        setContentView(R.layout.activity_canvas_operation);
        ButterKnife.bind(this);
        initForTranslate();
        initForScale();
        initForRotate();
        initForSkew();
    }

    private void initForSkew() {
        mSkewXSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mSkewX = (progress - 100) * 1.0f / 100;
                mSkewXTextView.setText(String.valueOf(mSkewX));
                mCanvasView.skew(mSkewX, mSkewY);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSkewYSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mSkewY = (progress - 100) * 1.0f / 100;
                mSkewXTextView.setText(String.valueOf(mSkewX));
                mCanvasView.skew(mSkewX, mSkewY);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initForRotate() {
        mRotateSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mRotateAngle = progress - 360;
                mRotateTextView.setText(String.valueOf(mRotateAngle));
                mCanvasView.rotate(mRotateAngle);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initForScale() {

        mScaleXSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mScaleX = (progress - 200) * 1.0f / 100;
                mScaleXTextView.setText(String.valueOf(mScaleX));
                mCanvasView.scale(mScaleX, mScaleY);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mScaleYSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mScaleY = (progress - 200) * 1.0f / 100;
                mScaleYTextView.setText(String.valueOf(mScaleY));
                mCanvasView.scale(mScaleX, mScaleY);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initForTranslate() {
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
