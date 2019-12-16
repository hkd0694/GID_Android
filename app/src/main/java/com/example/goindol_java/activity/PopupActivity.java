package com.example.goindol_java.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.example.goindol_java.R;

public class PopupActivity extends Activity {

    private ImageView imageView;
    private Button popup_reset;
    private Button popup_ing;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_popup);
        name = getIntent().getStringExtra("name");

        imageView = findViewById(R.id.popup_cancel);
        popup_reset = findViewById(R.id.popup_reset);
        popup_ing = findViewById(R.id.popup_ing);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        popup_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LearnActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
                finish();
            }
        });

        popup_ing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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
