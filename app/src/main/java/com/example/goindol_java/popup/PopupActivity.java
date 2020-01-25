package com.example.goindol_java.popup;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.goindol_java.R;
import com.example.goindol_java.activity.EndActivity;
import com.example.goindol_java.activity.LearnActivity;
import com.example.goindol_java.activity.ProblemActivity;
import com.example.goindol_java.data.ArrangeData;
import com.example.goindol_java.data.ExcelProblem;
import com.example.goindol_java.data.Period;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.goindol_java.activity.MainActivity.period_data;
import static com.example.goindol_java.activity.SplashActivity.SETTINGS_PLAYER;

//Popup으로 띄울 시 AppCompatActivity가 아닌 Activity를 상속 받아야 한다.
public class PopupActivity extends Activity {

    private ImageView imageView;
    private Button popup_reset;
    private Button popup_ing;
    private TextView popup_content;
    private String name;
    private String intent_name;
    private View view;
    //popup_cancel
    private SharedPreferences prefs;
    private Gson gson = new Gson();
    private List<Period> list = new ArrayList<>();
    private List<ExcelProblem> excelProblems = new ArrayList<>();
    private List<ArrangeData> arrangeData = new ArrayList<>();
    private Type listType;

    private Intent intent;
    

    private int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);
        //팝업 화면를 제외한 바깥 부분의 색을 투명하게 해주는 함수
        getWindow().setBackgroundDrawable(new PaintDrawable(Color.TRANSPARENT));
        size = getIntent().getIntExtra("size", 0);

        intent_name = getIntent().getStringExtra(period_data);
        imageView = findViewById(R.id.popup_cancel);
        popup_reset = findViewById(R.id.popup_reset);
        popup_ing = findViewById(R.id.popup_ing);
        view = findViewById(R.id.popup_view);
        popup_content = findViewById(R.id.popup_content);
        if (size == 40) {
            popup_content.setText("이 파트는 이미 다 풀었습니다.\n" + "한 번 더 학습 하시겠어요?");
            popup_ing.setText("뒤로 가기");
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //popup중 처음부터 다시 풀기 누를 시 저장되어 있는 문제들 초기화 해서 LearnActivity로 넘겨줌 (처음 문제 풀 때랑 동일하게)
        popup_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(Integer.parseInt(intent_name.split(",")[1]) - 1).setPeriod_data(excelProblems);
                list.get(Integer.parseInt(intent_name.split(",")[1]) - 1).setIndex(1);
                list.get(Integer.parseInt(intent_name.split(",")[1]) - 1).setArrangeData(arrangeData);
                Gson gson1 = new GsonBuilder().create();
                String json = gson1.toJson(list, listType);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(SETTINGS_PLAYER, json);
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), LearnActivity.class);
                intent.putExtra(period_data, intent_name);
                startActivity(intent);
                finish();
            }
        });

        //이어서 진행하기 버튼을 누를 시 발생하는 리스너
        //만약 사용자가 Excel 안에 주어진 문제들을 모두 풀었을 경우 ProblemActivity가 아닌 EndActivity로 바로 넘겨준다.
        //사용자가 Excel 안에 주어진 문제들을 다 못풀었을 경우 사용자가 마지막에 풀었던 문제를 기억하여 그 데이터와 함께 ProblemActivity로 넘겨준다.
        popup_ing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이부분을 수정해야 함!!
                //마지막으로 진행한 문제!!
                //만약 문제를 모두 다 풀었을 경우 체크하여 바로 EndActivity로 넘겨준다..!!;;
                //문제 수가 시대별로 모두 같아야만 돌아가는거임 다시 수정해야 함!!
                if (!popup_ing.getText().equals("뒤로 가기")) {
                    if (list.get(Integer.parseInt(intent_name.split(",")[1]) - 1).getPeriod_data().size() >= 40) {
                        intent = new Intent(getApplicationContext(), EndActivity.class);
                        intent.putExtra("name", intent_name.split(",")[0]);
                    } else {
                        intent = new Intent(getApplicationContext(), ProblemActivity.class);
                        intent.putExtra(period_data, intent_name);
                    }
                    startActivity(intent);
                }
                finish();
            }
        });
    }

    @Override
    //MainActivity와 동일하게 계속해서 sharedpreferences안에 저장되어 있는 값들을 불러와야한다.
    protected void onResume() {
        prefs = getSharedPreferences("shared", MODE_PRIVATE);
        name = prefs.getString(SETTINGS_PLAYER, null);
        listType = new TypeToken<ArrayList<Period>>() {
        }.getType();
        list = gson.fromJson(name, listType);
        super.onResume();
    }

    //바깥 레이아웃 눌러도 닫히지 않게 하기.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
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
