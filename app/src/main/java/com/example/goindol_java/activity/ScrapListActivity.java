package com.example.goindol_java.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.goindol_java.R;
import com.example.goindol_java.data.Period;
import com.example.goindol_java.data.ScriptData;
import com.example.goindol_java.popup.InitPopupActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.goindol_java.activity.SplashActivity.SETTINGS_PLAYER;

public class ScrapListActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private View toolbar;
    private NavigationView navigationView;
    private ImageButton btnShowNavigationDrawer;
    private ImageButton navi_cancel;
    private ImageButton navi_home;
    private ImageButton toolbar_cancel;

    private View naviScriptView;
    private View naviMiddleView;
    private View naviSettingView;
    private View naviInitialView;

    private TextView naviMiddleText;
    private TextView naviScriptText;

    private int scriptIndex = 0;
    private int middleIndex = 0;

    private Intent intent;

    private TextView scrapList_text;
    private Button grid_button1;
    private Button grid_button2;
    private Button grid_button3;
    private Button grid_button4;
    private Button grid_button5;
    private Button grid_button6;
    private Button grid_button7;
    private Button grid_button8;
    private Button grid_button9;
    private Button grid_button10;
    private Button grid_button11;
    private Button grid_button12;
    private Button grid_button13;
    private Button grid_button14;
    private Button grid_button15;
    private Button grid_button16;
    private Button grid_button17;
    private Button grid_button18;
    private Button grid_button19;
    private Button grid_button20;
    private Button grid_button21;
    private Button grid_button22;
    private Button grid_button23;
    private Button grid_button24;
    private Button grid_button25;
    private Button grid_button26;
    private Button grid_button27;
    private Button grid_button28;
    private Button grid_button29;
    private Button grid_button30;
    private Button grid_button31;
    private Button grid_button32;
    private Button grid_button33;
    private Button grid_button34;
    private Button grid_button35;
    private Button grid_button36;
    private Button grid_button37;
    private Button grid_button38;
    private Button grid_button39;
    private Button grid_button40;


    private SharedPreferences prefs;
    private Gson gson = new Gson();
    private List<Period> list = new ArrayList<>();
    private Type listType;
    private String name;

    private int index;
    private String area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_list);
        settingapp_bar();
        navi_header_click();
        init();
        index = getIntent().getIntExtra("index", 0);
        area = getIntent().getStringExtra("area");
        scrapList_text.setText(area + " 스크랩한 문제를 다시 풀면서\n" + "한번 더 확인해 보세요.");
        prefs = getSharedPreferences("shared", MODE_PRIVATE);
        name = prefs.getString(SETTINGS_PLAYER, null);
        listType = new TypeToken<ArrayList<Period>>() {
        }.getType();
        list = gson.fromJson(name, listType);

        grid_check();

    }

    private void grid_check() {
        List<ScriptData> dataList = list.get(index).getScriptData();
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).isScript()) {
                switch (i) {
                    case 0:
                        grid_button1.setEnabled(true);
                        grid_button1.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button1.setTextColor(Color.WHITE);
                        grid_button1.setOnClickListener(this);
                        break;
                    case 1:
                        grid_button2.setEnabled(true);
                        grid_button2.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button2.setTextColor(Color.WHITE);
                        grid_button2.setOnClickListener(this);
                        break;
                    case 2:
                        grid_button3.setEnabled(true);
                        grid_button3.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button3.setTextColor(Color.WHITE);
                        grid_button3.setOnClickListener(this);
                        break;
                    case 3:
                        grid_button4.setEnabled(true);
                        grid_button4.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button4.setTextColor(Color.WHITE);
                        grid_button4.setOnClickListener(this);
                        break;
                    case 4:
                        grid_button5.setEnabled(true);
                        grid_button5.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button5.setTextColor(Color.WHITE);
                        grid_button5.setOnClickListener(this);
                        break;
                    case 5:
                        grid_button6.setEnabled(true);
                        grid_button6.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button6.setTextColor(Color.WHITE);
                        grid_button6.setOnClickListener(this);
                        break;
                    case 6:
                        grid_button7.setEnabled(true);
                        grid_button7.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button7.setTextColor(Color.WHITE);
                        grid_button7.setOnClickListener(this);
                        break;
                    case 7:
                        grid_button8.setEnabled(true);
                        grid_button8.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button8.setTextColor(Color.WHITE);
                        grid_button8.setOnClickListener(this);
                        break;
                    case 8:
                        grid_button9.setEnabled(true);
                        grid_button9.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button9.setTextColor(Color.WHITE);
                        grid_button9.setOnClickListener(this);
                        break;
                    case 9:
                        grid_button10.setEnabled(true);
                        grid_button10.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button10.setTextColor(Color.WHITE);
                        grid_button10.setOnClickListener(this);
                        break;
                    case 10:
                        grid_button11.setEnabled(true);
                        grid_button11.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button11.setTextColor(Color.WHITE);
                        grid_button11.setOnClickListener(this);
                        break;
                    case 11:
                        grid_button12.setEnabled(true);
                        grid_button12.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button12.setTextColor(Color.WHITE);
                        grid_button12.setOnClickListener(this);
                        break;
                    case 12:
                        grid_button13.setEnabled(true);
                        grid_button13.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button13.setTextColor(Color.WHITE);
                        grid_button13.setOnClickListener(this);
                        break;
                    case 13:
                        grid_button14.setEnabled(true);
                        grid_button14.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button14.setTextColor(Color.WHITE);
                        grid_button14.setOnClickListener(this);
                        break;
                    case 14:
                        grid_button15.setEnabled(true);
                        grid_button15.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button15.setTextColor(Color.WHITE);
                        grid_button15.setOnClickListener(this);
                        break;
                    case 15:
                        grid_button16.setEnabled(true);
                        grid_button16.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button16.setTextColor(Color.WHITE);
                        grid_button16.setOnClickListener(this);
                        break;
                    case 16:
                        grid_button17.setEnabled(true);
                        grid_button17.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button17.setTextColor(Color.WHITE);
                        grid_button17.setOnClickListener(this);
                        break;
                    case 17:
                        grid_button18.setEnabled(true);
                        grid_button18.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button18.setTextColor(Color.WHITE);
                        grid_button18.setOnClickListener(this);
                        break;
                    case 18:
                        grid_button19.setEnabled(true);
                        grid_button19.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button19.setTextColor(Color.WHITE);
                        grid_button19.setOnClickListener(this);
                        break;
                    case 19:
                        grid_button20.setEnabled(true);
                        grid_button20.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button20.setTextColor(Color.WHITE);
                        grid_button20.setOnClickListener(this);
                        break;
                    case 20:
                        grid_button21.setEnabled(true);
                        grid_button21.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button21.setTextColor(Color.WHITE);
                        grid_button21.setOnClickListener(this);
                        break;
                    case 21:
                        grid_button22.setEnabled(true);
                        grid_button22.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button22.setTextColor(Color.WHITE);
                        grid_button22.setOnClickListener(this);
                        break;
                    case 22:
                        grid_button23.setEnabled(true);
                        grid_button23.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button23.setTextColor(Color.WHITE);
                        grid_button23.setOnClickListener(this);
                        break;
                    case 23:
                        grid_button24.setEnabled(true);
                        grid_button24.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button24.setTextColor(Color.WHITE);
                        grid_button24.setOnClickListener(this);
                        break;
                    case 24:
                        grid_button25.setEnabled(true);
                        grid_button25.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button25.setTextColor(Color.WHITE);
                        grid_button25.setOnClickListener(this);
                        break;
                    case 25:
                        grid_button26.setEnabled(true);
                        grid_button26.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button26.setTextColor(Color.WHITE);
                        grid_button26.setOnClickListener(this);
                        break;
                    case 26:
                        grid_button27.setEnabled(true);
                        grid_button27.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button27.setTextColor(Color.WHITE);
                        grid_button27.setOnClickListener(this);
                        break;
                    case 27:
                        grid_button28.setEnabled(true);
                        grid_button28.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button28.setTextColor(Color.WHITE);
                        grid_button28.setOnClickListener(this);
                        break;
                    case 28:
                        grid_button29.setEnabled(true);
                        grid_button29.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button29.setTextColor(Color.WHITE);
                        grid_button29.setOnClickListener(this);
                        break;
                    case 29:
                        grid_button30.setEnabled(true);
                        grid_button30.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button30.setTextColor(Color.WHITE);
                        grid_button30.setOnClickListener(this);
                        break;
                    case 30:
                        grid_button31.setEnabled(true);
                        grid_button31.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button31.setTextColor(Color.WHITE);
                        grid_button31.setOnClickListener(this);
                        break;
                    case 31:
                        grid_button32.setEnabled(true);
                        grid_button32.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button32.setTextColor(Color.WHITE);
                        grid_button32.setOnClickListener(this);
                        break;
                    case 32:
                        grid_button33.setEnabled(true);
                        grid_button33.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button33.setTextColor(Color.WHITE);
                        grid_button33.setOnClickListener(this);
                        break;
                    case 33:
                        grid_button34.setEnabled(true);
                        grid_button34.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button34.setTextColor(Color.WHITE);
                        grid_button34.setOnClickListener(this);
                        break;
                    case 34:
                        grid_button35.setEnabled(true);
                        grid_button35.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button35.setTextColor(Color.WHITE);
                        grid_button35.setOnClickListener(this);
                        break;
                    case 35:
                        grid_button36.setEnabled(true);
                        grid_button36.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button36.setTextColor(Color.WHITE);
                        grid_button36.setOnClickListener(this);
                        break;
                    case 36:
                        grid_button37.setEnabled(true);
                        grid_button37.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button37.setTextColor(Color.WHITE);
                        grid_button37.setOnClickListener(this);
                        break;
                    case 37:
                        grid_button38.setEnabled(true);
                        grid_button38.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button38.setTextColor(Color.WHITE);
                        grid_button38.setOnClickListener(this);
                        break;
                    case 38:
                        grid_button39.setEnabled(true);
                        grid_button39.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button39.setTextColor(Color.WHITE);
                        grid_button39.setOnClickListener(this);
                        break;
                    case 39:
                        grid_button40.setEnabled(true);
                        grid_button40.setBackgroundResource(R.drawable.grid_button_green_radius);
                        grid_button40.setTextColor(Color.WHITE);
                        grid_button40.setOnClickListener(this);
                        break;
                }
            }
        }
    }

    private void init() {
        scrapList_text = findViewById(R.id.scrapList_text);
        grid_button1 = findViewById(R.id.grid_button1);
        grid_button2 = findViewById(R.id.grid_button2);
        grid_button3 = findViewById(R.id.grid_button3);
        grid_button4 = findViewById(R.id.grid_button4);
        grid_button5 = findViewById(R.id.grid_button5);
        grid_button6 = findViewById(R.id.grid_button6);
        grid_button7 = findViewById(R.id.grid_button7);
        grid_button8 = findViewById(R.id.grid_button8);
        grid_button9 = findViewById(R.id.grid_button9);
        grid_button10 = findViewById(R.id.grid_button10);
        grid_button11 = findViewById(R.id.grid_button11);
        grid_button12 = findViewById(R.id.grid_button12);
        grid_button13 = findViewById(R.id.grid_button13);
        grid_button14 = findViewById(R.id.grid_button14);
        grid_button15 = findViewById(R.id.grid_button15);
        grid_button16 = findViewById(R.id.grid_button16);
        grid_button17 = findViewById(R.id.grid_button17);
        grid_button18 = findViewById(R.id.grid_button18);
        grid_button19 = findViewById(R.id.grid_button19);
        grid_button20 = findViewById(R.id.grid_button20);
        grid_button21 = findViewById(R.id.grid_button21);
        grid_button22 = findViewById(R.id.grid_button22);
        grid_button23 = findViewById(R.id.grid_button23);
        grid_button24 = findViewById(R.id.grid_button24);
        grid_button25 = findViewById(R.id.grid_button25);
        grid_button26 = findViewById(R.id.grid_button26);
        grid_button27 = findViewById(R.id.grid_button27);
        grid_button28 = findViewById(R.id.grid_button28);
        grid_button29 = findViewById(R.id.grid_button29);
        grid_button30 = findViewById(R.id.grid_button30);
        grid_button31 = findViewById(R.id.grid_button31);
        grid_button32 = findViewById(R.id.grid_button32);
        grid_button33 = findViewById(R.id.grid_button33);
        grid_button34 = findViewById(R.id.grid_button34);
        grid_button35 = findViewById(R.id.grid_button35);
        grid_button36 = findViewById(R.id.grid_button36);
        grid_button37 = findViewById(R.id.grid_button37);
        grid_button38 = findViewById(R.id.grid_button38);
        grid_button39 = findViewById(R.id.grid_button39);
        grid_button40 = findViewById(R.id.grid_button40);
    }

    @Override
    public void onClick(View v) {
        intent = new Intent(getApplicationContext(), ProblemActivity.class);
        switch (v.getId()) {
            case R.id.grid_button1: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "1"); break;
            case R.id.grid_button2: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "2"); break;
            case R.id.grid_button3: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "3"); break;
            case R.id.grid_button4: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "4"); break;
            case R.id.grid_button5: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "5"); break;
            case R.id.grid_button6: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "6"); break;
            case R.id.grid_button7: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "7"); break;
            case R.id.grid_button8: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "8"); break;
            case R.id.grid_button9: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "9"); break;
            case R.id.grid_button10: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "10"); break;
            case R.id.grid_button11: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "11"); break;
            case R.id.grid_button12: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "12"); break;
            case R.id.grid_button13: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "13"); break;
            case R.id.grid_button14: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "14"); break;
            case R.id.grid_button15: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "15"); break;
            case R.id.grid_button16: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "16"); break;
            case R.id.grid_button17: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "17"); break;
            case R.id.grid_button18: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "18"); break;
            case R.id.grid_button19: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "19"); break;
            case R.id.grid_button20: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "20"); break;
            case R.id.grid_button21: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "21"); break;
            case R.id.grid_button22: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "22"); break;
            case R.id.grid_button23: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "23"); break;
            case R.id.grid_button24: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "24"); break;
            case R.id.grid_button25: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "25"); break;
            case R.id.grid_button26: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "26"); break;
            case R.id.grid_button27: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "27"); break;
            case R.id.grid_button28: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "28"); break;
            case R.id.grid_button29: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "29"); break;
            case R.id.grid_button30: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "30"); break;
            case R.id.grid_button31: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "31"); break;
            case R.id.grid_button32: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "32"); break;
            case R.id.grid_button33: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "33"); break;
            case R.id.grid_button34: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "34"); break;
            case R.id.grid_button35: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "35"); break;
            case R.id.grid_button36: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "36"); break;
            case R.id.grid_button37: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "37"); break;
            case R.id.grid_button38: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "38"); break;
            case R.id.grid_button39: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "39"); break;
            case R.id.grid_button40: intent.putExtra(MainActivity.period_data, area + "," + (index+1) + "," + "40"); break;
            //값 다 넣어야함.
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    //Toolbar 안에있는 값들 초기화
    private void settingapp_bar() {
        toolbar = findViewById(R.id.scrapList_toolbar);
        btnShowNavigationDrawer = toolbar.findViewById(R.id.navibutton);
        toolbar_cancel = toolbar.findViewById(R.id.toolbar_cancel);
        toolbar_cancel.setVisibility(View.VISIBLE);
        btnShowNavigationDrawer.setOnClickListener(onClickListener);
        drawerLayout = findViewById(R.id.scrapList_drawerlayout);
        navigationView = findViewById(R.id.scrapList_navigation);

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
                    for (int i = 0; i < list.size(); i++) {
                        scriptIndex += list.get(i).getScriptTotalCount();
                        if (list.get(i).getArrangeData().size() == 0) continue;
                        middleIndex += list.get(i).getArrangeData().size() / 10;
                    }
                    if (scriptIndex == 0) naviScriptText.setVisibility(View.GONE);
                    else {
                        naviScriptText.setVisibility(View.VISIBLE);
                        naviScriptText.setText(String.valueOf(scriptIndex));
                    }
                    if (middleIndex == 0) naviMiddleText.setVisibility(View.GONE);
                    else {
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
