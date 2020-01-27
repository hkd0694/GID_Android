package com.example.goindol_java.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.goindol_java.R;
import com.example.goindol_java.data.Period;
import com.example.goindol_java.popup.InitPopupActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.goindol_java.activity.SplashActivity.SETTINGS_PLAYER;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String SETTINGS_DATE = "setting";

    private DrawerLayout drawerLayout;
    private View toolbar;
    private NavigationView navigationView;
    private ImageButton btnShowNavigationDrawer;
    private ImageButton navi_cancel;
    private ImageButton navi_home;
    private ImageButton toolbar_cancel;
    private TextView toolbarText;

    private Button settingButton;
    private Button settingYearButton;
    private Button settingMonthButton;
    private Button settingDayButton;

    private Intent intent;

    private View naviScriptView;
    private View naviMiddleView;
    private View naviSettingView;
    private View naviInitialView;

    private TextView naviMiddleText;
    private TextView naviScriptText;

    private int scriptIndex = 0;
    private int middleIndex = 0;

    private SharedPreferences prefs;
    private Gson gson = new Gson();
    private List<Period> list = new ArrayList<>();
    private Type listType;
    private String name;

    private SpinnerDatePickerDialogBuilder builder;
    private Calendar cal = Calendar.getInstance();

    private String exam;
    private int year;
    private int month;
    private int days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        prefs = getSharedPreferences("shared", MODE_PRIVATE);
        name = prefs.getString(SETTINGS_PLAYER, null);
        exam = prefs.getString(SETTINGS_DATE,"");
        listType = new TypeToken<ArrayList<Period>>() {
        }.getType();
        list = gson.fromJson(name, listType);
        settingapp_bar();
        navi_header_click();
        init();
        if(!exam.equals("")) {
            settingYearButton.setText(exam.split("-")[0]);
            settingMonthButton.setText(exam.split("-")[1]);
            settingDayButton.setText(exam.split("-")[2]);
            year = Integer.parseInt(exam.split("-")[0]);
            month = Integer.parseInt(exam.split("-")[1]);
            days = Integer.parseInt(exam.split("-")[2]);
        }
        settingButton.setOnClickListener(this);
    }

    private void init() {
        settingButton = findViewById(R.id.setting_button);
        settingYearButton = findViewById(R.id.setting_year);
        settingMonthButton = findViewById(R.id.setting_month);
        settingDayButton = findViewById(R.id.setting_day);
        settingYearButton.setOnClickListener(this);
        settingMonthButton.setOnClickListener(this);
        settingDayButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_button:
                exam = String.format("%d-%02d-%02d",year,month,days);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(SETTINGS_DATE,exam);
                editor.commit();
                finish();
                break;
            case R.id.setting_year:
            case R.id.setting_month:
            case R.id.setting_day:
                if(exam.equals("")) {
                    year = cal.get(cal.YEAR);
                    month = cal.get(cal.MONTH)+1;
                    days = cal.get(cal.DATE);
                }
                builder = new SpinnerDatePickerDialogBuilder();
                builder.context(this).defaultDate(year,month-1,days).callback(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int years, int monthOfYear, int dayOfMonth) {
                        String settingDate = String.format("%d-%02d-%02d",years,(monthOfYear+1),dayOfMonth);
                        year = years;
                        month = monthOfYear+1;
                        days = dayOfMonth;
                        settingYearButton.setText(String.valueOf(years));
                        settingMonthButton.setText(settingDate.split("-")[1]);
                        settingDayButton.setText(settingDate.split("-")[2]);
                    }
                }).build().show();
                break;
        }
    }

    //Toolbar 안에있는 값들 초기화
    private void settingapp_bar() {
        toolbar = findViewById(R.id.setting_toolbar);
        btnShowNavigationDrawer = toolbar.findViewById(R.id.navibutton);
        toolbar_cancel = toolbar.findViewById(R.id.toolbar_cancel);
        toolbar_cancel.setVisibility(View.VISIBLE);
        toolbarText = toolbar.findViewById(R.id.toolbar_textView);
        btnShowNavigationDrawer.setOnClickListener(onClickListener);
        drawerLayout = findViewById(R.id.setting_drawerlayout);
        navigationView = findViewById(R.id.setting_navigation);

        if(!exam.equals("")) {
            toolbarText.setText("시험 " + exam.split("-")[0] + "년 " + exam.split("-")[1] + "월 " + exam.split("-")[2] + "일");
            toolbarText.setTextColor(Color.parseColor("#3698a0"));
        } else{
            toolbarText.setText("시험 일정을 기록해 보세요.");
            toolbarText.setTextColor(Color.parseColor("#e65555"));
        }

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
