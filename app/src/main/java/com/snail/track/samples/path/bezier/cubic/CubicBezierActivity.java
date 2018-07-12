package com.snail.track.samples.path.bezier.cubic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.snail.track.R;

public class CubicBezierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.cubic_bezier);
        setContentView(R.layout.activity_beaier_cubic);
    }
}
