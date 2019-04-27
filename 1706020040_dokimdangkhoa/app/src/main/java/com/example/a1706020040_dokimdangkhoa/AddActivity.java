package com.example.a1706020040_dokimdangkhoa;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a1706020040_dokimdangkhoa.AsyncTask.DataAsyncTask;
import com.example.a1706020040_dokimdangkhoa.Interface.IviewDta;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
    EditText name;
    EditText code;
    EditText number;
    EditText des;
    Button btnAdd;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> mMap = new HashMap<>();
                mMap.put("name", name.getText().toString());
                mMap.put("number", number.getText().toString());
                mMap.put("code", code.getText().toString());
                mMap.put("description",des.getText().toString());
                mMap.put("user_id",String.valueOf(MainActivity.id));
                new DataAsyncTask(mMap, new IviewDta() {
                    @Override
                    public void getData(String s, JSONArray data) {

                    }

                    @Override
                    public void Error(String s) {

                    }
                }).execute("http://www.vidophp.tk/api/account/dataaction?context=insert");
                finish();
            }
        });
    }
    void init(){
        name = findViewById(R.id.a_subject_name);
        code = findViewById(R.id.a_subject_code);
        number = findViewById(R.id.a_credits);
        des = findViewById(R.id.a_description);
        btnAdd = findViewById(R.id.a_Add);
        btnBack = findViewById(R.id.a_back);
    }
}
