package com.example.thicuoiky;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.CircularBorderDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.thicuoiky.models.Book;
import com.example.thicuoiky.Module.Adapter.FragmentAdapter;
import com.example.thicuoiky.models.Member;
import com.example.thicuoiky.Module.SQLControl;
import com.example.thicuoiky.models.order_model;
import com.example.thicuoiky.fragment.AddPhieu_order_Fragment;
import com.example.thicuoiky.fragment.Addphieu_list_Fragment;

import java.lang.Object;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

public class AddPhieuMuonActivity extends AppCompatActivity {
    SQLControl sqlControl;
    Button btnAdd;
    Button btnBack;
    Button btnPickDate;
    TabLayout tabLayout;
    ViewPager viewPager;

    FragmentAdapter fragmentAdapter;
    ArrayAdapter<String> arrayAdapter;

    EditText editTen;
    EditText editNgay;
    public static EditText soLuong;

    DatePickerDialog datePickerDialog;
    DatePickerDialog.OnDateSetListener mListener;
    ImageButton btn_SoLuong;
    List<order_model> order_modelList = new ArrayList<>();
    List<Book> books = new ArrayList<>();
    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phieu_muon);
        init();
        sqlControl = new SQLControl(this);
        books = sqlControl.getAllBook();
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.Add("Sách", new Addphieu_list_Fragment());
        fragmentAdapter.Add("Order", new AddPhieu_order_Fragment());
        viewPager.setAdapter(fragmentAdapter);

        //quay lại trang chính
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i= 0; i < books.size(); i++) {
                    sqlControl.UpdateSoluongBook(books.get(i).getID(), books.get(i).getSoLuongOrgin());
                    finish();
                }
            }
        });
        //chọn ngày
        Date date = Calendar.getInstance().getTime();
        editNgay.setText(new SimpleDateFormat("dd/MM/yyyy").format(date));
        btnPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(AddPhieuMuonActivity.this, android.R.style.Theme_DeviceDefault_Dialog, mListener, year, month, day);
                datePickerDialog.show();
            }
        });
        mListener = new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth+"/"+month+"/"+year;
                editNgay.setText(date);
            }
        };
        soLuong.setText(String.valueOf(AddPhieu_order_Fragment.order_modelList.size()));
        //lưu lai danh sách đã chọn
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Valueable()) {
                    order_modelList.clear();
                    order_modelList.addAll(AddPhieu_order_Fragment.order_modelList);
                    soLuong.setText(String.valueOf(AddPhieu_order_Fragment.order_modelList.size()));
                    if (order_modelList.size() > 0) {
                        sqlControl.Insert(editTen.getText().toString(), Integer.parseInt(soLuong.getText().toString()), editNgay.getText().toString());
                        Member item = sqlControl.getLastMember();
                        for (int i = 0; i < order_modelList.size(); i++) {
                            sqlControl.Insert(item.getID(), order_modelList.get(i).getMaSach());
                        }
                        Toast.makeText(AddPhieuMuonActivity.this, "Đã Lưu", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddPhieuMuonActivity.this, MainActivity.class));
                        finish();
                    } else {
                        soLuong.setError("");
                    }
                }
            }
        });
    }

    void init() {
        tabLayout = findViewById(R.id.AddPhieuMuon_tablayout);
        viewPager = findViewById(R.id.AddPhieuMuon_viewPager);
        tabLayout.setupWithViewPager(viewPager);
        btnBack = findViewById(R.id.AddPhieuMuon_btnback);
        btnAdd = findViewById(R.id.AddPhieuMuon_btnSave);
        editTen = findViewById(R.id.AddPhieuMuon_Ten);
        editNgay = findViewById(R.id.AddPhieuMuon_date);
        soLuong = findViewById(R.id.AddPhieuMuon_soluong);
        btnPickDate = findViewById(R.id.AddPhieuMuon_pickDate);

    }

    boolean Valueable() {
        if (editTen.getText().toString().trim().equals("")) {
            editTen.setError("Không Được Bỏ Trống");
            return false;
        } else if (editNgay.getText().toString().trim().equals("")) {
            editNgay.setError("Không Được Bỏ Trống");
            return false;
        }
        return true;
    }
}
