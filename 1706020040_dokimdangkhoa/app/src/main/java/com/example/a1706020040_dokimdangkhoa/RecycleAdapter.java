package com.example.a1706020040_dokimdangkhoa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a1706020040_dokimdangkhoa.AsyncTask.DataAsyncTask;
import com.example.a1706020040_dokimdangkhoa.Interface.IviewDta;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    Context context;
    int IDVG;
    List<subjectModel> modelList;

    public RecycleAdapter(Context context, int IDVG, List<subjectModel> modelList) {
        this.context = context;
        this.IDVG = IDVG;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(context).inflate(IDVG, viewGroup, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.subject_name.setText(modelList.get(i).getSubjectName());
        viewHolder.subject_code.setText(modelList.get(i).getSubjectCode());
        viewHolder.credits.setText(modelList.get(i).getCredits());
       // viewHolder.description.setText(modelList.get(i).getDescription());
        final int postion = i;
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context, DetilActivity.class);
                intent.putExtra("subject", modelList.get(postion));
                context.startActivity(intent);
            }
        });
        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> mMap= new HashMap<>();
                mMap.put("id", String.valueOf(modelList.get(postion).getId()));
                mMap.put("user_id", String.valueOf(modelList.get(postion).getIdUser()));
                new DataAsyncTask(mMap, new IviewDta() {
                    @Override
                    public void getData(String s, JSONArray data) {

                    }

                    @Override
                    public void Error(String s) {

                    }
                }).execute("http://www.vidophp.tk/api/account/dataaction?context=delete");
                MainActivity.Update();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView subject_name;
        TextView subject_code;
        TextView credits;
        LinearLayout linearLayout;
       // TextView description;
        Button btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subject_name = (TextView) itemView.findViewById(R.id.r_subject_name);
            subject_code = (TextView) itemView.findViewById(R.id.r_subject_code);
            credits = (TextView) itemView.findViewById(R.id.r_credits);
            linearLayout = itemView.findViewById(R.id.r_click);
            btnDelete = (Button) itemView.findViewById(R.id.r_btn_delete);
          //  description = (TextView) itemView.findViewById(R.id.r_description);
        }
    }
}
