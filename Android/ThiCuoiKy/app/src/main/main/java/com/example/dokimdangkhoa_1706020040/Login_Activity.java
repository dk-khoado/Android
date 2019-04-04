package com.example.dokimdangkhoa_1706020040;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity {
    Button btnLogin;
    EditText userName;
    EditText password;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        init();
        CheckLogged();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valuable())
                {
                    if (userName.getText().toString().equals("root") && password.getText().toString().equals("admin"))
                    {
                        Toast.makeText(Login_Activity.this, "Susses", Toast.LENGTH_SHORT).show();
                        editor.putBoolean("login",true);
                        editor.commit();
                        startActivity(new Intent(Login_Activity.this, MainActivity.class));
                    }
                }
            }
        });
    }
    void init()
    {
        btnLogin = findViewById(R.id.btn_login);
        userName = findViewById(R.id.edit_login_usermane);
        password = findViewById(R.id.edit_login_password);
        sharedPreferences = getSharedPreferences("save", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    boolean valuable()
    {
        if (userName.getText().toString().trim().equals(""))
        {
            userName.setError("Không được bỏ Trống");
            return false;
        }else if(password.getText().toString().equals(""))
        {
            password.setError("Không được bỏ Trống");
            return false;
        }
        return true;
    }
    void CheckLogged(){

        if (sharedPreferences.getBoolean("login",false))
        {
            startActivity(new Intent(Login_Activity.this, MainActivity.class));
            finish();
        }
    }
}
