package com.example.goindol_java.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.goindol_java.R;
import com.google.android.material.navigation.NavigationView;

public class LearnActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private ImageButton btnShowNavigationDrawer;
    private ImageButton navi_cancel;
    private ImageButton navi_home;
    private ImageButton toolbar_cancel;

    private String name;
    private TextView textView;
    private Button learn_button;
    private Button learn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        settingapp_bar();
        navi_header_click();
        name = getIntent().getStringExtra("name");
        textView = findViewById(R.id.learn_text);
        learn_button = findViewById(R.id.learn_cancel);
        learn_start = findViewById(R.id.learn_start);

        textView.setText("한국사능력검정시험을 위해\n" + name + "를 공부합니다.");

        learn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        learn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //학습 시작.
            }
        });
    }

    private void settingapp_bar(){
        toolbar = findViewById(R.id.toolbarInclude);
        setSupportActionBar(toolbar);
        btnShowNavigationDrawer =  toolbar.findViewById(R.id.navibutton);
        toolbar_cancel = toolbar.findViewById(R.id.toolbar_cancel);
        toolbar_cancel.setVisibility(View.VISIBLE);
        btnShowNavigationDrawer.setOnClickListener(onClickListener);
        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = setUpActionBarToggle();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView = findViewById(R.id.design_navigation_view);
        setUpDrawerContent(navigationView);

        toolbar_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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

    @Override
    public void onBackPressed() {
        return;
    }
}
