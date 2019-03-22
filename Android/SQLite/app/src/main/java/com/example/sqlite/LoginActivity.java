package com.example.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public static DatabaseHelper helper;
    Button btnLogin,btnRegister;
    EditText editUsername;
    EditText editPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        helper = new DatabaseHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Valiable()){
                    if (helper.CheckData(editUsername.getText().toString(), editPassword.getText().toString())){
                        Toast.makeText(LoginActivity.this, "Đăng Nhập thàng công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Đăng Nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }
    void init(){
        btnLogin = findViewById(R.id.btn_m_login);
        btnRegister = findViewById(R.id.btn_m_register);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
    }
    boolean Valiable(){
        if (editUsername.getText().toString().equals("")){
            editUsername.setError("Null");
            return false;
        }else  if(editPassword.getText().toString().equals("")){
            editPassword.setError("Null");
            return false;
        }else
        return true;
    }
}
