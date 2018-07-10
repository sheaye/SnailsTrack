package com.snail.track.samples.path;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.snail.track.R;

public class PathTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.path_operations);
        setContentView(R.layout.activity_path_test);
    }
}
