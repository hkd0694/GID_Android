package com.example.goindol_java.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goindol_java.R;
import com.example.goindol_java.activity.ScrapListActivity;
import com.example.goindol_java.data.ArrangeData;

import java.util.ArrayList;
import java.util.List;

public class ScriptAdapter extends RecyclerView.Adapter<ScriptAdapter.ViewHolder> {

    private Context context;
    private List<ArrangeData> arrangeData = new ArrayList<>();

    private Intent intent;

    public ScriptAdapter(Context context, List<ArrangeData> arrangeData) {
        this.context = context;
        this.arrangeData = arrangeData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.scrap_recyclerview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArrangeData data = arrangeData.get(position);
        holder.scrap_image.setImageResource(R.drawable.icon_star_filled_color_copy);
        holder.scrap_number.setText(data.getNumber());
        holder.scrap_summary.setText(data.getSummary());
        if(data.getNumber().equals("0")) {
            holder.scrap_relative.setClickable(false);
            holder.scrap_relative.setFocusable(false);
            holder.scrap_relative.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }
        holder.scrap_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, ScrapListActivity.class);
                intent.putExtra("index",position);
                intent.putExtra("area",holder.scrap_summary.getText().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrangeData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView scrap_number;
        private ImageView scrap_image;
        private TextView scrap_summary;
        private RelativeLayout scrap_relative;

        public ViewHolder(View view){
            super(view);
            scrap_number = view.findViewById(R.id.script_number);
            scrap_image = view.findViewById(R.id.script_image);
            scrap_summary = view.findViewById(R.id.script_arrange);
            scrap_relative = view.findViewById(R.id.script_relative);
        }
    }


}
