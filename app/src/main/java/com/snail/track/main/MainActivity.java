package com.snail.track.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;

import com.google.gson.reflect.TypeToken;
import com.snail.track.R;
import com.snail.track.data.MainEntity;
import com.snail.track.utils.LocalJsonParser;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.home_page);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Type type = new TypeToken<List<MainEntity>>() {
        }.getType();
        List<MainEntity> mainEntities = LocalJsonParser.parseJsonToList(this, "main.json", type);
        MainAdapter mainAdapter = new MainAdapter(this,mainEntities);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mainAdapter);
    }

}
