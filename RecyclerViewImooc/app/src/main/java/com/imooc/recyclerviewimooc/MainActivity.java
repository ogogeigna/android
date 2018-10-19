package com.imooc.recyclerviewimooc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.imooc.recyclerviewimooc.R.id.id_recyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DemoAdapter mAdapter;

    int[] colors = {android.R.color.holo_blue_bright, android.R.color.holo_green_dark, android.R.color.holo_orange_light, android.R.color.holo_red_light};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.id_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                                                                LinearLayoutManager.VERTICAL,
                                                                false));

        // TODO: 将adapter设置进来
        mAdapter = new DemoAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        initData();
    }

    private void initData() {

        List<DataModel> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {

            int type = (int) ((Math.random() * 3) + 1);

            DataModel data = new DataModel();
            data.avatarColor = colors[type - 1];
            data.type = type;
            data.name = "name : " + i;
            data.contentColor = colors[(type + 1) % 3];

            list.add(data);

        }

        // TODO: 填充数据
        mAdapter.addList(list);

        // TODO: Notify any registered observers that the data set has changed.
        mAdapter.notifyDataSetChanged();

    }


}
