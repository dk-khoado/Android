package com.example.dokimdangkhoa_1706020040;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.dokimdangkhoa_1706020040.Module.FragmentAdapter;
import com.example.dokimdangkhoa_1706020040.Module.SQLControl;
import com.example.dokimdangkhoa_1706020040.fragment.DanhSach;
import com.example.dokimdangkhoa_1706020040.fragment.PhieuMuongFragment;

public class MainActivity extends AppCompatActivity {
    Button btnLogout;
    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentAdapter adapter;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.Add("Sách", new DanhSach());
        adapter.Add("Danh Sách Mượng", new PhieuMuongFragment());
        viewPager.setAdapter(adapter);
    }

    void init(){
        tabLayout =findViewById(R.id.main_tabLayout);
        viewPager =findViewById(R.id.main_viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }
    void Logout(){
       // btnLogout = findViewById(R.id.main_btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("save",Context.MODE_PRIVATE).edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(MainActivity.this, Login_Activity.class));
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout,menu);
        return true;
    }
}
