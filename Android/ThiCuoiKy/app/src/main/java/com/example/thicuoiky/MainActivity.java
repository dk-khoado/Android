package com.example.thicuoiky;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.thicuoiky.Module.Adapter.FragmentAdapter;
import com.example.thicuoiky.fragment.DanhSach;
import com.example.thicuoiky.fragment.PhieuMuongFragment;


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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.Add("Sách", new DanhSach());
        adapter.Add("Danh Phiếu Mượn", new PhieuMuongFragment());
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    void init(){
        tabLayout =findViewById(R.id.main_tabLayout);
        viewPager =findViewById(R.id.main_viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_logout) {
            SharedPreferences.Editor editor = getSharedPreferences("save",Context.MODE_PRIVATE).edit();
            editor.clear();
            editor.apply();
            startActivity(new Intent(MainActivity.this, Login_Activity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
