package com.example.sqllogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText username;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        final DatabaseManager databaseManager = new DatabaseManager(this);
        databaseManager.Insert("admin", "123");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkNull()){
                    boolean check= databaseManager.CheckInfo(username.getText().toString(), password.getText().toString());
                    if (check){
                        Toast.makeText(MainActivity.this, "Đăng nhâp thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    /**
     * ánh xạ qua layout
     */
    void init(){
        login = findViewById(R.id.btn_login);
        username = findViewById(R.id.edi_userName);
        password = findViewById(R.id.edi_password);
    }
    //kiểm tra các trường nhập có trống
    boolean checkNull(){
        if (username.getText().toString().equals("")){
            username.setError("Null");
            return false;
        }else  if (password.getText().toString().equals("")){
            password.setError("Null");
            return false;
        }
        return true;
    }
}
