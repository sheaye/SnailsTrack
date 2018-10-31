package com.snail.track.samples.custom_view.path;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.snail.track.R;
import com.snail.track.samples.custom_view.path.basic.BasicPathActivity;
import com.snail.track.samples.custom_view.path.basic.BasicPathTestView;
import com.snail.track.samples.custom_view.path.bezier.BezierActivity;
import com.snail.track.samples.custom_view.path.bool.PathBoolActivity;
import com.snail.track.samples.custom_view.path.fill.FillPathActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PathTestActivity extends AppCompatActivity {

    @BindView(R.id.path_test_view)
    BasicPathTestView mBasicPathTestView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.path_operations);
        setContentView(R.layout.activity_path_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.path_basic, R.id.path_bezier, R.id.path_fill, R.id.path_bool})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.path_basic:
                startActivity(new Intent(this, BasicPathActivity.class));
                break;
            case R.id.path_bezier:
                startActivity(new Intent(this, BezierActivity.class));
                break;
            case R.id.path_fill:
                startActivity(new Intent(this, FillPathActivity.class));
                break;
            case R.id.path_bool:
                startActivity(new Intent(this, PathBoolActivity.class));
                break;
        }
    }

}
