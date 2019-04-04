package com.example.myapplication;
import com.example.myapplication.clsDataName;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditContactsActivity extends AppCompatActivity {
    TextView mName;
    TextView mPhone;
    TextView mDia_chi;
    TextView mCity;
    int ID;
    Button btn_edit, btn_update, btn_cancel;
    clsDataName clsDataName = new clsDataName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contacts);
        init();
        //bắt lấy inten được gửi
        final Intent intent = getIntent();
        mName.setText(intent.getStringExtra(clsDataName.array[0]));
        mPhone.setText(intent.getStringExtra(clsDataName.array[1]));
        mDia_chi.setText(intent.getStringExtra(clsDataName.array[2]));
        mCity.setText(intent.getStringExtra(clsDataName.array[3]));
        ID = intent.getIntExtra("ID",0);
        //cho phép chỉnh sửa
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllowEdit();
                btn_edit.setEnabled(false);
            }
        });
        //cập nhập nội dung đã chỉnh sữa
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DontEdit();
                MainActivity.user_contacts.set(intent.getIntExtra("index",0),new User_contacts(ID, mName.getText().toString(),
                        mPhone.getText().toString(),mDia_chi.getText().toString(),mCity.getText().toString()));
                btn_edit.setEnabled(true);
                MainActivity.adapter.notifyDataSetChanged();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    //ánh xạ qua layout
    void init(){
        mName = findViewById(R.id.edit_txt_name);
        mPhone = findViewById(R.id.edit_txt_sdt);
        mDia_chi = findViewById(R.id.edit_txt_diaChi);
        mCity = findViewById(R.id.edit_txt_city);
        btn_edit = findViewById(R.id.edit_btnedit);
        btn_update = findViewById(R.id.edit_btnUpdate);
        btn_cancel = findViewById(R.id.edit_btnCancel);
    }
    void AllowEdit(){
        mName.setEnabled(true);
        mPhone.setEnabled(true);
        mDia_chi.setEnabled(true);
        mCity.setEnabled(true);
    }
    void DontEdit(){
        mName.setEnabled(false);
        mPhone.setEnabled(false);
        mDia_chi.setEnabled(false);
        mCity.setEnabled(false);
    }
}
