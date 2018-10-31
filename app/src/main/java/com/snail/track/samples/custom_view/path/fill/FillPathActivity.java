package com.snail.track.samples.custom_view.path.fill;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.snail.track.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FillPathActivity extends AppCompatActivity {


    @BindView(R.id.path_fill_test_view)
    FillPathTestView mPathFillTestView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Path填充");
        setContentView(R.layout.activity_fill_path);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.testFillEvenOdd,R.id.testInverseFillEvenOdd, R.id.testFillWinding, R.id.testFillWinding2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.testFillEvenOdd:
                mPathFillTestView.testFillEvenOdd();
                break;
            case R.id.testInverseFillEvenOdd:
                mPathFillTestView.testFillInverseEvenOdd();
                break;
            case R.id.testFillWinding:
                mPathFillTestView.testFillWinding();
                break;
            case R.id.testFillWinding2:
                mPathFillTestView.testFillWinding2();
                break;

        }
    }
}
