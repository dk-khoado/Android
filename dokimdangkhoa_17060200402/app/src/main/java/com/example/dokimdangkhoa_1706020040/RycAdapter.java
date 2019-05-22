package com.example.dokimdangkhoa_1706020040;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dokimdangkhoa_1706020040.models.Product;
import com.example.dokimdangkhoa_1706020040.models.Subject;
import com.google.firebase.database.DatabaseReference;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RycAdapter extends RecyclerView.Adapter<RycAdapter.ViewHolder> {
    List<Subject> subjectList;
    List<Product> productList;
    Context context;
    int vgID;


    public RycAdapter(List<Subject> subjectList, List<Product> productList, Context context, int vgID) {
        this.subjectList = subjectList;
        this.productList = productList;
        this.context = context;
        this.vgID = vgID;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(vgID, viewGroup, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//        if (i % 2 == 0) {
//            viewHolder.background.setBackgroundResource(R.color.background_);
//        }
        //=============================================================
        Button btnYes;
        Button btnNo;
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_layout);
        dialog.setTitle("Delete ?");
        btnYes = dialog.findViewById(R.id.dialog_btn_ok);
        btnNo = dialog.findViewById(R.id.dialog_btn_cancel);
        //==============================================================
        if (!subjectList.isEmpty()) {
            final Subject item = subjectList.get(i);
            viewHolder.name.setText("subject name:");
            viewHolder.code_producer.setText("subject code:");
            viewHolder.price_credits.setText("credits:");

            viewHolder.custom_txt_id.setText(String.valueOf(item.getId()));
            viewHolder.txt_name.setText(item.getSubject_name());
            viewHolder.txt_code_producer.setText(item.getSubject_code());
            viewHolder.txt_price_credits.setText(String.valueOf(item.getCredits()));
//            viewHolder.txt_des.setText(item.getDescription());

            viewHolder.btn_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("type", "subject");
                    intent.putExtra("data", item);
                    context.startActivity(intent);
                }
            });
            viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.show();
//                    DatabaseReference child = MainActivity.myChilRef.child(item.getKeyParent());
//                    child.removeValue();
//                    notifyDataSetChanged();
                }
            });
            //button dialog (yes)
            btnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference child = MainActivity.myChilRef.child(item.getKeyParent());
                    child.removeValue();
                    notifyDataSetChanged();
                    dialog.dismiss();

                }
            });
            //button dialog (no)
            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        } else {
            final Product item = productList.get(i);
            viewHolder.name.setText("product name:");
            viewHolder.code_producer.setText("producer:");
            viewHolder.price_credits.setText("price:");

            viewHolder.custom_txt_id.setText(String.valueOf(item.getId()));
            viewHolder.txt_name.setText(item.getProduct_name());
            viewHolder.txt_code_producer.setText(item.getProducer());
            viewHolder.txt_price_credits.setText(String.valueOf(item.getPrice()));
//            viewHolder.txt_des.setText(item.getDescription());
            viewHolder.btn_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("type", "product");
                    intent.putExtra("data", item);
                    context.startActivity(intent);
                }
            });
            viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.show();
//                    DatabaseReference child = MainActivity.myChilRef.child(item.getKeyParent());
//                    child.removeValue();
//                    notifyDataSetChanged();

                }
            });
            //button dialog (yes)
            btnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference child = MainActivity.myChilRef.child(item.getKeyParent());
                    child.removeValue();
                    notifyDataSetChanged();
                    dialog.dismiss();
                }
            });
            //button dialog (no)
            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (!subjectList.isEmpty()) {
            return subjectList.size();
        } else {
            return productList.size();
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView custom_txt_id;
        TextView txt_des;
        TextView price_credits;
        TextView txt_price_credits;

        TextView name;
        TextView txt_name;
        TextView code_producer;
        TextView txt_code_producer;

        ImageButton btn_delete;
        CardView btn_detail;

        LinearLayout background;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            custom_txt_id = itemView.findViewById(R.id.custom_txt_ID);
            //txt_des = itemView.findViewById(R.id.custom_txt_des);
            price_credits = itemView.findViewById(R.id.custom_price_credits);
            txt_price_credits = itemView.findViewById(R.id.custom_txt_price_credits);

            name = itemView.findViewById(R.id.custom_Name);
            txt_name = itemView.findViewById(R.id.custom_txt_name);
            code_producer = itemView.findViewById(R.id.custom_producer_code);
            txt_code_producer = itemView.findViewById(R.id.custom_txt_producer_code);

            btn_delete = itemView.findViewById(R.id.custom_btnDelete);
            btn_detail = itemView.findViewById(R.id.custom_lm_);

//            background = itemView.findViewById(R.id.custom_lm_);
        }
    }
}
