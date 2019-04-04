package com.example.sqlite;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragmentActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    FramentAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        tabLayout = findViewById(R.id.Tablayout);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.setupWithViewPager(viewPager);

        adapter = new FramentAdapter(getSupportFragmentManager());

        adapter.addFragmaent(new DienthoaiFragment(),"A");
        adapter.addFragmaent(new MonhocFragment(),"B");
        viewPager.setAdapter(adapter);
    }
}
