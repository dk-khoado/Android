package com.example.mychatsimple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<userModel> {
    Context context;
    private  int MyRS;
    private  int noMYRS;
    List<userModel> userModelList;
    public ListViewAdapter(Context context, int itMy, int itYou,  List<userModel> objects) {
        super(context, itMy, itYou, objects);
        this.context = context;
        this.MyRS = itMy;
        this.noMYRS = itYou;
        userModelList = objects;
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (userModelList.get(position).getUserID().equals( LoginActivity.userID)){
            convertView = LayoutInflater.from(context).inflate(MyRS, parent, false);
            viewHolder.message = convertView.findViewById(R.id.custom_textMy);
            viewHolder.message.setText(userModelList.get(position).getText());
        }else
        {
            convertView = LayoutInflater.from(context).inflate(noMYRS, parent, false);
            viewHolder.Name = convertView.findViewById(R.id.custom_name);
            viewHolder.message = convertView.findViewById(R.id.custom_textnoMy);
            viewHolder.Name.setText(userModelList.get(position).getUserName());
            viewHolder.message.setText(userModelList.get(position).getText());
        }

        return convertView;

    }
    class ViewHolder{
        TextView message;
        TextView Name;
    }
}
