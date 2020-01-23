package com.example.goindol_java.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goindol_java.Adapter.InterimAdapter;
import com.example.goindol_java.R;
import com.example.goindol_java.data.ArrangeData;
import com.example.goindol_java.data.Period;
import com.example.goindol_java.popup.InitPopupActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.goindol_java.activity.SplashActivity.SETTINGS_PLAYER;

public class InterimActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private View toolbar;
    private NavigationView navigationView;
    private ImageButton btnShowNavigationDrawer;
    private ImageButton navi_cancel;
    private ImageButton navi_home;
    private ImageButton toolbar_cancel;

    private Intent intent;

    private View naviScriptView;
    private View naviMiddleView;
    private View naviSettingView;
    private View naviInitialView;

    private TextView naviMiddleText;
    private TextView naviScriptText;

    private int scriptIndex = 0;
    private int middleIndex = 0;

    private TextView inter_text;
    private RecyclerView inter_recyclerview;
    private List<ArrangeData> inter_recycler = new ArrayList<>();

    private SharedPreferences prefs;
    private Gson gson = new Gson();
    private List<Period> list = new ArrayList<>();
    private Type listType;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interim);
        settingapp_bar();
        navi_header_click();
        init();
        inter_text.setText("중간정리를 다시 보면서\n" + "한번 더 확인해 보세요");
        prefs = getSharedPreferences("shared", MODE_PRIVATE);
        name = prefs.getString(SETTINGS_PLAYER, null);
        listType = new TypeToken<ArrayList<Period>>() {
        }.getType();
        list = gson.fromJson(name, listType);
        recycler_adapter();
    }

    private void recycler_adapter() {
        ArrangeData arrangeData = new ArrangeData();
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i).getPeriodic();
            int index = 0;
            switch (list.get(i).getArrangeData().size() / 10) {
                case 0:
                    index = 0;
                    break;
                case 1:
                    index = 1;
                    break;
                case 2:
                    index = 2;
                    break;
                case 3:
                    index = 3;
                    break;
                case 4:
                    index = 4;
                    break;
            }
            //number, check, summary
            arrangeData = new ArrangeData(String.valueOf(index), String.valueOf(i), name);
            inter_recycler.add(arrangeData);
        }
        InterimAdapter adapter = new InterimAdapter(this, inter_recycler);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        inter_recyclerview.setLayoutManager(linearLayout);
        inter_recyclerview.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        inter_recycler.clear();
        super.onStop();
    }

    private void init() {
        inter_text = findViewById(R.id.inter_text);
        inter_recyclerview = findViewById(R.id.inter_recyclerview);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    //Toolbar 안에있는 값들 초기화
    private void settingapp_bar() {
        toolbar = findViewById(R.id.inter_toolbar);
        btnShowNavigationDrawer = toolbar.findViewById(R.id.navibutton);
        toolbar_cancel = toolbar.findViewById(R.id.toolbar_cancel);
        toolbar_cancel.setVisibility(View.VISIBLE);
        btnShowNavigationDrawer.setOnClickListener(onClickListener);
        drawerLayout = findViewById(R.id.inter_drawerlayout);
        navigationView = findViewById(R.id.inter_navigation);



        toolbar_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //Navigation 버튼 클릭시 발생하는 리스너
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.navibutton:
                    drawerLayout.openDrawer(GravityCompat.END);

                    scriptIndex = 0;
                    middleIndex = 0;
                    //여기다가 중간정리 및 스크랩한 갯수 계속해서 초기화!!
                    for(int i=0;i<list.size();i++) {
                        scriptIndex +=list.get(i).getScriptTotalCount();
                        if(list.get(i).getArrangeData().size() == 0) continue;
                        middleIndex += list.get(i).getArrangeData().size() / 10;
                    }
                    if(scriptIndex == 0) naviScriptText.setVisibility(View.GONE);
                    else {
                        naviScriptText.setVisibility(View.VISIBLE);
                        naviScriptText.setText(String.valueOf(scriptIndex));
                    }
                    if(middleIndex == 0) naviMiddleText.setVisibility(View.GONE);
                    else{
                        naviMiddleText.setVisibility(View.VISIBLE);
                        naviMiddleText.setText(String.valueOf(middleIndex));
                    }
                    break;
            }
        }
    };

    //navi header를 클릭하면 발생하는 이벤트 함수 (엑스, 홈)
    private void navi_header_click() {
        View naviView = navigationView.getHeaderView(0);
        navi_cancel = naviView.findViewById(R.id.navi_cancel);
        navi_home = naviView.findViewById(R.id.navi_home);

        naviScriptView = naviView.findViewById(R.id.navi_script_view);
        naviMiddleView = naviView.findViewById(R.id.navi_middle_view);
        naviSettingView = naviView.findViewById(R.id.navi_setting_view);
        naviInitialView = naviView.findViewById(R.id.navi_initial_view);
        naviMiddleText = naviView.findViewById(R.id.navi_middle_text);
        naviScriptText = naviView.findViewById(R.id.navi_script_text);

        naviScriptView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), ScrapActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.END);
            }
        });

        naviMiddleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), InterimActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.END);
            }
        });

        naviSettingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), SettingActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.END);
            }
        });

        naviInitialView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), InitPopupActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.END);
            }
        });

        navi_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(GravityCompat.END);
            }
        });

        navi_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

    }

}
