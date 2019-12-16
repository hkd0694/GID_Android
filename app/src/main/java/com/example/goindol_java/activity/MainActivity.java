package com.example.goindol_java.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.goindol_java.R;
import com.example.goindol_java.customView.CustomActionBar;

public class MainActivity extends AppCompatActivity {

    private Button origin_formation;
    private Button ancient_society;
    private Button goryeo_dynasty;
    private Button joseon_before;
    private Button joseon_after;
    private Button modern;
    private Button occupation_period;
    private Button modern_times;
    private Button random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBar();
        init();
        click_event();
    }

    private void click_event(){

        origin_formation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ancient_society.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        goryeo_dynasty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        joseon_before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        joseon_after.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        modern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        occupation_period.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        modern_times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setActionBar(){
        CustomActionBar ca = new CustomActionBar(this,getSupportActionBar());
        ca.setActionBar();
    }


    private void init(){
        origin_formation = findViewById(R.id.origin_formation);
        ancient_society = findViewById(R.id.ancient_society);
        goryeo_dynasty = findViewById(R.id.goryeo_dynasty);
        joseon_before = findViewById(R.id.joseon_before);
        joseon_after = findViewById(R.id.joseon_after);
        modern = findViewById(R.id.modern);
        modern_times = findViewById(R.id.modern_times);
        random = findViewById(R.id.random);
        occupation_period = findViewById(R.id.occupation_period);
    }
}
