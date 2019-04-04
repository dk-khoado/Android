package com.example.thicuoiky.Module.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.thicuoiky.models.Book;
import com.example.thicuoiky.R;

import java.util.List;

public class detailListviewAdapter extends ArrayAdapter<Book> {
    private List<Book> bookList;
    private Context context;
    private int resouce;
    public detailListviewAdapter(Context context, int resource, List<Book> objects) {
        super(context, resource, objects);
        this.bookList = objects;
        this.context = context;
        this.resouce = resource;
    }
    class ViewHolder{
        TextView maSach;
        TextView tenSach;
        TextView tacGia;
        TextView NXB;
        TextView theLoai;

    }
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder viewHolder = new ViewHolder();
        if (rowView == null)
        {
            rowView = LayoutInflater.from(context).inflate(resouce,parent,false);
            viewHolder.tenSach = (TextView) rowView.findViewById(R.id.detail_lv_tenSach);
            viewHolder.tacGia = (TextView) rowView.findViewById(R.id.detail_lv_tenTG);
            viewHolder.NXB = (TextView) rowView.findViewById(R.id.detail_lv_NXB);
            viewHolder.theLoai = (TextView) rowView.findViewById(R.id.detail_lv_type);
            viewHolder.maSach= rowView.findViewById(R.id.detail_lv_maSach);

            rowView.setTag(viewHolder);
        }else
        {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        Book item = getItem(position);
        viewHolder.tenSach.setText(item.getTenSach());
        viewHolder.tacGia.setText(item.getTenTG());
        viewHolder.NXB.setText(item.getNXB());
        viewHolder.theLoai.setText(item.getLoaiSach());
        viewHolder.maSach.setText(item.getMaSach());
        return rowView;
    }
}