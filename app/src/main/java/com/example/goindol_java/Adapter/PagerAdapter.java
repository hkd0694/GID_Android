package com.example.goindol_java.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.goindol_java.data.ArrangeData;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends androidx.viewpager.widget.PagerAdapter {

    private Context context;
    private List<ArrangeData> arrangeData = new ArrayList<>();

    public PagerAdapter(Context context, List<ArrangeData> arrangeData) {
        this.context = context;
        this.arrangeData = arrangeData;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return arrangeData.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}
