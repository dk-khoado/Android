package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ContactsAdapter extends ArrayAdapter<User_contacts>{
    List<User_contacts> list;
    int groupID;
    Context context;
    public ContactsAdapter(Context context,int vg , List<User_contacts> List_user) {
        super(context, vg,List_user);
        this.list = List_user;
        this.groupID = vg;
        this.context = context;
    }
    class Viewholder{
        TextView textView;
        Button button;
    }
    //xử lý dư liệu để truyền lên listView
    public View getView(int position,  View convertView, ViewGroup parent) {
        View rowView = convertView;
        if(rowView == null){
            rowView = LayoutInflater.from(getContext()).inflate(groupID, parent, false);

        }
        User_contacts user = getItem(position);
        Viewholder viewholder = new Viewholder();
        viewholder.textView = rowView.findViewById(R.id.txt);
        viewholder.button = rowView.findViewById(R.id.btn);
        viewholder.textView.setText(user.getName());
        viewholder.button.setText(user.getPhoneNumber());
        //rowView.setTag(viewholder);
        return rowView;
    }
}
