package com.example.thicuoiky;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thicuoiky.models.Detail;
import com.example.thicuoiky.Module.SQLControl;
import com.example.thicuoiky.Module.Adapter.detailListviewAdapter;
import com.example.thicuoiky.models.Member;

public class DetailActivity extends AppCompatActivity {
    detailListviewAdapter adapter;
    ListView listView;
    SQLControl sqlControl;
    Detail detail;
    Intent intent;
    TextView ten,ngayMuon, trangThai;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setTitle("Chi Tiết Phiếu Mươn");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
        listView =findViewById(R.id.detail_listview);
        sqlControl = new SQLControl(this);

        intent= getIntent();
        Member item =(Member) intent.getSerializableExtra("item");
        detail = sqlControl.getDetail(intent.getIntExtra("ID",0));
        adapter = new detailListviewAdapter(this, R.layout.listview_custom_detail, detail.getBookList());
        listView.setAdapter(adapter);
        //hiện tên thành viên
        ten.setText(detail.getName());
        ngayMuon.setText(detail.getNgayMuon());
        if (detail.isStatus()){
            trangThai.setText("Đã Trả Sách");
        }else
        {
            trangThai.setText("Chưa Trả Sách");
        }
    }
    void init(){
        ten = findViewById(R.id.detail_ten);
        ngayMuon = findViewById(R.id.detail_ngayMuon);
        trangThai = findViewById(R.id.detail_trangThai);
    }
}
