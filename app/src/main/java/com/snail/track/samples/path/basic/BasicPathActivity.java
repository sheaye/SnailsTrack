
package com.snail.track.samples.path.basic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.snail.track.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BasicPathActivity extends AppCompatActivity {

    @BindView(R.id.path_test_view)
    BasicPathTestView mBasicPathTestView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.path_operations);
        setContentView(R.layout.activity_basic_path);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.testBasic, R.id.testArcTo, R.id.testAddArc, R.id.testAddPath, R.id.testRPath})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.testBasic:
                mBasicPathTestView.testBasic();
                break;
            case R.id.testArcTo:
                mBasicPathTestView.testArcTo();
                break;
            case R.id.testAddArc:
                mBasicPathTestView.testAddArc();
                break;
            case R.id.testAddPath:
                mBasicPathTestView.testAddPath();
                break;
            case R.id.testRPath:
                mBasicPathTestView.testRPath();
                break;

        }
    }
}
