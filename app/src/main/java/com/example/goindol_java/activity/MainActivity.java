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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.goindol_java.R;
import com.example.goindol_java.data.Period;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private ImageButton btnShowNavigationDrawer;
    private ImageButton navi_cancel;
    private ImageButton navi_home;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settingapp_bar();
        navi_header_click();
        init();
        click_event();
        prefs = getSharedPreferences("shared", MODE_PRIVATE);
        String data = prefs.getString(SplashActivity.SETTINGS_PLAYER,null);
        Type listType = new TypeToken<ArrayList<Period>>() {}.getType();
        // 변환
        list = gson.fromJson(data, listType);
        /*for(Period period : list){
            Log.e("Start",period.getPeriodic() + " : " + period.getPeriod_data().size());
        }*/
    }

    private void settingapp_bar(){
        toolbar = findViewById(R.id.toolbarInclude);
        setSupportActionBar(toolbar);
        //여기서 setContentView로 설정되어있는건 activity_main이므로
        //toolbar에 구현해둔 컴포넌트를 findViewById로 가져오기위해
        //toolbar.findViewById로 찾아준다
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

    //시대별 버튼 클릭시 발생하는 리스너들
    private void click_event(){

        origin_formation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PopupActivity.class);
                intent.putExtra("name",origin_formation.getText().toString());
                startActivity(intent);
            }
        });

        ancient_society.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        goryeo_dynasty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        joseon_before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        joseon_after.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        modern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        occupation_period.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        modern_times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
