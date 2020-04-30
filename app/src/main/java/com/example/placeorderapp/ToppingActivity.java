package com.example.placeorderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

public class ToppingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int id;             //user id
    String fname, toppings = "" , size;       //food name, topping string and size


    Button toppingButton;
    CheckBox pepper, bacon, green, mushroom, pinapple, onion, tomato, hot;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topping);

        /**********************Receiving intent*********************/
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        fname = intent.getStringExtra("fname");

        /******************asigning IDs************************/
        pepper = findViewById(R.id.pepper);
        bacon = findViewById(R.id.bacon);
        green = findViewById(R.id.green);
        mushroom = findViewById(R.id.mushroom);
        pinapple = findViewById(R.id.pineapple);
        onion = findViewById(R.id.onion);
        tomato = findViewById(R.id.tomato);
        hot = findViewById(R.id.hot);

        spinner = (Spinner)findViewById(R.id.spinner);
        toppingButton = findViewById(R.id.toppingButton);


        /**********************setting spinner*****************************/
        final ArrayAdapter<CharSequence> adapter = android.widget.ArrayAdapter.createFromResource(ToppingActivity.this, R.array.array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(ToppingActivity.this);         //Setting the spinner with the Class implementation

        size = spinner.getSelectedItem().toString();                               //getting value from spinner

        /**********************************Add button**************************/
        toppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***********************adding all checked toppings to tooping string****************/
                if(pepper.isChecked()) {
                    toppings = "Pepperoni, ";
                }
                if(bacon.isChecked()){
                    toppings += "Bacon, ";
                }
                if(green.isChecked()){
                    toppings += "Green Pepper, ";
                }
                if(mushroom.isChecked()){
                    toppings += "Mushroom, ";
                }
                if(pinapple.isChecked()){
                    toppings += "Pinapple, ";
                }
                if(onion.isChecked()){
                    toppings += "Onion, ";
                }
                if(tomato.isChecked()){
                    toppings += "Tomato, ";
                }
                if(hot.isChecked()){
                    toppings += "Hot Pepper, ";
                }
                Intent intent = new Intent(ToppingActivity.this,ConfirmOrderActivity.class);
                intent.putExtra("fname",fname);
                intent.putExtra("id",id);
                intent.putExtra("topping",toppings);
                intent.putExtra("size",size);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
