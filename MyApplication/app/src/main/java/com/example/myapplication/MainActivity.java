package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import  java.lang.Object;

//import com.example.myapplication.User_contacts;
public class MainActivity extends AppCompatActivity {
    static List<User_contacts> user_contacts = new ArrayList<>();
    static ContactsAdapter adapter;
    ListView listView;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        adapter = new ContactsAdapter(this, R.layout.custom_listliew, user_contacts);
        listView.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main_addContacts.class));
            }
        });
    }

    void init() {
        listView = findViewById(R.id.ListView);
        btn_add = findViewById(R.id.btn_addContacts);
    }
}
