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
import androidx.viewpager.widget.ViewPager;

import com.example.goindol_java.Adapter.ArrangePagerAdapter;
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

import me.relex.circleindicator.CircleIndicator;

import static androidx.annotation.Dimension.DP;

public class TotalinterimActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private View toolbar;
    private NavigationView navigationView;
    private ImageButton btnShowNavigationDrawer;
    private ImageButton navi_cancel;
    private ImageButton navi_home;
    private ImageButton toolbar_cancel;

    private TextView total_text;

    private CircleIndicator indicator;

    private View naviScriptView;
    private View naviMiddleView;
    private View naviSettingView;
    private View naviInitialView;

    private TextView naviMiddleText;
    private TextView naviScriptText;

    private int scriptIndex = 0;
    private int middleIndex = 0;

    private ViewPager viewPager;
    private Intent intent;
    private String name;
    private int index;
    private ArrangePagerAdapter arrangePagerAdapter;

    private List<Period> list = new ArrayList<>();
    private List<ArrangeData> arrangeData = new ArrayList<>();
    private SharedPreferences prefs;
    private Gson gson = new Gson();
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_totalinterim);
        settingapp_bar();
        navi_header_click();
        name = getIntent().getStringExtra("area");
        index = getIntent().getIntExtra("index", 10);
        total_text = findViewById(R.id.total_text);
        total_text.setText(name + " 중간정리를 다시 보면서\n" + "한번 더 확인해 보세요.");
        prefs = getSharedPreferences("shared", MODE_PRIVATE);
        data = prefs.getString(SplashActivity.SETTINGS_PLAYER, null);
        Type listType = new TypeToken<ArrayList<Period>>() {
        }.getType();
        list = gson.fromJson(data, listType);
        viepager_adapter();
    }

    private void viepager_adapter() {
        viewPager = findViewById(R.id.total_viewpager);
        indicator = findViewById(R.id.indicator);

        arrangePagerAdapter = new ArrangePagerAdapter(this, list.get(index).getArrangeData());
        viewPager.setClipToPadding(false);
        viewPager.setAdapter(arrangePagerAdapter);
        indicator.setViewPager(viewPager);
        float density = getResources().getDisplayMetrics().density;
        int margin = (int) (DP * density);
        viewPager.setPadding(margin, 0, margin, 0);
        viewPager.setPageMargin(margin / 2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //Toolbar 안에있는 값들 초기화
    private void settingapp_bar() {
        toolbar = findViewById(R.id.total_toolbar);
        toolbar_cancel = toolbar.findViewById(R.id.toolbar_cancel);
        toolbar_cancel.setVisibility(View.GONE);
        btnShowNavigationDrawer = toolbar.findViewById(R.id.navibutton);
        btnShowNavigationDrawer.setOnClickListener(onClickListener);
        drawerLayout = findViewById(R.id.total_drawerlayout);
        navigationView = findViewById(R.id.total_navigation);
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
