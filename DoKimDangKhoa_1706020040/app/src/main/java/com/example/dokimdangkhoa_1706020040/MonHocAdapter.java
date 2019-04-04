package com.example.dokimdangkhoa_1706020040;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MonHocAdapter extends ArrayAdapter<MonHoc> {
    List<MonHoc> monHocList;
    Context context;
    int resouce;
    public MonHocAdapter(Context context, int resource, List<MonHoc> objects) {
        super(context, resource, objects);
        this.monHocList = objects;
        this.context =context;
        this.resouce = resource;
    }
    class ViewHolder{
        TextView tenMonHoc;
        TextView maMonHoc;
        TextView soTinChi;
        LinearLayout detail;
    }
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder viewHolder =new ViewHolder();
        if(rowView == null){
            rowView = LayoutInflater.from(context).inflate(resouce, parent,false);

            viewHolder.tenMonHoc = rowView.findViewById(R.id.ct_txt_tenMonHoc);
            viewHolder.maMonHoc = rowView.findViewById(R.id.ct_txt_maMonHoc);
            viewHolder.soTinChi = rowView.findViewById(R.id.ct_txt_soTinChi);
            viewHolder.detail = rowView.findViewById(R.id.click);

            rowView.setTag(viewHolder);
        }else {
            viewHolder =(ViewHolder)rowView.getTag();
        }
        final MonHoc monHoc  = monHocList.get(position);

        viewHolder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("ten mon hoc", monHoc.getTenMonHoc());
                i.putExtra("ma mon hoc", monHoc.getMaMonHoc());
                i.putExtra("so tin chi", monHoc.getSoTinChi());
                i.putExtra("ho ten", monHoc.getHoten());
                i.putExtra("ma SV", monHoc.getMaSV());
                i.putExtra("nganh", monHoc.getNganh());
                i.putExtra("giang vien", monHoc.getGiaVien());
                context.startActivity(i);
            }
        });
        viewHolder.tenMonHoc.setText(monHoc.getTenMonHoc());
        viewHolder.maMonHoc.setText(monHoc.getMaMonHoc());
        viewHolder.soTinChi.setText(Integer.toString(monHoc.getSoTinChi()));

        return rowView;
    }
}
