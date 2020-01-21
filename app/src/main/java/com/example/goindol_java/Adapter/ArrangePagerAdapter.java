package com.example.goindol_java.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.goindol_java.R;
import com.example.goindol_java.data.ArrangeData;

import java.util.ArrayList;
import java.util.List;

public class ArrangePagerAdapter extends PagerAdapter {

    private Context context;
    private List<ArrangeData> arrangeData = new ArrayList<>();
    private int size;

    private TextView pager_middel;
    private RecyclerView pager_recyclerview;

    public ArrangePagerAdapter(Context context, List<ArrangeData> arrangeData) {
        this.context = context;
        this.arrangeData = arrangeData;
        size = arrangeData.size() / 10;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return size;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = null;
        if (context != null) {
            List<ArrangeData> recycler_data = new ArrayList<>();
            // LayoutInflater를 통해 "/res/layout/page.xml"을 뷰로 생성.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.interimpager, container, false);
            pager_middel = (TextView) view.findViewById(R.id.pager_middle);
            pager_middel.setText("중간정리 " + (position + 1) + "번째");
            pager_recyclerview = (RecyclerView) view.findViewById(R.id.pager_recyclerview);
            int startIndex = 0;
            switch (position) {
                case 0:
                    startIndex = 0;
                    break;
                case 1:
                    startIndex = 10;
                    break;
                case 2:
                    startIndex = 20;
                    break;
                case 3:
                    startIndex = 30;
                    break;
            }
            for (int i = startIndex; i < startIndex + 10; i++)
                recycler_data.add(arrangeData.get(i));
            ArrangeAdapter arrangeAdapter = new ArrangeAdapter(context, recycler_data);
            LinearLayoutManager linearLayout = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
            pager_recyclerview.setLayoutManager(linearLayout);
            pager_recyclerview.setAdapter(arrangeAdapter);
        }
        // 뷰페이저에 추가.
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // 뷰페이저에서 삭제.
        container.removeView((View) object);
    }
}
