package com.example.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    EditText edit_username, editPassword, editCpassword;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

    }
    void init(){
        edit_username = findViewById(R.id.edit_R_Username);
        editPassword = findViewById(R.id.edit_R_Password);
        editCpassword = findViewById(R.id.edit_R_CPassword);
        btn_register = findViewById(R.id.btn_register);
    }

}
