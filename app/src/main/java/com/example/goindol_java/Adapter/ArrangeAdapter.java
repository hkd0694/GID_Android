package com.example.goindol_java.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goindol_java.R;
import com.example.goindol_java.data.ArrangeData;

import java.util.ArrayList;
import java.util.List;

public class ArrangeAdapter extends RecyclerView.Adapter<ArrangeAdapter.ViewHolder>{

    private Context context;
    private List<ArrangeData> arrangeData = new ArrayList<>();

    public ArrangeAdapter(Context context, List<ArrangeData> arrangeData){
        this.context = context;
        this.arrangeData = arrangeData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_arrange,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArrangeData data = arrangeData.get(position);
        holder.arr_number.setText(data.getNumber());
        holder.arr_summary.setText(data.getSummary());
        if(data.isCheck()) {
            holder.arr_image.setImageResource(R.drawable.icon_answer_correct);
        } else {
            holder.arr_image.setImageResource(R.drawable.icon_answer_wrong);
        }
    }

    @Override
    public int getItemCount() {
        return arrangeData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView arr_number;
        private ImageView arr_image;
        private TextView arr_summary;

        public ViewHolder(View view){
            super(view);
            arr_number = view.findViewById(R.id.recyclerview_number);
            arr_image = view.findViewById(R.id.recyclerview_image);
            arr_summary = view.findViewById(R.id.recyclerview_arrange);
        }
    }
}
