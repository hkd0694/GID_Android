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
import com.example.goindol_java.data.ArrangeData;
import com.example.goindol_java.data.ExcelProblem;
import com.example.goindol_java.data.Period;
import com.example.goindol_java.data.ScriptData;
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
    private List<ArrangeData> arrange = new ArrayList<>();
    private List<ScriptData> scriptData = new ArrayList<>();
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //앱을 키자마자 만약 사용자가 이용하고 있는 데이터가 있을 경우 data 변수에 값이 들어온다.
        prefs = getSharedPreferences("shared", MODE_PRIVATE);
        String data = prefs.getString(SETTINGS_PLAYER, null);
        for(int i=1;i<=40;i++) scriptData.add(new ScriptData(String.valueOf(i),0,false));
        if(data == null) {
            init();
        }
        imageView = findViewById(R.id.imageView);
        //Glide를 통해 gif를 로딩한다. 이미지 라이브러리 중 Glide 와 Picasso가 있지만 gif를 지원하는 Glide사용!
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(imageView);
        Glide.with(this).load(R.raw.splash).into(gifImage);
        startLoading();
    }

    //만약 앱을 처음 설치하고 처음 실행시에 시대 별로 데이터를 보관해야 하기 때문에 초기화 진행
    private void init(){
        arrayList.add(new Period("기원과 형성",list,arrange,scriptData));
        arrayList.add(new Period("삼국시대",list,arrange,scriptData));
        arrayList.add(new Period("고려시대",list,arrange,scriptData));
        arrayList.add(new Period("조선전기",list,arrange,scriptData));
        arrayList.add(new Period("조선후기",list,arrange,scriptData));
        arrayList.add(new Period("근대개화",list,arrange,scriptData));
        arrayList.add(new Period("일제 강점기~현대",list,arrange,scriptData));
        Gson gson  = new GsonBuilder().create();
        Type listType = new TypeToken<ArrayList<Period>>() {}.getType();
        String json = gson.toJson(arrayList, listType);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(SETTINGS_PLAYER, json); // JSON으로 변환한 객체를 저장한다.
        editor.commit(); //완료한다.
    }

    //MainThread 에서 진행을 하게 되면 오류가 나기 때문에 Handler를 통하여 thread를 진행 3초뒤에 MainActivity로 넘어간다.
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
