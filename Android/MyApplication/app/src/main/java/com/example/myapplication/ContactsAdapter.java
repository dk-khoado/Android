package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ContactsAdapter extends ArrayAdapter<User_contacts> {
    List<User_contacts> list;
    int groupID;
    Context context;

    public ContactsAdapter(Context context, int vg, List<User_contacts> List_user) {
        super(context, vg, List_user);
        this.list = List_user;
        this.groupID = vg;
        this.context = context;
    }

    class Viewholder {
        TextView textView;
        Button button;
        TextView textViewSub;
        LinearLayout layout;
        Button btnIcon;
    }

    //xử lý dư liệu để truyền lên listView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        Viewholder viewholder = new Viewholder();
        if (rowView == null) {
            rowView = LayoutInflater.from(getContext()).inflate(groupID, parent, false);
            viewholder.textViewSub = rowView.findViewById(R.id.txt_sub);
            viewholder.textView = rowView.findViewById(R.id.txt);
            viewholder.button = rowView.findViewById(R.id.btn);
            viewholder.layout = rowView.findViewById(R.id.lay_click);
            viewholder.btnIcon = rowView.findViewById(R.id.btnIcon);
        }else { viewholder = (Viewholder) rowView.getTag();}

        final User_contacts user = getItem(position);

        viewholder.textView.setText(user.getName());
        viewholder.textViewSub.setText(user.getPhoneNumber());
        viewholder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + user.getPhoneNumber())));
            }
        });
        viewholder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Main_addContacts.class);
                intent.putExtra("name",user.getName());
                intent.putExtra("phone",user.getPhoneNumber());
                intent.putExtra("diachi",user.getDia_chi());
                intent.putExtra("city",user.getCity());
                context.startActivity(intent);
            }
        });
        viewholder.btnIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Main_addContacts.class);
                intent.putExtra("name",user.getName());
                intent.putExtra("phone",user.getPhoneNumber());
                intent.putExtra("diachi",user.getDia_chi());
                intent.putExtra("city",user.getCity());
                context.startActivity(intent);
            }
        });
        rowView.setTag(viewholder);
        return rowView;
    }

    public void Refresh() {
        notifyDataSetChanged();
    }
}
