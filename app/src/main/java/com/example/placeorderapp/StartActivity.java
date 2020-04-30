package com.example.placeorderapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Timer;

public class StartActivity extends AppCompatActivity {
    ViewPager viewPager;
    CustomSwipeAdapter adapter;
    Button menu, prevOrder, logout;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        /**********************Receiving intent*********************/
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);

        /****************************Image Slider*****************/
        viewPager = findViewById(R.id.view_pager);
        adapter = new CustomSwipeAdapter(this);                                      //implementation in CustomSwipeAdapter class
        viewPager.setAdapter(adapter);

        menu = findViewById(R.id.menuButton);
        prevOrder = findViewById(R.id.prevOrder);
        logout = findViewById(R.id.logout);

        /**************************Call Menu Activity********************/
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,MenuActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        /*************************Previous Order***********************/
        prevOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(StartActivity.this, PreOrderActivity.class);
                intent1.putExtra("id",id);
                startActivity(intent1);
            }
        });

        /***************logout******************/
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });
    }

}
