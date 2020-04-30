package com.example.placeorderapp;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;          //object of recycle view

    ArrayList<ModelFood> foodList;          //arrayList object that get and set data with the help of ModelFood Class
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        /**********************Receiving intent*********************/
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);

        recyclerView = findViewById(R.id.rv);

        /********************adding data to list View******************************/
        foodList = new ArrayList<>();

        foodList.add(new ModelFood(R.drawable.pizza,"Pizza","8 Topping 4 sizes","$13.50"));
        foodList.add(new ModelFood(R.drawable.calzones,"Calzones","8 Topping 3 sizes","$13.50"));
        foodList.add(new ModelFood(R.drawable.pasta,"Pasta","3 Sauces","$6.70"));
        foodList.add(new ModelFood(R.drawable.chicken,"Chicken Wings","5 Sauces","$15.99"));
        foodList.add(new ModelFood(R.drawable.fries,"French Fries","3 sizes","$3.99"));
        foodList.add(new ModelFood(R.drawable.soda,"Soda Pop","Coke, Diet Coke, Sprite, Ginger Ale, Orange Crush","$0.99"));

        /*******************setting recycle view and adapter*****************************/
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLayoutManager = layoutManager;
        recyclerView.setLayoutManager(rvLayoutManager);

        FoodAdapter adapter = new FoodAdapter(this,foodList);                       //implementation in FoodAdapter class

        recyclerView.setAdapter(adapter);

    }

    public int get(){
        return id;
    };
}
