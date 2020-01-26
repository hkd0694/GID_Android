package com.example.goindol_java.popup;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.example.goindol_java.R;
import com.example.goindol_java.activity.MainActivity;
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

import static com.example.goindol_java.activity.SplashActivity.SETTINGS_PLAYER;

public class InitPopupActivity extends Activity {

    private ImageView init_imageView;
    private Button initpopup_cancel;
    private Button initpopup_ing;

    private SharedPreferences prefs;
    private Gson gson = new Gson();
    private List<Period> list = new ArrayList<>();
    private List<ExcelProblem> excelProblems = new ArrayList<>();
    private List<ArrangeData> arrangeData = new ArrayList<>();
    private List<ScriptData> scriptData = new ArrayList<>();
    private Type listType;


    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_init_popup);
        getWindow().setBackgroundDrawable(new PaintDrawable(Color.TRANSPARENT));
        init();

        init_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initpopup_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initpopup_ing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++) {
                    // 초기화 부분
                    list.get(i).setPeriod_data(excelProblems);
                    list.get(i).setIndex(1);
                    list.get(i).setArrangeData(arrangeData);
                    list.get(i).setScriptData(scriptData);
                    list.get(i).setScriptTotalCount(0);
                    list.get(i).setMiddleTotalCount(0);
                }
                Gson gson1 = new GsonBuilder().create();
                String json = gson1.toJson(list, listType);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(SETTINGS_PLAYER, json);
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        prefs = getSharedPreferences("shared", MODE_PRIVATE);
        name = prefs.getString(SETTINGS_PLAYER, null);
        listType = new TypeToken<ArrayList<Period>>() {
        }.getType();
        list = gson.fromJson(name, listType);
        super.onResume();
    }

    private void init() {
        init_imageView = findViewById(R.id.initpopup_close);
        initpopup_cancel = findViewById(R.id.initpopup_cancel);
        initpopup_ing = findViewById(R.id.initpopup_ing);
    }
}
