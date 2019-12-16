package com.example.goindol_java.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.goindol_java.R;
import com.example.goindol_java.data.ExcelProblem;
import com.example.goindol_java.data.Period;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends Activity {

    public static final String SETTINGS_PLAYER = "goindol";

    private ImageView imageView;
    private List<Period> arrayList = new ArrayList<>();
    private List<ExcelProblem> list = new ArrayList<>();
    private List<ExcelProblem> list1 = new ArrayList<>();
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        prefs = getSharedPreferences("shared", MODE_PRIVATE);
        String data = prefs.getString(SETTINGS_PLAYER, null);
        if(data == null) {
            Log.e("Start","어플 처음 깔때만 들어옴!");
            init();
        }
        imageView = findViewById(R.id.imageView);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(imageView);
        Glide.with(this).load(R.raw.splash).into(gifImage);
        startLoading();
    }

    private void init(){
        arrayList.add(new Period("기원과 형성",list));
        arrayList.add(new Period("고대사회",list));
        arrayList.add(new Period("고려시대",list));
        arrayList.add(new Period("조선전기",list));
        arrayList.add(new Period("조선후기",list));
        arrayList.add(new Period("근대 개화",list));
        arrayList.add(new Period("일제 강점기",list));
        arrayList.add(new Period("현대",list));
        list1.add(new ExcelProblem("ㅐㅏㅐㅏ",1));
        arrayList.add(new Period("랜덤",list1));
        Log.e("Start","init() 함수 들어옴" + arrayList.size());
        Gson gson  = new GsonBuilder().create();
        Type listType = new TypeToken<ArrayList<Period>>() {}.getType();
        String json = gson.toJson(arrayList, listType);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(SETTINGS_PLAYER, json); // JSON으로 변환한 객체를 저장한다.
        editor.commit(); //완료한다.
    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
