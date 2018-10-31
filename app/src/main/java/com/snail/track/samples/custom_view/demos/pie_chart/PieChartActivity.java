package com.snail.track.samples.custom_view.demos.pie_chart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.snail.track.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PieChartActivity extends AppCompatActivity {

    @BindView(R.id.pie_chart_view)
    PieChartView mPieChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.pie_chart);
        setContentView(R.layout.activity_pie_chart);
        ButterKnife.bind(this);
        ArrayList<PieData> list = new ArrayList<>();
        list.add(new PieData("赤",10));
        list.add(new PieData("橙",10));
        list.add(new PieData("黄",10));
        list.add(new PieData("绿",10));
        list.add(new PieData("青",10));
        list.add(new PieData("蓝",10));
        list.add(new PieData("紫",10));
        mPieChartView.setPieData(list);
    }
}
