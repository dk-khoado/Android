package com.example.sqlite;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Users> {
    private List<Users> usersList;
    private Context context;
    private int IDgv;
    private Dialog dialogDelete;
    private Dialog dialogEdit;

    public CustomAdapter(Context context, int resource, List<Users> objects) {
        super(context, resource, objects);
        this.usersList = objects;
        this.context = context;
        this.IDgv = resource;
    }

    class Viewholder {
        TextView name, password;
        ImageButton btn_delete, btn_edit;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        Viewholder viewholder = new Viewholder();
        if (rowView == null) {
            rowView = LayoutInflater.from(context).inflate(IDgv, parent, false);
            viewholder.name = rowView.findViewById(R.id.txt_custom_name);
            viewholder.password = rowView.findViewById(R.id.txt_custom_password);
            viewholder.btn_delete = rowView.findViewById(R.id.btn_custom_delete);
            viewholder.btn_edit = rowView.findViewById(R.id.btn_custom_edit);
            rowView.setTag(viewholder);
        } else {
            viewholder = (Viewholder) rowView.getTag();
        }
        final Users users = getItem(position);
        assert users != null;
        viewholder.name.setText(users.getName());
        viewholder.password.setText(users.getPassword());
        //nút xóa
        viewholder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.helper.Delete(users.getId());
                MonhocFragment.list = LoginActivity.helper.getAllData();
                notifyDataSetChanged();
                //hiện dialog-->
//                dialogDelete = new Dialog(context);
//                dialogDelete.setContentView(R.layout.dialog_delete_layout);
//                dialogDelete.setTitle("Bạn có Muốn xóa");
//                dialogDelete.show();
                //<--end
                //ánh qua qua layout custom dialog-->
//                Button btnOK = dialogDelete.findViewById(R.id.btn_dialog_ok);
//                Button btnCancel = dialogDelete.findViewById(R.id.btn_dialog_cancel);
                //<--end
//                btnOK.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        LoginActivity.helper.Delete(users.getId());
//                        usersList = LoginActivity.helper.getAllData();
//                        dialogDelete.dismiss();
//                        notifyDataSetChanged();
//                    }
//                });
//                btnCancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialogDelete.dismiss();
//                    }
//                });
                notifyDataSetChanged();
            }
        });
        //sự kiện edit
        viewholder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogEditShow(users.getId(),users.getName(),users.getPassword());
            }
        });
        return rowView;
    }

    private void DialogEditShow(final int ID,String name, String password) {
        dialogEdit = new Dialog(context);
        dialogEdit.setContentView(R.layout.diablog_edit_layout);
        dialogEdit.setTitle("Chỉnh sửa");
        dialogEdit.show();
        final TextView txtName = dialogEdit.findViewById(R.id.dialog_userName);
        final EditText edtiPassword = dialogEdit.findViewById(R.id.dialog_password);
        txtName.setText(name);
        edtiPassword.setText(password);
        Button btnUpdate = dialogEdit.findViewById(R.id.btn_dialog_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.helper.Update(ID, txtName.getText().toString(), edtiPassword.getText().toString());
                HomeActivity.list = LoginActivity.helper.getAllData();
                notifyDataSetChanged();
                dialogEdit.dismiss();
            }
        });
    }
}
