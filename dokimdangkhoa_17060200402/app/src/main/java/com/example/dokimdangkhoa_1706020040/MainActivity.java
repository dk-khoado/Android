package com.example.dokimdangkhoa_1706020040;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.dokimdangkhoa_1706020040.models.Product;
import com.example.dokimdangkhoa_1706020040.models.Subject;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RycAdapter adapter;
    static List<Subject> subjectList = new ArrayList<>();
    static List<Product> productList = new ArrayList<>();
    static DatabaseReference myChilRef;
    Button btn_sinOut;
    Button btn_add;
    Button btn_dialog_ok;
    Button btn_dialog_cancel;

    boolean isProduct = true;

    ProgressBar loadingData;
    Dialog exitDialog;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle("App cuối kỳ.... copyright by KHOA");
        toolbar.setTitleTextColor(R.color.background_);

        startService(new Intent(getBaseContext(), CleanOnkillApp.class));

        init();
        //phần lưu thong tin tai khoản
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("user");
        final DatabaseReference childUserRef = userRef.child(LoginActivity.IDgg);
//        childUserRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Object data = dataSnapshot.getValue();
//                if (data ==null){
//                    if (isProduct){
//                        childUserRef.setValue("product");
//                    }else{
//                        childUserRef.setValue("subject");
//                    }
//                }
//            }

//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        //end
        //==========================
        FirebaseDatabase myFire = FirebaseDatabase.getInstance();
        DatabaseReference myRef = myFire.getReference().child(LoginActivity.IDgg);
        myChilRef = myRef.child("AdvancedAndroidFinalTest");
        myChilRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                subjectList.clear();
                productList.clear();
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    loadingData.setVisibility(View.VISIBLE);
                    try
                    {
                        Subject subject = item.getValue(Subject.class);
                        Product product = item.getValue(Product.class);
                        String key = item.getKey();
                        assert subject != null;
                        if (subject.getSubject_code() != null) {
                            boolean dont = true;
                            isProduct = false;
                            for (Subject chil : subjectList) {
                                if (chil.getId() == subject.getId()) {
                                    dont = false;
                                    break;
                                }
                            }
                            if (dont) {
                                childUserRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Object data = dataSnapshot.getValue();
                                        if (data ==null){
                                                childUserRef.setValue("subject");
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                subject.setKeyParent(key);
                                subjectList.add(subject);
                            }
                        }
                        assert product != null;
                        if (product.getProduct_name() != null) {
                            boolean dont = true;
                            for (Product chil : productList) {
                                if (chil.getId() == product.getId()) {
                                    dont = false;
                                    break;
                                }
                            }
                            if (dont) {
                                childUserRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Object data = dataSnapshot.getValue();
                                        if (data ==null){
                                            childUserRef.setValue("product");
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                product.setKeyParent(key);
                                productList.add(product);
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
                loadingData.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                if (isProduct){
                    intent.putExtra("type","product");
                }else
                {
                    intent.putExtra("type","subject");
                }
                startActivity(intent);
            }
        });
        adapter = new RycAdapter(subjectList, productList, MainActivity.this, R.layout.ryc_customlayout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        btn_dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.mGoogleSignInClient.signOut();
                exitDialog.dismiss();
                finish();
                //startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        btn_dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitDialog.dismiss();
            }
        });
        exitDialog.setCanceledOnTouchOutside(false);
        btn_sinOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitDialog.show();
            }
        });
    }

    void init() {
        recyclerView = findViewById(R.id.list);
        btn_add = findViewById(R.id.main_btnAdd);
        loadingData = findViewById(R.id.indeterminateBar);

        exitDialog = new Dialog(this);
        exitDialog.setContentView(R.layout.dialog_layout);
        exitDialog.setTitle("Are you Sure ????");

        btn_dialog_ok = exitDialog.findViewById(R.id.dialog_btn_ok);
        btn_dialog_cancel = exitDialog.findViewById(R.id.dialog_btn_cancel);
        btn_sinOut = findViewById(R.id.main_btnSignOut);

    }

    @Override
    public void onBackPressed() {
        exitDialog.show();
    }
}
