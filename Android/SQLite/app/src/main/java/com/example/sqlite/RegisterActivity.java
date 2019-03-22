package com.example.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    EditText edit_username, editPassword, editCpassword;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        edit_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!MainActivity.helper.CheckNameExsit(edit_username.getText().toString())) {
                    edit_username.setError("Tên đã tồn tại");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valuable()) {

                    if (editPassword.getText().toString().equals(editCpassword.getText().toString())) {
                        MainActivity.helper.Insert(edit_username.getText().toString(), editPassword.getText().toString());
                        finish();
                    } else {
                        editCpassword.setError("Nhập lại éo đúng???");
                    }
                }
            }

        });
    }

    void init() {
        edit_username = findViewById(R.id.edit_R_Username);
        editPassword = findViewById(R.id.edit_R_Password);
        editCpassword = findViewById(R.id.edit_R_CPassword);
        btn_register = findViewById(R.id.btn_register);
    }

    Boolean valuable() {
        if (edit_username.getText().toString().equals("")) {
            edit_username.setError("Null");
            return false;
        } else if (editPassword.getText().toString().equals("")) {
            editPassword.setError("Null");
            return false;
        } else if (editCpassword.getText().toString().equals("")) {
            editCpassword.setError("Null");
            return false;
        }
        return true;
    }
}
