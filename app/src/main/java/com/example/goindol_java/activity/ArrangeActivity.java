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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goindol_java.Adapter.ArrangeAdapter;
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

import static com.example.goindol_java.activity.SettingActivity.SETTINGS_DATE;
import static com.example.goindol_java.activity.SplashActivity.SETTINGS_PLAYER;


public class ArrangeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private View toolbar;
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

    private List<ArrangeData> arrangeData = new ArrayList<>();
    private List<ArrangeData> recycler = new ArrayList<>();

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

    private String exam;
    private TextView toolbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrange);
        settingapp_bar();
        navi_header_click();
        init();
        intent = getIntent();
        page = intent.getIntExtra("page", 1);
        sheet_number = intent.getIntExtra("sheet", 1);
        arr_middle.setText("중간정리 " + page + "번째");

        prefs = getSharedPreferences("shared", MODE_PRIVATE);
        name = prefs.getString(SETTINGS_PLAYER, null);
        exam = prefs.getString(SETTINGS_DATE,"");
        listType = new TypeToken<ArrayList<Period>>() {
        }.getType();
        list = gson.fromJson(name, listType);

        if(!exam.equals("")) {
            toolbarText.setText("시험 " + exam.split("-")[0] + "년 " + exam.split("-")[1] + "월 " + exam.split("-")[2] + "일");
            toolbarText.setTextColor(Color.parseColor("#3698a0"));
        } else{
            toolbarText.setText("시험 일정을 기록해 보세요.");
            toolbarText.setTextColor(Color.parseColor("#e65555"));
        }

        // 저장되어 있는 값을 가져옴..
        arrangeData = list.get(sheet_number - 1).getArrangeData();
        Log.e("Start", arrangeData.size() + " 사이즈 몇개");
        recycler_adapter(arrangeData);

        arr_text.setText("한국사능력검정시험 " + list.get(sheet_number - 1).getPeriodic());

        //계속 진행하기 버튼을 누를 시 발생하는 리스너로
        //만약 마지막 페이지인 4번째 중간정리가 나오게 되면 더이상 풀 문제가 없으므로 EndActivity로 넘어가게 된다.
        //4번째 중간정리가 나오기 전까진 해당 Activity를 종료만 시킨다.
        arr_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrangeData.get(arrangeData.size() - 1).getNumber().equals("40")) {
                    //문제가 다 끝났으므로 시대별 종료 화면으로 넘어감...!!!!
                    intent = new Intent(getApplicationContext(), EndActivity.class);
                    intent.putExtra("name", list.get(sheet_number - 1).getPeriodic());
                    startActivity(intent);
                }
                finish();
            }
        });
    }

    //Recyclerview 데이터를 10개씩 추가하여 Adapter에 붙여주는 함수
    private void recycler_adapter(List<ArrangeData> arr) {
        int recy_index = (page * 10) - 10;
        for (int i = recy_index; i < recy_index + 10; i++) recycler.add(arr.get(i));
        ArrangeAdapter adapter = new ArrangeAdapter(this, recycler);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        arr_recyclerview.setLayoutManager(linearLayout);
        arr_recyclerview.setAdapter(adapter);
    }

    @Override
    //10개씩 데이터를 보여주기 때문에 해당 activity가 없어질 때 arraylist도 clear를 통해 초기화 해준다.
    protected void onStop() {
        recycler.clear();
        super.onStop();
    }

    //해당 Activity xml 에 있는 데이터들 초기화 해주는 함수
    private void init() {
        arr_text = findViewById(R.id.arr_text);
        arr_middle = findViewById(R.id.arr_middle);
        arr_button = findViewById(R.id.arr_button);
        arr_recyclerview = findViewById(R.id.arr_recyclerview);
    }

    //Toolbar 안에있는 값들 초기화
    private void settingapp_bar() {
        toolbar = findViewById(R.id.arr_toolbar);
        btnShowNavigationDrawer = toolbar.findViewById(R.id.navibutton);
        toolbar_cancel = toolbar.findViewById(R.id.toolbar_cancel);
        toolbar_cancel.setVisibility(View.VISIBLE);
        btnShowNavigationDrawer.setOnClickListener(onClickListener);
        drawerLayout = findViewById(R.id.arr_drawerlayout);
        navigationView = findViewById(R.id.arr_navigation);
        toolbarText = toolbar.findViewById(R.id.toolbar_textView);

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



    @Override
    //뒤로가기 버튼 막음
    public void onBackPressed() {
        return;
    }
}
