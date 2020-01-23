package com.example.goindol_java.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.goindol_java.R;
import com.example.goindol_java.data.Period;
import com.example.goindol_java.popup.InitPopupActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.goindol_java.activity.SplashActivity.SETTINGS_PLAYER;

public class EndActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private View toolbar;
    private NavigationView navigationView;
    private ImageButton btnShowNavigationDrawer;
    private ImageButton navi_cancel;
    private ImageButton navi_home;
    private ImageButton toolbar_cancel;

    private TextView end_text;
    private Button end_button;
    private ImageView end_image;

    private String name;
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
    private String name1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        init();
        settingapp_bar();
        navi_header_click();
        intent = getIntent();
        name = intent.getStringExtra("name");
        end_text.setText(name + " 모든 문제를 풀었습니다!\n" + "메인화면으로 돌아가서\n" + "다른 시대를 선택해주세요.");
        //시대 마다 end_image 화면을 다르게 해줘야함!
        if (name.equals("고려시대")) {
            end_image.setImageResource(R.drawable.goryeo);
        } else if (name.equals("고대사회")) {
            end_image.setImageResource(R.drawable.godae_image);
        } else {
            end_image.setImageResource(R.drawable.area_image);
        }

        prefs = getSharedPreferences("shared", MODE_PRIVATE);
        name = prefs.getString(SETTINGS_PLAYER, null);
        listType = new TypeToken<ArrayList<Period>>() {
        }.getType();
        list = gson.fromJson(name1, listType);
        //메인화면 돌아가기 버튼을 클릭 시 발생하는 리스너로
        //Activitiy Stack 안에 있는 MainActivity 위에 있는 Activity를 모두 없애고 MainActivity를 다시 불러서 재사용 한다.
        end_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    //해당 Activity xml 에 있는 데이터들 초기화 해주는 함수
    private void init() {
        end_text = findViewById(R.id.end_textview);
        end_button = findViewById(R.id.end_button);
        end_image = findViewById(R.id.end_imageview);
    }

    //Toolbar 안에있는 값들 초기화
    private void settingapp_bar() {
        toolbar = findViewById(R.id.end_toolbar);
        btnShowNavigationDrawer = toolbar.findViewById(R.id.navibutton);
        toolbar_cancel = toolbar.findViewById(R.id.toolbar_cancel);
        toolbar_cancel.setVisibility(View.VISIBLE);
        btnShowNavigationDrawer.setOnClickListener(onClickListener);
        drawerLayout = findViewById(R.id.end_drawerlayout);
        navigationView = findViewById(R.id.end_navigation);
        setUpDrawerContent(navigationView);

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

    //nvai 몸통부분 안에 있는 버튼 클릭시 발생하는 리스너 (스크랩한 문제 보기, 중간정리 보기, 시험일정 세팅하기, 문제 초기화 하기)
    private void setUpDrawerContent(NavigationView navi) {
        navi.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.script:
                        intent = new Intent(getApplicationContext(), ScrapActivity.class);
                        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        break;
                    case R.id.setting:
                        break;
                    case R.id.middle:
                        intent = new Intent(getApplicationContext(), InterimActivity.class);
                        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        break;
                    case R.id.initial:
                        intent = new Intent(getApplicationContext(), InitPopupActivity.class);
                        //intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.END);
                return false;
            }
        });
    }

    //뒤로가기 버튼 막음
    @Override
    public void onBackPressed() {
        return;
    }
}
