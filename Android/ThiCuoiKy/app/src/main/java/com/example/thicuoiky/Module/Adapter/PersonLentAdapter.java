package com.example.thicuoiky.Module.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thicuoiky.DetailActivity;
import com.example.thicuoiky.models.Member;
import com.example.thicuoiky.Module.SQLControl;
import com.example.thicuoiky.R;
import com.example.thicuoiky.fragment.PhieuMuongFragment;

import java.util.List;

public class PersonLentAdapter extends ArrayAdapter<Member> {
    List<Member> personLentList;
    Context context;
    int resourse;
    SQLControl sqlControl;
    public PersonLentAdapter(Context context, int resource, List<Member> objects) {
        super(context, resource, objects);
        this.personLentList = objects;
        this.context = context;
        this.resourse = resource;
        sqlControl = new SQLControl(this.context);
    }
    class ViewHolder{
        TextView txtTenSach, txtSoSach, txtNgay;
        LinearLayout btnDetail;
        Button status;
    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder viewHolder =new ViewHolder();
        if(rowView == null){
            rowView = LayoutInflater.from(context).inflate(resourse, parent, false);
            viewHolder.txtTenSach = rowView.findViewById(R.id.custom_pm_name);
            viewHolder.txtSoSach = rowView.findViewById(R.id.custom_pm_count);
            viewHolder.txtNgay = rowView.findViewById(R.id.custom_pm_date);
            viewHolder.btnDetail = rowView.findViewById(R.id.custom_pm_clickDetail);
            viewHolder.status = rowView.findViewById(R.id.custom_pm_sattus);
            rowView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) rowView.getTag();
        }
        final Member member = getItem(position);
        viewHolder.txtTenSach.setText(member.getName());
        viewHolder.txtSoSach.setText(String.valueOf(member.getSoLuongSach()));
        viewHolder.txtNgay.setText(member.getNgayMuon());
        viewHolder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final Dialog dialog = new Dialog(context);
               dialog.setContentView(R.layout.dialog_xacnhan);
               Button btnXacNhan = dialog.findViewById(R.id.dialog_btnXatNhan);
               Button btnHuy = dialog.findViewById(R.id.dialog_btnHuy);
               dialog.setTitle("Xác Nhận Đã Trả Sách");
               dialog.show();
               //kiểm tra xác nhận
               btnXacNhan.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       sqlControl.UpdateStatusMenber(member.getID(), "true");
                       PhieuMuongFragment.Update();
                       dialog.dismiss();
                   }
               });
               btnHuy.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       dialog.dismiss();
                   }
               });
            }
        });
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
