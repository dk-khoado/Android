package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main_addContacts extends AppCompatActivity {
    TextView mName;
    TextView mPhone;
    TextView mDia_chi;
    TextView mCity;
    Button btnBack;
    String sName, sPhoneNu, sDiaChi, sCity;
    int ID=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_contacts);
        init();
//        Intent intent = getIntent();
//        mName.setText(intent.getStringExtra("name"));
//        mPhone.setText(intent.getStringExtra("phone"));
//        mDia_chi.setText(intent.getStringExtra("diachi"));
//        mCity.setText(intent.getStringExtra("city"));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidateForm()){
                    getValue();
                    if (MainActivity.user_contacts.size() > 0 ) {
                        ID = MainActivity.user_contacts.get(MainActivity.user_contacts.size() - 1).getID() + 1;
                        MainActivity.user_contacts.add(new User_contacts(ID,sName, sPhoneNu, sDiaChi, sCity));
                        MainActivity.adapter.notifyDataSetChanged();
                        finish();
                    }else {
                        ID = 0;
                        MainActivity.user_contacts.add(new User_contacts(ID,sName, sPhoneNu, sDiaChi, sCity));
                        MainActivity.adapter.notifyDataSetChanged();
                        finish();
                    }
                }
            }
        });
    }
    Boolean ValidateForm(){
        boolean check ;
        if (mName.getText().toString().equals("")){
            mName.setError("Null");
            check= false;
        }else if(mPhone.getText().toString().equals("")){
            mPhone.setError("Null");
            check= false;
        }else if(mDia_chi.getText().toString().equals("")){
            mDia_chi.setError("Null");
            check = false;
        }else if(mCity.getText().toString().equals("")){
            mCity.setError("Null");
            check = false;
        }else {
            check = true;
        }
        return check;
    }
    void getValue(){
        sName = mName.getText().toString();
        sPhoneNu = mPhone.getText().toString();
        sDiaChi = mDia_chi.getText().toString();
        sCity = mCity.getText().toString();
    }
    void init(){
        mName= findViewById(R.id.txt_name);
        mPhone= findViewById(R.id.txt_sdt);
        mDia_chi= findViewById(R.id.txt_diaChi);
        mCity= findViewById(R.id.txt_city);
        btnBack = findViewById(R.id.btn_back);

    }
}
