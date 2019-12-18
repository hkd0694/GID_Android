package com.example.goindol_java.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.IOException;
import java.io.InputStream;

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

        try {
            InputStream is;
            AssetManager assetManager = getAssets();
            is = assetManager.open("goindol.xls");
            POIFSFileSystem poif = new POIFSFileSystem(is);
            hss = new HSSFWorkbook(poif);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sh = hss.getSheetAt(sheet_indexs);
        row = sh.getRow(row_number);
        //시트 번호에서 해설 부분 가져오기
        cell = row.getCell(10);

        if(check.equals("정답")){
            check_image.setImageResource(R.drawable.good);
            check_answer.setText("정답을 맞췄습니다!");
        } else  {
            check_image.setImageResource(R.drawable.wrong);
            check_cry.setVisibility(View.VISIBLE);
            check_answer.setText("틀렸습니다.");
        }
        check_explanation.setText(cell.getStringCellValue());
        check_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        check_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });

    }

    private void init(){
        check_script = findViewById(R.id.check_script);
        check_answer = findViewById(R.id.check_answer);
        check_cry = findViewById(R.id.check_cry);
        check_explanation = findViewById(R.id.check_explanation);
        check_back = findViewById(R.id.check_back);
        check_next = findViewById(R.id.check_next);
        check_image = findViewById(R.id.check_image);
    }

    //바깥 레이아웃 눌러도 닫히지 않게 하기.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    //뒤로가기 버튼 막음
    @Override
    public void onBackPressed() {
        return;
    }
}
