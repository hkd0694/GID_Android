package com.example.goindol_java.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.goindol_java.R;
import com.example.goindol_java.data.Period;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String period_data = "PERIOD";

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private ImageButton btnShowNavigationDrawer;
    private ImageButton navi_cancel;
    private ImageButton navi_home;
    private ImageButton toolbar_cancel;

    private TextView text;

    private Button origin_formation;
    private Button ancient_society;
    private Button goryeo_dynasty;
    private Button joseon_before;
    private Button joseon_after;
    private Button modern;
    private Button occupation_period;
    private Button modern_times;
    private Button random;

    private List<Period> list = new ArrayList<>();
    private SharedPreferences prefs;
    private Gson gson = new Gson();
    private Period period;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text_area);
        text.setText("한국사능력검정시험을 공부합니다.\n" + "어느 시대를 공부할까요?");
        settingapp_bar();
        navi_header_click();
        init();
        click_event();
        prefs = getSharedPreferences("shared", MODE_PRIVATE);
        String data = prefs.getString(SplashActivity.SETTINGS_PLAYER,null);
        Type listType = new TypeToken<ArrayList<Period>>() {}.getType();
        // 변환
        list = gson.fromJson(data, listType);
    }

    private void settingapp_bar(){
        toolbar = findViewById(R.id.toolbarInclude);
        setSupportActionBar(toolbar);
        toolbar_cancel = toolbar.findViewById(R.id.toolbar_cancel);
        toolbar_cancel.setVisibility(View.GONE);
        btnShowNavigationDrawer =  toolbar.findViewById(R.id.navibutton);
        btnShowNavigationDrawer.setOnClickListener(onClickListener);
        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = setUpActionBarToggle();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView = findViewById(R.id.design_navigation_view);
        setUpDrawerContent(navigationView);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.navibutton:
                    drawerLayout.openDrawer(GravityCompat.END);
                    break;
            }
        }
    };

    private void navi_header_click(){
        View naviView = navigationView.getHeaderView(0);
        navi_cancel = naviView.findViewById(R.id.navi_cancel);
        navi_home = naviView.findViewById(R.id.navi_home);

        navi_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(GravityCompat.END);
            }
        });

        navi_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void setUpDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.script :
                        break;
                    case R.id.setting:
                        break;
                    case R.id.middle:
                        break;
                    case R.id.initial:
                        break;
                }
                return false;
            }
        });
    }

    private ActionBarDrawerToggle setUpActionBarToggle(){
        return new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.app_name, R.string.app_name);
    }

    //시대별 버튼 초기화
    private void init(){
        origin_formation = findViewById(R.id.origin_formation);
        ancient_society = findViewById(R.id.ancient_society);
        goryeo_dynasty = findViewById(R.id.goryeo_dynasty);
        joseon_before = findViewById(R.id.joseon_before);
        joseon_after = findViewById(R.id.joseon_after);
        modern = findViewById(R.id.modern);
        modern_times = findViewById(R.id.modern_times);
        random = findViewById(R.id.random);
        occupation_period = findViewById(R.id.occupation_period);
    }

    //여기만 수정하면 댐;
    private void shared_check(String name){
        int index = 0;
        switch (name) {
            case "기원과 형성":     period = list.get(0); index = 0; break;
            case "고대사회":        period = list.get(1); index = 1; break;
            case "고려시대":        period = list.get(2); index = 2; break;
            case "조선전기":        period = list.get(3); index = 3; break;
            case "조선후기":        period = list.get(4); index = 4; break;
            case "근대 개화":       period = list.get(5); index = 5; break;
            case "일제 강점기":     period = list.get(6); index = 6;break;
            case "현대":            period = list.get(7); index = 7; break;
            case "랜덤":            period = list.get(8); index = 8; break;
        }
        Intent intent;
        if(period.getPeriod_data().size() == 0) {
            intent = new Intent(getApplicationContext(),LearnActivity.class);
        } else {
            intent = new Intent(getApplicationContext(),PopupActivity.class);
        }
        intent.putExtra(period_data,period.getPeriodic() + " " + index);
        startActivity(intent);
    }

    //시대별 버튼 클릭시 발생하는 리스너들
    private void click_event(){

        origin_formation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shared_check(origin_formation.getText().toString());
            }
        });

        ancient_society.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shared_check(ancient_society.getText().toString());
            }
        });

        goryeo_dynasty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shared_check(goryeo_dynasty.getText().toString());
            }
        });

        joseon_before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shared_check(joseon_before.getText().toString());
            }
        });

        joseon_after.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shared_check(joseon_after.getText().toString());
            }
        });

        modern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shared_check(modern.getText().toString());
            }
        });

        occupation_period.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shared_check(occupation_period.getText().toString());
            }
        });

        modern_times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shared_check(modern_times.getText().toString());
            }
        });

        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shared_check(random.getText().toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
