package com.example.placeorderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SodaActivity extends AppCompatActivity {
    int id;
    String fname;

    ImageButton coke, diet, ginger, sprite, orange, pepsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soda);

        /*****************Assigning IDs***********************/
        coke = findViewById(R.id.coke);
        diet = findViewById(R.id.diet);
        ginger = findViewById(R.id.ginger);
        sprite = findViewById(R.id.sprite);
        orange = findViewById(R.id.orange);
        pepsi = findViewById(R.id.pepsi);

        /**********************Receiving intent*********************/
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        fname = intent.getStringExtra("fname");

        /*******************OnClick Methods**********************/
        coke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SodaActivity.this,ConfirmOrderActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",fname);
                intent.putExtra("soda","Coke");
                startActivity(intent);
            }
        });

        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SodaActivity.this,ConfirmOrderActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",fname);
                intent.putExtra("soda","Diet Coke");
                startActivity(intent);
            }
        });

        ginger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SodaActivity.this,ConfirmOrderActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",fname);
                intent.putExtra("soda","(Canada Dry) Ginger Ale");
                startActivity(intent);
            }
        });

        sprite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SodaActivity.this,ConfirmOrderActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",fname);
                intent.putExtra("soda","Sprite");
                startActivity(intent);
            }
        });

        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SodaActivity.this,ConfirmOrderActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",fname);
                intent.putExtra("soda","Crush Orange");
                startActivity(intent);
            }
        });

        pepsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SodaActivity.this,ConfirmOrderActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",fname);
                intent.putExtra("soda","Pepsi");
                startActivity(intent);
            }
        });
    }
}
