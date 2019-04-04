package com.example.thicuoiky.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.thicuoiky.AddPhieuMuonActivity;
import com.example.thicuoiky.HistoryActivity;
import com.example.thicuoiky.models.Member;
import com.example.thicuoiky.Module.Adapter.PersonLentAdapter;
import com.example.thicuoiky.Module.SQLControl;
import com.example.thicuoiky.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhieuMuongFragment extends Fragment {
    @SuppressLint("StaticFieldLeak")
    private static PersonLentAdapter adapter;
    ListView listView;
    Button btnAdd;
    Button btnHistory;
    private static SQLControl sqlControl;
    private static List<Member> memberList = new ArrayList<>();
    private static List<Member> memberListForAdapter = new ArrayList<>();
    public PhieuMuongFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        Update();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_phieu_muong, container, false);
        listView = view.findViewById(R.id.phieuMuon_listView);
        btnAdd = view.findViewById(R.id.phieuMuon_Add);
        btnHistory =view.findViewById(R.id.phieuMuon_update);
        sqlControl = new SQLControl(getContext());
        memberList = sqlControl.getAllMember();
        for (int i = 0; i < memberList.size(); i++){
            if (!memberList.get(i).isStatus()){
                memberListForAdapter.add(memberList.get(i));
            }
        }
        adapter = new PersonLentAdapter(getContext(),R.layout.listview_custom_phieu_muong, memberListForAdapter);
        listView.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddPhieuMuonActivity.class));
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HistoryActivity.class));
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    public static void Update(){
        memberList.clear();
        memberList.addAll(sqlControl.getAllMember());
        memberListForAdapter.clear();
        for (int i = 0; i < memberList.size(); i++){
            if (!memberList.get(i).isStatus()){
                memberListForAdapter.add(memberList.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }
}
