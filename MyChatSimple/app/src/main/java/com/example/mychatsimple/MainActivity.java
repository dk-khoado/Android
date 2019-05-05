package com.example.mychatsimple;

import android.app.NotificationManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btnSend;
    EditText editMessage;
    FirebaseDatabase database;
    ListView listView;
    List<userModel> dataMessage = new ArrayList<>();
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        init();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editMessage.getText().toString().trim().equals("")) {
                    SendMessage();
                    editMessage.setText("");
                } else {
                    editMessage.setError("Null");
                }
            }
        });
        DatabaseReference myRef = database.getReference("message");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //dataMessage.clear();
                if (dataMessage.isEmpty()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        userModel value = ds.getValue(userModel.class);
                        dataMessage.add(value);
                    }
                }else
                {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        userModel value = ds.getValue(userModel.class);
                        Boolean check = false;
                        for (int i = 0; i < dataMessage.size(); i ++){
                            if (value.getTextID().equals(dataMessage.get(i).getTextID())){
                                check = true;
                                break;
                            }
                        }
                        if (!check){
                            dataMessage.add(value);
                            if (value.getUserID() != LoginActivity.userID) {
                                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                                        .setContentTitle("Có tin nhắn kìa mấy thằng lồn")
                                        .setContentText(value.getUserName() + ": " + value.getText())
                                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                notificationManager.notify(1, builder.build());
                            }
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                listView.smoothScrollToPosition(adapter.getCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        adapter = new ListViewAdapter(this, R.layout.lisviewlayout, R.layout.custom1layout, dataMessage);
        listView.setAdapter(adapter);
    }

    void init() {
        btnSend = findViewById(R.id.btn_send);
        editMessage = findViewById(R.id.edit_message);
        listView = findViewById(R.id.listView);
    }

    void SendMessage() {
        DatabaseReference myRef_c = database.getReference("message");
        userModel data = new userModel();
        data.setUserEmail(LoginActivity.userEmail);
        data.setUserName(LoginActivity.userName);
        data.setUserID(LoginActivity.userID);
        data.setText(editMessage.getText().toString());
        DatabaseReference post = myRef_c.push();
        data.setTextID(post.getKey());
        post.setValue(data);
    }
}
