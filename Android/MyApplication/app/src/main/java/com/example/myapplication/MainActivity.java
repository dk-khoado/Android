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

//import com.example.myapplication.User_contacts;
public class MainActivity extends AppCompatActivity {
    List<User_contacts> user_contacts = new ArrayList<>();
    ContactsAdapter adapter;
    ListView listView;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        AddContacts();
        adapter = new ContactsAdapter(this, R.layout.custom_listliew, user_contacts);
        listView.setAdapter(adapter);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main_addContacts.class));
            }
        });
    }
    void ShowInfo(){
        Intent intent = new Intent(this, Main_addContacts.class);
        startActivity(intent);
    }

    void init() {
        listView = findViewById(R.id.ListView);
        btn_add = findViewById(R.id.btn_addContacts);
    }

    void Refesh() {
        adapter.Refresh();
    }

    private void AddContacts() {
        user_contacts.add(new User_contacts("khoa", "1213","187", "hoc mon"));
        user_contacts.add(new User_contacts("tu", "3122","21a, ấp tân lập", "TP.HCM"));
        user_contacts.add(new User_contacts("lozzz duy", "54","Nguyễn thị tú", "TP.hcm"));
        user_contacts.add(new User_contacts("Tuan's", "65", "nhị tân","Tp.HCM"));
    }

}
