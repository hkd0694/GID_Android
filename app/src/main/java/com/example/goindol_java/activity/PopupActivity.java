package com.example.goindol_java.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.example.goindol_java.R;
import com.example.goindol_java.data.ExcelProblem;
import com.example.goindol_java.data.Period;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.goindol_java.activity.MainActivity.period_data;
import static com.example.goindol_java.activity.SplashActivity.SETTINGS_PLAYER;

public class PopupActivity extends Activity {

    private ImageView imageView;
    private Button popup_reset;
    private Button popup_ing;
    private String name;
    private String intent_name;

    private SharedPreferences prefs;
    private Gson gson = new Gson();
    private List<Period> list = new ArrayList<>();
    private List<ExcelProblem> excelProblems = new ArrayList<>();
    private Type listType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);
        getWindow().setBackgroundDrawable(new PaintDrawable(Color.TRANSPARENT));
        intent_name = getIntent().getStringExtra(period_data);
        prefs = getSharedPreferences("shared", MODE_PRIVATE);
        name = prefs.getString(SETTINGS_PLAYER,null);
        listType = new TypeToken<ArrayList<Period>>() {}.getType();
        list = gson.fromJson(name, listType);

        imageView = findViewById(R.id.popup_cancel);
        popup_reset = findViewById(R.id.popup_reset);
        popup_ing = findViewById(R.id.popup_ing);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        popup_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(Integer.parseInt(intent_name.split(" ")[1])).setPeriod_data(excelProblems);
                Gson gson1  = new GsonBuilder().create();
                String json = gson1.toJson(list, listType);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(SETTINGS_PLAYER, json);
                editor.commit();
                Intent intent = new Intent(getApplicationContext(),LearnActivity.class);
                intent.putExtra(period_data,intent_name);
                startActivity(intent);
            }
        });

        popup_ing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //마지막으로 진행한 문제!!
            }
        });

    }

    //바깥 레이아웃 눌러도 닫히지 않게 하기.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    //뒤로가기 버튼 막음
    @Override
    public void onBackPressed() {
        return;
    }
}
