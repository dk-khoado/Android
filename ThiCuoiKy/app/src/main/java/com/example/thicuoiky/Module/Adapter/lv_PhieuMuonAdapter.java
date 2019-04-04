package com.example.thicuoiky.Module.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thicuoiky.AddPhieuMuonActivity;
import com.example.thicuoiky.Module.SQLControl;
import com.example.thicuoiky.models.lv_model;
import com.example.thicuoiky.models.order_model;
import com.example.thicuoiky.R;
import com.example.thicuoiky.fragment.AddPhieu_order_Fragment;

import java.util.List;

public class lv_PhieuMuonAdapter extends ArrayAdapter<lv_model> {
    public List<lv_model> bookList;
    private Context context;
    private int resouce;
    public lv_PhieuMuonAdapter(Context context, int resource, List<lv_model> objects) {
        super(context, resource, objects);
        this.bookList = objects;
        this.context = context;
        this.resouce = resource;
    }
    class ViewHolder{
        TextView maSach;
        TextView tenSach;
        TextView tacGia;
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
            viewHolder.maSach = (TextView) rowView.findViewById(R.id.custom_lv_add_maSach);
            viewHolder.tenSach = (TextView) rowView.findViewById(R.id.custom_lv_add_tenSach);
            viewHolder.tacGia = (TextView) rowView.findViewById(R.id.custom_lv_add_TG);
            viewHolder.soLuong = rowView.findViewById(R.id.custom_lv_add_soLuong);
            viewHolder.btn_Add = rowView.findViewById(R.id.custom_lv_add_btnAdd);
            rowView.setTag(viewHolder);
        }else
        {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        final lv_model item = getItem(position);
        viewHolder.maSach.setText(item.getMaSach());
        viewHolder.tenSach.setText(item.getTenSach());
        viewHolder.tacGia.setText(item.getTenTG());
        viewHolder.soLuong.setText(String.valueOf(item.getSoLuong()));

        viewHolder.btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getSoLuong() >0) {
                    SQLControl sqlControl  = new SQLControl(context);
                    order_model item_order = new order_model();
                    item_order.setMaSach(item.getMaSach());
                    item_order.setTenSach(item.getTenSach());
                    item_order.setTenTG(item.getTenTG());
                    item_order.setID_book(item.getID());
                    AddPhieu_order_Fragment.order_modelList.add(item_order);
                    AddPhieuMuonActivity.soLuong.setText(String.valueOf(AddPhieu_order_Fragment.order_modelList.size()));
                    int kq = sqlControl.getSoLuongSachDaMuon(item.getID()) + 1;
                    item.setSoLuong(item.getSoLuong() -1);
                    sqlControl.UpdateSoluongBook(item.getID(), kq);
                    AddPhieu_order_Fragment.adapter.notifyDataSetChanged();
                    notifyDataSetChanged();
                    Toast.makeText(context, "Đã Thêm Vào Danh Sách", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rowView;
    }
}
