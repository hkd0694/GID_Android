package com.example.goindol_java.popup;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.goindol_java.R;
import com.example.goindol_java.activity.ArrangeActivity;
import com.example.goindol_java.data.ArrangeData;
import com.example.goindol_java.data.ExcelProblem;
import com.example.goindol_java.data.Period;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

//Popup식으로 화면에 띄우기 때문에 Activity를 상속한다.
public class CheckActivity extends Activity {

    private ImageButton check_script;
    private TextView check_answer;
    private ImageView check_image;
    private ImageView check_cry;
    private TextView check_explanation;
    private Button check_back;
    private Button check_next;

    private String check;
    private int sheet_indexs;
    private int row_number;
    private Intent intent;

    private HSSFWorkbook hss;
    private HSSFSheet sh;
    private HSSFRow row;
    private HSSFCell cell;

    private SharedPreferences prefs;
    private Gson gson = new Gson();
    private List<Period> list = new ArrayList<>();
    private List<ExcelProblem> excelProblems = new ArrayList<>();
    private List<ArrangeData> arrangeData = new ArrayList<>();
    private Type listType;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_check);
        getWindow().setBackgroundDrawable(new PaintDrawable(Color.TRANSPARENT));
        init();
        intent = getIntent();
        //"정답" or "오답"
        check = intent.getStringExtra("select");
        //시트 번호
        sheet_indexs = intent.getIntExtra("sheet_index",2);
        // 행 번호
        row_number = intent.getIntExtra("row_first",1);

        prefs = getSharedPreferences("shared", MODE_PRIVATE);
        name = prefs.getString(SETTINGS_PLAYER,null);
        listType = new TypeToken<ArrayList<Period>>() {}.getType();
        list = gson.fromJson(name, listType);

        excelProblems = list.get(sheet_indexs-1).getPeriod_data();
        arrangeData = list.get(sheet_indexs-1).getArrangeData();

        //엑셀 파일 불러와 데이터 저장
        try {
            InputStream is;
            AssetManager assetManager = getAssets();
            is = assetManager.open("goindol_update.xls");
            POIFSFileSystem poif = new POIFSFileSystem(is);
            hss = new HSSFWorkbook(poif);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(list.get(sheet_indexs-1).getScriptData().get(row_number-1).isScript()) check_script.setImageResource(R.drawable.star_active_darkblue);
        else {
            check_script.setImageResource(R.drawable.star_normal_darkblue);
        }

        check_script.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable temp = check_script.getDrawable();
                Drawable temp1 = getDrawable(R.drawable.star_normal_darkblue);
                Bitmap tmpBitmap = ((BitmapDrawable)temp).getBitmap();
                Bitmap tmpBitmap1 = ((BitmapDrawable)temp1).getBitmap();
                if(tmpBitmap.equals(tmpBitmap1)) {
                    list.get(sheet_indexs-1).getScriptData().get(row_number-1).setScript(true);
                    int add_count = list.get(sheet_indexs-1).getScriptData().get(row_number-1).getCount();
                    list.get(sheet_indexs-1).getScriptData().get(row_number-1).setCount(add_count+1);
                    check_script.setImageResource(R.drawable.star_active_darkblue);
                } else{
                    list.get(sheet_indexs-1).getScriptData().get(row_number-1).setScript(false);
                    int delete_count = list.get(sheet_indexs-1).getScriptData().get(row_number-1).getCount();
                    list.get(sheet_indexs-1).getScriptData().get(row_number-1).setCount(delete_count-1);
                    check_script.setImageResource(R.drawable.star_normal_darkblue);
                }
                gson  = new GsonBuilder().create();
                String json = gson.toJson(list, listType);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(SETTINGS_PLAYER, json);
                editor.commit();
            }
        });

        sh = hss.getSheetAt(sheet_indexs);
        row = sh.getRow(row_number);
        //시트 번호에서 해설 부분 가져오기
        cell = row.getCell(8);
        if(check.equals("정답")){
            check_image.setImageResource(R.drawable.good);
            check_answer.setText("정답을 맞췄습니다!");
        } else  {
            check_image.setImageResource(R.drawable.wrong);
            check_cry.setVisibility(View.VISIBLE);
            check_answer.setText("틀렸습니다.");
        }
        check_explanation.setText(cell.getStringCellValue());

        //문제로 돌아가기 버튼을 누를 시 발생하는 리스너
        //정답인지 오답인지만 보여주고, SharedPreferences에다가 저장은 하지 않고 Activity 종료
        check_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //다음 문제 버튼를 누를 시 발생하는 리스너
        check_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExcelProblem pro = saveExcelProblem();
                ArrangeData arr = saveArrangeData();
                //중복이 있을 수 있음...;; 특히 스크랩 문제를 풀 때나 수정 필요함...
                excelProblems.add(pro);
                arrangeData.add(arr);
                //문제 ArrayList로 add 하여 저장
                list.get(sheet_indexs-1).setPeriod_data(excelProblems);
                list.get(sheet_indexs-1).setArrangeData(arrangeData);
                //현재 푼 문제가 아닌 다음 번호를 기억해 저장
                int next = row_number + 1;
                list.get(sheet_indexs-1).setIndex(next);
                gson  = new GsonBuilder().create();
                String json = gson.toJson(list, listType);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(SETTINGS_PLAYER, json);
                editor.commit();
                // 사용자가 10문제를 풀 때마다 중간정리를 보여주기 위해 ArrangeActivity로 넘어간다.
                //만약 아직 10문제를 못 풀었으면 setResult를 통해 전 액티비티로 돌아간다.
                if(pro.getExcel_no() % 10 == 0) {
                    intent = new Intent(getApplicationContext(), ArrangeActivity.class);
                    int page = pro.getExcel_no() / 10;
                    intent.putExtra("page",page);
                    intent.putExtra("sheet",sheet_indexs);
                    startActivity(intent);
                } else setResult(RESULT_OK);
                finish();
            }
        });
    }

    //SharedPreferences 에다가 저장하기 위해 ArrangeData class에 데이터 넣는 함수
    private ArrangeData saveArrangeData(){
        ArrangeData arrangeData = new ArrangeData();
        arrangeData.setNumber(String.valueOf((int)row.getCell(0).getNumericCellValue()));
        if(check.equals("정답")) {
            arrangeData.setCheck("정답");
        } else arrangeData.setCheck("오답");
        //Excel 에 요약 정보 추가되면 그에 맞는 인덱스 넣어줘야함 지금은 보기 5번으로 고정 시킴.
        arrangeData.setSummary(row.getCell(9).getStringCellValue());
        return arrangeData;
    }

    //SharedPreferences에다가 저장하기 위해 ExcelProblem class에 데이터 넣는 함수
    private ExcelProblem saveExcelProblem(){
        ExcelProblem problem = new ExcelProblem();
        problem.setExcel_no((int)row.getCell(0).getNumericCellValue());
        problem.setEra(row.getCell(1).getStringCellValue());
        problem.setProblem(row.getCell(2).getStringCellValue());
        problem.setExam_1(row.getCell(3).getStringCellValue());
        problem.setExam_2(row.getCell(4).getStringCellValue());
        problem.setExam_3(row.getCell(5).getStringCellValue());
        problem.setExam_4(row.getCell(6).getStringCellValue());
        problem.setAnswer((int)row.getCell(7).getNumericCellValue());
        problem.setSolution(row.getCell(8).getStringCellValue());
        return problem;
    }

    //해당 Activity xml 데이터 초기화 하는 함수
    private void init(){
        check_script = findViewById(R.id.check_script);
        check_answer = findViewById(R.id.check_answer);
        check_cry = findViewById(R.id.check_cry);
        check_explanation = findViewById(R.id.check_explanation);
        check_back = findViewById(R.id.check_back);
        check_next = findViewById(R.id.check_next);
        check_image = findViewById(R.id.check_image);
    }

    @Override
    //바깥 레이아웃 눌러도 닫히지 않게 하기.
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    //뒤로가기 버튼 막음
    public void onBackPressed() {
        return;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
