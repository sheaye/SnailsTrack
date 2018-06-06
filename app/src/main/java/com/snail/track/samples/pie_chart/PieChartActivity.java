package com.snail.track.samples.pie_chart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.snail.track.R;

public class PieChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.pie_chart);
        setContentView(R.layout.activity_pie_chart);
    }
}
