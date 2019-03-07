package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main_addContacts extends AppCompatActivity {
    TextView mName;
    TextView mPhone;
    TextView mDia_chi;
    TextView mCity;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_contacts);
        init();
        Intent intent = getIntent();
        mName.setText(intent.getStringExtra("name"));
        mPhone.setText(intent.getStringExtra("phone"));
        mDia_chi.setText(intent.getStringExtra("diachi"));
        mCity.setText(intent.getStringExtra("city"));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_addContacts.this, MainActivity.class));
            }
        });
    }
    void init(){
        mName= findViewById(R.id.txt_name);
        mPhone= findViewById(R.id.txt_sdt);
        mDia_chi= findViewById(R.id.txt_diaChi);
        mCity= findViewById(R.id.txt_city);
        btnBack = findViewById(R.id.btn_back);

    }
}
