package com.example.dokimdangkhoa_1706020040.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.dokimdangkhoa_1706020040.AddBookActivity;
import com.example.dokimdangkhoa_1706020040.Module.Book;
import com.example.dokimdangkhoa_1706020040.Module.CustomAdapter;
import com.example.dokimdangkhoa_1706020040.Module.SQLControl;
import com.example.dokimdangkhoa_1706020040.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DanhSach extends Fragment {
    ListView listView;
    FloatingActionButton btnAdd;
    SearchView searchView;
    SQLControl sqlControl;
    CustomAdapter adapter;
    List<Book> bookList = new ArrayList<>();

    public DanhSach() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_danh_sach, container, false);
        listView  = view.findViewById(R.id.danhsach_listview);
//        // searchView = view.findViewById(R.id.danhsach_search);
        btnAdd = view.findViewById(R.id.btn_Add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddBookActivity.class));
            }
        });
        sqlControl = new SQLControl(getContext());
        bookList =sqlControl.getAll();
        if (bookList.isEmpty()){
            AddDataFirstRun();
            bookList = sqlControl.getAll();
        }
        adapter = new CustomAdapter(getContext(), R.layout.listview_custom, bookList);
        listView.setAdapter(adapter);
        return view;
    }

    void AddDataFirstRun(){
        sqlControl.Insert("mb123", "Mắt Biết", "Nguyễn Nhật Ánh", "NXB Trẻ", 10,"A");
        sqlControl.Insert("mb321", "Tony Buổi Sáng - Trên Đường Băng", "Tony Buổi Sáng", "NXB Trẻ", 5,"A");
        sqlControl.Insert("mb432", "Từ Tốt Đến Vĩ Đại", "Jim Collins", "NXB Trẻ", 10,"A");
        sqlControl.Insert("mb666", "D.Trump. Nghệ Thuật Đàm Phán", "Donald J.Trump & Tony Schwartz", "NXB Trẻ", 2,"A");
        sqlControl.Insert("mbiu", "Có Hai Con Mèo Ngồi Bên Cửa Sổ", "Nguyễn Nhật Ánh", "NXB Trẻ", 1,"A");
    }
}
