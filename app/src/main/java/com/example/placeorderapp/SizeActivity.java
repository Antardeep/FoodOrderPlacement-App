package com.example.placeorderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SizeActivity extends AppCompatActivity {
    int id;
    String fname, spicy, sauce = "";

    RadioGroup radioGroup;
    RadioButton radioButton;
    CheckBox bolognese, alfredo, carbonara, honey, bbq;
    Button sauceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size);

        /******************asigning IDs************************/
        radioGroup =  findViewById(R.id.radioGroup);
        bolognese = findViewById(R.id.bolognese);
        alfredo = findViewById(R.id.alfredo);
        carbonara = findViewById(R.id.carbonara);
        honey = findViewById(R.id.honey);
        bbq = findViewById(R.id.bbq);
        sauceButton = findViewById(R.id.sauceButton);

        /**********************Receiving intent*********************/
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        fname = intent.getStringExtra("fname");


        /**********************************Add button**************************/
        sauceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /************************* getting value of radio button**********************/
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedId);
                spicy = (String) radioButton.getText();

                /***********************adding all checked toppings to tooping string****************/
                if(bolognese.isChecked()) {
                    sauce = "Bolognese,  ";
                }
                if(alfredo.isChecked()){
                    sauce += "Alfredo, ";
                }
                if(carbonara.isChecked()){
                    sauce += "Carbonara, ";
                }
                if(honey.isChecked()){
                    sauce += "Honey Ginger, ";
                }
                if(bbq.isChecked()){
                    sauce += "BBQ, ";
                }
                Intent intent = new Intent(SizeActivity.this,ConfirmOrderActivity.class);
                intent.putExtra("fname",fname);
                intent.putExtra("id",id);
                intent.putExtra("spicy",spicy);
                intent.putExtra("sauce",sauce);
                startActivity(intent);
            }
        });



    }
}
