package com.example.sqlite;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sqlite.R;

import java.util.ArrayList;
import java.util.List;


public class MonhocFragment extends Fragment {
    ListView listView;
    static List<Users> list = new ArrayList<>();
    static CustomAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        onSetData();
        View view = inflater.inflate(R.layout.fragment_monhoc, container, false);
        listView = view.findViewById(R.id.fragmentListview);
        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter = new CustomAdapter(getContext(),R.layout.listview_layout,list);
        listView.setAdapter(adapter);
    }

    void onSetData(){
//        list.add(new Users(1,"khoa","123"));
//        list.add(new Users(2,"khoa","123"));
//        list.add(new Users(3,"khoa","123"));
//        list.add(new Users(4,"khoa","123"));
//        list.add(new Users(5,"khoa","123"));
        list = LoginActivity.helper.getAllData();


    }
}
