package com.example.thicuoiky;

import android.content.Intent;
import android.support.annotation.ArrayRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.thicuoiky.Module.RandomMaSach;
import com.example.thicuoiky.Module.SQLControl;

import java.util.ArrayList;
import java.util.List;

public class AddBookActivity extends AppCompatActivity {
    EditText tenSach, maSach, tenTG, tenNXB, soLuong;
    Spinner theLoai;
    Button btnAdd;
    Button btnCreateMaSach;
    SQLControl sqlControl;
    ArrayAdapter<CharSequence> arrayAdapter;
    RandomMaSach randomMaSach;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        init();
        sqlControl = new SQLControl(AddBookActivity.this);
        arrayAdapter = ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        theLoai.setAdapter(arrayAdapter);

        //thiết lập toolbar-->
        toolbar.setTitle("Thêm Sách");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //<--end
        btnCreateMaSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maSach.setText(randomMaSach.Create());
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Valueable()) {
                    sqlControl.Insert(maSach.getText().toString(), tenSach.getText().toString(), tenTG.getText().toString(),
                            tenNXB.getText().toString(), Integer.parseInt(soLuong.getText().toString()), theLoai.getSelectedItem().toString());
                    Toast.makeText(AddBookActivity.this, "Đã Thêm Sách Mới", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddBookActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }

    void init() {
        maSach = findViewById(R.id.add_maSach);
        tenSach = findViewById(R.id.add_tenSach);
        tenTG = findViewById(R.id.add_tenTG);
        tenNXB = findViewById(R.id.add_NXB);
        theLoai = findViewById(R.id.add_theLoai);
        soLuong = findViewById(R.id.add_soLuong);
        btnAdd = findViewById(R.id.add_btnAdd);
        btnCreateMaSach = findViewById(R.id.add_btnCeateMaSach);
        randomMaSach = new RandomMaSach(this);
        toolbar = findViewById(R.id.add_toolbar);
    }

    boolean isNull(EditText editText) {
        if (editText.getText().toString().trim().equals("")) {
            editText.setError("Không được bỏ trống");
            return true;
        }
        return false;
    }

    boolean Valueable() {
        if (isNull(maSach)) {
            return false;

        } else if (

                isNull(tenSach)) {
            return false;
        } else if (

                isNull(tenTG)) {
            return false;
        } else if (

                isNull(tenNXB)) {
            return false;
        } else if (

                isNull(soLuong)) {
            return false;
        }
        return true;
    }
}
