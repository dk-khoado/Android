package com.example.sqlite;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    static CustomAdapter adapter;
    ListView listView;
    static List<Users> list = new ArrayList<>();
    Button btn_refesh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = findViewById(R.id.Listview);
        btn_refesh = findViewById(R.id.btn_refesh);
        list = LoginActivity.helper.getAllData();
        adapter = new CustomAdapter(HomeActivity.this, R.layout.listview_layout, list);
        listView.setAdapter(adapter);
        btn_refesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
    }
    public static void refresh(){
        list = LoginActivity.helper.getAllData();
        list.add(new Users(1,"a","v"));
        adapter.notifyDataSetChanged();

    }
}