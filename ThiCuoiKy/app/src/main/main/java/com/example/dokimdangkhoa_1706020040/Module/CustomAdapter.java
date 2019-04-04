package com.example.dokimdangkhoa_1706020040.Module;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dokimdangkhoa_1706020040.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Book> {
    List<Book> bookList;
    Context context;
    int resouce;
    public CustomAdapter(Context context, int resource, List<Book> objects) {
        super(context, resource, objects);
        this.bookList = objects;
        this.context = context;
        this.resouce = resource;
    }
    class ViewHolder{
        TextView tenSach;
        TextView tacGia;
        TextView NXB;
        TextView theLoai;
        TextView soLuong;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder viewHolder = new ViewHolder();
        if (rowView == null)
        {
            rowView = LayoutInflater.from(context).inflate(resouce,parent,false);
            viewHolder.tenSach = (TextView) rowView.findViewById(R.id.custom_tenSach);
            viewHolder.tacGia = (TextView) rowView.findViewById(R.id.custom_TG);
            viewHolder.NXB = (TextView) rowView.findViewById(R.id.custom_NXB);
            viewHolder.theLoai = (TextView) rowView.findViewById(R.id.custom_Type);
            viewHolder.soLuong = (TextView) rowView.findViewById(R.id.custom_SoLuong);
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
        viewHolder.soLuong.setText(Integer.toString(item.getSoLuong()));
        return rowView;
    }
}
