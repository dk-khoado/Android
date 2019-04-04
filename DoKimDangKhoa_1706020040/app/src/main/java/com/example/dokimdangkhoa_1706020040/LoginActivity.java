package com.example.dokimdangkhoa_1706020040;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkNull()){
                    if (username.getText().toString().equals("root") && password.getText().toString().equals("admin")){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        Toast.makeText(LoginActivity.this, "Login susses", Toast.LENGTH_SHORT).show();

                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    void init(){
        username = findViewById(R.id.edit_user);
        password = findViewById(R.id.edit_password);
        login = findViewById(R.id.btnLogin);
    }
    boolean checkNull(){
        boolean check  =false;
        if (username.getText().toString().equals("")){
            username.setError("Null");

        }else if (password.getText().toString().equals("")){
            password.setError("Null");
        }else {
            check =true;
        }
        return check;
    }
}

