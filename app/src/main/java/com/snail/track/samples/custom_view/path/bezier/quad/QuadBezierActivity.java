package com.snail.track.samples.custom_view.path.bezier.quad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.snail.track.R;

public class QuadBezierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.quad_bezier);
        setContentView(R.layout.activity_bezier_quad);
    }
}
