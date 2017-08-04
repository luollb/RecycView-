package com.xincai.a803;

import android.app.Service;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerviewAdapter adapter;
    private RecyclerView rc;
    private List<String> list = new ArrayList<>();
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取系统震动服务
        final Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);

        for (int i = 'A'; i < 'Z'; i++) {
            list.add((char) i + "");
        }

        rc = (RecyclerView) findViewById(R.id.rv);
        adapter = new RecyclerviewAdapter(this, list);

        GridLayoutManager manager = new GridLayoutManager(this, 4);
        rc.setLayoutManager(manager);
        rc.setAdapter(adapter);
        mItemTouchHelper = new ItemTouchHelper(new MyItemTouchHelper(list, adapter));
        mItemTouchHelper.attachToRecyclerView(rc);
        rc.addItemDecoration(new MyDecoration(this, MyDecoration.HORIZONTAL_LIST));
        rc.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        adapter.notifyDataSetChanged();
        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, list.get(position).toString() + "", Toast.LENGTH_SHORT).show();

            }
        });

        adapter.setItemLongClickListener(new ItemLongClickListener() {
            @Override
            public void onItemLongClick(RecyclerView.ViewHolder view, int position) {
                vib.vibrate(200);
                mItemTouchHelper.startDrag(view);
            }
        });
    }
}
