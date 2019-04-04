package com.example.thicuoiky.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.thicuoiky.Module.SQLControl;
import com.example.thicuoiky.Module.Adapter.lv_PhieuMuonAdapter;
import com.example.thicuoiky.models.lv_model;
import com.example.thicuoiky.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Addphieu_list_Fragment extends Fragment  implements SearchView.OnQueryTextListener{

    ListView listView;
    SearchView searchView;
    static SQLControl sqlControl;
    static lv_PhieuMuonAdapter adapter;
    static List<lv_model> lv_modelList;
    public Addphieu_list_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addphieu_list_, container, false);
        listView = view.findViewById(R.id.AddPhieuMuon_f_listview);
        searchView = view.findViewById(R.id.AddPhieuMuon_f_search);
        searchView.setOnQueryTextListener(this);
        sqlControl = new SQLControl(getContext());
        lv_modelList = sqlControl.getAllBookList();
        adapter = new lv_PhieuMuonAdapter(getContext(), R.layout.listview_custom_addphieumuon, lv_modelList);
        listView.setAdapter(adapter);
        return view;
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
            if (!newText.trim().equals("")){
                lv_modelList.clear();
                lv_modelList.addAll(sqlControl.SearchBookforOrder(newText));
                Addphieu_list_Fragment.adapter.notifyDataSetChanged();
            }else
            {
                lv_modelList.clear();
                lv_modelList.addAll( sqlControl.getAllBookList());
                adapter.notifyDataSetChanged();
            }

        return false;
    }
    public static void Update(){
        lv_modelList.clear();
        lv_modelList.addAll( sqlControl.getAllBookList());
        adapter.notifyDataSetChanged();
    }
}
