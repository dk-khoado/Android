package com.example.thicuoiky.Module.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.thicuoiky.EditBookActivity;
import com.example.thicuoiky.models.Book;
import com.example.thicuoiky.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Book> {
    private List<Book> bookList;
    private Context context;
    private int resouce;
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
        ImageButton btn_Add;
    }
    @SuppressLint("SetTextI18n")
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
            viewHolder.btn_Add = rowView.findViewById(R.id.custom_btn_Edit);
            rowView.setTag(viewHolder);
        }else
        {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        final Book item = getItem(position);
        viewHolder.tenSach.setText(item.getTenSach());
        viewHolder.tacGia.setText(item.getTenTG());
        viewHolder.NXB.setText(item.getNXB());
        viewHolder.theLoai.setText(item.getLoaiSach());
        viewHolder.soLuong.setText(Integer.toString(item.getSoLuong()));
        viewHolder.btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = context.getResources();
                String[] string = res.getStringArray(R.array.type);
                int id=0;
                Intent intent = new Intent(context, EditBookActivity.class);
                for(int i= 0; i < string.length; i++){
                    if (item.getLoaiSach().equals(string[i])){
                        id = i;
                        break;
                    }
                    id = string.length-1;
                }
                intent.putExtra("book",item);
                intent.putExtra("ID", id);
                context.startActivity(intent);
            }
        });
        return rowView;
    }
}
