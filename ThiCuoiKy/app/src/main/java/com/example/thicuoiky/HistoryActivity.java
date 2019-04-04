package com.example.thicuoiky;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.thicuoiky.models.Member;
import com.example.thicuoiky.Module.SQLControl;
import com.example.thicuoiky.fragment.history_listviewAdapter;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView listView;
    SQLControl sqlControl;
    List<Member> memberList;
    history_listviewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        init();
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle("Lịch Sử Mượn Sách");

        memberList =sqlControl.getAllMember();
        adapter = new history_listviewAdapter(this, R.layout.listview_custom_history, memberList);
        listView.setAdapter(adapter);
    }
    void init(){
        listView = findViewById(R.id.history_listview);
        toolbar = findViewById(R.id.history_toolbar);
        sqlControl = new SQLControl(this);
    }
}
