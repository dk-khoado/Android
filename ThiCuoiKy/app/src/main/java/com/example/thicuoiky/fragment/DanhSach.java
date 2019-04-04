package com.example.thicuoiky.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.thicuoiky.AddBookActivity;
import com.example.thicuoiky.models.Book;
import com.example.thicuoiky.Module.Adapter.CustomAdapter;
import com.example.thicuoiky.Module.SQLControl;
import com.example.thicuoiky.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DanhSach extends Fragment implements SearchView.OnQueryTextListener{
    ListView listView;
    Button btnAdd;
    SearchView searchView;
    SQLControl sqlControl;
    CustomAdapter adapter;
    List<Book> bookList = new ArrayList<>();

    public DanhSach() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        bookList.clear();
        bookList.addAll(sqlControl.getAllBook());
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_danh_sach, container, false);
        listView = view.findViewById(R.id.danhsach_listview);

        searchView = view.findViewById(R.id.danhsach_search);
        searchView.setOnQueryTextListener(this);
        btnAdd = view.findViewById(R.id.danhsach_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddBookActivity.class));
            }
        });
        sqlControl = new SQLControl(getContext());
        bookList = sqlControl.getAllBook();
        if (bookList.isEmpty()) {
            AddDataFirstRun();
            bookList = sqlControl.getAllBook();
        }
        adapter = new CustomAdapter(getContext(), R.layout.listview_custom, bookList);
        listView.setAdapter(adapter);
        return view;
    }

    void AddDataFirstRun() {
        sqlControl.Insert("ABC123", "Mắt Biết", "Nguyễn Nhật Ánh", "NXB Trẻ", 10, "A");
        sqlControl.Insert("XYZ321", "Tony Buổi Sáng - Trên Đường Băng", "Tony Buổi Sáng", "NXB Trẻ", 5, "A");
        sqlControl.Insert("MMN432", "Từ Tốt Đến Vĩ Đại", "Jim Collins", "NXB Trẻ", 10, "A");
        sqlControl.Insert("WBH666", "D.Trump. Nghệ Thuật Đàm Phán", "Donald J.Trump & Tony Schwartz", "NXB Trẻ", 2, "A");
        sqlControl.Insert("KHO123", "Có Hai Con Mèo Ngồi Bên Cửa Sổ", "Nguyễn Nhật Ánh", "NXB Trẻ", 1, "A");
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!newText.equals("")){
            bookList.clear();
            bookList.addAll(sqlControl.SearchBook(newText));
            adapter.notifyDataSetChanged();
        }else
        {
            bookList.clear();
            bookList.addAll(sqlControl.getAllBook());
            adapter.notifyDataSetChanged();
        }
        return false;
    }
}
