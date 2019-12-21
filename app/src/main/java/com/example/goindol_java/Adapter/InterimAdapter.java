package com.example.goindol_java.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.goindol_java.activity.TotalinterimActivity;
import com.example.goindol_java.data.ArrangeData;

import java.util.ArrayList;
import java.util.List;

public class InterimAdapter extends RecyclerView.Adapter<InterimAdapter.ViewHolder>{

    private Context context;
    private List<ArrangeData> arrangeData = new ArrayList<>();
    private ArrangeData arrange = new ArrangeData();

    private Intent intent;

    public InterimAdapter(Context context, List<ArrangeData> arrangeData){
        this.context = context;
        this.arrangeData = arrangeData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.inter_recyclerview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        arrange = arrangeData.get(position);
        holder.inter_image.setImageResource(R.drawable.icon_document_filled_color_2);
        holder.inter_number.setText(arrange.getNumber());
        holder.inter_summary.setText(arrange.getSummary());
        if(arrange.getNumber().equals("0")) {
            holder.inter_relative.setClickable(false);
            holder.inter_relative.setFocusable(false);
            holder.inter_relative.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }
        holder.inter_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //중간 정리 한거 Activity로 보여줌!!
                intent = new Intent(context, TotalinterimActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrangeData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView inter_number;
        private ImageView inter_image;
        private TextView inter_summary;
        private RelativeLayout inter_relative;

        public ViewHolder(View view){
            super(view);
            inter_number = view.findViewById(R.id.interview_number);
            inter_image = view.findViewById(R.id.Interview_image);
            inter_summary = view.findViewById(R.id.interview_arrange);
            inter_relative = view.findViewById(R.id.interview_relative);
        }
    }
}
