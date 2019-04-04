package com.example.thicuoiky.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thicuoiky.Module.Adapter.order_PhieuMuonAdapter;
import com.example.thicuoiky.models.order_model;
import com.example.thicuoiky.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPhieu_order_Fragment extends Fragment {
    public static List<order_model> order_modelList = new ArrayList<>();
    public static order_PhieuMuonAdapter  adapter;
    ListView listView;
    TextView soLuong;
    public AddPhieu_order_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onStop() {
        super.onStop();
        order_modelList.clear();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_add_phieu_order_, container, false);
        // Inflate the layout for this fragment
        listView  = view.findViewById(R.id.AddPhieuMuon_f_listviewOrder);
        adapter = new order_PhieuMuonAdapter(getContext(), R.layout.listview_custom_addphieumuon_order, order_modelList);
        listView.setAdapter(adapter);
        return view;
    }
}
