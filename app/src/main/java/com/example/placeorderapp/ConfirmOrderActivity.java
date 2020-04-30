package com.example.placeorderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.Snackbar;

public class ConfirmOrderActivity extends AppCompatActivity {
    TextView confirmTextDetail;
    Button confirmButton, bMenu;
    int id;
    String fname, toppings, size, spicy, sauce, soda;

    Order o = new Order(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        confirmTextDetail = findViewById(R.id.confrimDetailText);
        confirmButton = findViewById(R.id.confirmButton);
        bMenu = findViewById(R.id.bMenu);

        /**********************Receiving intent*********************/
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        fname = intent.getStringExtra("fname");
        toppings = intent.getStringExtra("topping");
        size = intent.getStringExtra("size");
        spicy =intent.getStringExtra("spicy");
        sauce = intent.getStringExtra("sauce");
        soda = intent.getStringExtra("soda");



        /*******************display order************************/
        if(soda == null && sauce == null) {
            confirmTextDetail.setText("User ID = " + id + "\n....Order Details....\n" + fname + "\nTopping : " + toppings + "\nSize : " + size );
        }else if(soda == null && toppings == null){
            confirmTextDetail.setText("User ID = " + id + "\n....Order Details....\n" + fname + "\nSpicy : " + spicy + "\nSauce : " + sauce);
        }else{
            confirmTextDetail.setText("User ID = " + id + "\n....Order Details....\n" + soda);
        }

        /*****************Confirm button add all order data to order table with user id******************/
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp_id = String.valueOf(id);
                o.addData(temp_id,fname,toppings,size,spicy,sauce,soda);

                Snackbar.make(confirmButton, "Order Confirmed", Snackbar.LENGTH_LONG).show();
            }
        });

        /*****************back to menu button*********************/
        bMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmOrderActivity.this,StartActivity.class);
                startActivity(intent);
            }
        });


    }
}
