package com.example.goindol_java.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.goindol_java.R;
import com.example.goindol_java.popup.InitPopupActivity;
import com.google.android.material.navigation.NavigationView;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.IOException;
import java.io.InputStream;

public class LearnActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private View toolbar;
    private NavigationView navigationView;
    private ImageButton btnShowNavigationDrawer;
    private ImageButton navi_cancel;
    private ImageButton navi_home;
    private ImageButton toolbar_cancel;

    private Intent intent;

    private String name;
    private TextView textView;
    private Button learn_cancel;
    private Button learn_start;

    private TextView learnArrange;

    private HSSFWorkbook hss;
    private HSSFSheet sh;
    private HSSFRow row;
    private HSSFCell cell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        settingapp_bar();
        navi_header_click();
        name = getIntent().getStringExtra(MainActivity.period_data);
        textView = findViewById(R.id.learn_text);
        learn_cancel = findViewById(R.id.learn_cancel);
        learn_start = findViewById(R.id.learn_start);
        learnArrange = findViewById(R.id.learn_arrange);
        textView.setText("한국사능력검정시험을 위해\n" + name.split(",")[0] + "를 공부합니다.");

        sh = hss.getSheetAt(8);
        row = sh.getRow(Integer.parseInt(name.split(",")[1]) - 1);
        cell = row.getCell(0);
        learnArrange.setText(cell.getStringCellValue());

        //뒤로가기 버튼을 누를 시 해당 Activity를 종료하고 MainActivity로 넘어간다.
        learn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //학습 시작 버튼을 누를시 발생하는 리스너
        //해당 시대 데이터를 넘겨 주면서 ProblemActivity로 넘어간다.
        learn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //학습 시작.
                Intent intent = new Intent(getApplicationContext(), ProblemActivity.class);
                intent.putExtra(MainActivity.period_data, name);
                startActivity(intent);
                finish();
            }
        });
    }

    //Toolbar 안에있는 값들 초기화
    private void settingapp_bar() {
        toolbar = findViewById(R.id.learn_toolbar);
        btnShowNavigationDrawer = toolbar.findViewById(R.id.navibutton);
        toolbar_cancel = toolbar.findViewById(R.id.toolbar_cancel);
        toolbar_cancel.setVisibility(View.VISIBLE);
        btnShowNavigationDrawer.setOnClickListener(onClickListener);
        drawerLayout = findViewById(R.id.learn_drawerlayout);
        navigationView = findViewById(R.id.learn_navigation);
        setUpDrawerContent(navigationView);

        try {
            //엑셀 시트 가져오기
            InputStream is;
            AssetManager assetManager = getAssets();
            is = assetManager.open("goindol_update.xls");
            POIFSFileSystem poif = new POIFSFileSystem(is);
            hss = new HSSFWorkbook(poif);
        } catch (IOException e) {
            e.printStackTrace();
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
                    break;
            }
        }
    };

    //navi header를 클릭하면 발생하는 이벤트 함수 (엑스, 홈)
    private void navi_header_click() {
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
