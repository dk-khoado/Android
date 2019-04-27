package com.example.a1706020040_dokimdangkhoa;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a1706020040_dokimdangkhoa.AsyncTask.DataAsyncTask;
import com.example.a1706020040_dokimdangkhoa.Interface.IviewDta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    static RecycleAdapter adapter;
    static int id;
    static List<subjectModel> modelList = new ArrayList<>();
    static Map<String , String >mMap = new HashMap<>();

    Button btnAdd;

    @Override
    protected void onResume() {
        super.onResume();
        Update();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Intent intent = getIntent();
        id= intent.getIntExtra("id", 0);
        mMap.put("id", String.valueOf(id));
//        new DataAsyncTask(mMap, new IviewDta() {
//            @Override
//            public void getData(String s, JSONArray data) {
//                for (int i = 0; i <  data.length(); i++){
//                    try {
//                        JSONObject jsonObject = data.getJSONObject(i);
//                        subjectModel model = new subjectModel();
//                        model.setIdUser(id);
//                        model.setId(jsonObject.getInt("id"));
//                        model.setSubjectName(jsonObject.getString("subject_name"));
//                        model.setSubjectCode(jsonObject.getString("subject_code"));
//                        model.setCredits(jsonObject.getString("credits"));
//                        model.setDescription(jsonObject.getString("description"));
//                        modelList.add(model);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            @Override
//            public void Error(String s) {
//
//            }
//        }).execute("http://www.vidophp.tk/api/account/getdata");
        adapter = new RecycleAdapter(MainActivity.this,R.layout.recycle_layout, modelList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });
    }

    void init(){
        recyclerView = findViewById(R.id.main_recycler);
        btnAdd = findViewById(R.id.main_btnAdd);
    }

    public static void Update(){
        modelList.clear();
        new DataAsyncTask(mMap, new IviewDta() {
            @Override
            public void getData(String s, JSONArray data) {
                for (int i = 0; i <  data.length(); i++){
                    try {
                        JSONObject jsonObject = data.getJSONObject(i);
                        subjectModel model = new subjectModel();
                        model.setIdUser(id);
                        model.setId(jsonObject.getInt("id"));
                        model.setSubjectName(jsonObject.getString("subject_name"));
                        model.setSubjectCode(jsonObject.getString("subject_code"));
                        model.setCredits(jsonObject.getString("credits"));
                        model.setDescription(jsonObject.getString("description"));
                        modelList.add(model);
                        adapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void Error(String s) {

            }
        }).execute("http://www.vidophp.tk/api/account/getdata");
    }
}
