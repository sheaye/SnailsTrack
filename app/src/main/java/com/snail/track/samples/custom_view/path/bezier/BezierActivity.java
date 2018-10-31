package com.snail.track.samples.custom_view.path.bezier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.snail.track.R;
import com.snail.track.samples.custom_view.path.bezier.heart.HeartBezierActivity;
import com.snail.track.samples.custom_view.path.bezier.jelly.JellyBezierActivity;
import com.snail.track.samples.custom_view.path.bezier.quad.QuadBezierActivity;
import com.snail.track.samples.custom_view.path.bezier.cubic.CubicBezierActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BezierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.bezier_path);
        setContentView(R.layout.activity_bezier);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.quad_bezier, R.id.cubic_bezier, R.id.heart_bezier, R.id.jelly_bezier})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.quad_bezier:
                startActivity(new Intent(this, QuadBezierActivity.class));
                break;
            case R.id.cubic_bezier:
                startActivity(new Intent(this, CubicBezierActivity.class));
                break;
            case R.id.heart_bezier:
                startActivity(new Intent(this, HeartBezierActivity.class));
                break;
            case R.id.jelly_bezier:
                startActivity(new Intent(this, JellyBezierActivity.class));
                break;
        }
    }
}
