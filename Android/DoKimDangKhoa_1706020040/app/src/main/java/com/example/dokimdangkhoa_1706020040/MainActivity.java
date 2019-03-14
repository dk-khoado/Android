package com.example.dokimdangkhoa_1706020040;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<MonHoc> monHocList = new ArrayList<>();
    MonHocAdapter adapter;
    ListView listView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Add();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter  =new MonHocAdapter(this, R.layout.custon_layout, monHocList);
        listView.setAdapter(adapter);
    }
    void init(){
        listView = findViewById(R.id.ListView);
        imageView = findViewById(R.id.back);
    }
    void Add(){
        monHocList.add(new MonHoc("Android cơ bản", "ar123",2,"đo kim dang khoa",1706020040,"TIN HỌC ƯNG DUNG","CAO THANH PHÚ"));
        monHocList.add(new MonHoc("Anh Văn 2", "ar123",2,"đo kim dang khoa",1706020040,"TIN HỌC ƯNG DUNG","CAO THANH PHÚ"));
        monHocList.add(new MonHoc("Lập Trinh PHP cơ bản", "ar123",2,"đo kim dang khoa",1706020040,"TIN HỌC ƯNG DUNG","CAO THANH PHÚ"));
    }
}
