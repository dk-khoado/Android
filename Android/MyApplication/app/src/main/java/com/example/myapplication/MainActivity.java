package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

//import com.example.myapplication.User_contacts;
public class MainActivity extends AppCompatActivity {
    List<User_contacts> user_contacts = new ArrayList<>();
    ContactsAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.ListView);
        AddContacts();
        adapter = new ContactsAdapter(this,R.layout.custom_listliew, user_contacts);
        listView.setAdapter(adapter);
        listView.setClickable(true);

    }
    private void AddContacts(){
       user_contacts.add(new User_contacts("khoa","1213"));
       user_contacts.add(new User_contacts("tu","3122"));
       user_contacts.add(new User_contacts("lozzz duy","54"));
       user_contacts.add(new User_contacts("Tuan's","65"));
    }
}
