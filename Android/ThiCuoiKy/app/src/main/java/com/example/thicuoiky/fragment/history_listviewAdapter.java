package com.example.thicuoiky.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thicuoiky.DetailActivity;
import com.example.thicuoiky.models.Member;
import com.example.thicuoiky.Module.SQLControl;
import com.example.thicuoiky.R;

import java.util.List;

public class history_listviewAdapter extends ArrayAdapter<Member> {
   private List<Member> personLentList;
   private Context context;
   private int resourse;
   private SQLControl sqlControl;
    public history_listviewAdapter(Context context, int resource, List<Member> objects) {
        super(context, resource, objects);
        this.personLentList = objects;
        this.context = context;
        this.resourse = resource;
        sqlControl = new SQLControl(this.context);
    }
    class ViewHolder{
        TextView txtTenSach, txtSoSach, txtNgay;
        LinearLayout btnDetail;
        TextView status;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder viewHolder =new ViewHolder();
        if(rowView == null){
            rowView = LayoutInflater.from(context).inflate(resourse, parent, false);
            viewHolder.txtTenSach = rowView.findViewById(R.id.custom_history_name);
            viewHolder.txtSoSach = rowView.findViewById(R.id.custom_history_count);
            viewHolder.txtNgay = rowView.findViewById(R.id.custom_history_date);
            viewHolder.btnDetail = rowView.findViewById(R.id.custom_history_clickDetail);
            viewHolder.status = rowView.findViewById(R.id.custom_history_status);
            rowView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) rowView.getTag();
        }
        final Member member = personLentList.get(position);
        viewHolder.txtTenSach.setText(member.getName());
        viewHolder.txtSoSach.setText(String.valueOf(member.getSoLuongSach()));
        viewHolder.txtNgay.setText(member.getNgayMuon());
        if (member.isStatus()){
            viewHolder.status.setText(R.string.daTra);
        }else
        {
            viewHolder.status.setText(R.string.chưaTra);
        }
        viewHolder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("ID",member.getID());
                context.startActivity(intent);
            }
        });
        return rowView;
    }
}
