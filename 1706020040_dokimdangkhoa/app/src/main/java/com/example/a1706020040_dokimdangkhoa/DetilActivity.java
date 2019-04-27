package com.example.a1706020040_dokimdangkhoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a1706020040_dokimdangkhoa.AsyncTask.DataAsyncTask;
import com.example.a1706020040_dokimdangkhoa.Interface.IviewDta;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DetilActivity extends AppCompatActivity {
    EditText edit_subjectName;
    EditText edit_subjectCode;
    EditText edit_credits;
    EditText edit_description;
    Button btn_Edit;
    Button btn_Update;
    Button btn_Back;
    Map<String, String>mMap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil);
        init();
        Intent intent = getIntent();
        final subjectModel item = (subjectModel) intent.getSerializableExtra("subject");
        edit_subjectName.setText(item.getSubjectName());
        edit_subjectCode.setText(item.getSubjectCode());
        edit_credits.setText(item.getCredits());
        edit_description.setText(item.getDescription());
        btn_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEdit();

            }
        });
        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdate();
                mMap.put("name", edit_subjectName.getText().toString());
                mMap.put("number", edit_credits.getText().toString());
                mMap.put("code", edit_subjectCode.getText().toString());
                mMap.put("description",edit_description.getText().toString());
                mMap.put("id",String.valueOf(item.getId()));
                mMap.put("user_id",String.valueOf(item.getIdUser()));

                new DataAsyncTask(mMap, new IviewDta() {
                    @Override
                    public void getData(String s, JSONArray data) {

                    }

                    @Override
                    public void Error(String s) {
                        Toast.makeText(DetilActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                }).execute("http://www.vidophp.tk/api/account/dataaction?context=update");
            }
        });

        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void init() {
        edit_subjectName = findViewById(R.id.d_subject_name);
        edit_subjectCode = findViewById(R.id.d_subject_code);
        edit_credits = findViewById(R.id.d_credits);
        edit_description = findViewById(R.id.d_description);
        btn_Edit = findViewById(R.id.d_edit);
        btn_Update = findViewById(R.id.d_update);
        btn_Back = findViewById(R.id.d_back);
    }
    void onEdit(){
        edit_description.setEnabled(true);
        edit_subjectName.setEnabled(true);
        edit_subjectCode.setEnabled(true);
        edit_credits.setEnabled(true);
        btn_Update.setEnabled(true);
    }
    void onUpdate(){
        edit_description.setEnabled(false);
        edit_subjectName.setEnabled(false);
        edit_subjectCode.setEnabled(false);
        edit_credits.setEnabled(false);

    }
}
