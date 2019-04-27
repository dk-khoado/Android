package com.example.a1706020040_dokimdangkhoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a1706020040_dokimdangkhoa.AsyncTask.LoginAsyncTask;
import com.example.a1706020040_dokimdangkhoa.Interface.IviewLogin;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText editUsername;
    EditText editPassword;
    Button btnLogin;
    Map<String, String > mMap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.put("user_name", editUsername.getText().toString());
                mMap.put("password", editPassword.getText().toString());
                new LoginAsyncTask(mMap, new IviewLogin() {
                    @Override
                    public void onLogin(String s, int userID) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("id",userID);
                        Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }

                    @Override
                    public void onLoginFail(String s) {
                        Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                }).execute("http://www.vidophp.tk/api/account/signin");
            }
        });
    }
    void init(){
        editUsername = findViewById(R.id.login_username);
        editPassword = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.login_btnLogin);
    }
}
