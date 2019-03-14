package com.example.dokimdangkhoa_1706020040;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
        TextView hoten;
        TextView nganh;
        TextView maSv;
        TextView tinChi;
        TextView tenMH;
        TextView maMH;
        TextView giangVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        Intent i = getIntent();
        hoten.setText(i.getStringExtra("ho ten"));
        nganh.setText(i.getStringExtra("nganh"));
        maSv.setText(String.valueOf(i.getIntExtra("ma SV",0)));
        tinChi.setText(String.valueOf(i.getIntExtra("so tin chi",0)));
        tenMH.setText(i.getStringExtra("ten mon hoc"));
        maMH.setText(i.getStringExtra("ma mon hoc"));
        giangVien.setText(i.getStringExtra("giang vien"));

    }
    void init(){
        hoten = findViewById(R.id.de_name);
        nganh = findViewById(R.id.de_nganh);
        maSv =findViewById(R.id.de_maSV);
        tinChi =findViewById(R.id.de_tinChi);
        tenMH = findViewById(R.id.de_teMH);
        giangVien = findViewById(R.id.de_giangVien);
        maMH = findViewById(R.id.de_maMH);
    }
}
