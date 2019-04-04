package com.example.thicuoiky.Module.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.thicuoiky.AddPhieuMuonActivity;
import com.example.thicuoiky.Module.SQLControl;
import com.example.thicuoiky.fragment.Addphieu_list_Fragment;
import com.example.thicuoiky.models.order_model;
import com.example.thicuoiky.R;
import com.example.thicuoiky.fragment.AddPhieu_order_Fragment;

import java.util.List;

public class order_PhieuMuonAdapter extends ArrayAdapter<order_model> {
    List<order_model> bookList;
    private Context context;
    private int resouce;
    public order_PhieuMuonAdapter(Context context, int resource, List<order_model> objects) {
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
        ImageButton btn_Remove;
    }
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder viewHolder = new ViewHolder();
        if (rowView == null)
        {
            rowView = LayoutInflater.from(context).inflate(resouce,parent,false);
            viewHolder.maSach = (TextView) rowView.findViewById(R.id.custom_lv_add_maSach);
            viewHolder.tenSach = (TextView) rowView.findViewById(R.id.custom_lv_add_tenSach);
            viewHolder.tacGia = (TextView) rowView.findViewById(R.id.custom_lv_add_TG);
            viewHolder.btn_Remove = rowView.findViewById(R.id.custom_lv_add_btnRemove);
            rowView.setTag(viewHolder);
        }else
        {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        final order_model item = getItem(position);
        viewHolder.maSach.setText(item.getMaSach());
        viewHolder.tenSach.setText(item.getTenSach());
        viewHolder.tacGia.setText(item.getTenTG());

        viewHolder.btn_Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLControl sqlControl =new SQLControl(context);

                AddPhieu_order_Fragment.order_modelList.remove(position);
                AddPhieuMuonActivity.soLuong.setText(String.valueOf(AddPhieu_order_Fragment.order_modelList.size()));
                sqlControl.UpdateSoluongBook(item.getID_book(), sqlControl.getSoLuongSachDaMuon(item.getID_book()) -1);
                AddPhieu_order_Fragment.adapter.notifyDataSetChanged();
                Addphieu_list_Fragment.Update();
            }
        });
        return rowView;
    }
}
