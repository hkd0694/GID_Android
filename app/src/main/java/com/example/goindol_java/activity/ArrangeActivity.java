package com.example.goindol_java.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.goindol_java.Adapter.ArrangeAdapter;
import com.example.goindol_java.R;
import com.example.goindol_java.data.ArrangeData;
import com.example.goindol_java.data.ExcelProblem;
import com.example.goindol_java.data.Period;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.goindol_java.activity.SplashActivity.SETTINGS_PLAYER;

public class ArrangeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private ImageButton btnShowNavigationDrawer;
    private ImageButton navi_cancel;
    private ImageButton navi_home;
    private ImageButton toolbar_cancel;

    private TextView arr_text;
    private TextView arr_middle;
    private Button arr_button;
    private RecyclerView arr_recyclerview;

    private Intent intent;
    private int page;
    private int sheet_number;

    private SharedPreferences prefs;
    private Gson gson = new Gson();
    private List<Period> list = new ArrayList<>();
    private List<ArrangeData> arrangeData = new ArrayList<>();
    private List<ArrangeData> recycler = new ArrayList<>();
    private Type listType;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrange);
        settingapp_bar();
        navi_header_click();
        init();
        intent = getIntent();
        page = intent.getIntExtra("page",1);
        sheet_number = intent.getIntExtra("sheet",1);
        arr_middle.setText("중간정리 " + page + "번째");

        prefs = getSharedPreferences("shared", MODE_PRIVATE);
        name = prefs.getString(SETTINGS_PLAYER,null);
        listType = new TypeToken<ArrayList<Period>>() {}.getType();
        list = gson.fromJson(name, listType);

        // 저장되어 있는 값을 가져옴..
        arrangeData = list.get(sheet_number).getArrangeData();
        Log.e("Start", arrangeData.size() + " 사이즈 몇개");
        recycler_adapter(arrangeData);

        arr_text.setText("한국사능력검정시험 " + list.get(sheet_number).getPeriodic());

        arr_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void recycler_adapter(List<ArrangeData> arr){
        int recy_index = (page * 10) - 10;
        for(int i=recy_index; i< recy_index+10;i++) {
            recycler.add(arr.get(i));
        }
        Log.e("Start", recycler.size() + " 사이즈??");
        ArrangeAdapter adapter = new ArrangeAdapter(this,recycler);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        arr_recyclerview.setLayoutManager(linearLayout);
        arr_recyclerview.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        recycler.clear();
        super.onStop();
    }

    private void init(){
        arr_text = findViewById(R.id.arr_text);
        arr_middle = findViewById(R.id.arr_middle);
        arr_button = findViewById(R.id.arr_button);
        arr_recyclerview = findViewById(R.id.arr_recyclerview);
    }

    private void settingapp_bar(){
        toolbar = findViewById(R.id.arr_toolbar);
        setSupportActionBar(toolbar);
        btnShowNavigationDrawer =  toolbar.findViewById(R.id.navibutton);
        toolbar_cancel = toolbar.findViewById(R.id.toolbar_cancel);
        toolbar_cancel.setVisibility(View.VISIBLE);
        btnShowNavigationDrawer.setOnClickListener(onClickListener);
        drawerLayout = findViewById(R.id.arr_drawerlayout);
        actionBarDrawerToggle = setUpActionBarToggle();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView = findViewById(R.id.arr_navigation);
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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    private void setUpDrawerContent(NavigationView navi){
        navi.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
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

}
