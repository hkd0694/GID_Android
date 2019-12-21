package com.example.goindol_java.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.goindol_java.R;
import com.example.goindol_java.data.ExcelProblem;
import com.example.goindol_java.data.Period;
import com.example.goindol_java.popup.CheckActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.goindol_java.activity.SplashActivity.SETTINGS_PLAYER;

public class ProblemActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private ImageButton btnShowNavigationDrawer;

    private ImageButton navi_cancel;
    private ImageButton navi_home;
    private ImageButton toolbar_cancel;

    private TextView pro_text;
    private TextView pro_no;
    private ImageButton pro_script;
    private TextView pro_content;
    private RadioGroup pro_radiogroup;
    private RadioButton pro_radio1,pro_radio2,pro_radio3,pro_radio4,pro_radio5;
    private Button pro_answer;

    private String name;
    private int sheet_index;
    //내가 선택한 번호
    private int seleted;
    //처음 여길로 올 때 번호
    private int row_first;


    private Intent intent;

    private HSSFWorkbook hss;
    private HSSFSheet sh;
    private HSSFRow row;
    private HSSFCell cell;

    private SharedPreferences prefs;
    private Gson gson = new Gson();
    private List<Period> list = new ArrayList<>();
    private List<ExcelProblem> excelProblems = new ArrayList<>();
    private Type listType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        init();
        settingapp_bar();
        navi_header_click();
        name = getIntent().getStringExtra(MainActivity.period_data).split(",")[0];
        sheet_index = Integer.parseInt(getIntent().getStringExtra(MainActivity.period_data).split(",")[1]);
        pro_text.setText("한국사능력검정시험 " + name);
        try {
            //엑셀 시트 가져오기
            InputStream is;
            AssetManager assetManager = getAssets();
            is = assetManager.open("goindol.xls");
            POIFSFileSystem poif = new POIFSFileSystem(is);
            hss = new HSSFWorkbook(poif);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //정답 확인하기 버튼 누를 시 발생하는 리스너
        //
        pro_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int answer = (int)row.getCell(9).getNumericCellValue();
                String ck;
                if(answer == seleted) ck = "정답";
                else ck = "오답";
                Intent intent = new Intent(getApplicationContext(), CheckActivity.class);
                //"정답", "오답"을 체크해주기 위해 넘겨줌
                intent.putExtra("select",ck);
                //sheet 번호를 기억해야하기 때문에 같이 넘겨줌
                intent.putExtra("sheet_index",sheet_index);
                //sheet 번호와 같이 행번호도 기억해야 하기 때문에 intent를 통하여 넘겨줌.
                intent.putExtra("row_first",row_first);
                startActivityForResult(intent,101);
            }
        });

        //radiobutton을 누를 시 계속해서 발생하는 리스너 어떤 버튼을 눌렀는지 계속해서 기억하여 정답과 비교해야한다.
        pro_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.pro_radio1: seleted = 1; break;
                    case R.id.pro_radio2: seleted = 2; break;
                    case R.id.pro_radio3: seleted = 3; break;
                    case R.id.pro_radio4: seleted = 4; break;
                    case R.id.pro_radio5: seleted = 5; break;
                }
            }
        });

        pro_script.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    //CheckActivity 액티비티가 종료되면 onResume()함수가 다시 불리므로
    //SharedPreferences 데이터를 다시 불러와 다음 문제를 보여주기 위해 데이터를 불러온다.
    protected void onResume() {
        prefs = getSharedPreferences("shared", MODE_PRIVATE);
        name = prefs.getString(SETTINGS_PLAYER,null);
        listType = new TypeToken<ArrayList<Period>>() {}.getType();
        list = gson.fromJson(name, listType);
        //만약 마지막 번호가 2가 아닌 다른 값이 저장되어 있을 경우 사용자가 풀었던 번호가 있다는 뜻이므로 row_first를 마지막 번호로 넣어준다
        if(list.get(sheet_index).getIndex() != 2) {
            row_first = list.get(sheet_index).getIndex();
        } else{
            row_first = 2;
        }
        sh = hss.getSheetAt(sheet_index);
        row = sh.getRow(row_first);
        Log.e("Start",row_first + " ok");
        if(row != null){
            //Excel 데이터를 불러와 화면에 맞게 계속해서 불러온다.
            for(int i=1;i<=10;i++) {
                cell = row.getCell(i);
                switch (i){
                    case 1: pro_no.setText(String.valueOf(cell.getNumericCellValue())); break;
                    case 3: pro_content.setText(cell.getStringCellValue()); break;
                    case 4: pro_radio1.setText(cell.getStringCellValue()); break;
                    case 5: pro_radio2.setText(cell.getStringCellValue()); break;
                    case 6: pro_radio3.setText(cell.getStringCellValue()); break;
                    case 7: pro_radio4.setText(cell.getStringCellValue()); break;
                    case 8: pro_radio5.setText(cell.getStringCellValue()); break;
                }
            }
        }

        super.onResume();
    }

    @Override
    protected void onPause() {
        pro_radio1.setChecked(false); pro_radio2.setChecked(false);
        pro_radio3.setChecked(false); pro_radio4.setChecked(false);
        pro_radio5.setChecked(false);
        super.onPause();
    }

    //onActivityResult가 불리고 난 후에 onResume() 이 불림!!
    //저장 후에 row_first를 1 증가시켜서 onResume() 함수를 부른다.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //팝업 액티비티에서 다음 문제로 넘어갈 경우에 rows를 증가!!
        if(requestCode == 101) {
            if(resultCode == -1) {
                //마지막 번호 기억하기
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //해당 Activity xml 데이터 초기화 하는 함수
    private void init(){
        pro_text = findViewById(R.id.pro_text);
        pro_no = findViewById(R.id.pro_no);
        pro_script = findViewById(R.id.pro_script);
        pro_content = findViewById(R.id.pro_content);
        pro_radiogroup = findViewById(R.id.pro_radiogroup);
        pro_radio1 = findViewById(R.id.pro_radio1);
        pro_radio2 = findViewById(R.id.pro_radio2);
        pro_radio3 = findViewById(R.id.pro_radio3);
        pro_radio4 = findViewById(R.id.pro_radio4);
        pro_radio5 = findViewById(R.id.pro_radio5);
        pro_answer = findViewById(R.id.pro_answer);
    }

    //Toolbar 안에있는 값들 초기화
    private void settingapp_bar(){
        toolbar = findViewById(R.id.pro_toolbar);
        setSupportActionBar(toolbar);
        btnShowNavigationDrawer =  toolbar.findViewById(R.id.navibutton);
        toolbar_cancel = toolbar.findViewById(R.id.toolbar_cancel);
        toolbar_cancel.setVisibility(View.VISIBLE);
        btnShowNavigationDrawer.setOnClickListener(onClickListener);
        drawerLayout = findViewById(R.id.pro_drawlayout);
        actionBarDrawerToggle = setUpActionBarToggle();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView = findViewById(R.id.pro_navigation);
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
            switch (v.getId()){
                case R.id.navibutton:
                    drawerLayout.openDrawer(GravityCompat.END);
                    break;
            }
        }
    };

    //navi header를 클릭하면 발생하는 이벤트 함수 (엑스, 홈)
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
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

    }

    //nvai 몸통부분 안에 있는 버튼 클릭시 발생하는 리스너 (스크랩한 문제 보기, 중간정리 보기, 시험일정 세팅하기, 문제 초기화 하기)
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
                        intent = new Intent(getApplicationContext(),InterimActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        break;
                    case R.id.initial:
                        intent = new Intent(getApplicationContext(),InitialActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.END);
                return false;
            }
        });
    }

    private ActionBarDrawerToggle setUpActionBarToggle(){
        return new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.app_name, R.string.app_name);
    }

    @Override
    //뒤로 가기 버튼 막음
    public void onBackPressed() {
        return;
    }
}
